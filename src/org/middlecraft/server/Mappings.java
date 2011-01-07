/**
 * Copyright (c) 2010, MiddleCraft Contributors
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * 
 *  * Redistributions of source code must retain the above copyright notice, this list of 
 *    conditions and the following disclaimer.
 * 
 *  * Redistributions in binary form must reproduce the above copyright notice, this list 
 *    of conditions and the following disclaimer in the documentation and/or other materials 
 *    provided with the distribution.
 * 
 *  * Neither the name of MiddleCraft nor the names of its contributors may be 
 *    used to endorse or promote products derived from this software without specific prior 
 *    written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT 
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, 
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT 
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE 
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.middlecraft.server;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javassist.ClassMap;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

/**
 * @author Rob
 *
 */
public class Mappings {

	static Timer saveTimer=new Timer();

	static Logger l = Logger.getLogger("Middlecraft");

	public static String serverVersion="1.1_02"; // Loads the appropriate object mappings 

	public static HashMap<String,MCClassInfo> classes = new HashMap<String,MCClassInfo>();
	public static ClassMap deobfuscationMap = new ClassMap();
	public static Map<String,Class<?>> loadedClasses = new HashMap<String,Class<?>>();


	public static void initialize() throws IOException {
		// Read data from our simplified MCP deobfuscation mappings
		File data = new File(String.format("data/server/%s/", serverVersion));
		if(!data.exists()) {
			l.log(Level.WARNING,"No mappings folder exists, creating...");
			data.mkdirs();
			save();
		}
		ReadClasses();
	}


	@SuppressWarnings("unchecked")
	private static boolean ReadClasses() throws IOException {
		l.info("Loading class mappings...");
		// CLASS_NAME,OBF_CLASS_NAME,DESCRIPTION
		File f  = new File(String.format("data/server/%s/mappings.yml", serverVersion));
		if(!f.exists()) {
			l.severe("No class mapping table!");
			return false;
		}
		Yaml yml = new Yaml();
		Map<String,Object> _classes = (Map<String,Object>)yml.load(new FileReader(f));
		Collection<Object> vals = new ArrayList<Object>(_classes.values());
		for(Object o : vals) {
			if(o instanceof Map<?,?>) {
				MCClassInfo ci = new MCClassInfo((Map<String,Object>)o);
				classes.put(ci.realName,ci);
				//l.info("Loaded "+ci.realName+".");
			}
		}
		return true;
	}

	/**
	 * Add a class to the list.
	 * @param name
	 * @param superClass 
	 */
	public static void addObfuscatedClassDefinition(String name, String superClass) {
		unkClasses++;
		if(superClass=="") {
			try {
				superClass = ClassPool.getDefault().get(name).getSuperclass().getName();
			} catch (NotFoundException e) {	} 
		}
		MCClassInfo ci = new MCClassInfo();
		ci.name="*";
		ci.realName=name;
		ci.description="*";
		ci.realSuperClass=superClass;
		classes.put(ci.realName, ci);
		setDirty();
	}
	static int unkClasses=0;
	public static void addReadableClassDefinition(String name) {
		unkClasses++;
		MCClassInfo ci = new MCClassInfo();
		ci.name=name;
		ci.realName="UNKNOWN_"+Integer.toString(unkClasses);
		ci.description="";
		classes.put(ci.realName, ci);
		setDirty();
	}

	static int unkFields=0; 
	public static void addObfuscatedFieldDefinition(String className, String fieldName, String type) {
		unkFields++;
		MCClassInfo ci = classes.get(className);
		MCFieldInfo field = new MCFieldInfo();
		field.className=className;
		field.realName=fieldName;
		field.type=type;
		ci.fields.put(fieldName,field);
		classes.put(className, ci);
		setDirty();
	}

	static int unkMethods=0;

	private static boolean dirty;

	public static Map<String, String> obfuscationMap = new HashMap<String,String>();

	public static void addObfuscatedMethodDefinition(String className, String methodName, String signature, String extraData) {
		//unkMethods++;
		//l.log(Level.WARNING,String.format(" + [M] %s.%s %s",className,methodName,signature));
		MCClassInfo ci = classes.get(className);

		MCMethodInfo mi = new MCMethodInfo();
		mi.name="";
		mi.parentClass=className;
		mi.realName=methodName;
		mi.signature=signature;
		mi.description = extraData;
		ci.methods.put(mi.toIndex(), mi);
		//ci.methodNames.put("UNKNOWN_"+Integer.toString(unkMethods),methodName+" "+signature);
		classes.put(className, ci);
		setDirty();
	}

	/**
	 * 
	 */
	private static void setDirty() {
		dirty=true;
		SaveTask st = new SaveTask();
		saveTimer.schedule(st, 5000);
	}

	public static void save() {
		if(!dirty) return;
		File data = new File(String.format("data/server/%s/", serverVersion));
		if(!data.exists())
			data.mkdirs();
		try {
			writeClasses();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * @throws IOException 
	 * 
	 */
	private static void writeClasses() throws IOException {
		l.info("Writing class mappings to disk...");
	    DumperOptions options = new DumperOptions();
	    options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		Yaml yml = new Yaml(options);
		Map<String,Object> classMap = new HashMap<String,Object>();
		for(MCClassInfo ci : classes.values()) {
			classMap.put(ci.name, ci.toMap());
		}
		yml.dump(classMap, new FileWriter(String.format("data/server/%s/mappings.yml", serverVersion))); 			
	}


	/**
	 * @param className
	 * @return
	 */
	public static String getNewClassName(String className) {
		MCClassInfo ci = classes.get(className);
		if(ci==null) return className;
		return ci.name;
	}

	/**
	 * 
	 * @param className OBFUSCATED class name.
	 * @param name
	 * @param signature
	 * @return
	 */
	public static MCMethodInfo getMethod(String className, String name, String signature) {
		String ocn = getOldClassName(className);
		MCClassInfo ci = classes.get(className);
		//l.info(String.format("getMethod(%s,%s,%s)",className,name,signature));
		if(ci==null) {
			ci = classes.get(ocn);
			//l.info("Found old name "+ocn);
		}
		if(ci==null) {
			l.log(Level.WARNING, "Can't find parent class "+className+" ("+ocn+") for method "+name);
			return null;
		}
		for(MCMethodInfo mi : ci.methods.values()) {
			if((mi.name.equals(name) || mi.realName.equals(name)) && mi.signature.equals(signature)) {
				return mi;
			}
		}
		MCMethodInfo mi = new MCMethodInfo();
		mi.realName=name;
		mi.signature=signature;
		addObfuscatedMethodDefinition(className,name,signature, "");
		return null;
	}

	/**
	 * 
	 * @param className OBFUSCATED class name.
	 * @param name
	 * @param signature
	 * @return
	 */
	public static MCFieldInfo getField(String className, String name, String type) {
		MCClassInfo ci = classes.get(className);
		if(ci==null) {
			l.log(Level.WARNING, "Can't find parent class "+className+" for field "+name);
			return null;
		}
		if(!ci.fields.containsKey(name)) {
			addObfuscatedFieldDefinition(className,name,type);
			return null;
		}
		return ci.fields.get(name);
	}

	/**
	 * @param className
	 * @return
	 */
	public static String getOldClassName(String className) {
		for(MCClassInfo ci : classes.values()) {
			if(ci.name.equals(className)) {
				//l.info(String.format("getOldClassName: %s->%s",className,ci.realName));
				return ci.realName;
			}
		}
		return className;
	}

	/**
	 * Update classdef superclass information. For reference, mostly.
	 * @param cc
	 */
	public static void updateSuperclassInfo(CtClass cc) {

		try {
			MCClassInfo ci = classes.get(cc.getName());
			if(ci==null) return;
			CtClass sc = cc.getSuperclass();
			if(sc==null) return;
			if(!ci.realSuperClass.equals(sc.getName())) {
				ci.realSuperClass=sc.getName();
				classes.put(cc.getName(), ci);
				setDirty();
			}
		} catch (NotFoundException e) {
			return;
		}
	}
}

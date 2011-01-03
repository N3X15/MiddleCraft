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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javassist.CannotCompileException;
import javassist.ClassMap;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

/**
 * @author Rob
 *
 */
public class SmartReflector {

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
		if(ReadClasses()) {
			ReadMethods();
			ReadFields();
		}
	}

	/**
	 * 
	 */
	private static void ReadFields() throws IOException {
		l.info("Loading field mappings...");
		// CLASS_NAME,FIELD_NAME,READABLE_FIELD_NAME
		File f  = new File(String.format("data/server/%s/fields.csv", serverVersion));
		if(!f.exists()) {
			l.log(Level.WARNING,"No field mapping table!");
			return;
		}
		boolean hasReadHeader=false;
		Scanner scanner = new Scanner(new FileInputStream(f));
		try {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if(!hasReadHeader) {
					hasReadHeader=true;
					continue;
				}

				MCFieldInfo field = new MCFieldInfo(line);

				if(!classes.containsKey(field.className))
					continue;
				MCClassInfo ci = classes.get(field.className);
				ci.fieldNames.put(field.realName,field);
				classes.put(field.className, ci);
			}
		} catch(Exception e) { e.printStackTrace(); }

	}

	/**
	 * 
	 */
	private static void ReadMethods() throws IOException {
		l.info("Loading method mappings...");
		//CLASS,FUNC_NAME,FUNC_SIG,READABLE_FUNC_NAME
		File f  = new File(String.format("data/server/%s/methods.csv", serverVersion));
		if(!f.exists()) {
			l.log(Level.WARNING,"No method mapping table!");
			return;
		}

		CsvListReader rdr = new CsvListReader(new FileReader(f),CsvPreference.STANDARD_PREFERENCE);
		rdr.read();
		try {
			while (true){
				List<String> line = rdr.read();
				if(line==null) break;
				try {
				MCMethodInfo mi = new MCMethodInfo(line);

				if(!classes.containsKey(mi.parentClass))
					continue;

				MCClassInfo ci = classes.get(mi.parentClass);
				ci.methodNames.put(mi.toIndex(), mi);
				classes.put(mi.parentClass, ci);
				} catch(Throwable e) {
					l.warning(String.format("Skipping line %s.",line.toString()));
					continue;
				}
			}
		}
		finally{
			rdr.close();
		}
	}

	private static boolean ReadClasses() throws IOException {
		l.info("Loading class mappings...");
		// CLASS_NAME,OBF_CLASS_NAME,DESCRIPTION
		File f  = new File(String.format("data/server/%s/classes.csv", serverVersion));
		if(!f.exists()) {
			l.severe("No class mapping table!");
			return false;
		}
		CsvListReader rdr = new CsvListReader(new FileReader(f),CsvPreference.STANDARD_PREFERENCE);
		rdr.read();
		try {
			while (true){
				List<String> line = rdr.read();
				if(line==null) break;
				MCClassInfo ci = new MCClassInfo(line);

				if(!classes.containsKey(ci.realName)) {
					deobfuscationMap.put(ci.realName, ci.name);
					obfuscationMap.put(ci.name,ci.realName);
					classes.put(ci.realName, ci);
				}
			}
		}
		finally{
			rdr.close();
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
		ci.fieldNames.put(fieldName,field);
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
		ci.methodNames.put(mi.toIndex(), mi);
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
			writeMethods();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writeFields();
	}

	/**
	 * @throws IOException 
	 * 
	 */
	private static void writeMethods() throws IOException {
		CsvListWriter w=null;
		try {
			w = new CsvListWriter(new FileWriter(new File(String.format("data/server/%s/methods.csv", serverVersion))),CsvPreference.STANDARD_PREFERENCE);
			int num=0;
			w.write(MCMethodInfo.header);
			for(MCClassInfo ci : classes.values()) {
				//l.log(Level.INFO,"Class "+ci.name+" contains "+Integer.toString(ci.methodNames.size())+" known methods.");
				for(MCMethodInfo mi : ci.methodNames.values()) {
					w.write(mi.toList());
					num++;
				}
			}
			l.log(Level.INFO,"Wrote "+Integer.toString(num)+" methods to disk.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}

	/**
	 * 
	 */
	private static void writeFields() {		
		PrintStream f=null;
		int num = 0;
		try {
			f = new PrintStream(new FileOutputStream(String.format("data/server/%s/fields.csv", serverVersion)));
			f.println(MCFieldInfo.header);
			for(MCClassInfo ci : classes.values()) {
				for(MCFieldInfo fi : ci.fieldNames.values()) {
					f.println(fi.toString());
					num++;
				}
			}
			l.info(String.format("Wrote %d fields to disk.", num));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(f!=null)
				f.close();
		}
	}

	/**
	 * @throws IOException 
	 * 
	 */
	private static void writeClasses() throws IOException {
		CsvListWriter w = null;
		try {
			int num = 0;
			w = new CsvListWriter(new FileWriter(new File(String.format("data/server/%s/classes.csv", serverVersion))),CsvPreference.STANDARD_PREFERENCE); 			
			List<String> keys = new ArrayList<String>(classes.keySet());
			Collections.sort(keys);
			w.write(MCClassInfo.header);
			for(String key : keys) {
				w.write(classes.get(key).toList());
				num++;
			}
			l.info(String.format("Wrote %d classes to disk.", num));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(w!=null)
				w.close();
		}
	}


	/**
	 * @param string
	 * @return
	 * @throws NotFoundException 
	 * @throws CannotCompileException 
	 */
	private static CtClass GrabCtClass(String className,String defaultSuperClass){
		try
		{
			ClassPool cp = ClassPool.getDefault();
			CtClass cl = cp.getOrNull(className);
			MCClassInfo ci = classes.get(className);
			if(ci.superClass=="") {
				ci.superClass=defaultSuperClass;
				classes.put(className, ci);
				save();
			}
			try {
				cl.setSuperclass(cp.get(ci.superClass));
			} catch (CannotCompileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Fix field names...
			for(CtField cf : cl.getDeclaredFields()) {
				if(ci.fieldNames.containsKey(cf.getName())) {
					MCFieldInfo field = ci.fieldNames.get(cf.getName());
					l.log(Level.INFO,"Fixing field "+cf.getName()+" to "+field.name);
					cf.setName(field.name);
				}
			}
			// Fix methods
			for(CtMethod cm : cl.getDeclaredMethods()) {
				String methodID=cm.getName()+" "+cm.getSignature();
				l.log(Level.INFO,"Fixing method "+className+"."+methodID);
				if(ci.methodNames.containsKey(methodID))
					cm.setName(ci.methodNames.get(methodID).name);

				// Also patch, if required.
				File methodPatch = new File(String.format("data/server/%s/patches/%s/%s.%s.java",serverVersion,ci.name,cm.getName(),cm.getSignature()));
				if(methodPatch.exists()) {
					l.log(Level.INFO,"Patching method "+className+"."+methodID);
					try {
						cm.setBody(Utils.getFileContents(methodPatch));
					} catch (CannotCompileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return cl;
		} catch(NotFoundException e) {
			l.log(Level.WARNING,"Can't find class "+className+"!");
			addReadableClassDefinition(className);
			return null;
		}
	}

	/**
	 * @param string
	 * @return
	 * @throws NotFoundException 
	 * @throws CannotCompileException 
	 */
	public static Class<?> GrabClass(String className,String defaultSuperClass){
		if(!loadedClasses.containsKey(className))
		{
			CtClass cc = GrabCtClass(className,defaultSuperClass);
			if(cc==null) return null;
			try {
				loadedClasses.put(className, cc.toClass());
			} catch (CannotCompileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return loadedClasses.get(className);
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
		for(MCMethodInfo mi : ci.methodNames.values()) {
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
		if(!ci.fieldNames.containsKey(name)) {
			addObfuscatedFieldDefinition(className,name,type);
			return null;
		}
		return ci.fieldNames.get(name);
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

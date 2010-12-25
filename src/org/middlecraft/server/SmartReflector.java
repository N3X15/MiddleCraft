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
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.logging.Logger;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * @author Rob
 *
 */
public class SmartReflector {

	static Logger l = Logger.getLogger("Minecraft");
	
	public static String serverVersion="1.1_02"; // Loads the appropriate object mappings 
	
	public static HashMap<String,ClassInfo> classes = new HashMap<String,ClassInfo>();
	public static Map<String,Class<?>> loadedClasses;
	
	public static void initialize() throws IOException {
		// Read data from our simplified MCP deobfuscation mappings
		//  (I'll make a few python scripts to do this - N3X)
		ReadClasses();
		ReadMethods();
		ReadFields();
		
		renameClasses();
	}
	
	/**
	 * God help us.
	 */
	private static void renameClasses(){
		ClassPool cp = ClassPool.getDefault();
		for(ClassInfo ci : classes.values()) {
			try {
				CtClass cc = cp.getAndRename(ci.realName, ci.name);
				for(CtField cf : cc.getDeclaredFields()) {
					if(ci.FieldNames.containsKey(cf.getName())) {
						cf.setName(ci.FieldNames.get(cf.getName()));
					}
				}
				for(CtMethod cm : cc.getDeclaredMethods()) {
					String methodID=cm.getName()+" "+cm.getSignature();
					if(ci.MethodNames.containsKey(methodID))
						cm.setName(ci.MethodNames.get(methodID));
					File methodPatch = new File(String.format("data/server/%s/patches/%s/%s.%s.java",serverVersion,ci.name,cm.getName(),cm.getSignature()));
					if(methodPatch.exists()) {
						try {
							cm.setBody(Utils.getFileContents(methodPatch));
						} catch (CannotCompileException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} catch(NotFoundException e) {
				
			}
		}
	}

	/**
	 * 
	 */
	private static void ReadFields() throws IOException {
		l.info("Loading field mappings...");
		// CLASS_NAME,FIELD_NAME,READABLE_FIELD_NAME
		//
		Scanner scanner = new Scanner(new FileInputStream(String.format("data/server/%s/fields.csv", serverVersion)));
	    try {
	    	while (scanner.hasNextLine()) {
	    		String line = scanner.nextLine();
	    		String[] chunks = line.split(",");
	    		
	    		String className = chunks[0];
	    		String oldFieldName = chunks[1];
	    		String fieldName = chunks[2];
	    		
	    		if(!classes.containsKey(className))
	    			continue;
	    		ClassInfo ci = classes.get(className);
	    		ci.MethodNames.put(oldFieldName,fieldName);
	    		classes.put(className, ci);
	    	}
	    } catch(Exception e) { e.printStackTrace(); }
		
	}

	/**
	 * 
	 */
	private static void ReadMethods() throws IOException {
		l.info("Loading method mappings...");
		//CLASS,FUNC_NAME,FUNC_SIG,READABLE_FUNC_NAME
		Scanner scanner = new Scanner(new FileInputStream(String.format("data/server/%s/methods.csv", serverVersion)));
	    try {
	    	while (scanner.hasNextLine()) {
	    		String line = scanner.nextLine();
	    		String[] chunks = line.split(",");
	    		
	    		String className = chunks[0];
	    		String obfMethodName = chunks[1]+" "+chunks[2];
	    		String methodName = chunks[2];
	    		
	    		if(!classes.containsKey(className))
	    			continue;
	    		
	    		ClassInfo ci = classes.get(className);
	    		ci.MethodNames.put(obfMethodName, methodName);
	    		classes.put(className, ci);
	    	}
	    } catch(Exception e) { e.printStackTrace(); }
	}

	private static void ReadClasses() throws IOException {
		l.info("Loading class mappings...");
		// CLASS_NAME,OBF_CLASS_NAME,DESCRIPTION
		Scanner scanner = new Scanner(new FileInputStream(String.format("data/server/%s/mcp/classes.csv", serverVersion)));

	    try {
	    	while (scanner.hasNextLine()){
	    		String line = scanner.nextLine();
	    		String[] chunks = line.split(",");
    		
    			ClassInfo ci = new ClassInfo();
    			ci.name=chunks[0];
    			ci.realName = chunks[1];
    			ci.description=chunks[2];
    			if(!classes.containsKey(ci.name))
    				classes.put(ci.name, ci);
	    	}
	    }
	    finally{
	    	scanner.close();
	    }
	}

	/**
	 * Find the named class and load it. 
	 * @param className
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IllegalArgumentException 
	 */
	public static Object GrabClassInstance(String className, Object... params) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		if(!loadedClasses.containsKey(className))
		{
			addClassDefinition(className);
			return null;
		}
		Class<?> theClass = loadedClasses.get(className);
		// Find a suitable constructor, if possible.
		for( Constructor<?> c : theClass.getConstructors()) {
			Class<?>[] c_params = c.getParameterTypes();
			if(c_params.length != params.length)
				continue;
			if(c_params.length==0)
			{
				// Constructor doesn't need arguments, just initialize it.
				return c.newInstance();
			}
			// Check types
			boolean types_ok = true;
			for(int i = 0;i<c_params.length;i++) {
				if(c_params[i].getName()!=params[i].getClass().getName())
					types_ok=false;
			}
			if(!types_ok) continue;
			
			// LETS DO DIS
			l.info("[SmartReflector] Initializing "+c.toString());
			return c.newInstance(params);
		}
		return null;
	}
	
	/**
	 * Add a class to the list.
	 * @param name
	 */
	public static void addClassDefinition(String name) {
		ClassInfo ci = new ClassInfo();
		ci.name=name;
		ci.realName="";
		ci.description="";
		classes.put(name, ci);
	}
	
	static Integer unkFields=0; 
	public static void addFieldDefinition(String className, String fieldName) {
		unkFields++;
		ClassInfo ci = classes.get(className);
		ci.FieldNames.put("UNKNOWN_"+unkFields.toString(),fieldName);
		classes.put(className, ci);
	}
	
	static Integer unkMethods=0;
	public static void addMethodDefinition(String className, String methodName, String signature) {
		unkMethods++;
		ClassInfo ci = classes.get(className);
		ci.MethodNames.put("UNKNOWN_"+unkMethods.toString(),methodName+" "+signature);
		classes.put(className, ci);
	}
	
	public static void save() {
		File data = new File(String.format("data/server/%s/", serverVersion));
		if(!data.exists())
			data.mkdirs();
		writeClasses();
		writeFields();
		writeMethods();
	}

	/**
	 * 
	 */
	private static void writeMethods() {
		PrintStream f=null;
		try {
			f = new PrintStream(new FileOutputStream(String.format("data/server/%s/methods.csv", serverVersion)));
		
			for(ClassInfo ci : classes.values()) {
				for(Entry<String,String> mi : ci.MethodNames.entrySet()) {
					String realMethodName = mi.getKey().split(" ")[0];
					String methodSignature = mi.getKey().split(" ")[1];
					String methodName = mi.getValue();
					String className = ci.name;
					f.println(className+","+realMethodName+","+methodSignature+","+methodName);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(f!=null)
				f.close();
		}
	}

	/**
	 * 
	 */
	private static void writeFields() {		
		PrintStream f=null;
		try {
			f = new PrintStream(new FileOutputStream(String.format("data/server/%s/fields.csv", serverVersion)));
		
			for(ClassInfo ci : classes.values()) {
				for(Entry<String,String> mi : ci.FieldNames.entrySet()) {
					String realFieldName = mi.getKey();
					String fieldName = mi.getValue();
					String className = ci.name;
					f.println(className+","+fieldName+","+realFieldName);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(f!=null)
				f.close();
		}
	}

	/**
	 * 
	 */
	private static void writeClasses() {		
		PrintStream f=null;
		try {
			f = new PrintStream(new FileOutputStream(String.format("data/server/%s/classes.csv", serverVersion)));
		
			for(ClassInfo ci : classes.values()) {
				f.println(ci.realName+","+ci.name);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(f!=null)
				f.close();
		}
	}
}

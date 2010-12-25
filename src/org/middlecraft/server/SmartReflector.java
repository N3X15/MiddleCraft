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
import java.lang.reflect.*;
import java.util.HashMap;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;


/**
 * @author Rob
 *
 */
public class SmartReflector {

	static Logger l = Logger.getLogger("Minecraft");
	
	public static String serverVersion="1.1_02"; // Loads the appropriate object mappings 
	
	public static HashMap<String,ClassInfo> classes = new HashMap<String,ClassInfo>();
	
	public static void initialize() throws IOException {
		// Read data from our simplified MCP deobfuscation mappings
		//  (I'll make a few python scripts to do this - N3X)
		ReadClasses();
		ReadMethods();
		ReadFields();
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
	    		ci.MethodNames.put(fieldName, oldFieldName);
	    		classes.put(className, ci);
	    	}
	    } catch(Exception e) { e.printStackTrace(); }
		
	}

	/**
	 * 
	 */
	private static void ReadMethods() throws IOException {
		l.info("Loading method mappings...");
		//CLASS,READABLE_FUNC_NAME,FUNC_NAME
		Scanner scanner = new Scanner(new FileInputStream(String.format("data/server/%s/methods.csv", serverVersion)));
	    try {
	    	while (scanner.hasNextLine()) {
	    		String line = scanner.nextLine();
	    		String[] chunks = line.split(",");
	    		
	    		String className = chunks[0];
	    		String methodName = chunks[1];
	    		String obfMethodName = chunks[2];
	    		
	    		if(!classes.containsKey(className))
	    			continue;
	    		
	    		ClassInfo ci = classes.get(className);
	    		ci.MethodNames.put(methodName, obfMethodName);
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
	 */
	public static GrabbedClassInstance GrabClassInstance(String className, Object... params) {
		if(!classes.containsKey(className))
			return null;
		ClassInfo classData = classes.get(className);
		Class theClass;
		try {
			theClass = Class.forName(classData.name);
		} catch(ClassNotFoundException e) {
			return null;
		}
		try {
			return new GrabbedClassInstance(theClass,className,params);
		} catch (Exception e) {
			return null;
		}
	}

}

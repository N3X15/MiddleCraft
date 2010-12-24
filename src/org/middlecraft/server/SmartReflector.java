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


/**
 * @author Rob
 *
 */
public class SmartReflector {

	public static String serverVersion="1.1_02"; // Loads the appropriate object mappings
	
	public static HashMap<String,ClassInfo> classes = new HashMap<String,ClassInfo>();
	
	public static void initialize() throws IOException {
		// Read data from MCP deobfuscation mappings.
		ReadClasses();
		ReadMethods();
		ReadFields();
	}
	
	/**
	 * 
	 */
	private static void ReadFields() throws IOException {
		//*,*,*,ServerConfigurationManager,*,field_9253_e,maxPlayers,"the maximum amount of players that can be connected"
		//*,*,*,CLASS_NAME,*,FIELD_NAME,READABLE_FIELD_NAME,DESCRIPTION
		//0,1,2,3         ,4,5         ,6                  ,7
		Scanner scanner = new Scanner(new FileInputStream(String.format("data/server/%s/mcp/fields.csv", serverVersion)));
	    try {
	    	while (scanner.hasNextLine()) {
	    		String line = scanner.nextLine();
	    		String[] chunks = line.split(",");
	    		if(chunks[0]!="*") continue;
	    		
	    		String className = chunks[3];
	    		String[] field_chunks = chunks[5].split("_");
	    		String fieldName = chunks[6];
	    		
	    		if(!classes.containsKey(className))
	    			continue;
	    		ClassInfo ci = classes.get(className);
	    		ci.MethodNames.put(fieldName, field_chunks[2]);
	    		classes.put(className, ci);
	    	}
	    } catch(Exception e) { e.printStackTrace(); }
		
	}

	/**
	 * 
	 */
	private static void ReadMethods() throws IOException {
		//IGNORE,IGNORE,CLASS,FUNC_NAME,READABLE_FUNC_NAME,DESC
		Scanner scanner = new Scanner(new FileInputStream(String.format("data/server/%s/mcp/methods.csv", serverVersion)));
	    try {
	    	while (scanner.hasNextLine()) {
	    		String line = scanner.nextLine();
	    		String[] chunks = line.split(",");
	    		if(chunks[0]!="*") continue;
	    		String className = chunks[2];
	    		if(!classes.containsKey(className))
	    			continue;
	    		
	    		// func_000_a
	    		String[] method_chunks = chunks[3].split("_");
	    		ClassInfo ci = classes.get(className);
	    		ci.MethodNames.put(chunks[4], method_chunks[2]);
	    		classes.put(className, ci);
	    	}
	    } catch(Exception e) { e.printStackTrace(); }
	}

	private static void ReadClasses() throws IOException {
		// NAME,VERSION_CLIENT_A,VERSION_CLIENT_B,VERSION_SERVER_A,VERSION_SERVER_B,DESC
		Scanner scanner = new Scanner(new FileInputStream(String.format("data/server/%s/mcp/classes.csv", serverVersion)));
		int ColumnServerStart=0;
		int ColumnServerEnd=0;
		int ColumnServer=0;
		int ColumnComment=0;
	    try {
	    	while (scanner.hasNextLine()){
	    		String line = scanner.nextLine();
	    		String[] chunks = line.split(",");
	    		if(ColumnServerStart==0)
	    		{
		    		for(int i = 0;i<chunks.length;i++) {
		    			if(chunks[i]=="SERVER") {
		    				if(ColumnServerStart==0) {
		    					ColumnServerStart=i;
		    					continue;
		    				}
		    			}
		    			if(ColumnServerStart!=0 && chunks[i]!="SERVER") {
		    				ColumnServerEnd=i-1;
		    				ColumnComment=i;
		    				break;
		    			}
		    		}
	    		}
	    		else if(ColumnServer==0)
	    		{
		    		for(int i = ColumnServerStart;i<ColumnServerEnd+1;i++) {
		    			if(chunks[i]==serverVersion)
		    			{
		    				ColumnServer=i;
		    			}
		    		}
	    		}
	    		else {
	    			ClassInfo ci = new ClassInfo();
	    			ci.realName = chunks[ColumnServer];
	    			ci.name=chunks[0];
	    			ci.description=chunks[ColumnComment];
	    			if(!classes.containsKey(ci.name))
	    				classes.put(ci.name, ci);
	    		}
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

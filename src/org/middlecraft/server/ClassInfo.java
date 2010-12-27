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

import java.util.HashMap;

/**
 * @author Rob
 *
 */
public class ClassInfo {
	public static final String header = "Real Name,MCP Name,Superclass,Description";
	public String name;
	public HashMap<String,MethodInfo> MethodNames = new HashMap<String,MethodInfo>();
	public HashMap<String,FieldInfo> FieldNames = new HashMap<String,FieldInfo>();
	public String realName;
	public String description="*";
	public String superClass="java.lang.Object";
	/**
	 * @param name2
	 * @return
	 */
	public MethodInfo getMethod(String name2) {
		return MethodNames.get(name2);
	}
	/**
	 * @param name2
	 * @return
	 */
	public FieldInfo getField(String name2) {
		return FieldNames.get(name2);
	}
	public ClassInfo() {}
	public ClassInfo(String line) {
		String[] chunks = line.split(",");
		realName=chunks[0];
		name=chunks[1];
		superClass=chunks[2];
		description=chunks[3];
	}
	
	public String toString() {
		return String.format("%s,%s,%s,%s",realName,name,superClass,description);
	}
}

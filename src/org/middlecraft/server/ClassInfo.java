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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Rob
 *
 */
public class ClassInfo {
	//realName,name,realSuperClass,superClass,description
	public static final String[] header = new String[]{"Real Name","MCP Name","Real Superclass","MiddleCraft Superclass","Description"};
	public String name;
	public HashMap<String,MethodInfo> MethodNames = new HashMap<String,MethodInfo>();
	public HashMap<String,FieldInfo> FieldNames = new HashMap<String,FieldInfo>();
	public String realName;
	public String description="*";
	public String superClass="*";
	public String realSuperClass;
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
	public ClassInfo(List<String> cells) {
		realName=cells.get(0);
		name=cells.get(1);
		realSuperClass=cells.get(2);
		superClass=cells.get(3);
		description=cells.get(4);
	}
	
	public String toString() {
		return String.format("%s,%s,%s,%s,%s",realName,name,realSuperClass,superClass,description);
	}
	public List<String> toList() {
		List<String> list = new ArrayList<String>();
		list.add(realName);
		list.add(name);
		list.add(realSuperClass);
		list.add(superClass);
		list.add(description);
		return list;
	}
}

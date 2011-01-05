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
import java.util.List;
import java.util.logging.Logger;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.Descriptor;


/**
 * @author Rob
 *
 */
public class MCMethodInfo {
	static Logger l = Logger.getLogger("Middlecraft");
	public String realName;
	public String signature;
	public String parentClass;
	public String name="";
	public String description="";
	public int modifiers=0;
	public static final String[] header = new String[]{"Real Name","Signature","Parent Class","Readable Name","Description"};
	public MCMethodInfo() {}
	public MCMethodInfo(List<String> line) {
		realName=line.get(0);
		signature=line.get(1);
		parentClass=line.get(2);
		name=line.get(3);

		if(name.equals("*"))
			name="";

		try {
			description=line.get(4);
		} catch(IndexOutOfBoundsException e) {
			l.warning(String.format("Could not retrieve description for %s.%s%s.",parentClass,name,signature));
		}
	}
	public List<String> toList() {
		List<String> list = new ArrayList<String>();
		list.add(realName);
		list.add(signature);
		list.add(parentClass);
		list.add(name);
		list.add(description);
		return list;
	}
	public void setModifiers(int m) {
		this.modifiers=m;
	}

	public String toString() {
		String oName = name;
		if(name.isEmpty())
			oName="*";
		return String.format("%s,%s,%s,%s,%s",realName,signature,parentClass,oName,description);
	}
	/**
	 * @return
	 */
	public String toIndex() {
		return String.format("%s %s",realName, signature);
	}

	public String toAbstractJava(ClassPool cp) {
		try {
			String returnName = Descriptor.getReturnType(signature, cp).getName();
			int i = 0;
			List<String> paramDefs = new ArrayList<String>();
			for(CtClass c : Descriptor.getParameterTypes(signature, cp)) {
				String type = SmartReflector.getNewClassName(c.getName());
				paramDefs.add(String.format("%s %s",type,(char)(i+97)));
				i++;
			}
			return String.format("\n\t\n\t/**\n\t * %s\n\t */\n\t%s %s %s(%s)",description, Modifier.toString(modifiers),SmartReflector.getNewClassName(returnName), (name.isEmpty()) ? realName:name, Utils.join(paramDefs,", "));
		} catch (NotFoundException e) {
			return "";
		}
	}
}

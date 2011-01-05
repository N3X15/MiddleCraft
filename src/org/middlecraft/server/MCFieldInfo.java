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

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Rob
 *
 */
public class MCFieldInfo {
	static Logger l = Logger.getLogger("Middlecraft");
	public String type;
	public String realName;
	public String className;
	public String name="";
	public int modifiers;


	public static String header="Class,Real Name,Readable Name,Type";
	public MCFieldInfo() {}
	public MCFieldInfo(String line) {
		String[] chunks = line.split(",");

		try {
			className=chunks[0];
			realName=chunks[1];
			name=chunks[2];
			if(name.equals("*"))
				name="";
			type=chunks[3];
		} catch(Throwable e) {
			l.log(Level.SEVERE,String.format("Error parsing field %s:",line),e);
		}
	}

	public String toString() {
		String oName = (name.isEmpty()) ? "*" : name;
		return String.format("%s,%s,%s,%s",className,realName,oName,type);
	}
	public void setModifiers(int m) {
		modifiers=m;
	}
}

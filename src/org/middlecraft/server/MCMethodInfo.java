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
public class MCMethodInfo {
	static Logger l = Logger.getLogger("Minecraft");
	public String realName;
	public String signature;
	public String parentClass;
	public String name="";
	public String description="";

	public static String header="Real Name,Signature,Parent Class,Readable Name";
	public MCMethodInfo() {}
	public MCMethodInfo(String line) {
		String[] chunks = line.split(",");
		try {
			realName=chunks[0];
			signature=chunks[1];
			parentClass=chunks[2];
			name=chunks[3];
			if(name.equals("*"))
				name="";
		} catch(ArrayIndexOutOfBoundsException e) {
			l.log(Level.SEVERE,String.format("Error parsing %s:",line),e);
		}
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
}

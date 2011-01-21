/**
 * Copyright (c) 2010-2011, MiddleCraft Contributors
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
package org.middlecraft.patcher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * @author Rob
 *
 */
public class ClassComparer {
	CtClass newClass;
	CtClass oldClass;
	List<String> deletedMethods = new ArrayList<String>();
	List<String> unchangedMethods = new ArrayList<String>();
	List<String> differentMethods = new ArrayList<String>();
	List<String> newMethods = new ArrayList<String>();
	/**
	 * Examine classes.
	 * @param newClass
	 * @param oldClass
	 * @return Differences.
	 * @throws NotFoundException
	 */
	public ClassComparer(CtClass nc, CtClass oc) throws NotFoundException {
		newClass=nc;
		oldClass=oc;
		List<CtMethod> unmatchedMethods = new ArrayList<CtMethod>();
		for(CtMethod nm : newClass.getDeclaredMethods())
		{
			unmatchedMethods.add(nm);
		}
		for(CtMethod m : oldClass.getDeclaredMethods()) {
			boolean found = false;
			boolean diff = false;
			CtMethod mm=null;
			for(CtMethod nm : unmatchedMethods)
			{
				// Compare signatures.
				if(m.getSignature()==nm.getSignature())
				{
					mm=nm;
					int al = nm.getMethodInfo().getCodeAttribute().length();
					int bl = m.getMethodInfo().getCodeAttribute().length();
					if(al!=bl)
						diff=true;
				}
			}
			if(!found)
				deletedMethods.add(m.getName());
			else {
				unmatchedMethods.remove(mm);
				if (diff)
					differentMethods.add(m.getName()+"="+mm.getName());
				else
					unchangedMethods.add(m.getName()+"="+mm.getName());
			}
		}
		for(CtMethod m : unmatchedMethods) {
			newMethods.add(m.toString());
		}
	}
	
	public double getScore()
	{
		int allcount = unchangedMethods.size() + newMethods.size() + differentMethods.size();
		int ccount = deletedMethods.size() + newMethods.size() + differentMethods.size();
		return ((double)ccount)/(double)allcount;
	}

	public String toString()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("--- old/"+oldClass.getName().replace('.', '/')+"\n");
		sb.append("+++ new/"+newClass.getName().replace('.', '/')+"\n");
		sb.append("\nNEW METHODS:\n");
		for(String d : newMethods)
		{
			sb.append("+ "+d+"\n");
		}
		sb.append("\nCHANGED METHODS:\n");
		for(String d : differentMethods)
		{
			String[] sd=d.split("=");
			sb.append("- "+sd[0]+"\n");
			sb.append("+ "+sd[1]+"\n");
		}
		sb.append("\nDELETED METHODS:\n");
		for(String d : deletedMethods)
		{
			sb.append("- "+d+"\n");
		}
		return sb.toString();
	}
}

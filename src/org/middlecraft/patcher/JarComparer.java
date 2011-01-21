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

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javassist.*;

/**
 * Try to match up classes. Check for matching method sigs+field sigs, then add
 * new shit. Next, remap MCP mappings.
 * 
 * @author Rob
 * 
 */
public class JarComparer {
	static Map<String, String> changedClassMappings = new HashMap<String, String>();

	// java -jar minecraft.jar JarComparer jars/serverA.jar jars/serverB.jar data/server/1.2_02/changes.txt
	public static void main(String[] arguments) throws Throwable {

	    Writer output = new BufferedWriter(new FileWriter(new File(arguments[2])));
	    output.write("CHANGES:\n\n");
	    output.write(Go(new File(arguments[0]),new File(arguments[1])));
	    output.close();
	}
	public static String Go(File _oldJar, File _newJar) throws NotFoundException {
		Map<String, CtClass> oldClasses;
		Map<String, CtClass> newClasses;
		try {
			oldClasses = loadJar(_oldJar);
			newClasses = loadJar(_newJar);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}

		StringBuilder sb = new StringBuilder();

		List<CtClass> unmatched = new ArrayList<CtClass>();
		for (CtClass clas : newClasses.values()) {
			unmatched.add(clas);
		}
		double bct = 1; // Best score threshold.
		while (bct > 0.5) {
			for (CtClass newClass : unmatched) {
				// Skip if mapped.
				if (changedClassMappings.containsValue(newClass.getName()))
					continue;
				List<ClassComparer> ccl = new ArrayList<ClassComparer>();
				for (CtClass oldClass : oldClasses.values()) {
					ccl.add(new ClassComparer(newClass, oldClass));
				}
				double bestScore = 0;
				int idxOfBestScore = -1;
				for (ClassComparer cc : ccl) {
					double score = cc.getScore();
					if (score > bestScore) {
						bestScore = score;
						idxOfBestScore = ccl.indexOf(cc);
					}
				}
				if (bestScore > bct) {
					ClassComparer cc = ccl.get(idxOfBestScore);
					changedClassMappings.put(cc.oldClass.getName(),
							cc.newClass.getName());
					sb.append(cc.oldClass.getName()+"="+cc.newClass.getName());
					sb.append(String.format(" #%d%% -  %d methods added, %d removed, %d changed\n",
							bestScore*100D,
							cc.newMethods.size(),
							cc.deletedMethods.size(),
							cc.differentMethods.size()));
				}
			}
		}
		return sb.toString();
	}

	private static Map<String, CtClass> loadJar(File jar) throws IOException {
		Map<String,CtClass> classes = new HashMap<String,CtClass>();
		ClassPool cp = ClassPool.getDefault();
		JarFile inJar = new JarFile(jar);
		for(JarEntry e : Collections.list(inJar.entries())) {
			if(e.getName().endsWith(".class")) {
				// Strip off the .class
				String className = e.getName().substring(0, e.getName().indexOf('.'));
				classes.put(className.replace('/', '.'),cp.makeClass(inJar.getInputStream(e)));
			}
		}
		return classes;
	}
}

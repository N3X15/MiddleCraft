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
package org.middlecraft.patcher;
import java.io.*;
import java.util.logging.Logger;

import javassist.*;

import org.middlecraft.patcher.reflect.*;
import org.middlecraft.server.*;

/**
 * Terribad class patcher.
 * @author Rob
 *
 */
public class Patches {
	private static ClassPool pool=null;
	private static Logger l = Logger.getLogger("Minecraft");
	
	/**
	 * Patches the named class.  Temporary measure until patching setup is complete.
	 * @param className Name of the victim class (obfuscated)
	 * @throws CannotCompileException 
	 * @throws IOException 
	 * @throws NotFoundException 
	 */
	public static void Patch(String className) throws NotFoundException, IOException, CannotCompileException {
		
		/* Set up classpool, if needed. */
		if(pool==null) {
			pool=ClassPool.getDefault();
		}
		
		/* Load our victim class.  If possible. */
		CtClass cc=null;
		try {
			cc = pool.get(className);
		} catch(NotFoundException e) {
			e.printStackTrace();
			return;
		}
		/* MC package? BAIL OUT. */
		if(!isMinecraftPackage(cc.getPackageName())) return;
		

		l.info(String.format("Processing [%s] %s...",cc.getPackageName(),className));
		
		/* Deobfuscate class name, assuming MCP mappings are installed... :/ */
		String newClassName=SmartReflector.getNewClassName(className);
		if(!newClassName.equals(className))
		{
			cc.setName(newClassName);
			l.info(String.format("Renaming class %s to %s.",className,newClassName));
		}
		
		/* Grab our patch, if possible, and load it. */
		CtClass patch=null;
		try {
			File pf = new File(getPatchFilename(className));
			patch=pool.makeClass(new FileInputStream(pf));
		} catch(FileNotFoundException e) {
			return;
		} catch (Throwable e) {
			l.severe("Cannot compile patch "+getPatchFilename(className)+":");
			e.printStackTrace();
			return;
		}
		
		l.info("Patching "+className+"...");
		try {
			/* For each method in the patch, add or replace as needed. */
			for(CtMethod method : patch.getMethods()) {
				// Replace
				if(method.hasAnnotation(Replace.class)) {
					String name = method.getName();
					String sig = method.getSignature();
					cc.getMethod(name, sig).setBody(method, null);
				}
				// Add
				if(method.hasAnnotation(Add.class)) {
					CtMethod m = new CtMethod(method, cc, null);
					cc.addMethod(m);
				}
			}
			/* Same deal with fields. */
			for(CtField field : patch.getFields()) {
				// Add
				if(field.hasAnnotation(Add.class)) {
					cc.addField(field);
				}
				// Replace
				if(field.hasAnnotation(Replace.class)) { 
					cc.removeField(cc.getField(field.getName()));
					cc.addField(field);
				}
			}
		} catch (CannotCompileException e) {
			l.severe(String.format("ERROR in %s:", patch.getName()));
			e.printStackTrace();
			return;
		} catch (NotFoundException e) {
			l.severe(String.format("ERROR in %s:", patch.getName()));
			e.printStackTrace();
			return;
		}
		// Save.
		cc.writeFile();
	}
	/**
	 * Determine if the named package is one of the Minecraft packages.
	 * @param packageName Name of the package.
	 * @return
	 */
	private static boolean isMinecraftPackage(String packageName) {
		return packageName.equals("") || packageName.equals("net.minecraft.server");
	}
	/**
	 * Get a patch's filename.
	 * @param className
	 * @return
	 */
	private static String getPatchFilename(String className) {
		// TODO Use patches-src or whatever.
		return String.format("/data/server/%s/patches/Patched%s.java", SmartReflector.serverVersion, className);
	}
	
}

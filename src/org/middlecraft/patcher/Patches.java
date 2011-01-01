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
import java.util.*;
import java.util.jar.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javassist.*;
import javassist.bytecode.ClassFile;
import javassist.bytecode.Descriptor;

import org.middlecraft.patcher.reflect.*;
import org.middlecraft.server.SmartReflector;

/**
 * Terribad class patcher.
 * @author Rob
 *
 */
public class Patches {
	private static ClassPool pool=null;
	private static Logger l = Logger.getLogger("Minecraft");

	static Map<String,CtClass> patches = new HashMap<String,CtClass>();

	public static void initialize() throws NotFoundException {
		try {
			pool=ClassPool.getDefault();
			pool.appendClassPath("lib/*");
			JarFile jar = new JarFile("patches.jar");
			for(JarEntry e : Collections.list(jar.entries())) {
				if(e.getName().endsWith(".class")) {
					patches.put(e.getName().replace(".class", ""),pool.makeClass(jar.getInputStream(e)));
				}
			}
			l.info(String.format("Loaded %d patches...",patches.size()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Patches the named class.  Temporary measure until patching setup is complete.
	 * @param className Name of the victim class (obfuscated)
	 * @throws CannotCompileException 
	 * @throws IOException 
	 * @throws NotFoundException 
	 */
	public static void Patch(String className, JarOutputStream outJar) throws NotFoundException, IOException, CannotCompileException {

		/* Load our victim class.  If possible. */
		CtClass cc=null;
		try {
			cc = pool.get(className);
		} catch(NotFoundException e) {
			e.printStackTrace();
			System.exit(1);
			return;
		}
		/* NOT MC package? BAIL OUT. */
		if(!isMinecraftPackage(cc.getPackageName())) {
			//l.info(cc.getPackageName()+" is not the MC package.");
			return;
		}

		//l.info(String.format("Processing [%s] %s...",cc.getPackageName(),className));

		/* Check if superclass mappings are correct. */
		SmartReflector.updateSuperclassInfo(cc);

		/* Deobfuscate class name, assuming MCP mappings are installed... :/ */
		String newClassName=SmartReflector.getNewClassName(className);
		if(newClassName.equals(className))
		{
			newClassName=className+"2"; // To ensure we get fresh classes.
		}
		cc.setName(newClassName);
		l.fine(String.format("Renaming class %s to %s.",className,newClassName));
		className=newClassName;

		/* Grab our patch, if possible, and load it. */
		CtClass patch=patches.get("Patched"+className);
		if(patch==null) return;

		l.info("Patching "+className+"...");
		try {
			/* For each method in the patch, add or replace as needed. */
			for(CtConstructor ctor : patch.getConstructors()) {
				// Replace
				if(ctor.hasAnnotation(Replace.class)) {
					try {
						String sig = fixSig(ctor);
						l.info(String.format(" + Replacing constructor %s...",ctor.getLongName()));
						cc.getConstructor(sig).setBody(ctor, null);
					} catch(NotFoundException e) {
						l.log(Level.SEVERE,"Could not find constructor, following is a list of constructors for this class:",e);
						printConstructorList(cc);
						System.exit(1);
					}
				}
				// Add
				if(ctor.hasAnnotation(Add.class)) {
					CtConstructor m = new CtConstructor(ctor, cc, null);
					l.info(String.format(" + Adding constructor %s...",ctor.getLongName()));
					cc.addConstructor(m);
				}
			}
			/* For each method in the patch, add or replace as needed. */
			for(CtMethod method : patch.getMethods()) {
				// Replace
				if(method.hasAnnotation(Replace.class)) {
					String name = method.getName();
					String sig = fixSig(method);
					l.info(String.format(" + Replacing %s...",method.getLongName()));
					try{
						cc.getMethod(name, sig).setBody(method, null);
					} catch(NotFoundException e) {
						l.log(Level.SEVERE,"Could not find method, following is a list of methods for this class:",e);
						printMethodList(method, cc);
						System.exit(1);
					}
				}
				// Add
				if(method.hasAnnotation(Add.class)) {
					CtMethod m = new CtMethod(method, cc, null);
					l.info(String.format(" + Adding %s...",method.getLongName()));
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
		} catch (ClassNotFoundException e) {
			l.severe(String.format("ERROR in %s:", patch.getName()));
			e.printStackTrace();
			return;
		}
		// Save.
		if(outJar==null)
			cc.writeFile("classes/");
		else {
			ClassFile cf = cc.getClassFile();
			cf.write(new DataOutputStream(outJar));
		}
	}

	/**
	 * Returns the signature after having applied SetParamType operations.
	 * @param method
	 * @return
	 * @throws NotFoundException 
	 * @throws ClassNotFoundException 
	 */
	private static String fixSig(CtConstructor ctor) throws NotFoundException, ClassNotFoundException {
		CtClass[] params = Descriptor.getParameterTypes(ctor.getSignature(), pool);
		
		for(int i = 0;i<params.length;i++) {
			Object[] pas = ctor.getParameterAnnotations()[i];
			for(Object pa : pas) {
				if(pa.getClass()==SetParamType.class) {
					SetParamType spt = (SetParamType)pa;
					params[i]=pool.get(spt.value());
				}
			}
		}
		
		return Descriptor.ofConstructor(params);
	}

	/**
	 * Returns the signature after having applied SetParamType operations.
	 * @param method
	 * @return
	 * @throws NotFoundException 
	 * @throws ClassNotFoundException 
	 */
	private static String fixSig(CtMethod method) throws NotFoundException, ClassNotFoundException {
		CtClass[] params = Descriptor.getParameterTypes(method.getSignature(), pool);
		CtClass returnType = Descriptor.getReturnType(method.getSignature(), pool);
		
		for(int i = 0;i<params.length;i++) {
			Object[] pas = method.getParameterAnnotations()[i];
			for(Object pa : pas) {
				if(pa.getClass()==SetParamType.class) {
					SetParamType spt = (SetParamType)pa;
					params[i]=pool.get(spt.value());
				}
			}
		}
		
		return Descriptor.ofMethod(returnType, params);
	}

	private static void printMethodList(CtMethod method, CtClass cc) {
		for(CtMethod m : cc.getMethods()) {
			if(m.getName().equals(method.getName())) {
				l.severe(" * "+m.getLongName());
			}
		}
	}

	private static void printConstructorList(CtClass cc) {
		for(CtConstructor c : cc.getConstructors()) {
			l.severe(" * "+c.getLongName());
		}
	}

	/**
	 * Determine if the named package is one of the Minecraft packages.
	 * @param packageName Name of the package.
	 * @return
	 */
	private static boolean isMinecraftPackage(String packageName) {
		return packageName==null || packageName.equals("") || packageName.equals("net.minecraft.server");
	}

}

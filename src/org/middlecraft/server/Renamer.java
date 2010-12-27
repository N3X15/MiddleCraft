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
import java.util.logging.Level;
import java.util.logging.Logger;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.Translator;

/**
 * @author Rob
 *
 */
public class Renamer implements Translator {
	Logger l = Logger.getLogger("Minecraft");
	/**
	 * Deobfuscate classes
	 * @see javassist.Translator#onLoad(javassist.ClassPool, java.lang.String)
	 */
	@Override
	public void onLoad(ClassPool cp, String className) throws NotFoundException,
			CannotCompileException {
		ClassInfo ci = SmartReflector.classes.get(className);
		if(ci==null)
		{
			SmartReflector.addObfuscatedClassDefinition(className);
			ci = SmartReflector.classes.get(className);
		}
			
		String ncn = ci.name;
		l.log(Level.FINE,"Renaming class "+className+" to "+ncn+".");
		try {
			if(className!="net.minecraft.server.MinecraftServer") {
				if(className==ncn) throw new NotFoundException(className);
				CtClass cc = cp.getAndRename(className, ncn);
				String pkg = cc.getPackageName();
				if(pkg==null || pkg == "net.minecraft.server")
				{
					cc=remapFields(cc, className);
					cc=remapMethods(cc, className);
				}
			}
		} catch(NotFoundException e) {
			l.log(Level.WARNING,"Failed to get new classname for "+className);
			SmartReflector.addObfuscatedClassDefinition(className);
		}
	}
	
	List<String> skippedPackages = new ArrayList<String>();

	/**
	 * @param cc
	 * @param className 
	 * @return
	 * @throws CannotCompileException 
	 */
	private CtClass remapMethods(CtClass cc, String className) throws CannotCompileException {
		if(cc == null) {
			l.log(Level.WARNING,"cc=null");
		}
		if(cc.isFrozen())
			cc.defrost();
		for(CtMethod method : cc.getMethods()) {
			String pkg=method.getDeclaringClass().getPackageName();
			if(!Utils.isMinecraftPackage(pkg))
			{
				if(!skippedPackages.contains(pkg)) {
					l.log(Level.FINE,"Skipping package "+pkg);
					skippedPackages.add(pkg);
				}
				continue;
			}
			MethodInfo mi = SmartReflector.getMethod(className,method.getName(),method.getSignature());
			
			
			if(mi!=null) {
				if(mi.name!="*" || mi.name!="") continue;
				if(cc.isFrozen())
					cc.defrost();
				l.log(Level.INFO,String.format(" [M] [%s]%s%s to \"%s\"...",method.getDeclaringClass().getPackageName(),method.getLongName(),mi.signature,mi.name));
				method.setName(mi.name);
				method=mi.DoPatch(method);
			}
		}
		return cc;
	}

	/**
	 * @param cc
	 * @param className 
	 * @return
	 */
	private CtClass remapFields(CtClass cc, String className) {
		if(cc == null) {
			l.log(Level.WARNING,"cc=null");
		}
		if(cc.isFrozen())
			cc.defrost();
		for(CtField field : cc.getFields()) {
			String pkg=field.getDeclaringClass().getPackageName();
			if(!Utils.isMinecraftPackage(pkg))
			{
				if(!skippedPackages.contains(pkg)) {
					l.log(Level.FINE,"Skipping package "+pkg);
					skippedPackages.add(pkg);
				}
				continue;
			}
			try
			{
				FieldInfo fi = SmartReflector.getField(className,field.getName(),field.getType().getName());
				
				if(fi!=null) {
					if(fi.name!="*" || fi.name!="") continue;
					if(cc.isFrozen())
						cc.defrost();
					field.setName(fi.name);
				}
			} catch(Exception e){}
		}
		return cc;
	}

	/* (non-Javadoc)
	 * @see javassist.Translator#start(javassist.ClassPool)
	 */
	@Override
	public void start(ClassPool cp) throws NotFoundException,
			CannotCompileException {
		
	}

}

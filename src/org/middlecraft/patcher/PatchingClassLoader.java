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


import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import org.middlecraft.server.Utils;



/**
 * @author Joshua 'Skrylar' Cearley
 */
public class PatchingClassLoader extends URLClassLoader {
	protected static final Logger l = Logger.getLogger("Minecraft");
	protected Map<String, Class<?>> classCache;
	
	public PatchingClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
		classCache = new HashMap<String, Class<?>>();
	}
	
	protected boolean isSystemClassName(String name) {
		return name.startsWith("java.") || name.startsWith("javax.");
	}
	
	protected synchronized Class<?> loadClass(String className, boolean resolve)
	throws ClassNotFoundException {
		/* Don't mess around with system classes. */
		if (isSystemClassName(className)) {
			l.info(String.format("[%s] Loaded via isSystemClassName", className));
			return super.loadClass(className, resolve);
		}
		
		/* Check our class cache first, if we find something there then
		 * return it otherwise we need to generate or look up the class
		 * in question. */
		Class<?> cls = classCache.get(className);
		if (cls != null) {
			l.info(String.format("[%s] Cached", className));
			return cls;
		} else {
			l.info(String.format("[%s] Loaded via Patcher", className));
			try {
				Patches.Patch(className);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CannotCompileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Determine if patching is required, and patch if needed.
			cls = super.loadClass(className, resolve);
			classCache.put(className, cls);
			return cls;
		}
	}
}

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

import java.net.*;
import java.util.*;
import java.util.logging.*;

import org.middlecraft.server.Mappings;

/**
 * A custom ClassLoader that patches certain classes as they are loaded.
 *
 * PatchingClassLoaders allows for certain classes to be loaded with hooks
 * for performing custom modifications before said classes are linked in to
 * the virtual machine.  If a class is not being patched, it is loaded as
 * normal thanks to the URLClassLoader that PatchingClassLoaders inherits
 * from.
 * <br><br>
 * Note that this inheritence is neccesary as classes inherit their
 * classloader and refer to that one for future class loading by default,
 * meaning if we did not load them through this class then we could lose
 * oversight of class loading on accident.
 *
 * @author Joshua 'Skrylar' Cearley
 */
public class PatchingClassLoader extends URLClassLoader {
	protected static final Logger l = Logger.getLogger("Middlecraft");

	public void addURI(URI uri) {
		try {
			this.addURL(uri.toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Stores loaded and generated classes for faster lookup.
	 */
	protected Map<String, Class<?>> classCache;

	/**
	 * Creates a new PatchingClassLoader that will consult the given array
	 * of URLs as well as a given parent classloader.
	 *
	 * @param urls An array of URLs that make up a class path of jars and
	 * class files to be used if a given class is not being patched.
	 * @param parent A parent class loader used to load system classes.
	 *
	 * @see java.net.URL
	 * @see java.lang.ClassLoader
	 */
	public PatchingClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
		classCache = new HashMap<String, Class<?>>();
	}

	/**
	 * Determines whether a given class name belongs to the system, which
	 * means that it must be loaded through the primordial class loader.
	 * We can't hook these functions directly, they would need to be renamed
	 * through Javasist's ClassMap.
	 *
	 * @param name Fully quantified name of the class in question.
	 * @return True if a class name belongs to the system, false if we may
	 * load it as we wish.
	 */
	protected boolean isSystemClassName(String name) {
		return name.startsWith("java.") || name.startsWith("javax.");
	}

	/**
	 * Performs class loading as needed.
	 *
	 * @name className Fully quantified name of the class to be loaded.
	 * @name resolve Whether or not to resolve (link with the JVM) the class
	 * upon loading.
	 * @return A loaded and (optionally) linked class.
	 * @throws ClassNotFoundException If a class can not be found, a
	 * ClassNotFoundException will be thrown to indicate such.
	 */
	protected synchronized Class<?> loadClass(String className, boolean resolve)
	throws ClassNotFoundException {
		/* Don't mess around with system classes. */
		if (isSystemClassName(className)) {
			//l.info(String.format("[%s] Loaded via isSystemClassName", className));
			return super.loadClass(className, resolve);
		}

		/* Check our class cache first, if we find something there then
		 * return it otherwise we need to generate or look up the class
		 * in question. */
		Class<?> cls = classCache.get(className);
		if (cls != null) {
			//l.info(String.format("[%s] Cached", className));
			return cls;
		} else {
			//l.info(String.format("[%s] Loaded via Patcher", className));
			// Determine if patching is required, and patch if needed.
			try {
				Patches.Patch(className, null);
				className=Mappings.getNewClassName(className);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			try {
				cls = super.loadClass(className, resolve);
				classCache.put(className, cls);
				return cls;
			} catch(ClassFormatError e) {
				l.log(Level.SEVERE,String.format("loadClass %s failed:",className),e);
				System.exit(1);
				return null;
			}
		}
	}
}

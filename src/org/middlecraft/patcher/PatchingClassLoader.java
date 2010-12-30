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

import java.util.*;
import java.lang.*;
import java.util.logging.*;

/**
 * Assures tampered-with classes are used instead of originals.
 * 
 * The PatchingClassLoader ensures that a set of patches are applied to
 * classes being loaded so that any desirable changes in behavior will be
 * made, falling back to the parent class loader if there is no patching
 * solution for the class being requested.  Patched classes are cached after
 * generation for faster retrieval.
 * 
 * @author Joshua 'Skrylar' Cearley
 */
public class PatchingClassLoader extends ClassLoader {
	protected static final Logger l = Logger.getLogger("PatchingClassLoader");
	protected Map<String, Class<?>> classCache;
	
	public PatchingClassLoader(ClassLoader parent) {
		super(parent);
		if (parent == null) {
			throw new NullPointerException();
		}
	}
	
	@Override
	public Class<?> loadClass(String name)
	throws ClassNotFoundException {
		return this.synchLoadClass(name);
	}
	
	protected synchronized Class<?> synchLoadClass(String name)
	throws ClassNotFoundException {
		l.info("Looking up class: " + name);
		
		/* Pass-through to the parent. */
		return this.getParent().loadClass(name);
	}
}
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

import java.lang.reflect.*;
import java.util.logging.Logger;

/**
 * Acts as the middleman between Minecraft classes and MiddleCraft classes.
 * @author Rob
 *
 */
public class GrabbedClassInstance {
	Logger l = Logger.getLogger("Minecraft");
	Class theClass;
	Object instance;
	public String name;
	/**
	 * @param theClass
	 * @param className
	 * @param params
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IllegalArgumentException 
	 */
	@SuppressWarnings("rawtypes")
	public GrabbedClassInstance(Class _class, String _className,
			Object[] _params) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		theClass=_class;
		name=_className;
		
		// Find a suitable constructor, if possible.
		for( Constructor c : theClass.getConstructors()) {
			Class[] c_params = c.getParameterTypes();
			if(c_params.length != _params.length)
				continue;
			if(c_params.length==0)
			{
				// Constructor doesn't need arguments, just initialize it.
				instance=c.newInstance();
				return;
			}
			// Check types
			boolean types_ok = true;
			for(int i = 0;i<c_params.length;i++) {
				if(c_params[i].getName()!=_params[i].getClass().getName())
					types_ok=false;
			}
			if(!types_ok) continue;
			
			// LETS DO DIS
			l.info("[GrabbedClassInstance] Initializing "+c.toString());
			instance=c.newInstance(_params);
			return;
		}
	}
	
	/**
	 * Call a class method with the specified arguments.
	 * @param name
	 * @param args
	 * @return
	 * @throws NoSuchMethodException
	 */
	public Object callMethod(String name, Object... args) throws NoSuchMethodException
	{
		Class[] types = new Class[args.length];
		for(int i = 0;i < args.length; i++)
			types[i]=args[i].getClass();
		Method meth = theClass.getMethod(SmartReflector.classes.get(this.name).getMethod(name), types);
		try {
			return meth.invoke(instance, args);
		} catch (Exception e) {
			l.warning(e.toString());
			return null;
		}
	}

	/**
	 * Get the value of a class field.
	 * @param name
	 * @return
	 */
	public Object getField(String name) {
		try {
			Field fld = theClass.getField(SmartReflector.classes.get(this.name).getField(name));
			return fld.get(instance);
		} catch (Exception e) {
			l.warning(e.toString());
			return null;
		}
	}

	/**
	 * Set the value of a class field.
	 * @param name
	 * @param value
	 * @return
	 */
	public boolean setField(String name, Object value) {
		try {
			Field fld = theClass.getField(SmartReflector.classes.get(this.name).getField(name));
			fld.set(instance,value);
		} catch (Exception e) {
			l.warning(e.toString());
			return false;
		}
		return true;
	}
}

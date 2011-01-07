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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javassist.ClassPool;
import javassist.Modifier;

/**
 * @author Rob
 * 
 */
public class MCClassInfo {
	// realName,name,realSuperClass,superClass,description
	public static final String[] header = new String[] { "Real Name",
			"MCP Name", "Real Superclass", "MiddleCraft Superclass",
			"Description" };
	public String description = "*";
	public HashMap<String, MCFieldInfo> fields = new HashMap<String, MCFieldInfo>();
	static Logger l = Logger.getLogger("Middlecraft");
	public HashMap<String, MCMethodInfo> methods = new HashMap<String, MCMethodInfo>();
	public String name;
	public String realName;
	public String realSuperClass;
	public String superClass = "*";
	public int modifiers = 0;
	private String searge="";

	public MCClassInfo() {
	}

	public MCClassInfo(List<String> cells) {
		realName = cells.get(0);
		name = cells.get(1);
		realSuperClass = cells.get(2);
		superClass = cells.get(3);
		description = cells.get(4);
	}

	@SuppressWarnings("unchecked")
	public MCClassInfo(Map<String, Object> o) {
		/*
        return_dic[entry['trg_name']] = {}                
        return_dic[entry['trg_name']]['notch']      = notch_data[-1]
        return_dic[entry['trg_name']]['searge']     = entry['trg_name']
        return_dic[entry['trg_name']]['full']       = entry['trg_name']
        return_dic[entry['trg_name']]['class']      = entry['trg_name']
        return_dic[entry['trg_name']]['full_final'] = entry['trg_name']
        return_dic[entry['trg_name']]['notch_pkg']  = '/'.join(notch_data[:-1])
        return_dic[entry['trg_name']]['modified']   = False
        return_dic[entry['trg_name']]['methods']    = {}
        return_dic[entry['trg_name']]['fields']    = {}
        */
		realName = (String)o.get("notch");
		searge=(String)o.get("searge");
		name=(String)o.get("class");
		if(o.containsKey("superclass"))
			superClass=(String)o.get("superclass");
		if(o.containsKey("modifiers"))
			modifiers=(Integer)o.get("modifiers");
		
		for(Object _m : ((Map<String,Object>)o.get("methods")).values()) {
			if(_m instanceof Map<?,?>)
				addMethod(new MCMethodInfo((Map<String,Object>)_m));
		}
		
		for(Object _f : ((Map<String,Object>)o.get("fields")).values()) {
			if(_f instanceof Map<?,?>)
			{
				MCFieldInfo f = new MCFieldInfo((Map<String,Object>)_f);
				addField(f);
			}
		}
		
	}

	private void addField(MCFieldInfo f) {
		if (!fields.containsKey(f.realName))
			fields.put(f.realName, f);
	}

	private void addMethod(MCMethodInfo m) {
		if (!methods.containsKey(m.realName))
			methods.put(m.realName, m);
	}

	public void setField(MCFieldInfo f) throws Exception {
		if (fields.containsKey(f.realName))
			fields.put(f.realName, f);
		else throw new Exception("Can't find "+f.realName+" in array.");
	}

	/**
	 * @param fldName
	 * @return
	 */
	public MCFieldInfo getField(String fldName) {
		return fields.get(fldName);
	}

	/**
	 * @param methName
	 * @param methSig
	 * @return
	 */
	public MCMethodInfo getMethod(String methName, String methSig) {
		return methods.get(methName + " " + methSig);
	}

	public void setMethod(MCMethodInfo m) throws Exception {
		if (methods.containsKey(m.toIndex()))
			methods.put(m.toIndex(), m);
		else throw new Exception("Can't find "+m.realName+" in array.");
	}

	public List<String> toList() {
		List<String> list = new ArrayList<String>();
		list.add(realName);
		list.add(name);
		list.add(realSuperClass);
		list.add(superClass);
		list.add(description);
		return list;
	}

	public String toString() {
		return String.format("%s,%s,%s,%s,%s", realName, name, realSuperClass,
				superClass, description);
	}

	public String toJava(ClassPool cp, String _package) {
		StringBuilder sb = new StringBuilder();
		sb.append("// AUTOMATICALLY GENERATED BY MIDDLECRAFT\n");
		sb.append("/* Allows plugins to access server functions without needing to link the actual server Jar. */\n");
		sb.append("package "+_package+";");
		// public class HURP
		String classorinterface = "class";
		if(Modifier.isInterface(modifiers))
			classorinterface="interface"; // I think?
		if(!Modifier.isAbstract(modifiers) && !Modifier.isInterface(modifiers))
			modifiers |= Modifier.ABSTRACT;
		sb.append(String.format("\n\n%s %s %s", Modifier.toString(modifiers),classorinterface,name));
		if(!superClass.equals("java.lang.Object")&&!superClass.equals("*")) {
			sb.append(String.format(" extends %s", Mappings.getNewClassName(superClass)));
		}
		sb.append(" {\n\t// FIELDS");
		List<String> fieldKeys = new ArrayList<String>(fields.keySet());
		Collections.sort(fieldKeys);
		for(String fi : fieldKeys) {
			MCFieldInfo f = fields.get(fi);
			sb.append(String.format("\n\t%s %s %s;", Modifier.toString(f.modifiers),Mappings.getNewClassName(f.type),(f.name.isEmpty()) ? f.realName : f.name));
		}
		sb.append("\n\t\n\t// METHODS");
		List<String> methodKeys = new ArrayList<String>(methods.keySet());
		Collections.sort(methodKeys);
		for(String fi : methodKeys) {
			MCMethodInfo m = methods.get(fi);
			sb.append(m.toAbstractJava(cp));
		}
		sb.append("\n\n}\n");
		return sb.toString().replace("interface interface","interface");
	}

	public void setClassModifiers(int m) {
		modifiers = m;
	}

	public String getClassFileName(String p) {
		if (p == null)
			p = "net.minecraft.server";
		else if (p.isEmpty())
			p = "net.minecraft.server";

		return p.replace('.', '/') + "/" + name;
	}

	public void clearAllDefs() {
		fields.clear();
		methods.clear();
	}

	public Map<String,Object> toMap() {
		/*
		BlockLeavesBase:
		  class: BlockLeavesBase
		  fields: {}
		  full: BlockLeavesBase
		  full_final: BlockLeavesBase
		  methods: {}
		  modified: false
		  notch: w
		  notch_pkg: ''
		  searge: BlockLeavesBase
		*/
		Map<String,Object> c = new HashMap<String,Object>();
		Map<String,Object> m = new HashMap<String,Object>();
		for(MCMethodInfo _m : methods.values()) {
			String name = _m.name;
			if(name.isEmpty())
				name=_m.searge;
			m.put(name, _m.toMap());
		}
		Map<String,Object> f = new HashMap<String,Object>();
		for(MCFieldInfo _f : fields.values()) {
			String name = _f.name;
			if(name.isEmpty())
				name=_f.searge;
			f.put(name, _f.toMap());
		}

		c.put("class",name);
		c.put("fields",f);
		c.put("methods",m);
		c.put("searge",searge);
		c.put("notch",realName);
		c.put("superclass",superClass);
		c.put("modifiers",modifiers);
		
		return c;
	}
}

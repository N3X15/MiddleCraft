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

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;





/**
 * @author Rob
 *
 */
public class Hooks {
	
	protected static Map<String,PluginListener> enabledListeners;
	protected static Map<String,Plugin> loadedPlugins;
	
	public static void initialize()
	{
		File dir = new File("plugins/");
		if(!dir.exists())
			dir.mkdir();
		for(File f : dir.listFiles()) {
			if(f.isFile() && f.getName().endsWith(".jar")) {
				try {
					loadPlugin(f);
				} catch (Exception e) {
					
				}
			}
		}
	}

	/**
	 * @param f
	 */
	private static void loadPlugin(File f) {
		try {
			URLClassLoader cl = new URLClassLoader(new URL[]{f.toURI().toURL()});
			String fname = f.getName();
			String pluginName= fname.substring(0, fname.lastIndexOf("."));
			Plugin p = (Plugin)cl.loadClass(pluginName).getConstructor().newInstance();
			loadedPlugins.put(pluginName,p);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void RegisterListener(String pluginName, PluginListener listener) {
		enabledListeners.put(pluginName,listener);
	}
	
	public static void DisablePlugin(String pluginName) {
		enabledListeners.remove(pluginName);
		loadedPlugins.get(pluginName).disable();
	}
	
	public static void StartPlugin(String pluginName) {
		loadedPlugins.get(pluginName).initialize();
		loadedPlugins.get(pluginName).enable();
	}
}
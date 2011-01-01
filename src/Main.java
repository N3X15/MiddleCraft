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
import java.io.*;
import java.util.Collections;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.logging.*;
import java.net.*;
import java.lang.reflect.*;

import javassist.*;

import org.middlecraft.server.*;
import org.middlecraft.patcher.*;

public class Main {
	static Logger l = Logger.getLogger("Minecraft");

	protected static ClassPool mcClassPool;
	protected static PatchingClassLoader mcClassLoader;
	
	public static void main(String[] arguments) throws Throwable {
		
		l.info("Stage-1 Boot Sequence Start");
		l.info(" + Setting up SmartReflector classmappings...");
		SmartReflector.initialize();

		l.info(" + Setting up bootloader...");
		try {
			File mcServerJar = new File("lib/minecraft_server.jar");
			URL mcServerJarURL = mcServerJar.toURI().toURL();
			l.log(Level.INFO, "MC Server: ", mcServerJarURL.toString());
			
			mcClassLoader = new PatchingClassLoader(
				new URL[] {
					mcServerJarURL//, mcServerJarURLNew
				}, ClassLoader.getSystemClassLoader());
			Thread.currentThread().setContextClassLoader(mcClassLoader);
		} catch (Exception e) {
			l.log(Level.SEVERE, "Problem setting up bootloader.", e);
			System.exit(1);
		}
		
		
		l.info("Stage 2: Patching server jar...");
		l.info(" + Loading patches...");
		Patches.initialize();
		l.info(" + Creating minecraft_server.jar.new...");
		File newMCServerJar = new File(String.format("lib/minecraft_server.jar.new"));
		if(!newMCServerJar.exists())
		{
			newMCServerJar.mkdirs();
			JarFile inJar = new JarFile("lib/minecraft_server.jar");
			JarOutputStream outJar = new JarOutputStream(new FileOutputStream(newMCServerJar));
			for(JarEntry e : Collections.list(inJar.entries())) {
				if(e.getName().endsWith(".class")) {
					// Strip off the .class
					String className = e.getName().substring(0, e.getName().indexOf('.')); 
					Patches.Patch(className, outJar);
				}
			}
			outJar.close();
		}
		l.info(" + Updating Classpath...");
		mcClassLoader.addURI(newMCServerJar.toURI());
		
		l.info("Stage 3: Booting server!");
		try {
			// Bootstrap...
			Class<?> mcBootClass =
					Class.forName("net.minecraft.server.MinecraftServer", true, mcClassLoader);
			Method mainMethod = mcBootClass.getMethod("main", String[].class);
			mainMethod.invoke(null, new Object[] {arguments});
		} catch (Exception e) {
			l.log(Level.SEVERE, " ! Unexpected error on Stage-3 boot.", e);
		}
	}
}

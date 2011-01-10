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
import java.util.zip.ZipEntry;
import java.net.*;
import java.lang.reflect.*;

import javassist.*;

import org.middlecraft.server.*;
import org.middlecraft.patcher.*;

public class Main {
	static Logger l = Logger.getLogger("Middlecraft");

	protected static ClassPool mcClassPool;
	protected static PatchingClassLoader mcClassLoader;

	private static boolean getServerInterfaces=false;

	public static void main(String[] arguments) throws Throwable {

		String classPath="";
		try { classPath = System.getProperty( "java.class.path" ) ; }
        catch ( Exception e ) 
          {
            System.out.println( "Exception: " + e ) ;
            e.printStackTrace() ;
          }
        System.out.println( "CLASSPATH = " + classPath) ;

		// Get net.minecraft.server interfaces and regenerate clean classmappings. Only use on new server updates.
		if(arguments.length==1 && arguments[0].equals("GetServerInterfaces"))
			getServerInterfaces=true;

		/* Set up logging. */
		l.setUseParentHandlers(false);
		ConsoleHandler handle_c = new ConsoleHandler();
		handle_c.setFormatter(new MiddlecraftFormatter());
		l.addHandler(handle_c);

		l.info("Stage-1 Boot Sequence Start");
		l.info(" + Setting up SmartReflector classmappings...");
		Mappings.initialize();
		l.info(" + Setting up bootloader...");
		try {
			File mcServerJar = new File("lib/minecraft_server.jar");
			URL mcServerJarURL = mcServerJar.toURI().toURL();
			l.log(Level.INFO, "MC Server: ", mcServerJarURL.toURI().toString());

			mcClassLoader = new PatchingClassLoader(
					new URL[] {
							mcServerJarURL//, mcServerJarURLNew
					}, ClassLoader.getSystemClassLoader());
			Thread.currentThread().setContextClassLoader(mcClassLoader);
		} catch (Exception e) {
			l.log(Level.SEVERE, "Problem setting up bootloader.", e);
			System.exit(1);
		}
		if(getServerInterfaces) {
			GenServerClasses();
			System.exit(0); // Hangs afterwards...
		}

		l.info("Stage 2: Patching server jar...");
		l.info(" + Loading patches...");
		Patches.initialize("patches.jar");
		l.info(" + Creating minecraft_server.jar.new...");
		File newMCServerJar = new File(String.format("lib/minecraft_server.jar.new"));
		JarOutputStream outJar = null;
		try {
			JarFile inJar = new JarFile("lib/minecraft_server.jar");
			outJar = new JarOutputStream(new FileOutputStream(newMCServerJar));
			for(JarEntry e : Collections.list(inJar.entries())) {
				if(e.isDirectory() && !e.getName().equals("META_INF")) {
					outJar.putNextEntry(new ZipEntry(e.getName()));
					outJar.closeEntry();
					l.info(" + Added dir "+e.getName()+" to jar.");
					continue;
				}
				if(e.getName().endsWith(".class")) {
					// Strip off the .class
					String className = e.getName().substring(0, e.getName().indexOf('.'));

					// Get new classname
					String newClassName=Mappings.getNewClassName(className.replace('/','.'));

					// Place the new class into the JAR.
					outJar.putNextEntry(new ZipEntry(newClassName.replace('.','/')+".class"));
					Patches.Patch(className.replace('/', '.'), outJar);
					outJar.closeEntry();
				}
			}
		} finally {
			outJar.close();
		}
		l.info(" + Updating Classpath...");
		mcClassLoader.addURI(newMCServerJar.toURI());

		Mappings.save(); // Force save.

		l.info("Stage 3: Booting server!");
		try {
			// Bootstrap...
			Class<?> mcBootClass =
				Class.forName(Mappings.getNewClassName("net.minecraft.server.MinecraftServer"), true, mcClassLoader);
			Method mainMethod = mcBootClass.getMethod("main", String[].class);
			mainMethod.invoke(null, new Object[] {arguments});
		} catch (Throwable e) {
			l.log(Level.SEVERE, " ! Unexpected error on Stage-3 boot.", e);
		}
	}

	private static void GenServerClasses() throws IOException, ClassNotFoundException, NotFoundException {

		Patches.initialize("staged/patches.jar");

		mcClassPool=ClassPool.getDefault();
		try {
			mcClassPool.appendClassPath("lib/*");
		} catch (NotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		JarFile inJar = new JarFile("lib/minecraft_server.jar");
		for(JarEntry e : Collections.list(inJar.entries())) {
			if(e.getName().endsWith(".class")) {
				// Strip off the .class
				String className = e.getName().substring(0, e.getName().indexOf('.')).replace('/','.');
				//l.info(className);
				// Get new classname
				String newClassName=Mappings.getNewClassName(className.replace('/','.'));
				if(newClassName.contains("."))
					newClassName=newClassName.substring(newClassName.lastIndexOf('.')+1);
				
				MCClassInfo ci = Mappings.classes.get(className.replace('/', '.'));
				if(ci==null) {
					l.warning("Generating mappings for "+className+"...");
					Mappings.addObfuscatedClassDefinition(className, "java.lang.Object");
					ci = Mappings.classes.get(className.replace('/', '.'));
				}
				CtClass mcClass = mcClassPool.get(className);
				ci.setClassModifiers(mcClass.getModifiers());
				if(!Patches.isMinecraftPackage(mcClass.getPackageName()))
					continue;
				
				//ci.clearAllDefs(); // Remapping.
				for(CtField fld : mcClass.getDeclaredFields()) {
					MCFieldInfo f = ci.getField(fld.getName());
					if(f==null)
					{
						Mappings.addObfuscatedFieldDefinition(className, fld.getName(), fld.getType().getName());
						f = ci.getField(fld.getName());
					}
					if(f.type==null)
						f.type=fld.getType().getName();
					
					if(!f.type.equals(fld.getType().getName())) {
						f.type=fld.getType().getName();
						l.info(f.name+" corrected to use the type "+f.type+".");
					}
					
					f.setModifiers(fld.getModifiers());
					try {
						ci.setField(f);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				for(CtMethod method : mcClass.getDeclaredMethods()) {
					MCMethodInfo m = ci.getMethod(method.getName(),method.getSignature());
					if(m==null) {
						Mappings.addObfuscatedMethodDefinition(className, method.getName(), method.getSignature(), "");
						m=ci.getMethod(method.getName(),method.getSignature());
					}
					
					m.setModifiers(method.getModifiers());
					try {
						ci.setMethod(m);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				ci.superClass=mcClass.getSuperclass().getName();
				
				File dir = new File("src-interface/net/minecraft/server/");
				dir.mkdirs();
				File f = new File(dir, newClassName+".java");
				Utils.putFileContents(f, ci.toJava(mcClassPool,"net.minecraft.server"));
				//l.info(newClassName);
			}
		}
		Mappings.save();
	}
}

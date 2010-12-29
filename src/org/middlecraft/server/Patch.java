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

import java.io.File;
import java.io.FileNotFoundException;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import javassist.bytecode.Descriptor;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;
/**
 * @author Rob
 *
 */
public class Patch {
	Logger l = Logger.getLogger("Minecraft");
	public CtClass mClass;
	public File mFile;

	private enum Subject {
		NEWMETHOD,
		NEWFIELD,
		METHOD,
		CONSTRUCTOR
	}

	private enum MethodOperation {
		SETBODYTOFILE,
		SETBODYTO,
		PREPENDBODY,
		APPENDBODY,
		INSERTAT
	}

	public Patch(CtClass Class) {
		mClass=Class;
		mFile = new File(String.format("data/server/%s/patches/%s.mcp",SmartReflector.serverVersion,mClass.getName()));
	}

	public void Parse() throws FileNotFoundException {
		// Don't parse if the file just isn't there.
		if(!mFile.exists()) return;
		l.info(String.format("Patching %s...",mClass.getName()));
		int ln=0;
		Scanner scanner = new Scanner(new FileInputStream(mFile));
		while(scanner.hasNext()) {
			ln++;
			String line = scanner.nextLine();
			try {

				// Skip line if it's a comment
				if(line.startsWith("#")) continue;
				if(line.trim().equals("")) continue;

				String[] chunks = line.trim().split("\t");
				Subject subject=null;
				try { 
					subject = Subject.valueOf(chunks[0].toUpperCase());
				} catch(IllegalArgumentException e) {
					throw new IllegalArgumentException(String.format("Could not parse subject \"%s\".",chunks[0]));
				}
				switch(subject) {
				case METHOD:
					DoMethodParse(chunks);
					break;
				case CONSTRUCTOR:
					DoConstructorParse(chunks);
					break;
				case NEWMETHOD:
					DoNewMethod(chunks);
					break;
				case NEWFIELD:
					DoNewField(chunks);
					break;
				default: break;
				}
			}  catch(Throwable e) {
				e.printStackTrace();
				l.severe(String.format("Error in patch %s:%d:",mFile.toString(),ln));
				l.severe(e.getMessage());
				l.severe(line);
			}
		}
	}

	/**
	 * @param chunks
	 * @throws CannotCompileException 
	 */
	private void DoNewMethod(String[] chunks) throws CannotCompileException {
		l.info(" + Creating new method: "+chunks[1].substring(0,chunks[1].indexOf('{')));
		// newmethod	void callOnTick (int x, int y, int z) {...}
		CtMethod nm = CtNewMethod.make(chunks[1], mClass);
		mClass.addMethod(nm);
	}

	/**
	 * @param chunks
	 * @throws CannotCompileException 
	 */
	private void DoNewField(String[] chunks) throws CannotCompileException {
		l.info(String.format(" + Creating new field: %s",chunks[1]));
		// newfield	int xPosition
		CtField field = CtField.make(chunks[1],mClass);
		mClass.addField(field);
	}

	/**
	 * @param chunks
	 * @throws CannotCompileException 
	 */
	private void DoConstructorParse(String[] chunks) throws CannotCompileException {
		// TODO Auto-generated method stub
		String sig = chunks[1];

		sig=Descriptor.rename(sig,SmartReflector.obfuscationMap);
		//CtClass[] types = Descriptor.getParameterTypes(sig, cp);

		CtConstructor method=null;
		try {
			method = mClass.getConstructor(sig);
		} catch(NotFoundException e) {
			l.severe("Can't find constructor with signature "+sig+".  Here are some alternatives:");
			for(CtConstructor ctor : mClass.getConstructors()) {
				l.info(ctor.getSignature());
			}
			System.exit(1);
		}

		MethodOperation op=null;
		try {
			op = MethodOperation.valueOf(chunks[2].toUpperCase());
		} catch(IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format("Could not parse method operation \"%s\".",chunks[0]));
		}

		String[] params = new String[chunks.length-3];
		System.arraycopy(chunks, 3, params, 0, params.length);

		switch(op) {
		case SETBODYTOFILE:
			l.info(String.format(" * Setting body of %s to contents of %s",method.getName(),params[0]));
			method.setBody(Utils.getFileContents(new File(String.format("data/server/%s/patches/%s/%s",SmartReflector.serverVersion,mClass.getName(),params[0]))));
			break;
		case SETBODYTO:
			l.info(String.format(" * Setting body of %s to %s",method.getName(),params[0]));
			method.setBody(params[0]); 
			break;
		case PREPENDBODY:
			l.info(String.format(" + Prepending body of %s with: %s",method.getName(),params[0]));
			method.insertBefore(params[0]);
			break;
		case APPENDBODY:
			l.info(String.format(" * Appending body of %s with: %s",method.getName(),params[0]));
			method.insertAfter(params[0]);
			break;
		case INSERTAT:
			int lineNum=Integer.parseInt(params[0]);
			l.info(String.format(" * Inserting code into %s:%d: %s",method.getName(),lineNum,params[1]));
			method.insertAt(lineNum,params[1]);
			break;
		default:
			l.severe("ERROR:  Operation "+op+" is invalid.");
			return;
		}
	}

	/**
	 * @param chunks
	 * @throws NotFoundException 
	 * @throws CannotCompileException 
	 */
	private void DoMethodParse(String[] chunks) throws NotFoundException, CannotCompileException {
		// method methodName signature dothings
		String methodName = chunks[1];
		String methodSig = chunks[2];

		CtMethod method;
		try {
			method = mClass.getMethod(methodName, methodSig);
		} catch (NotFoundException e) {
			l.severe(String.format("Can't find method %s with signature %s.  Here are some alternatives:",methodName,methodSig));
			for(CtMethod m : mClass.getMethods()) {
				l.info(m.toString());
			}
			throw new NotFoundException(methodName);
		}

		MethodOperation op=null;
		try {
			op = MethodOperation.valueOf(chunks[3].toUpperCase());
		} catch(IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format("Could not parse method operation \"%s\".",chunks[3]));
		}

		String[] params = new String[chunks.length-4];
		System.arraycopy(chunks, 4, params, 0, params.length);

		switch(op) {
		case SETBODYTOFILE:
			l.info(String.format(" * Setting body of %s to contents of %s",method.getName(),params[0]));
			method.setBody(Utils.getFileContents(new File(String.format("data/server/%s/patches/%s/%s",SmartReflector.serverVersion,mClass.getName(),params[0]))));
			break;
		case SETBODYTO:
			l.info(String.format(" * Setting body of %s to %s",method.getName(),params[0]));
			method.setBody(params[0]); 
			break;
		case PREPENDBODY:
			l.info(String.format(" + Prepending body of %s with: %s",method.getName(),params[0]));
			method.insertBefore(params[0]);
			break;
		case APPENDBODY:
			l.info(String.format(" * Appending body of %s with: %s",method.getName(),params[0]));
			method.insertAfter(params[0]);
			break;
		case INSERTAT:
			int lineNum=Integer.parseInt(params[0]);
			l.info(String.format(" * Inserting code into %s:%d: %s",method.getName(),lineNum,params[1]));
			method.insertAt(lineNum,params[1]);
			break;
		default:
			l.severe("ERROR:  Operation "+op+" is invalid.");
			return;
		}

	}
}

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

// Moved from DataJack
/**
 * Pluggable preferences class to allow unified access to plugin data.
 *
 * Datajack abstracts preference reading and writing away from the medium
 * used to store the information, the hierarchy that holds that information,
 * and the medium that archives the data.  This allows for Middlecraft as
 * well as plugins to simply store and retrieve configuration data, without
 * needing to write database or flatfile drivers for every module.
 * <br/><br/>
 * This reduces the work needed to make high quality plugins and increases
 * the overall modularity of the system.
 *
 * @author Joshua 'Skrylar' Cearley
 */
public interface IDataJack {
	// === GETTERS ===
	String getString(String _name, String _default);
	int getInteger(String _name, int _default);
	long getLong(String _name, long _default);
	boolean getBoolean(String _name, boolean _default);
	
	// === SETTERS ===
	void setString(String _name, String _value);
	void setInteger(String _name, int _value);
	void setLong(String _name, long _value);
	void setBoolean(String _name, boolean _value);
	
	// === NOTIFICATIONS ===
	//void dataJackedIn(DataJack _source, String _name);
	//void dataJackedOut(DataJack _source, String _name);
}

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



// Make this as compatible with hmod as possible without outright theft so it's easier for plugin authors to add their plugins to MiddleCraft.
// Which is going to be hard as we have to replicate functionality while adding our own.
/**
 * @author Rob
 *
 */
public class PluginListener {
	// hmod Functions
	public enum Priority {
		HIGH,
		MEDIUM,
		LOW
	}
	//////////////////////////////////////////////////////////////////////////////////
	// MiddleCraft functions
	//////////////////////////////////////////////////////////////////////////////////
	
	// Server THEN args
	

	// Yeah, I implemented something *I* wanted first.  Wanna fight about it? :V - N3X
	/**
	 * Handle block ticks.
	 */
	public void onBlockTick(Server serv, int x, int y, int z) {}
	
	/**
	 * Handle chunk load events.
	 * @param serv
	 * @param chunk
	 */
	public void onChunkLoaded(Server serv, Object chunk) {}
	
	/**
	 * Handle chunks that have been freshly generated.
	 * @param serv
	 * @param chunk
	 */
	public void onChunkGenerated(Server serv, Object chunk) {}
	
	/**
	 * Do stuff right before a chunk is released from memory.
	 * @param serv
	 * @param chunk
	 */
	public void onChunkUnLoading(Server serv, Object chunk) {}
	
	/**
	 * Handle a block melting (snow/ice).
	 * @param serv
	 * @param blockID BlockID of the block melting
	 * @param x
	 * @param y
	 * @param z
	 * @return False to stop the block from melting.
	 */
	public boolean onBlockMelt(Server serv, int blockID, int x, int y, int z) {return true;}
}

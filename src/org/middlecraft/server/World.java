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

/**
 * Provides accessors for many global functions.
 * @author Rob
 *
 */
public class World 
{
	protected Server server;
	
	/**
	 * Set up world class.
	 * @param s Server.
	 */
	public World(Server s){
		server=s;
	}
	
	/**
	 * Get a block from a global position.
	 * @param x
	 * @param y
	 * @param z
	 * @return block ID
	 */
	public int getBlockIdAt(int x, int y, int z) {
		return 0;
	}
	
	/**
	 * Set a block at a global position.
	 * @param x
	 * @param y
	 * @param z
	 * @param id BlockID
	 */
	public void setBlockIdAt(int x, int y, int z, int id) {
		// Use the equivalent of setBlockWithNotify.  We can create another function if lack of notification is required.
		
	}
	/**
	 * Get a block's physical properties.
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Material getBlockMaterialAt(int x, int y, int z) {
		return new Material();
	}
	
	/**
	 * Get a block's luminosity.
	 * @param x 
	 * @param y
	 * @param z
	 * @param blocklight Whether the value returned is blocklight (from torches, etc) or skylight (light from the "sun")
	 * @return
	 */
	public int getBlockLightAt(int x, int y, int z, boolean blocklight) {
		return 15;
	}
}
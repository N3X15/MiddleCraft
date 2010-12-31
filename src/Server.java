import org.middlecraft.server.World;

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


/**
 * @author Rob
 *
 */
public class Server {
	
	public World world;
	private PluginLoader loader=new PluginLoader();
	
	/**
	 * HMod compat
	 * @return
	 */
	public PluginLoader getLoader() { return loader; }
	
	/**
	 * Alias for IWorld.findTopSolidBlock
	 * @param x
	 * @param z
	 * @return
	 */
	public int getHighestBlockY(int x, int z) {
		return world.findTopSolidBlock(x, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public int getBlockIdAt(int x, int y, int z) {
		return world.getBlockIdAt(x, y, z);
	}

	/**
	 * @param x
	 * @param height
	 * @param z
	 * @param type
	 */
	public void setBlockAt(int x, int y, int z, int type) {
		world.setBlockAt(x,y,z,type);
	}

	/**
	 * @param x
	 * @param z
	 * @return
	 */
	@SuppressWarnings("static-access")
	public double getTemperatureValue(int x, int z) {
		world.getBiomeGenerator().genTemperatures(x,z,1,1);
		return world.getBiomeGenerator().temperature[0];
	}
}

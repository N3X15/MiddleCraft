// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class PathEntity {
	// FIELDS
	public int pathLength;
	private PathPoint[] points;
	private int pathIndex;
	
	// METHODS
	
	/**
	 * Directs this path to the next point in its array
	 */
	public abstract void incrementPathIndex();
	
	/**
	 * Gets the position that an entity should be at in the current point in the path
	 */
	public abstract Vec3D getPosition(Entity a);
	
	/**
	 * Returns true if this path has reached the end
	 */
	public abstract boolean isFinished();

}

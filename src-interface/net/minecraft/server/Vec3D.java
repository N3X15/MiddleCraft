// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class Vec3D {
	// FIELDS
	public double xCoord;
	public double yCoord;
	public double zCoord;
	private static java.util.List vectorList;
	private static int nextVector;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract double a(Vec3D a);
	
	/**
	 * 
	 */
	public abstract Vec3D a(Vec3D a, double b);
	
	/**
	 * 
	 */
	public abstract Vec3D b();
	
	/**
	 * 
	 */
	public abstract double b(Vec3D a);
	
	/**
	 * 
	 */
	public abstract Vec3D b(Vec3D a, double b);
	
	/**
	 * 
	 */
	public abstract double c();
	
	/**
	 * 
	 */
	public abstract Vec3D c(double a, double b, double c);
	
	/**
	 * 
	 */
	public abstract Vec3D c(Vec3D a, double b);
	
	/**
	 * 
	 */
	public abstract double d(double a, double b, double c);
	
	/**
	 * 
	 */
	public abstract java.lang.String toString();

}

// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class GuiLogOutputHandler extends java.util.logging.Handler {
	// FIELDS
	 java.util.logging.Formatter field_999;
	private int[] field_998;
	private int field_1001;
	private javax.swing.JTextArea field_1000;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_517_close();
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_518_flush();
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_519_publish(java.util.logging.LogRecord a);

}

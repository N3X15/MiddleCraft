/**
 * 
 */
package org.middlecraft.patcher.reflect;

/**
 * @author Rob
 *
 */
public @interface InsertAfterMethod {
	String _target();
	String afterclass();
	String aftermethod();

}

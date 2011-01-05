/**
 * 
 */
package org.middlecraft.patcher.reflect;

/**
 * @author Rob
 *
 */
public @interface InsertBeforeMethod {

	String target();

	String beforeclass();

	String beforemethod();

}

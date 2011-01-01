/**
 * 
 */
package org.middlecraft.patcher.reflect;

/**
 * Define a parameter's true type (since we don't have the base class available).
 * @author Rob
 *
 */
public @interface SetParamType {
	/**
	 * @return Parameter's unobfuscated type.
	 */
	String value();
}

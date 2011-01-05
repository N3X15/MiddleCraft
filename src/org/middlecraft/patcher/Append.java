/**
 * 
 */
package org.middlecraft.patcher;

/**
 * Append a function's bytecode to the end of the specified function.  
 * <br /><br />
 * The function's signature must be void(Object[] localHeap)
 * @author Rob
 *
 */
public @interface Append {
	String value();
}

/**
 * 
 */
package org.middlecraft.patcher;

import java.util.Comparator;

import javassist.CtField;

/**
 * @author Rob
 *
 */
public class FieldComparator implements Comparator<CtField> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(CtField arg0, CtField arg1) {
		String nameA=arg0.getName();
		String nameB=arg1.getName();
		return nameA.compareTo(nameB);
	}

}

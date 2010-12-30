package org.middlecraft.patcher;

/**
 * A provider of patched and compiled classes for a PatchingClassLoader.
 *
 * IPatchedClassSources are asked to provide a class for a given name when
 * the time comes to actually load the classes in question.  It does not
 * particularly matter how the class is obtained, but it is assumed that
 * the class will be compiled after a brief modification by Javassist.  Note
 * that once a patched class is provided the name is dropped from the
 * map of classes to be generated, as the patched class is cached and re-used
 * from then on out.  Hot swapping classes mid-runtime is not supported by
 * the PatchingClassLoader as it requires very specific steps to be taken,
 * and classes must be designed for it.
 *
 * @author Joshua 'Skrylar' Cearley
 */
public interface IPatchedClassSource {
	/**
	 * Gives a PatchingClassLoader a way to ask for a promised patched class.
	 *
	 * @return A valid class object; ostensibly one compiled by Javassist
	 * and then detached from its original ClassPool.
	 */
	public Class providePatchedClassForName(String name);
}

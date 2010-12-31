package org.middlecraft.patcher.reflect;

import java.lang.annotation.*;

/**
 * Indicates an item should be renamed upon patch compilation.
 *
 * When replacing classes in their entirety it may be desirable to assign
 * readable names to the patch methods, but they must be compiled to their
 * obfuscated forms to be useful at runtime.  This annotation tells the
 * patch compiler to rename a class, method or field so it takes on the
 * obfuscated name while leaving the source code with the more readable
 * names.
 *
 * @author Joshua 'Skrylar' Cearley
 */
@Documented
public @interface PatchRename {
	/** The name the affected class, member or field will take at runtime. */
	String value();
}

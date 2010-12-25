package org.middlecraft.server;

import java.util.*;

/**
 * Pluggable preferences class to allow unified access to plugin data.
 *
 * Datajack abstracts preference reading and writing away from the medium
 * used to store the information, the hierarchy that holds that information,
 * and the medium that archives the data.  This allows for Middlecraft as
 * well as plugins to simply store and retrieve configuration data, without
 * needing to write database or flatfile drivers for every module.
 * <br/><br/>
 * This reduces the work needed to make high quality plugins and increases
 * the overall modularity of the system.
 *
 * @author Joshua 'Skrylar' Cearley
 */
public class Datajack {
	// NOTE: I'm not yet sure how this should handle array properties in a scalable way; the trivial way for things like iConomy is to return an array of values, but this doesn't neccesarily scale to databases with umpteen rows of data. -- Skrylar
	
	public interface Root {
		// === GETTERS ===
		String getString(String _name, String _default);
		int getInteger(String _name, int _default);
		long getLong(String _name, long _default);
		boolean getBoolean(String _name, boolean _default);
		
		// === SETTERS ===
		void setString(String _name, String _value);
		void setInteger(String _name, int _value);
		void setLong(String _name, long _value);
		void setBoolean(String _name, boolean _value);
		
		// === NOTIFICATIONS ===
		void dataJackedIn(Datajack _source, String _name);
		void dataJackedOut(Datajack _source, String _name);
	}
	
	protected Map<String, Root> roots;
	
	/**
	 * Adds a root to the list of global preference roots.
	 *
	 * A given root will be added to the end of the chain of known preference
	 * roots, which means it will be called upon to provide values when
	 * they are requested using any number of the get* APIs.  The name of
	 * a root is not passed along to it, they are stripped from the global
	 * preference path as they are matched.  "economy:coins.name" will be
	 * stripped to "coins.name" and passed to the root, assuming the root
	 * was added with the name "economy".
	 *
	 * @param _name The _name of the root to be added; this is used to locate
	 * the root from a global preference identifier.
	 * @param root A Datajack.Root that will provide requested information
	 * to clients requesting it through a global preference identifier.
	 */
	public void addRoot(String _name, Root root) {
		// TODO: Implement root anchoring function.
	}
	
	/**
	 * Removes a named root from the list of global preference roots.
	 *
	 * A removed root will no longer be called upon to provide information
	 * through a global preference path.
	 *
	 * @param _name The name of a root to be removed.
	 */
	public void removeRoot(String _name) {
		// TODO: Implement root removal (by name) function.
	}
	
	// === GETTERS ===
	public String getString(String _name, String _default) {
		// TODO: Implement string getting method.
		return _default;
	}
	
	public int getInteger(String _name, int _default) {
		// TODO: Implement integer getting method.
		return _default;
	}

	public long getLong(String _name, long _default) {
		// TODO: Implement long getting method.
		return _default;
	}
	
	public boolean getBoolean(String _name, boolean _default) {
		// TODO: Implement boolean getting method.
		return _default;
	}
	
	// === SETTERS ===
	public void setString(String _name, String _value) {
		// TODO: Implement string setting method.
	}
	
	public void setInteger(String _name, int _value) {
		// TODO: Implement integer setting method.
	}
	
	public void setLong(String _name, long _value) {
		// TODO: Implement integer setting method.
	}
	
	public void setBoolean(String _name, boolean _value) {
		// TODO: Implement boolean setting method.
	}
}

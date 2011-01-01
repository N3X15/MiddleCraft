
import org.middlecraft.patcher.reflect.*;
import org.middlecraft.server.*;

import java.io.*;
import java.util.logging.*;

@Patch
public class PatchedPreferences {
	public static Logger l = Logger.getLogger("Minecraft");
	protected static DataJack dj = DataJack.getGlobal();
	
	@Replace
	public PatchedPreferences(File _file) {
		l.info("server.properties diverted through DataJack");
	}
	
	@Replace
	public void regenerateProperties() {saveProperties();}
	
	@Replace
	public void saveProperties() {
		l.info("Pretending to save properties.");
		// XXX: Ask datajack to flush changes to disk
	}
	
	@Replace
	public String getString(String _name, String _default) {
		return dj.getString(_name, _default);
	}
	
	@Replace
	public int getInteger(String _name, int _default) {
		return dj.getInteger(_name, _default);
	}
	
	@Replace
	public boolean getBoolean(String _name, boolean _default) {
		return dj.getBoolean(_name, _default);
	}
}

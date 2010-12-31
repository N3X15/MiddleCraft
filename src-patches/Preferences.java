
import org.middlecraft.patcher.reflect.PatchRename;
import org.middlecraft.server.*;

import java.io.*;
import java.lang.annotation.*;
import java.util.logging.*;

@PatchRename("dt")
public class Preferences {
	public static Logger l = Logger.getLogger("Minecraft");
	protected static DataJack dj = DataJack.getGlobal();
	
	@PatchRename("dt")
	public Preferences(File _file) {
		l.info("server.properties diverted through DataJack");
	}
	
	@PatchRename("a")
	public void regenerateProperties() {saveProperties();}
	
	@PatchRename("b")
	public void saveProperties() {
		l.info("Pretending to save properties.");
		// XXX: Ask datajack to flush changes to disk
	}
	
	@PatchRename("a")
	public String getString(String _name, String _default) {
		return dj.getString(_name, _default);
	}
	
	@PatchRename("a")
	public int getInteger(String _name, int _default) {
		return dj.getInteger(_name, _default);
	}
	
	@PatchRename("a")
	public boolean getBoolean(String _name, boolean _default) {
		return dj.getBoolean(_name, _default);
	}
}

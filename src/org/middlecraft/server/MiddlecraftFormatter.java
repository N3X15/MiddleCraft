package org.middlecraft.server;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Formats output so that it is similar to Minecraft's own logging format.
 * @author Rob
 *
 */
public class MiddlecraftFormatter extends Formatter {
	private SimpleDateFormat fmt;
	public MiddlecraftFormatter()
	{
		fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public String format(LogRecord logrecord)
	{
		String timestamp = fmt.format(Long.valueOf(logrecord.getMillis()));
		String lvl = logrecord.getLevel().getName();

		String logmsg = String.format("%s [%s] - %s\n", timestamp,lvl,logrecord.getMessage());
		Throwable except = logrecord.getThrown();
		if(except != null)
		{
			StringWriter stringwriter = new StringWriter();
			except.printStackTrace(new PrintWriter(stringwriter));
			logmsg += stringwriter.toString();
		}
		return logmsg;
	}
}

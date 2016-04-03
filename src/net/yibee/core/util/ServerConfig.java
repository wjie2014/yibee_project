package net.yibee.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServerConfig {

	private static final Properties pro = new Properties();
	static {
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("config.properties");
		try {
			pro.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String getValue(String key) {
		return pro.getProperty(key);
	}
}

package com.xmhx.cnlife.buzz.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;


public class PropertiesUtils {
	
	private static final Logger LOGGER = Logger.getLogger(PropertiesUtils.class);
	
	/**
	 * 加载资源文件
	 * @param propName
	 * @return
	 * @throws IOException
	 */
	public static Properties loadProperties(String propName) throws IOException {
		if (TextUtils.notEmpty(propName)) {
			InputStream inStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(propName);
			Properties prop = new Properties();
			prop.load(inStream);
			inStream.close();
			return prop;
		}
		return null;
	}
	
	/**
	 * Config配置
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> mapConfigAll() {
		try {
			Properties prop = loadProperties("config.properties");
			if (prop != null) {
				Map<String, String> map = new HashMap<String, String>();
				Enumeration e = prop.keys() ;
			    while(e.hasMoreElements()) {
			    	String name = (String)(e.nextElement()) ;
			    	map.put(name, prop.getProperty(name)) ;
			    }
			    return map;
			}
		} catch (IOException e) {
			LOGGER.error("PropertiesUtil mapConfigAll IOException " + e);
		}
		return null;
	}

}

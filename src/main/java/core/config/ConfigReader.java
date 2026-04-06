package core.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties prop;
	
	static {
        prop = new Properties();
        try {
            InputStream is = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config/qa.properties");

            prop.load(is);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    	public static String get(String key) {
        return prop.getProperty(key);
    }

}

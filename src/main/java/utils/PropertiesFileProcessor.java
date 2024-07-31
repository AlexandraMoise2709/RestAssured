package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileProcessor {
	

	public static String readPropertiesFile(String key, String file) {
		
		try(FileInputStream input =  new FileInputStream(file)){
			
			Properties propFile =  new Properties();
			propFile.load(input);
			
		return propFile.getProperty(key);
			
			
		}catch(IOException e) {
			System.out.println("Nu am putut citi fisierul");
			e.printStackTrace();
		}
		
		return null;
	}
		
		
		
	}


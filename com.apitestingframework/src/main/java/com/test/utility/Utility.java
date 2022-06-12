package com.test.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


import com.test.constants.Sourcepaths;

public class Utility {
	
	
	public static String getpropertyvalue(String key) throws IOException
	{
		Properties prop =new Properties();
		String path= Sourcepaths.PROPERTIES_PATH;
		FileInputStream fr= new FileInputStream(path);
		 try {
			prop.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 String value=prop.getProperty(key); //get values from  properties files
		 System.out.println("value from the file"+value);
		
			fr.close();
		
		
		 return value;
		
	}

}

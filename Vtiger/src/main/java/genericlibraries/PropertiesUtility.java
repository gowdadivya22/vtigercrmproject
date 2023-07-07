package genericlibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility implements IAutoConstants
{


    
	public  String readDataFromPropertiesFile(String key)
	
	
	{
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(PROPERTIESPATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties ppt = new Properties();
		try {
			ppt.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ppt.getProperty(key);
	
		
	}

}

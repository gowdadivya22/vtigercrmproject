package genericlibraries;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotUtility extends BaseClass implements IAutoConstants

{
	public static String takingScreenshot(String screenshotname) 
	 {
		 TakesScreenshot ts = (TakesScreenshot) driver; 
		 File src=ts.getScreenshotAs(OutputType.FILE);
		 String time = LocalDateTime.now().toString().replace(":","-");
		String pathh= System.getProperty("user.dir")+SSPATH+screenshotname+" "+time+".png";
		 File dest = new File(pathh);
			try {
				FileUtils.copyFile(src, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return pathh ;
		 
	 }

}

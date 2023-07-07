package TestCasesNew;
// creating new organization
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import genericlibraries.BaseClass;
import genericlibraries.ListenerImplementation;
@Listeners(genericlibraries.ListenerImplementation.class)
public class TC_01_Test extends BaseClass
{
	@Test
	public void newOrganizationCreation() throws InterruptedException
	{
		
		
		home.getOrganizationLink().click();
		ListenerImplementation.logger.log(Status.INFO, "clicked on organization link");
		createorg.getOrganizationCreationIcon().click();
		ListenerImplementation.logger.log(Status.INFO, " Clicked on organization creation icon");
		createorg.getOrganizationTF().sendKeys(ppt.readDataFromPropertiesFile("orgname")+jutil.generateRandomNum(200));
		ListenerImplementation.logger.log(Status.INFO, " organization name is entered");
		createorg.getSaveButton().click();
		
		Thread.sleep(3000);
		if(createorg.getPageHeader().isDisplayed())
		{
			ListenerImplementation.logger.log(Status.PASS, " New organization name is displayed");
		}
		else
		{
			ListenerImplementation.logger.log(Status.FAIL, " New organization name is not displayed");
		}
			
	
		Thread.sleep(2000);
		
	}

}

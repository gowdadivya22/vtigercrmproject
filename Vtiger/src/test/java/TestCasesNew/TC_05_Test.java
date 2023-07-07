package TestCasesNew;

import java.util.List;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import genericlibraries.BaseClass;
import genericlibraries.ListenerImplementation;

@Listeners(genericlibraries.ListenerImplementation.class)
public class TC_05_Test extends BaseClass
{
	@Test
	public void newLeadCreation () 
	{
		home.getLeadsLink().click();
		ListenerImplementation.logger.log(Status.INFO, " Leads link is clicked");
		leads.getLeadscreationIcon().click();
		ListenerImplementation.logger.log(Status.INFO, " Leads Creation Icon is clicked");
		leads.getLastNameTf().sendKeys(ppt.readDataFromPropertiesFile("lastname")+jutil.generateRandomNum(100));
		ListenerImplementation.logger.log(Status.INFO, " Last name data is entered into last name text field");
		leads.getCompanyTf().sendKeys(ppt.readDataFromPropertiesFile("company")+jutil.generateRandomNum(100));
		ListenerImplementation.logger.log(Status.INFO, " company name is entered into company text field");
		leads.getSaveButton().click();
		if(leads.getFinalConfirmation().isDisplayed())
		{
			ListenerImplementation.logger.log(Status.PASS," New lead is created");
		}
		else
		{
			ListenerImplementation.logger.log(Status.FAIL, " New lead is not created");
		}
		
		
	}

}

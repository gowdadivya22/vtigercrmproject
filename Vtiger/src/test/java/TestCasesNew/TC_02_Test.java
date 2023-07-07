package TestCasesNew;
// creating new organization with Industry and Type
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import genericlibraries.BaseClass;
import genericlibraries.ListenerImplementation;
@Listeners(genericlibraries.ListenerImplementation.class)
public class TC_02_Test extends BaseClass
{
	@Test
	public void newOrganizationCreationwithIndustryAndType()
	{
		home.getOrganizationLink().click();
		ListenerImplementation.logger.log(Status.INFO, " Organization link is clicked");
		createorg.getOrganizationCreationIcon().click();
		ListenerImplementation.logger.log(Status.INFO, " Organization creation Icon is clicked");
		createorg.getOrganizationTF().sendKeys(ppt.readDataFromPropertiesFile("orgname")+jutil.generateRandomNum(100));
		ListenerImplementation.logger.log(Status.INFO, " Organization name is entered into text field");
		utility.handlingDropdown(createorg.getIndustryDropDown(), ppt.readDataFromPropertiesFile("value1"));
		ListenerImplementation.logger.log(Status.INFO, " Industry is selected from dropdown");
		utility.handlingDropdown(createorg.getTypeDropDown(), ppt.readDataFromPropertiesFile("value2"));
		ListenerImplementation.logger.log(Status.INFO, " Type is selected from the dropdown");
	createorg.getGroupRadioButton().click();
	ListenerImplementation.logger.log(Status.INFO, " Group is selected");
	utility.handlingDropdown(createorg.getAssignedDropDown(), "3");
	ListenerImplementation.logger.log(Status.INFO, " Under group dropdown  data is selected");
	
		createorg.getSaveButton().click();
		if(createorg.getPageHeader().isDisplayed())
		{
			ListenerImplementation.logger.log(Status.PASS, " New organization name is displayed");
		}
		else
		{
			ListenerImplementation.logger.log(Status.FAIL, " New organization name is not displayed");
		}
			
	
		
	}

}

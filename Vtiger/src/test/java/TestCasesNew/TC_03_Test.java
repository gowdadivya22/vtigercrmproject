package TestCasesNew;
// creating contacts with mandatory fields
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import genericlibraries.BaseClass;
import genericlibraries.ListenerImplementation;
@Listeners(genericlibraries.ListenerImplementation.class)
public class TC_03_Test extends BaseClass
{
	@Test
	public void newContactCreation()
	{
		home.getContactsLink().click();
		ListenerImplementation.logger.log(Status.INFO, " Contacts link is clicked");
		contact.getCreatingContactPlusIcon().click();
		ListenerImplementation.logger.log(Status.INFO, " New contact creation icon is clicked");
		contact.getLastNameTf().sendKeys(ppt.readDataFromPropertiesFile("lastname")+jutil.generateRandomNum(100));
		ListenerImplementation.logger.log(Status.INFO, " Lastname is enetered into last name text field");
		contact.getSaveButton().click();
		if(contact.getFinalConfirmation().isDisplayed())
		{
			ListenerImplementation.logger.log(Status.PASS, " New contact is created ");
		}
		else
		{
			ListenerImplementation.logger.log(Status.FAIL, " New contact is not created");
		}
		
	}

}

package TestCasesNew;
// creating contacts with existing organization name
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import genericlibraries.BaseClass;
import genericlibraries.ListenerImplementation;
@Listeners(genericlibraries.ListenerImplementation.class)
public class TC_04_Test extends BaseClass
{
	@Test
	public void newContactCreationWithExistingOrganization () throws InterruptedException
	{
		home.getContactsLink().click();
		ListenerImplementation.logger.log(Status.INFO, " conatact link is clicked");
		contact.getCreatingContactPlusIcon().click();
		ListenerImplementation.logger.log(Status.INFO, " Contact creation icon is clicked");
		contact.getLastNameTf().sendKeys(ppt.readDataFromPropertiesFile("lastname")+jutil.generateRandomNum(100));
		ListenerImplementation.logger.log(Status.INFO, " Last name data is entered into last name text field");
		String parentId = driver.getWindowHandle();
		ListenerImplementation.logger.log(Status.INFO, " Control is changed to child browser");
		contact.getExistingOrganizationSelectionIcon().click();
		ListenerImplementation.logger.log(Status.INFO, " Existing organization selection icon is clicked");
		utility.switchingtargetPage(driver,driver.getWindowHandles(), parentId);
		Thread.sleep(2000);
		ListenerImplementation.logger.log(Status.INFO, " control is shifted to child browser ");
		contact.getExixtingOrganizationName().click();
		ListenerImplementation.logger.log(Status.INFO, " Existing organization name is slected");
		Thread.sleep(2000);
		utility.switchingBackToMain(driver,parentId);
ListenerImplementation.logger.log(Status.INFO, " Control is switched to main page");
		createorg.getSaveButton().click();
		if(contact.getFinalConfirmation().isDisplayed())
		{
			ListenerImplementation.logger.log(Status.PASS, " New Contact is created");
		}
		else
		{
			ListenerImplementation.logger.log(Status.FAIL, " New contact is not craeted");
		}
	}
}

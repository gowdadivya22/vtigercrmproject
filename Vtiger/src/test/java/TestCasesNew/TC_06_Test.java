package TestCasesNew;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import genericlibraries.BaseClass;
import genericlibraries.ListenerImplementation;
@Listeners(genericlibraries.ListenerImplementation.class)
public class TC_06_Test extends BaseClass
{
	// creating leads , duplicating and deleting
	@Test
	public void newLeadCreationAndDuplicationAndDeletion () throws InterruptedException 
	{
		home.getLeadsLink().click();
		ListenerImplementation.logger.log(Status.INFO, " Leads link is clicked");
		leads.getLeadscreationIcon().click();
		ListenerImplementation.logger.log(Status.INFO, " Leads Creation Icon is clicked");
		leads.getFirstnNameTf().sendKeys(ppt.readDataFromPropertiesFile("firstname"));
		ListenerImplementation.logger.log(Status.INFO, " First name  data is entered into First name text field");
		leads.getLastNameTf().sendKeys(ppt.readDataFromPropertiesFile("lastname"));
		ListenerImplementation.logger.log(Status.INFO, " last name data is entered into last name text field");
		leads.getCompanyTf().sendKeys(ppt.readDataFromPropertiesFile("company"));
		ListenerImplementation.logger.log(Status.INFO, " Company data is entered into company text field");
		leads.getSaveButton().click();
		if(leads.getFinalConfirmation().isDisplayed())
		{
			ListenerImplementation.logger.log(Status.PASS," New lead is created");
		}
		else
		{
			ListenerImplementation.logger.log(Status.FAIL, " New lead is not created");
		}
		
		leads.getDuplicateLeadbutton().click();
		
		
		Thread.sleep(4000);
		leads.getSaveButton().click();
		
		if(leads.getDeleteLeadButtoninLeadsInformationPage().isDisplayed())
		{
			ListenerImplementation.logger.log(Status.PASS, " Lead is duplicated");
		}
		else
		{
			ListenerImplementation.logger.log(Status.FAIL, " Lead is not duplicated");
		}
		Thread.sleep(4000);
		
		
		leads.getDeleteLeadButtoninLeadsInformationPage().click();
		ListenerImplementation.logger.log(Status.INFO, " Delete button is clicked");
		Thread.sleep(4000);
		driver.switchTo().alert().accept();
		ListenerImplementation.logger.log(Status.INFO, " Alert pop up is handled and clicked on OK");
		Thread.sleep(4000);
		WebElement checkBox = driver.findElement(By.xpath("//a[contains(text(),'"+ppt.readDataFromPropertiesFile("firstname")+"')]/../..//input[@name=\"selected_id\"]"));
		checkBox.click();
		ListenerImplementation.logger.log(Status.INFO, " check box of respective lead is selected");


		Thread.sleep(7000);
		
		leads.getDeleteLeadsCheckBox().click();
		ListenerImplementation.logger.log(Status.INFO, " delete button is clicked in leads page");
		
		driver.switchTo().alert().accept();
		ListenerImplementation.logger.log(Status.INFO, " alert popup is handled and clicked OK");
	Thread.sleep(7000);
		 
		
		
		
		
	}

}

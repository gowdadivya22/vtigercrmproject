package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	@FindBy(linkText = "Organizations")
	private WebElement organizationLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;
	
	@FindBy(linkText = "Leads")
	private WebElement leadsLink;
	
@FindBy(xpath="//img[@src=\"themes/softed/images/user.PNG\"]")
private WebElement administrationIcon;

@FindBy(xpath="//a[text()='Sign Out']")
private WebElement signoutButton;


public HomePage(WebDriver driver) 
{
	PageFactory.initElements(driver, this);
	
}


public WebElement getOrganizationLink() {
	return organizationLink;
}


public WebElement getContactsLink() {
	return contactsLink;
}


public WebElement getLeadsLink() {
	return leadsLink;
}


public WebElement getAdministrationIcon() {
	return administrationIcon;
}


public WebElement getSignoutButton() {
	return signoutButton;
}



}

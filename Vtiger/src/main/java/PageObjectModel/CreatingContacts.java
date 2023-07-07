package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingContacts 
{
	@FindBy(xpath = "//img[@alt=\"Create Contact...\"]")
	private WebElement creatingContactPlusIcon;
	
	@FindBy(name="lastname")
	private WebElement lastNameTf;
	
	@FindBy(name="account_name")
	private WebElement organizationNameTf;
	
	@FindBy(xpath="//input[normalize-space(@value)='Save']")
	private WebElement saveButton;
	
	@FindBy(xpath="//img[@src=\"themes/softed/images/select.gif\" and contains(@onclick,'return')]")
	private WebElement existingOrganizationSelectionIcon;
	
	@FindBy(xpath="//a[text()='TCS']")
	private WebElement exixtingOrganizationName;

	@FindBy(xpath = "//span[@class=\"dvHeaderText\"]")
	private WebElement finalConfirmation;
	
	
	

	

	public WebElement getFinalConfirmation() {
		return finalConfirmation;
	}
	public WebElement getExistingOrganizationSelectionIcon() {
		return existingOrganizationSelectionIcon;
	}
	public CreatingContacts(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		
		
	}
	public WebElement getExixtingOrganizationName() {
		return exixtingOrganizationName;
	}

	public WebElement getCreatingContactPlusIcon() {
		return creatingContactPlusIcon;
	}

	public WebElement getLastNameTf() {
		return lastNameTf;
	}

	public WebElement getOrganizationNameTf() {
		return organizationNameTf;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
	
	

}

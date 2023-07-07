package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingOrganization 
{
	@FindBy(xpath = "//img[@alt=\"Create Organization...\"]")
	private WebElement organizationCreationIcon;
	
	@FindBy(name="accountname")
	private WebElement organizationTF;
	
	@FindBy(xpath="//span[@class=\"dvHeaderText\"]")
	private WebElement pageHeader;
	
	public WebElement getPageHeader() {
		return pageHeader;
	}
	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}
	public WebElement getTypeDropDown() {
		return typeDropDown;
	}
	
	public WebElement getGroupRadioButton() {
		return groupRadioButton;
	}
	public WebElement getUserRadioButton() {
		return userRadioButton;
	}
	@FindBy(xpath="//input[normalize-space(@value)='Save']")
	private WebElement saveButton;
	
	@FindBy(name="industry")
	private WebElement industryDropDown;
	@FindBy(name="accounttype")
	private WebElement typeDropDown;
	@FindBy(xpath="//input[@value='T']")
	private WebElement groupRadioButton;
	@FindBy(xpath="//input[@value='U']")
	private WebElement userRadioButton;
	@FindBy(name="assigned_group_id")
	private WebElement assignedDropDown;
	
	public WebElement getAssignedDropDown() {
		return assignedDropDown;
	}
	public CreatingOrganization(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		
	}
	public WebElement getOrganizationCreationIcon() {
		return organizationCreationIcon;
	}
	public WebElement getOrganizationTF() {
		return organizationTF;
	}
	public WebElement getSaveButton() {
		return saveButton;
	}

}

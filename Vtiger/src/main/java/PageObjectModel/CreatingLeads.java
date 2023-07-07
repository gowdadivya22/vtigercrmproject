package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingLeads 
{@FindBy(xpath="//img[@alt=\"Create Lead...\"]")
private WebElement leadscreationIcon;

@FindBy(name="lastname")
private WebElement lastNameTf;

public CreatingLeads(WebDriver driver) 
{
	PageFactory.initElements(driver, this);
	// TODO Auto-generated constructor stub
}

public WebElement getLeadscreationIcon() {
	return leadscreationIcon;
}





public WebElement getLastNameTf() {
	return lastNameTf;
}





public WebElement getFirstnNameTf() {
	return firstnNameTf;
}





public WebElement getFinalConfirmation() {
	return finalConfirmation;
}





public WebElement getDuplicationLeadPageInfoHeader() {
	return duplicationLeadPageInfoHeader;
}





public WebElement getCompanyTf() {
	return companyTf;
}





public WebElement getDuplicateLeadbutton() {
	return duplicateLeadbutton;
}



public WebElement getDeleteLeadButtoninLeadsInformationPage() {
	return deleteLeadButtoninLeadsInformationPage;
}





public WebElement getSaveButton() {
	return saveButton;
}

@FindBy(name="firstname")
private WebElement firstnNameTf;
@FindBy(xpath=".//span[@class=\"dvHeaderText\"]")
private WebElement finalConfirmation;

@FindBy(xpath = "//span[@class=\"lvtHeaderText\"]")
private WebElement duplicationLeadPageInfoHeader;


@FindBy(name="company")
private WebElement companyTf;

@FindBy(xpath="//input[@value='Duplicate']")
private WebElement duplicateLeadbutton;

@FindBy(name="Delete")
private WebElement deleteLeadButtoninLeadsInformationPage;


@FindBy(xpath = "//input[@value=\"Delete\"]")
private WebElement deleteLeadsCheckBox;


public WebElement getDeleteLeadsCheckBox() {
	return deleteLeadsCheckBox;
}

@FindBy(xpath="//input[normalize-space(@value)='Save']")
private WebElement saveButton;






}

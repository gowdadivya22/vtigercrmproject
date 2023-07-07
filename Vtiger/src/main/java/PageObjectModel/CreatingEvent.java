package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingEvent 
{
	@FindBy(id = "qccombo")
	private WebElement quickCreateDropdown;

@FindBy(name = "subject")
private WebElement SubjectTextfield;

@FindBy(name = "date_start")
private WebElement datestartTF;

@FindBy(name = "due_date")
private WebElement EndDateTF;

@FindBy(xpath = "//input[@title='Save [Alt+S]']")
private WebElement savebutton;



public CreatingEvent(WebDriver driver) 
{
	PageFactory.initElements(driver, this);
	
}

public WebElement getQuickCreateDropdown() {
	return quickCreateDropdown;
}

public WebElement getSubjectTextfield() {
	return SubjectTextfield;
}

public WebElement getDatestartTF() {
	return datestartTF;
}

public WebElement getEndDateTF() {
	return EndDateTF;
}

public WebElement getSavebutton() {
	return savebutton;
}
}
	



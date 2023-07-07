package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericlibraries.BaseClass;

public class LogInPage 
{
	@FindBy(name="user_name")
	private WebElement userNameTF;
	
	@FindBy(name="user_password")
	private WebElement passwordTF;
	
	@FindBy(id="submitButton")
	private WebElement loginButton;
	
	public LogInPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		
	}

	public WebElement getUserNameTF()
	{
		return userNameTF;
	}

	public WebElement getPasswordTF() 
	{
		return passwordTF;
	}

	public WebElement getLoginButton() 
	{
		return loginButton;
	}
	
	

}

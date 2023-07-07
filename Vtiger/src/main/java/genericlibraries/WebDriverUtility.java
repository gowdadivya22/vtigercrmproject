package genericlibraries;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * This class contains all the reusable methods of WebDriver
 * @author MUKESH GOWDA
 *
 */

public class WebDriverUtility  implements IAutoConstants
{
	public WebDriver driver;
	public PropertiesUtility ppt = new PropertiesUtility();
	
	
	
	public void explicitWait(WebElement element)
	{
		
		
		WebDriverWait wait= new WebDriverWait(driver, Long.parseLong(ppt.readDataFromPropertiesFile("time")));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public void explicitWait( String title)
	{
		
		
		WebDriverWait wait= new WebDriverWait(driver, Long.parseLong(ppt.readDataFromPropertiesFile("time")));
		wait.until(ExpectedConditions.titleContains(title));
	}
	
	
	 public void dragAndDrop(WebElement src, WebElement dest)
	 {
		 Actions action= new Actions(driver);
		 action.dragAndDrop(src, dest).perform();
	 }
	 /**
	  * This method is used for right click
	  */
	 public void rightClick()
	 {
		 Actions action= new Actions(driver);
		 action.contextClick().perform();
	 }
	 /**
	  * This method is used to right click by using element reference
	  * @param element
	  */
	 public void rightClick(WebElement element) 
	 {
		 Actions action= new Actions(driver);
		 action.contextClick(element).perform();;
	 }
	 /**
	  * This method is used to double click
	  */
	 public void doubleClick()
	 {
		 Actions action= new Actions(driver);
		 action.doubleClick().perform();;
	 }
	 /**
	  * This method is used to mouse hover to element
	  * @param element
	  */
	 public void mouseOver(WebElement element)
	 {Actions action= new Actions(driver);
		 action.moveToElement(element).perform();
	 }
	 /**
	  * This method is used to handle dropdown using index
	  * @param dropdown
	  * @param index
	  */
	
	 public void handlingDropdown(WebElement dropdown, int index)
	 {
		 Select select=new Select(dropdown);
		 select.selectByIndex(index);
	 }
	 /**
	  * This method is used to handle dropdown by using value
	  * @param dropdown
	  * @param value
	  */
	 public void handlingDropdown(WebElement dropdown, String value)
	 {
		 Select select = new Select(dropdown);
		 select.selectByValue(value);
		 
	 }
	 /**
	  * This method is used to scroll down by using element location
	 * @param x 
	  * @param x
	 * @param y 
	  * @param y
	  */
	 
	 public void scrollingActionByHardCoded(int x, int y)
	 {
		 JavascriptExecutor js= (JavascriptExecutor )driver;
		 js.executeScript("Window.scrollBy("+x+","+y+")");
		 
	 }
	 /**
	  * This method is used to scroll down by clicking on element
	  * @param data
	  * @param element
	  */
	 public void clickingOnElement(String data, Object element) {
		 JavascriptExecutor js= (JavascriptExecutor )driver;
		 js.executeScript("arguments[0].value=' "+data+"'",element);
		 
	 }
	 /**
	  * This method is used to clear data from the textfield
	  * @param element
	  */
	 public void clearingDataFromElement(WebElement element)
	 {
		 JavascriptExecutor js= (JavascriptExecutor )driver;
		 js.executeScript("arguments[0].value=' ' ",element);
		 
	 }
	 /**
	  * This method is used to switch control to frame by using index
	  * @param index
	  */
	 public void switchingToFrame(int index)
	 {
		 driver.switchTo().frame(index);
	 }
	 /**
	  * This method is used to switch the control to frame by using name / id
	  * @param data
	  */
	 public void switchingToFrame(String data)
	 {
		 driver.switchTo().frame(data);
	 }
	 /**
	  * This method is used to switch to frame by using element
	  * @param element
	  */
	 public void switchingToFrame(WebElement element) {
		 driver.switchTo().frame(element);
	 }
	 /**
	  * This method is used to default content
	 * @param parentId 
	  * 
	  */
	 public void switchingBackToMain(WebDriver driver, String parentId)
	 {
		 driver.switchTo().window(parentId);
		 
	 }
	 public void switchingBackToMain(WebDriver driver)
	 {
		 driver.switchTo().defaultContent();
		 
	 }
	 
	 
	 /**
	  * This method is used to 
	  * @param longtime
	  * @return
	  */
	 public WebDriverWait explicitWaitingStatement(long longtime)
	 {
		 WebDriverWait wait = new WebDriverWait(driver,longtime);
		 return wait;
	 }
	 /**
	  * This method is used to handle alert popup
	 * @param element 
	  * @return
	  */
	 
	 public void enteringDataIntoElement(WebDriver driver,String data, WebElement element) {
			JavascriptExecutor js =(JavascriptExecutor) driver;
			js.executeScript("arguments[0].value='"+data+"'", element);
		}
     
	 
	 
	 public Alert switchToAlert()
	 {
		 
		 Alert alert = driver.switchTo().alert();
		 return alert;
	 }
	 /**
	  * This method is used to handle child browser popup 
	 * @param  
	 * @param set 
	 * @param organizationname 
	  * @param allWindowId
	  * @param targetWindowTitle
	  */
	 public void switchToWindow(Set<String> allWindowId, String targetWindowTitle) {
			for(String id:allWindowId) {
				driver.switchTo().window(id);
				if(targetWindowTitle.equalsIgnoreCase(driver.getTitle())) {
					break;
				}
				else 
				{
					driver.close();
				}
			}
	 }

	 public void switchingtargetPage(WebDriver driver,Set<String> allWindowId, String parentId) {
			allWindowId.remove(parentId);
			for(String id:allWindowId) {
				driver.switchTo().window(id);
			}
		}

	  
	 }
	 
	 
	 
		 
	 


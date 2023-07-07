package TestCasesNew;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import genericlibraries.BaseClass;
import genericlibraries.ListenerImplementation;
@Listeners(genericlibraries.ListenerImplementation.class)
public class TC_07_Test extends BaseClass
{

		@Test
		public void ceateEvenetTestcase() throws InterruptedException {
				//pass
				event.getQuickCreateDropdown().click();
			ListenerImplementation.logger.log(Status.INFO, " Quick create dropdown is selected");
				
				utility.handlingDropdown(event.getQuickCreateDropdown() ,"Events");
				ListenerImplementation.logger.log(Status.INFO, " Events is selected");
				
				event.getSubjectTextfield().sendKeys("demo");
				ListenerImplementation.logger.log(Status.INFO, " Demo is entered into subject text field");
				
				utility.enteringDataIntoElement(driver,"2023-07-28", event.getDatestartTF());
				ListenerImplementation.logger.log(Status.INFO, " Start date is entered into date text fied");
				
				Thread.sleep(2000);
				
				//homepage.getEndDateTF().sendKeys("2023-07-27");
				
				utility.enteringDataIntoElement(driver,"2023-07-30", event.getEndDateTF());

				Thread.sleep(2000);

				event.getSavebutton().click();
		}
		

		 
		
		
		
	}



package genericlibraries;

import static org.testng.Assert.assertEquals;

import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(genericlibraries.ListenerImplementation.class)
public class ListenerWorkingClass extends BaseClass
{ 
	@Test
	public void validatePassTest()
	{
		System.out.println("This is from passed testcase");
	}
	
	@Test
	public void validateFailedTest()
	{
		System.out.println("This is from failed testcase");
		assertEquals(false,true); 
		
		
	}
	
	@Test
	public void validateSkippedTest()
	{
		System.out.println("This is from Skipped testcase");
		throw new SkipException("This is skipped");
	}



}

package genericlibraries;

import java.util.Random;

public class JavaUtility 
{
	public int generateRandomNum(int range)
	{
		Random random = new Random();
		return random.nextInt(range);
		
	}
	

}

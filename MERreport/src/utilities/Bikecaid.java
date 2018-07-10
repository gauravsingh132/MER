package utilities;

import org.openqa.selenium.By;

public class Bikecaid {
	
	public static String customeractivityid(String risk)
	{
		String CAID = null;
		risk.replaceAll("\":\"", "=");
		String arr[]=risk.split("\",\"");
		for(String s: arr)
		{
			if(s.contains("conversion_quote_id"))
			{
				String s1[]=s.split("\":\"");
				CAID=s1[1];
				break;
			}
		}
		return CAID;
		
	}

}

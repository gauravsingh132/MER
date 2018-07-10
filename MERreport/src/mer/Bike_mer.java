package mer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import basepackage.Base;
import utilities.Bikecaid;
import utilities.Bikeutility;
import utilities.xlsdata;

public class Bike_mer extends Base {
	
	static int k=1;
	@Test
	public void test_mer() throws Exception
	{
		
		driver.get(rb.getString("bike_url_1"));
		String risk=driver.findElement(By.id("TagCommander")).getAttribute("value");
		String CAID=Bikecaid.customeractivityid(risk);
		System.out.println(CAID);
		WebDriverWait waiting=new WebDriverWait(driver, 3);
		waiting.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("divCoverQuotingResultAll"))));

		List <WebElement> element=driver.findElements(By.id("divCoverQuotingResultAll"));
		System.out.println("divCoverQuotingResultAll "+ element);
		int size=element.size();
		System.out.println("size OF divCoverQuotingResultAll "+ size);

		// for first portion
		for(int i=1;i<=size;i++)
		{
			
			List <WebElement> first_quote_row_noofprices=driver.findElements(By.xpath("//div[@id='divQuotingResults']//div["+i*2+"]//div[@class='promoHead']/div[@style='position:relative']"));
			System.out.println("no  of prices on first row="+ first_quote_row_noofprices.size());
			
			for(int j=1;j<=first_quote_row_noofprices.size();j++)  ////div[@id='divQuotingResults']//div[2]//div[@class='promoHead']
			{
				WebElement priceline_insurer=driver.findElement(By.xpath("//div[@id='divQuotingResults']//div["+i*2+"]//div[@rowindex='"+j+"']"));
				String insurer=priceline_insurer.getAttribute("insurer");
				String Price=priceline_insurer.getAttribute("rank");
				System.out.println(insurer+"  "+Price);

				// open expand text
				//int a= Integer.parseInt("");
				String expand=Bikeutility.expand_opened(priceline_insurer,j,driver);
			
				//pdf link
				String pdf_url=Bikeutility.pdf_link(j, driver);
				System.out.println(pdf_url);
								
				String[] phonemer=Bikeutility.phonemerclick(i,j,driver);
				System.out.println("phone mer button visible"+ phonemer[0]);
				System.out.println("phone mer typ visible"+ phonemer[1]);
				driver.findElement(By.className("link-back")).click();

				String[] emailmer=Bikeutility.emailmerclick(i,j,driver);
				System.out.println("email mer button visible"+ emailmer[0]);
				System.out.println("email mer TYP visible"+ emailmer[1]);
		
				 
				ArrayList<String> arr1= new ArrayList<String>();
				arr1.add(insurer);
				arr1.add(CAID);
				arr1.add(Price);
				arr1.add(expand);
				arr1.add(pdf_url);
				arr1.add(phonemer[0]);
				arr1.add(phonemer[1]);
				arr1.add(emailmer[0]);
				arr1.add(emailmer[1]);

				xlsdata.data(arr1,k);
				k=k+1;
			}
			Thread.sleep(3000);
		}
		System.out.println("completed");

	}
}

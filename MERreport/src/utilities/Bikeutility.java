package utilities;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Bikeutility {

	static WebDriverWait wait ;
	static String pdf_link=null;
	public static String expand_opened(WebElement element,int rank,WebDriver driver) {
		
		String result="false";
		element.findElement(By.xpath("//div[@rowindex='"+rank+"']//a[contains(text(),'Voir plus de détails')]")).click();
		//driver.findElement(By.xpath("//div[@rowindex='"+rank+"']//a[contains(text(),'Voir plus de détails')]")).click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String Expand_text=element.findElement(By.xpath("//div[@rowindex='"+rank+"']//p[contains(text(),'Qui est')]")).getText();
		//String Expand_text=driver.findElement(By.className("blue_title")).getText();
		System.out.println(Expand_text);

		if(Expand_text.contains("Qui est"))
		{
			System.out.println("expand text opened");
			result="true";
			
		}
		return result;		
	}
	
	public static  String pdf_link(int rank,WebDriver driver) throws Exception
	{
		String current_window=driver.getWindowHandle();
		wait= new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@rowindex='"+rank+"']//a[text()='Voir les conditions générales']"))));
		} catch (Exception e1) {
		}
		try {
			driver.findElement(By.xpath("//div[@rowindex='" + rank + "']//a[text()='Voir les conditions générales']"))
					.click();
		} catch (Exception e) {
		}
		Set<String> newwindows=driver.getWindowHandles();
		for(String win: newwindows)
		{
			driver.switchTo().window(win);
			if (driver.getTitle().contains("Comparatif"))
			{
				String basewin=win;
			}
			else
			{
				Thread.sleep(4000);

				  pdf_link=driver.getCurrentUrl();
				  driver.close();
			}
		}
		//Comparatif
	//	 pdf_link=driver.getCurrentUrl();
		driver.switchTo().window(current_window);
		try {
			driver.findElement(By.xpath("//a[contains(text(),'Voir moins de détails')]")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}

		return pdf_link;
	}
	
	public static String MER_data(WebDriver driver) throws Exception
	{
		String typ_pass=null;
		Thread.sleep(4000);
		String pass=driver.findElement(By.xpath("//h2[@class='title']")).getText();
		if(pass.contains("est prêt à vous assurer"))
		{
			typ_pass="true";
		}
		else
		{
			typ_pass="false";
		}
		return typ_pass;
	}
	
	public static  String[] phonemerclick(int i,int rank,WebDriver driver) throws Exception
	{					String mer_arr[]=new String[2];
		if(driver.findElement(By.xpath("//div[@id='divQuotingResults']//div["+i*2+"]//div[@class='promoHead']//div[@rowindex='"+rank+"']//div[@class='insurer_buttons']//a[@tagcommandermertypename='Phone']")).isDisplayed())
		{
			String Phonemer_Visible="True";
			driver.findElement(By.xpath("//div[@id='divQuotingResults']//div["+i*2+"]//div[@class='promoHead']//div[@rowindex='"+rank+"']//div[@class='insurer_buttons']//a[@tagcommandermertypename='Phone']")).click();
			String typ_visible=MER_data(driver);
			mer_arr[0]=Phonemer_Visible;
			mer_arr[1]=typ_visible;
		}
		return mer_arr;
	}
	
	public static  String[] emailmerclick(int i,int rank,WebDriver driver) throws Exception
	{					String mer_arr[]=new String[2];

		Thread.sleep(4000);
		if(driver.findElement(By.xpath("//div[@id='divQuotingResults']//div["+i*2+"]//div[@class='promoHead']//div[@rowindex='"+rank+"']//div[@class='insurer_buttons']//a[@tagcommandermertypename='Email']")).isDisplayed())
		{
			String Phonemer_Visible="True";
			driver.findElement(By.xpath("//div[@id='divQuotingResults']//div["+i*2+"]//div[@class='promoHead']//div[@rowindex='"+rank+"']//div[@class='insurer_buttons']//a[@tagcommandermertypename='Email']")).click();
			String typ_visible=MER_data(driver);
			mer_arr[0]=Phonemer_Visible;
			mer_arr[1]=typ_visible;
			driver.findElement(By.className("link-back")).click();
		}
		return mer_arr;
	}
	
}

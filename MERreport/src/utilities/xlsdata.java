package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.annotations.Test;

public class xlsdata {
	@Test
	public static void data(ArrayList<String> arr1,int row_num) throws Exception 
	{
		
		File f1= new File("C:\\work\\newclipse\\files\\MER\\bike_test.xls");
		FileInputStream finput=new FileInputStream(f1);
		HSSFWorkbook wb1=new HSSFWorkbook(finput);
		HSSFSheet sheet= wb1.getSheet("mertest");
		
		System.out.println(arr1);
		
		
		HSSFRow row=sheet.createRow(row_num);
		for(int i=0;i<arr1.size();i++)
		{
			
			HSSFCell cell=row.createCell(i);
			cell.setCellValue(arr1.get(i).toString());
		}
		System.out.println("data inserted in excel at row = "+row_num);
		
		finput.close();
		FileOutputStream foutput= new FileOutputStream(f1);

		wb1.write(foutput);
		wb1.close();
		foutput.close();
		
		
		

	}

}

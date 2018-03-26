package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase{
	public static long PAGE_LOAD_TIMEOUT= 20; 
	public static long IMPLICIT_WAIT= 20; 
	
	public static String TESTDATA_FILE_PATH="E:\\GK\\Selenium\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCRM_TestData.xlsx";
	static Workbook book;
	static Sheet sheet;
	
	public static void sleepFiveSec() throws InterruptedException
	{
		Thread.sleep(5000);
	}
	
	public void switchToFrame(String frameName)
	{
		driver.switchTo().frame(frameName);
	}
	
	//GetData From Excel
	public static Object[][] getTestData(String sheetName)
	{
		FileInputStream fis=null;
		
		try {
		fis=new FileInputStream(TESTDATA_FILE_PATH);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book=WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {			
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet=book.getSheet(sheetName);
		Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0; i<sheet.getLastRowNum();i++)
		{
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
			{
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				System.out.println(data);
			}			
		}
		return data; 		
		
	}
	
	//Take ScreenShot
	public static void takeScreenShotAtEndOfTest() throws IOException
	{
		File scrShtFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date dateObj=new Date();
		String date= dateObj.toString().replace(':', '-').replace(' ', '_');
		FileUtils.copyFile(scrShtFile, new File("E:/GK/Selenium/FreeCRMTest/Screen-Captures/Screenshot-"+date+".png"));
	}
	
	

}

package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.EventListener;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase()
	{
	   	try
	   	{
	   		FileInputStream fis=new FileInputStream("E:\\GK\\Selenium\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
	        prop=new Properties();
	        prop.load(fis);
	   	}
	   	catch(FileNotFoundException e)
	   	{
	   		e.printStackTrace();
	   	}
	   	catch(Exception e)
	   	{
	   		e.printStackTrace();
	   	}
	}
	
	public void initialization()
	{
		String browserName=prop.getProperty("browser");
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\softwares\\SeleniumFile\\chromedriver_win32\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if (browserName.equals("firefox")) 
		{
			System.setProperty("webdriver.gecko.driver", "D:\\softwares\\SeleniumFile\\geckodriver-v0.20.0-win64\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		
		//Event Listener Code
		e_driver=new EventFiringWebDriver(driver);
		eventListener=new WebEventListener();
		e_driver.register(eventListener);
		driver=e_driver;		
		//Ends
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
	

}

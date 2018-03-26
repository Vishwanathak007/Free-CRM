package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class LoginPage extends TestBase{
  
	//PageFactory - Page Objects
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath=".//input[@value='Login']")
	WebElement loginBtn;
	
	@FindBy(xpath=".//button[text()='Sign Up']")
	WebElement signUpBtn;
	
	@FindBy(xpath=".//img[contains(@src,'freecrm.jpg')]")
	WebElement logo;  
	
	//Initializing Page Objects
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String validateLoginPageTitle()
	{
	  return driver.getTitle();
	}
	
	public boolean validateCRMLogo()
	{
		return logo.isDisplayed();
	}
	
	public HomePage login(String un, String pwd) throws InterruptedException 
	{
		username.sendKeys(un);
		password.sendKeys(pwd);
		TestUtil.sleepFiveSec();
		loginBtn.click();
		
		return new HomePage();
	}
	
	
	
	
	
}

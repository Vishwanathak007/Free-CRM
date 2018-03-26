package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {
	
	@FindBy(xpath="//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;
	
	@FindBy(xpath=".//select[@name='title']")
	WebElement selectTitle;
	
	@FindBy(xpath=".//input[@id='first_name']")
	WebElement firstName;
	
	@FindBy(xpath=".//input[@id='surname']")
	WebElement lastName;
	
	@FindBy(xpath=".//input[@name='client_lookup']")
	WebElement company;
	
	@FindBy(xpath=".//input[@type='submit' and @value='Save']")
	WebElement saveBtn;
	
	public ContactsPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactsLabel()
	{
		return contactsLabel.isDisplayed();
	}
	
	public void createNewContactForm(String title, String fName, String lName, String compName)
	{
		Select select=new Select(selectTitle);
		select.selectByVisibleText(title);
		
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		company.sendKeys(compName);
		saveBtn.click();
	}

}

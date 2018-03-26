package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	String sheetName="contacts";
	
	public ContactsPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException
	{
		initialization();
		testUtil=new TestUtil();
		loginPage=new LoginPage();
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame("mainpanel");
		contactsPage=homePage.clickOnContactsLink();
		TestUtil.sleepFiveSec();
	}
	
	@Test(priority=1)
	public void verifyContactsLabelTest() throws InterruptedException
	{		
		boolean flag=contactsPage.verifyContactsLabel();		
		Assert.assertTrue(flag,"Contacts Label is Missing");
	}
	
	@Test(priority=2, dataProvider="getContactsTestData")
	public void createNewContactsTest(String title, String firstName, String lastName, String company) throws InterruptedException
	{
		homePage.clickOnNewContactLink();
		TestUtil.sleepFiveSec();
		//contactsPage.createNewContactForm("Mr.", "Cristiano", "Ronaldo", "RealMadrid");
       contactsPage.createNewContactForm(title, firstName, lastName, company);
	}
	
	@DataProvider
	public Object[][] getContactsTestData()
	{
		Object[][] contactsData=TestUtil.getTestData(sheetName);
		return contactsData;
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	

}

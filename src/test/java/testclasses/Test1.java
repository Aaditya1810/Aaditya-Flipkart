package testclasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import baseclass.BaseClass1;
import pomclass.HomePage;
import pomclass.LoginPage;
import pomclass.ProfilePage;
import utilityclass.Utility1;

public class Test1 {
	
	WebDriver driver;
	
	LoginPage login;
	
	HomePage homepage;
	
	ProfilePage profilepage;
	
	ExtentReports reports;  //report initatiate
	
	ExtentTest test;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String a)
	{
		reports=new ExtentReports("ExtentReports.html",true); //select the location where u want to save
		                           //true means save the report
		test=reports.startTest("Test1");  //start the report from the test1 class.
		
		driver= BaseClass1.getWebDriver(a);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		login= new LoginPage(driver);
		
		homepage= new HomePage(driver);
		
		profilepage= new ProfilePage(driver);
		
	}
	
	@Test
	public void test()
	{
		String actual ="youtube.com";
		String expected="youtube.com";
		
		Assert.assertEquals(actual, expected);
		
		Reporter.log("Test1 is passed", true);
	}
	
	@Test
	public void verifyUserLogin() throws InterruptedException, IOException
	{
		
		Utility1.implicitWait(driver);
		
		login.enterEmailId();
		
		Reporter.log("Enter email");
		
		login.enterPassword();
		
		Reporter.log("Enter password");
		
		login.submitButton();
		
		Reporter.log("submit button");
		
		homepage.overtoPrfileName();
		
		//homepage.waittoLoadPage();
		
		String txt =homepage.getLogOutTxt();
		
		Assert.assertEquals(txt, "Logout");
		
		Reporter.log("Test 1 is passed", true);
		
		test.log(LogStatus.PASS, "Login Test passed"); //this report to show result is pass or fail.
		
	/*	if(txt.equals("Logout"))
		{
			System.out.println("Test passed");
		}
		else
		{
			System.out.println("Test failed");
			Assert.fail();
		}*/
	
	}
	
	@DataProvider(name="Dataset")
	public String[][] dataToSet()
	{
		String[][] data= {{"Aaditya", "8208240528","424201","Pachora"},{"Akshay","8888834561","425001","Pune"}};
		
		return data;
	}
	
	@Test(priority=1,dataProvider="Dataset")
	public void verifyUserCanAddNewAddress(String name,String mobno,String pincode,String locality) throws EncryptedDocumentException, IOException, InterruptedException
	{
		homepage.overtoPrfileName();
		     
		homepage.clickMyprofiletxt();
		
        profilepage.manageAddress();
		
		profilepage.addnewAddresse();
		
		int oldcount =profilepage.getAddressCount();
		
		List<String> data= new ArrayList<String>();
		
		data.add(name);
		
		data.add(mobno);
		
		data.add(pincode);
		
		data.add(locality);
		
		profilepage.getdataforAddress(data);
		
		profilepage.enteraddress();
		
		profilepage.addressType();
		
		profilepage.saveAddress();
		
		Thread.sleep(1000);
		
		int newcount=profilepage.getAddressCount();
		
		Assert.assertEquals(newcount, oldcount+1);
		
		test.log(LogStatus.PASS, "Added new address");
				 
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException
	{
		if(result.getStatus() == ITestResult.FAILURE)  //test case is fail then screenshot is capture otherwise pass is captured.
		{
			test.log(LogStatus.FAIL, test.addScreenCapture(profilepage.screenCapture(driver)),"Taken Screenshot");
		}
	}
	
	@AfterClass
	public void afterClass()
	{
		reports.endTest(test); //end the report after execution complete
		reports.flush();     //compulsory declared the flush method
		//driver.quit();
	}

}

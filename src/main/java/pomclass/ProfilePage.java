package pomclass;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilityclass.Utility1;

public class ProfilePage extends Utility1{

WebDriver driver;
	
	@FindBy (xpath="//div[text()='Manage Addresses']")
	private WebElement manageaddress;

	@FindBy (xpath="//div[@class='_1QhEVk']")
	private WebElement addnewaddress;
	

	@FindBy (xpath="//textarea[@name='addressLine1']")
	private WebElement enteraddress;

	@FindBy (xpath="(//div[@class='_1XFPmK'])[1]")
	private WebElement typeaddress;

	@FindBy (xpath="(//button[@type=\"button\"])[2]")
	private WebElement saveaddress;

	@FindBy (xpath="//div[@class='_1CeZIA']")
	private List<WebElement>  addresscount;

	public ProfilePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
		this.driver=driver;
	}
	
	public void manageAddress()
	{
		explicitWait(driver,manageaddress);
		manageaddress.click();
	}
	
	public void addnewAddresse()
	{
		explicitWait(driver, addnewaddress);
		addnewaddress.click();
	}
	
	public void getdataforAddress(List<String> a) throws EncryptedDocumentException, IOException
	{
	  // List<String> list = getMultipleDataFromXcel(0, 3);
		
		for(int i=1; i<=4; i++)
		{
			WebElement  element=driver.findElement(By.xpath("((//form)[2]//input)["+i+"]"));
			
			element.sendKeys(a.get(i-1));  //list catch from testclass
		}
	}
	
	public int getAddressCount()
	{
		return addresscount.size();
	}
	
	public void enteraddress()
	{
		enteraddress.sendKeys("Bhaskar Nagar, Bhadgaon Road, Pachora");
	}
	
	public void addressType()
	{
		typeaddress.click();
	}
	
	public void saveAddress()
	{
		saveaddress.click();
	}
}

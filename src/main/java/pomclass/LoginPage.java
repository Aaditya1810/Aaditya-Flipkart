package pomclass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilityclass.Utility1;

public class LoginPage extends Utility1{

	WebDriver driver;

	@FindBy(xpath="(//input[@type='text'])[2]")
	private WebElement emailid;

	@FindBy(xpath="//input[@type='password']")
	private WebElement password;

	@FindBy(xpath="(//button[@type='submit'])[2]")
	private WebElement submitButton;


	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);

		this.driver= driver;

	}

	public void enterEmailId() throws IOException
	{
		//emailid.sendKeys("8554927736");
		
		emailid.sendKeys(getConfigData("phone"));  //get the data from the config file.
	}

	public void enterPassword() throws IOException
	{
		//password.sendKeys("Aaditya@12");
		
		password.sendKeys(getConfigData("password")); //get the data from the config file.

	}

	public void submitButton() throws InterruptedException
	{
		submitButton.click();
		
		Thread.sleep(2000);

	}
	

}

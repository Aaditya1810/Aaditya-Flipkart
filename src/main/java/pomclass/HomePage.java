package pomclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilityclass.Utility1;

public class HomePage extends Utility1{

WebDriver driver;
	
	@FindBy (xpath="(//div[@class='exehdJ'])[1]")
	private WebElement profilename;
	
	@FindBy (xpath="//div[text()='My Profile']")
	private WebElement myprfiletxt;
	
	@FindBy (xpath="(//li[@class='_2NOVgj'])[10]")
	private WebElement logoutTxt;
	
	
	public HomePage(WebDriver driver)
	{ 
		PageFactory.initElements(driver, this);
		
		this.driver=driver;
	}
	
	public void overtoPrfileName()
	{
		explicitWait(driver, profilename);
		moveToEleement(driver, profilename);
	}
	
	public void clickMyprofiletxt()
	{
		explicitWait(driver, myprfiletxt);
		myprfiletxt.click();
	}
	
	public String getLogOutTxt()
	{
		return logoutTxt.getText();
	}
	
	public void movePointer()throws InterruptedException
	{
		moveByOffset(driver);
		
		Thread.sleep(2000);
	}
	
}

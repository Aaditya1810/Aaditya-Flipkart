package baseclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass1 {

	public static WebDriver getWebDriver(String a)
	{
    	if(a.equals("chrome"))
    	{
    		WebDriverManager.chromedriver().setup();
    		
         	//System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\Browsers\\chromedriver.exe");

    		WebDriver driver= new ChromeDriver();

    		driver.get("https://flipkart.com/");  //open the website

    		driver.manage().window().maximize();      //maximize the site
    		
    		return driver;	
    	}

    	else 
    	{
    		WebDriverManager.firefoxdriver().setup();
    		
    		//System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\Browsers\\geckodriver.exe");

    		WebDriver driver= new FirefoxDriver();

    		driver.get("https://flipkart.com/");  //open the website

    		driver.manage().window().maximize();      //maximize the site
    		
    		return driver;	
    	}
    	

    	/*else 
    	{
    		WebDriverManager.firefoxdriver().setup();
    		
    		//System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\Browsers\\geckodriver.exe");

    		WebDriver driver= new FirefoxDriver();

    		driver.get("https://flipkart.com/");  //open the website

    		driver.manage().window().maximize();      //maximize the site
    		
    		return driver;	
    	}*/
	}
}

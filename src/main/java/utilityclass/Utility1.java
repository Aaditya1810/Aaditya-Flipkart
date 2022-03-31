package utilityclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility1 {
	
	
	public static void implicitWait(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public static void moveToEleement(WebDriver driver,  WebElement element)
	{
		Actions act = new Actions(driver);

		act.moveToElement(element).pause(2000).build().perform();

	}

	public static void moveByOffset(WebDriver driver)
	{
		Actions act = new Actions(driver);

		act.moveByOffset(200, 0).release().build().perform();

	}

	public static void explicitWait(WebDriver driver, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
	}

	public static List<String> getMultipleDataFromXcel(int firstrow, int lastrow) throws EncryptedDocumentException, IOException
	{
		FileInputStream file = new FileInputStream("C:\\Users\\ADITYA\\Desktop\\FlipAddress.xlsx");

		List<String> datalist = new ArrayList<String>();

		Sheet sheet = WorkbookFactory.create(file).getSheet("sheet1");

		for(int i=firstrow; i<=lastrow; i++)
		{
			try
			{
				String stringdata= sheet.getRow(i).getCell(1).getStringCellValue();

				datalist.add(stringdata);

			}catch(Exception e)
			{

				long intdata = (long) sheet.getRow(i).getCell(1).getNumericCellValue();

				String string = String.valueOf(intdata);  //value of overload method of int to string conversion

				datalist.add(string);
			}}

		return datalist;
	}
	
	public String screenCapture(WebDriver driver) throws IOException
	{
		Date date = new Date();
		
		String modifiedDate= new SimpleDateFormat("MM-dd-mm-hh-ss").format(date);
		
		File file =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
	  //addscreenscapture method return type is string so we can declared the string
		
		File detn = new File("screenShot" + modifiedDate +".png");
		
		String path =  detn.getAbsolutePath();
		
		FileUtils.copyFile(file, detn);
	//	FileHandler.copy(file, detn);
		return path;
	}
	
	 public String getConfigData(String key) throws IOException
	 {
		 FileInputStream file= new FileInputStream("configuration\\config.properties");
		 
		 Properties p = new Properties();  //data extract from config file
		 
		 p.load(file);  //file load in properties under
		 
		 return p.getProperty(key);  //get the data from string argument multiple key.
		
	 }
	 

}

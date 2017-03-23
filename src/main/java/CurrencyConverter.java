import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
// import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;public class CurrencyConverter {

	public CurrencyConverter() {
		// TODO Auto-generated constructor stub
	}
	

	private static WebDriver driver;
	@BeforeTest
	public static void StartTest() throws Exception
	{
	driver= setUp("Chrome",
			"http://www.xe.com/currencyconverter/",
			"XE Currency Converter - Live Rates"); 
	}
	
	@Test
	public static void ConvertUSD2RupeesTest() throws Exception
	{
		 WebElement amount = driver.findElement(By.id("amount"));
		 amount.clear();
		 amount.sendKeys("100");
		              	
		 WebElement from = driver.findElement(By.id("from"));
		 from.sendKeys("USD");
		 from.sendKeys(Keys.RETURN);
		 
		 WebElement to = driver.findElement(By.id("to"));
		 to.sendKeys("INR");
		 to.sendKeys(Keys.RETURN);
		   
		 WebElement gobutton = driver.findElement(By.id("ucc_go_btn_svg"));
		 gobutton.click();
		 
	
	}
	
	@AfterTest
	public static void ShutdownTest() throws InterruptedException
	{
		shutDown(driver,5);
	}
	
	
	
	// Setup test and open URL
	public static WebDriver setUp(String browser, String url, String expectedTitle) throws Exception
	{
		WebDriver d;
		switch (browser.toUpperCase())
			{
			case "CHROME":
				d = new ChromeDriver();
				break;
			case "FIREFOX":
				d = new FirefoxDriver();
				break;
			case "SAFARI":
				d = new SafariDriver();
				break;
			default:
				throw new Exception("Unknown browser specified: " + browser);			
			}
	
		d.get(url);
		d.manage().window().maximize();
        if (expectedTitle.equals(d.getTitle()))
        {
               System.out.println("Verification Successful - The correct title is displayed on the web page.");
        }
       else
        {
               System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
        }
		return d;
	}
	
	// shutdown
	public static void shutDown(WebDriver d) throws InterruptedException
	{		
        shutDown(d,0);	
	}
	
	public static void shutDown(WebDriver d, long waitseconds) throws InterruptedException
	{
		if (waitseconds > 1)
			System.out.println("Waiting " + waitseconds + " seconds so you can see the results");
		Thread.sleep(waitseconds * 1000);
        d.quit();
        System.out.println("Test script executed successfully."); 
        System.exit(0);
	}


}

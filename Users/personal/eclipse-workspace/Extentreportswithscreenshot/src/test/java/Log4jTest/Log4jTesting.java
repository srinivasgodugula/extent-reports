package Log4jTest;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Log4jTesting {
	WebDriver driver;
	Logger logger;
@BeforeMethod
	public  void initialization () {
	logger = Logger.getLogger(Log4jTesting.class.getName());
	PropertyConfigurator.configure("log4j.properties");
	
			System.setProperty("webdriver.chrome.driver","C:\\Users\\personal\\Downloads\\chromedriver.exe");
			driver = new ChromeDriver();// it launches blank url
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			logger.info("maximize the window");
			logger.debug("start initialization");
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			driver.get("http://freecrm.com/");
			logger.info("navigating to freecrm");
}
@Test(priority =1)
public void HomePageVerificationTest() throws InterruptedException {
	String Title = driver.getTitle();
	Thread.sleep(2000);
	System.out.println(Title);
	Assert.assertEquals(Title, "Free CRM in the cloud software boosts sales");
}
@Test(priority =2)
public void VerifyLogoTest() throws InterruptedException {
	boolean b = driver.findElement(By.xpath("//img[@class='img-responsive']")).isDisplayed();
	Assert.assertTrue(b);	
	}

@AfterMethod
public void Teardown() {
	driver.quit();
}
}
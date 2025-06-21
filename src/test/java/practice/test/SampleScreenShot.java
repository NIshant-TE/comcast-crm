package practice.test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SampleScreenShot {
	@Test
	public void AmazonTest() throws IOException {
		System.out.println("Hi");
		WebDriver driver = new ChromeDriver();
		driver.get("http://amazon.com");
		
		//step 1: create a oblect to event firing webdriver
		//EventFiringWebDriver edriver= new EventFiringWebDriver(driver);
		
		//step 2: use getscreenshotas method to  get file type of screnshot
		//File srcFile= edriver.getScreenshotAs(OutputType.FILE);
		
		//step 3: store screen on local driver
		//FileUtils.copyFile(srcFile, new File("./screenshot/test.png"));
		
		
	}

}

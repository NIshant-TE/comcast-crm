package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class GetProductInfoTest {
	
	 @Test
	 public void getProductInfoTst() {
		 WebDriver driver= new ChromeDriver();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 driver.get("http://amazon.in");
		 
		 //Search product
		 driver.findElement(By.id("twotabsearchtextbox")).sendKeys("shirt for man",Keys.ENTER);
		String xyz = driver.findElement(By.xpath("//h2[@aria-label=\"Men's Cotton Blend Mandarin Collar Self One Design Full Sleeve Casual Short Kurta\"]/ancestor::div[@class='a-section a-spacing-small puis-padding-left-micro puis-padding-right-micro']/descendant::span[@class='a-price-whole']")).getText();
		 System.out.println(xyz);
		 
		 //Capture product info
		 ////h2[@aria-label="Men's Cotton Blend Mandarin Collar Self One Design Full Sleeve Casual Short Kurta"]/ancestor::div[@class='a-section a-spacing-small puis-padding-left-micro puis-padding-right-micro']/descendant::span[@class='a-price-whole']
	 }

}

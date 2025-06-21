package practice.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import javax.imageio.stream.FileImageInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CreateOrganisationTest {

				public static void main(String[] args) throws IOException, InterruptedException {
				// TODO Auto-generated method stub
				FileInputStream fis = new FileInputStream("C:\\Users\\nvip7\\Desktop\\commondata.properties");
				Properties pObj = new Properties();
				pObj.load(fis);
				String BROWSER = pObj.getProperty("browser");
				String URL = pObj.getProperty("url");
				String USERNAME = pObj.getProperty("username");
				String PASSWORD =  pObj.getProperty("password");
				
				
				WebDriver driver= null;
				if(BROWSER.equals("chrome")) {
					driver=new ChromeDriver();
				} else if(BROWSER.equals("firefox")) {
					driver= new FirefoxDriver();
				} else if(BROWSER.equals("edge")) {
					driver= new EdgeDriver();
				} else {
					driver= new ChromeDriver();
				}
				
				//WebDriver driver= new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				driver.get(URL);
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				

				//generate random numbers
				Random random= new Random();
				int randomInt= random.nextInt(1000);
				
				//excel 
				FileInputStream fis1 = new FileInputStream("C:\\Users\\nvip7\\Desktop\\testScriptdata.xlsx");
				Workbook wb= WorkbookFactory.create(fis1);
				Sheet sh = wb.getSheet("org");
				Row row =sh.getRow(1);
				Cell cel= row.getCell(2);
				String orgName = cel.getStringCellValue()+ randomInt;
				wb.close();
				
				

				
				//step 2: navigate to organization module
				driver.findElement(By.linkText("Organizations")).click();
				
				//step 3: click on "create Organization" button
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();	
				
				//step 4: enter all the details and create new organization
				driver.findElement(By.name("accountname")).sendKeys(orgName);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				Thread.sleep(2000);
				
				
				//verify header msg expected result
				String headerInfo= driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if (headerInfo.contains(orgName))	{
					System.out.println(orgName+" is created== pass");
				} else {
					System.out.println(orgName+" is not created== fail");
				}
				
				//verify Header orgName info expected result
				driver.findElement(By.id("dtlview_Organization")).getText();
				if (headerInfo.equals(orgName))	{
					System.out.println(orgName+" is created== pass");
				} else {
					System.out.println(orgName+" is not created== fail");
				}
				//step 5 : logout
				Actions act = new Actions(driver);
				act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				driver.quit();
				
				
					

			}

		}


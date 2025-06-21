package com.concast.crm.orgtest;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganisationWithIndustriesTest {

				public static void main(String[] args) throws IOException, InterruptedException{
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
				Row row =sh.getRow(4);
				Cell cel= row.getCell(2);
				String orgName = cel.getStringCellValue()+ randomInt;
				String industry= row.getCell(3).toString();
				String type= row.getCell(4).toString();
				wb.close();
				
				

				
				//step 2: navigate to organization module
				driver.findElement(By.linkText("Organizations")).click();
				
				//step 3: click on "create Organization" button
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();	
				
				//step 4: enter all the details and create new organization
				driver.findElement(By.name("accountname")).sendKeys(orgName);
				WebElement wbsele1= driver.findElement(By.name("industry"));
				Select sell= new Select(wbsele1);
				sell.selectByVisibleText(industry);
				
				WebElement wbsele2= driver.findElement(By.name("accounttype"));
				Select sel2= new Select(wbsele2);
				sel2.selectByVisibleText(type);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				Thread.sleep(2000);
				
				//System.out.println("done=============");
				
				//verify  the industry and type info
				String actIndustries= driver.findElement(By.id("dtlview_Industry")).getText();
				if (actIndustries.equals(industry))	{
					System.out.println(industry+" is created== pass");
				} else {
					System.out.println(industry+" is not created== fail");
				}
				//System.out.println("done=============");
				
				String actType=driver.findElement(By.id("dtlview_Type")).getText();
				if (actType.equals(type))	{
					System.out.println(type+" is created== pass");
				} else {
					System.out.println(type+" is not created== fail");
				}
				//System.out.println("done=============");
				//step 5 : logout
				Actions act = new Actions(driver);
				act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				driver.quit();
				
				
					

			}

		}


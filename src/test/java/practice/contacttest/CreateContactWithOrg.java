package practice.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

public class CreateContactWithOrg {

	public static void main(String[] args) throws InterruptedException, IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\nvip7\\Desktop\\commondata.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD =  pObj.getProperty("password");
		
		

		//generate random numbers
		Random random= new Random();
		int randomInt= random.nextInt(1000);
		
		//excel 
		FileInputStream fis1 = new FileInputStream("C:\\Users\\nvip7\\Desktop\\testScriptdata.xlsx");
		Workbook wb= WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("contact");
		Row row =sh.getRow(7);
		Cell cel= row.getCell(2);
		String orgName = cel.getStringCellValue()+ randomInt;
		String contactLastName= row.getCell(3).getStringCellValue();
		wb.close();
		
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
		
		//step 1 login to appln
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
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
			System.out.println(orgName+" heade verified== pass");
		} else {
			System.out.println(orgName+" is not verified== fail");
		}
		
		//step 5: navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();
		
		//step 3: click on "create contact" button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();	
		
		//step 4: enter all the details and create new organization
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		
		System.out.println("=======done=======");
		//switch to child windows
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it= set.iterator();
		while (it.hasNext()) {
			String windowID=it.next();
			driver.switchTo().window(windowID);
			
			String actUrl=driver.getCurrentUrl();
			if(actUrl.contains("module=Accounts"))	{
				break;
				
			}
			System.out.println("=======done=======");
		}
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		//switch to parent window
		Set<String> set1 = driver.getWindowHandles();
		Iterator<String> it1= set1.iterator();
		while (it1.hasNext()) {
			String windowID=it1.next();
			driver.switchTo().window(windowID);
			
			String actUrl=driver.getCurrentUrl();
			if(actUrl.contains("module=Contacts&action"))	{
				break;
				
			}
		}
		
		
		System.out.println("=======done=======");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(2000);
		
		System.out.println("=======done=======");
		//verify header phone number expected result
		 headerInfo= driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(contactLastName))	{
			System.out.println(contactLastName +" is verified== pass");
		} else {
			System.out.println(contactLastName +" is not verified== fail");
		}
		
		//verify
		 String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
			if (actOrgName.trim().equals(orgName.trim()))	{
				System.out.println(orgName +" is verified== pass");
			} else {
				System.out.println(orgName +" is not verified== fail");
			}

		//step 5 : logout
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		


	}

}

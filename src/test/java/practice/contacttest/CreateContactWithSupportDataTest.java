package practice.contacttest;

	import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fwebdriverutility.JavaUtility;

	public class CreateContactWithSupportDataTest {

		public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
			// TODO Auto-generated method stub
			JavaUtility jLib= new JavaUtility();
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
			String lastName = wb.getSheet("contact").getRow(4).getCell(2).toString()+randomInt;
			wb.close();
			
			
			//step 2: navigate to organization module
			driver.findElement(By.linkText("Contacts")).click();
			
			//step 3: click on "create Organization" button
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();	
			
			//step 4: enter all the details and create new organization
			
//			Date dateobj= new Date();
//			//to get date and time and day all at once
//			//System.out.println(dateobj.toString());
//			
//			//to get date in 
//			SimpleDateFormat sim= new SimpleDateFormat("yyyy-MM-dd");
//			String startDate=sim.format(dateobj);
//			//System.out.println(startDate);
//			
//			Calendar cal=sim.getCalendar();
//			cal.add(Calendar.DAY_OF_MONTH, 30);
//			String endDate=sim.format(cal.getTime());
//			//System.out.println(endDate);
			String startDate=jLib.getSystemDateYYYYDDMM();
			String endDate=jLib.getRequiredDateYYYYDDMM(30);
			driver.findElement(By.name("lastname")).sendKeys(lastName);
			driver.findElement(By.name("support_start_date")).clear();
			driver.findElement(By.name("support_start_date")).sendKeys(startDate);
			
			
			driver.findElement(By.name("support_end_date")).clear();
			driver.findElement(By.name("support_end_date")).sendKeys(endDate);
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			Thread.sleep(2000);
			
			
			//verify header phone number expected result
			String actStartDate= driver.findElement(By.id("dtlview_Support Start Date")).getText();
			if (actStartDate.equals(startDate))	{
				System.out.println(startDate +" is verified== pass");
			} else {
				System.out.println(startDate +" is not verified== fail");
			}
			String actEndDate= driver.findElement(By.id("dtlview_Support End Date")).getText();
			if (actEndDate.equals(endDate))	{
				System.out.println( endDate+" is verified== pass");
			} else {
				System.out.println(endDate+" is not verified== fail");
			}

			//step 5 : logout
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
			driver.findElement(By.linkText("Sign Out")).click();
			driver.quit();

		}

	}


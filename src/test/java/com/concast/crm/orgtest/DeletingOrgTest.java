package com.concast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.fwebdriverutility.JavaUtility;
import com.comcast.crm.generic.fwebdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

	public class DeletingOrgTest {

		public static void main(String[] args) throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			FileUtility fLib= new FileUtility();
			ExcelUtility eLib= new ExcelUtility();
			JavaUtility jLib=new JavaUtility();
			WebDriverUtility wLib= new WebDriverUtility();
			
			//read common data from jSon file
			String BROWSER = fLib.getDataFromPropertiesFile("browser");
			String URL = fLib.getDataFromPropertiesFile("url");
			//String USERNAME = fLib.getDataFromPropertiesFile("username");
			//String PASSWORD =  fLib.getDataFromPropertiesFile("password");
			
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
			
			//step 1 : login to application
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get(URL);
			LoginPage lp= PageFactory.initElements(driver, LoginPage.class);
			
			lp.loginToapp(BROWSER, URL, URL);
			
			//excel 
			String orgName = eLib.getDataFromExcel("org", 10, 2)+jLib.getRandomNumber();
			
			

			
			//step 2: navigate to organization module
			HomePage hp= new HomePage(driver);
			hp.getOrgLink().click();
			System.out.println("==========done=========");
			
			//step 3: click on "create Organization" button
			OrganizationsPage cnp= new OrganizationsPage(driver);
			cnp.getCreateNewOrgBtn().click();
			System.out.println("cre nw org btn=========done=========");
			
			
			//step 4: enter all the details and create new organization
			CreatingNewOrganizationPage cnop= new CreatingNewOrganizationPage(driver);
			cnop.createOrg(orgName);
			System.out.println("==========done=========");
			
			
			//verify header msg Expected result
			OrganizationInfoPage oip= new  OrganizationInfoPage (driver);
			String actOrgName=oip.getHeaderMsg().getText();
			if(actOrgName.contains(orgName)) {
				System.out.println(orgName+"name is verified==PASS");
			} else {
				System.out.println(orgName+"name is not verified==FAIL");
			}
			
			//go back to organization page
			hp.getOrgLink().click();
			
			//search for organization
			cnp.getSearchEdt().sendKeys(orgName);
			wLib.Select(cnp.getGetSearchDD(),"Organization Name");
			cnp.getSearchBtn().click();
			
			//in dynamic website select & delete org
			driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
			
			
			//logout
			//hp.log();
			//driver.quit();
				
				

		}

	}


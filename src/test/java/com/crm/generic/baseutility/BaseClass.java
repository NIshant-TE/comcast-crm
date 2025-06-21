package com.crm.generic.baseutility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.fwebdriverutility.JavaUtility;
import com.comcast.crm.generic.fwebdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {
		
	public DataBaseUtility dbLib= new DataBaseUtility();
	public FileUtility   flib= new FileUtility();
	public ExcelUtility eLib= new ExcelUtility();
	public JavaUtility jLib=new JavaUtility();
	public WebDriverUtility wLib= new WebDriverUtility();

		public  WebDriver driver= null;
		
		@BeforeSuite(groups= {"smokeTest", "regression"})
		public void configBS() throws SQLException {
			System.out.println("===conect to database, eport config");
//			dbLib.getDbconnection();			
		}
		
		@Parameters("BROWSER" )
		@BeforeClass (groups= {"smokeTest", "regression"})
		public void configBC(@Optional("chrome")String browser) throws IOException {
			System.out.println("====launch the browser===");
			String BROWSER=browser;
					//flib.getDataFromPropertiesFile("browser"); 
			
			if(BROWSER.equals("chrome")) {
				driver=new ChromeDriver();
			} else if(BROWSER.equals("firefox")) {
				driver= new FirefoxDriver();
			} else if(BROWSER.equals("edge")) {
				driver= new EdgeDriver();
			} else {
				driver= new ChromeDriver();
			}
			
		}
		
		@BeforeMethod(groups= {"smokeTest", "regression"})
		public void configBM() throws IOException {
			System.out.println("--login---");
			String URL=flib.getDataFromPropertiesFile("url");
			String USERNAME=flib.getDataFromPropertiesFile("username");
			String PASSWORD=flib.getDataFromPropertiesFile("password");
			LoginPage lp= new LoginPage(driver);
			lp.loginToapp(USERNAME, PASSWORD, URL);
		}
		
		@AfterMethod(groups= {"smokeTest", "regression"})
		public void configAM() {
			System.out.println("--logout---");
			HomePage hp = new HomePage(driver);
		//	hp.logout();
		}

		
		@AfterClass(groups= {"smokeTest", "regression"})
		public void configAC()  {
			System.out.println("close the browsr");
			//driver.quit();
			
			
		}

		@AfterSuite(groups= {"smokeTest", "regression"})
		public void configAS() throws SQLException {
			System.out.println("===close the DB,report backup");
//			dbLib.closeDbconnection();
		}

	}


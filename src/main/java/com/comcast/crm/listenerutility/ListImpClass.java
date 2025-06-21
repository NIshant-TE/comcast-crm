package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.openqa.selenium.io.FileHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.comcast.crm.basetest.BaseClass;

public class ListImpClass implements ITestListener, ISuiteListener {

	public void onStart(ISuite suite) {

// TODO Auto-generated method stub

		System.out.println("Report Configuration");

	}

	public void onFinish(ISuite suite) {

// TODO Auto-generated method stub

		System.out.println("Report Backup");

	}

	public void onTestStart(ITestResult result) {

// TODO Auto-generated method stub

		System.out.println("======>" + result.getMethod().getMethodName() + "=========");

	}

	public void onTestSuccess(ITestResult result) {

// TODO Auto-generated method stub

		System.out.println("======>" + result.getMethod().getMethodName() + "=====END====");

	}

	public void onTestFailure(ITestResult result) {

// TODO Auto-generated method stub

		String testName = result.getMethod().getMethodName();

//create an object to EVENTFIRING WEBDRIVER

//EventFiringWebDriver edriver = new EventFiringWebDriver(BaseClass.sdriver); //to use this use selenium dependency 4.8.1 or 4.12.1

//use getscreenshotas method to get file type of screenshot

		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;

		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
//store screen shot in local drive

		try {

			FileHandler.copy(srcFile, new File("./screenshot/" + testName +"_"+ time + ".png"));
			

		} catch (IOException e) {

// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	public void onTestSkipped(ITestResult result) {

// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

// TODO Auto-generated method stub

	}

	public void onTestFailedWithTimeout(ITestResult result) {

// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {

// TODO Auto-generated method stub

	}

}
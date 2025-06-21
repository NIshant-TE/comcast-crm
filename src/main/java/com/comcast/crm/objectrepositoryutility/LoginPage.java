package com.comcast.crm.objectrepositoryutility;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fwebdriverutility.WebDriverUtility;

public class LoginPage  extends WebDriverUtility{
	// rule 1 create a seprate java class
	//rule 2 Object creation
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements( driver, this);
	}
	
	@FindBy(name="user_name")
	 private WebElement usernameEdit;
	
	
	@FindBy(name="user_password")
	 private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	// rule 3 object creation
	
	//rule 4 object encapsulation
	
	
	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//actions
	public void loginToapp(String username, String password, String url) {
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginBtn.click();
	}

	
		
	
}

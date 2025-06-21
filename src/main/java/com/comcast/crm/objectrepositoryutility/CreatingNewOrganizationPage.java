package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {
	
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements( driver, this);
	}
	
	
	@FindBy(name="accountname")
	private WebElement orgNameEdit;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDB;
	
	@FindBy(id="phone")
	private WebElement phoneDB;

	public WebElement getPhoneDB() {
		return phoneDB;
	}

	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createOrg(String orgName) {
		orgNameEdit.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createOrgwithind(String orgName, String industry) {
		orgNameEdit.sendKeys(orgName);
		Select sel= new Select(industryDB);
		sel.selectByVisibleText(industry);
		saveBtn.click();
	}
	public void createOrg(String orgName,String phoneNumber) {
		orgNameEdit.sendKeys(orgName);
		phoneDB.sendKeys(phoneNumber);
		saveBtn.click();
	}
	
	
	

}

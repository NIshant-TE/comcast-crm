package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {

	WebDriver driver;

	public CreatingNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "lastname")
	private WebElement lastNameEdt;

	@FindBy(name = "support_start_date")
	private WebElement startDate;

	@FindBy(name = "support_end_date")
	private WebElement endDate;

	@FindBy(xpath = "(//img[@src='themes/softed/images/select.gif'])[1]")
	private WebElement OrgLookUpBtn;

	@FindBy(xpath = "//input[@class='crmButton small save']")
	private WebElement saveContactBtn;

	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}

	public WebElement getStaretDate() {
		return startDate;
	}

	public WebElement getEndDate() {
		return endDate;
	}

	public WebElement getOrgLookUpBtn() {
		return OrgLookUpBtn;
	}

	public WebElement getSaveContactBtn() {
		return saveContactBtn;
	}

	public void CreatingNewContactWithSupportDate(String lastName, String startingDate, String endingDate) {
		lastNameEdt.sendKeys(lastName);
		startDate.clear();
		startDate.sendKeys(startingDate);
		endDate.clear();
		endDate.sendKeys(endingDate);
		saveContactBtn.click();

	}

}

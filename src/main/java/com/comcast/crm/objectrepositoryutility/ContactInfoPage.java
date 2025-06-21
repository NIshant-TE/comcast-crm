package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	WebDriver driver;
	public ContactInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}		
	
	@FindBy(id = "mouseArea_Organization Name")
	 private WebElement orgText;
	
	@FindBy(className = "dvHeaderText" )
	private WebElement contactHeaderText;
	
	@FindBy(id = "mouseArea_Last Name")
	 private WebElement lastNameText;
	
	@FindBy(id = "dtlview_Support End Date")
	 private WebElement endDateText;
	
	@FindBy(id = "dtlview_Support Start Date")
	 private WebElement startDateText;
	
	
	public WebElement getLastNameText() {
		return lastNameText;
	}

	public WebElement getEndDateText() {
		return endDateText;
	}

	public WebElement getStartDateText() {
		return startDateText;
	}

	public WebElement getOrgText() {
		return orgText;
	}

	public WebElement getContactHeaderText() {
		return contactHeaderText;
	}
	}
	
	

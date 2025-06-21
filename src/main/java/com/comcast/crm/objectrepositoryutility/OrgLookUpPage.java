package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgLookUpPage {
	
	WebDriver driver;
	public OrgLookUpPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	 @FindBy(name = "search_text")
	 private WebElement searchtextlookUp;
	 
	 @FindBy(name = "search")
	 private WebElement searchBtnlookUp;
	 
//	 @FindBy(xpath = "//a[text()='"+orgName+"']")
//	 private WebElement OrgInLookUp;
//	 
	public WebElement getSearchtextlookUp() {
		return searchtextlookUp;
	}

	public WebElement getSearchBtnlookUp() {
		return searchBtnlookUp;
	}

//	public WebElement getOrgInLookUp() {
//		return OrgInLookUp;
//	}
	 
}

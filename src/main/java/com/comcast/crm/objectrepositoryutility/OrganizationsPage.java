package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
		
		WebDriver driver;
		public OrganizationsPage(WebDriver driver) {
			this.driver=driver;
			PageFactory.initElements( driver, this);
		}
		
		@FindBy(name="search_text")
		private WebElement searchEdt;
		
		@FindBy(name="search_field")
		private WebElement searchDD;
		
		@FindBy(name="submit")
		private WebElement searchBtn;
		
		public WebElement getSearchBtn() {
			return searchBtn;
		}

		public void setSearchBtn(WebElement searchBtn) {
			this.searchBtn = searchBtn;
		}

		@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
		private WebElement createNewOrgBtn;

		

		public WebElement getSearchEdt() {
			return searchEdt;
		}

		public WebElement getGetSearchDD() {
			return searchDD;
		}

		
		public WebElement getCreateNewOrgBtn() {
			return createNewOrgBtn;
		}

	
		
}

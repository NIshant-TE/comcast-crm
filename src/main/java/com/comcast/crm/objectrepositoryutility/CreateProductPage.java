package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProductPage {
	WebDriver driver;
	public CreateProductPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="productname")
	private WebElement productNameTextField;

	public WebElement getProductNameTextField() {
		return productNameTextField;
		}
		
		@FindBy(xpath = "//input[@title='Save [Alt+S]']")
		private WebElement saveProductBtn;

		public WebElement getSaveProductBtn() {
			return saveProductBtn;
	}

}

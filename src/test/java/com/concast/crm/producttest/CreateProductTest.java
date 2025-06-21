package com.concast.crm.producttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreateProductPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.ProductPage;

public class CreateProductTest extends BaseClass {
	
	@Test
	public void createProduct() throws InterruptedException, EncryptedDocumentException, IOException {
		
		// Get product name from Excel and append random number
		String productName = eLib.getDataFromExcel("Sheet2", 1, 0) + jLib.getRandomNumber();

		// Navigate to product page
		HomePage hp = new HomePage(driver);
		hp.getProductsLink().click();
		
		// Click on create product link
		ProductPage pp = new ProductPage(driver);
		pp.getCreateNewPoductBtn().click();
		
		// Fill all the details and create new product
		CreateProductPage cpp = new CreateProductPage(driver);
		cpp.getProductNameTextField().sendKeys(productName);
		cpp.getSaveProductBtn().click();

		// Verification using Assert
		String headerInfo = driver.findElement(By.xpath("//span[@id='dtlview_Product Name']")).getText();

		Assert.assertTrue(headerInfo.contains(productName), "Product creation verification failed");

		// (Optional) You may print success message if needed:
		System.out.println(productName + " is created successfully.");
	}
}

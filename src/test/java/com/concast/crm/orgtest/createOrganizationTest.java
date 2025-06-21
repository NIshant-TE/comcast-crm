package com.concast.crm.orgtest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.crm.generic.baseutility.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class createOrganizationTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createOrg() throws EncryptedDocumentException, IOException {

		// excel
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		// step 2: navigate to organization module
		HomePage op = new HomePage(driver);
		op.getOrgLink().click();

		// step 3: click on "create Organization" button
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// step 4: enter all the details and create new organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		System.out.println("==========done=========");
		// verify header msg Expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + "name is verified==PASS");
		} else {
			System.out.println(orgName + "name is not verified==PASS");
		}
	}

	@Test(groups = "regression")
	public void createOrgWithIndustry() throws EncryptedDocumentException, IOException {

		String orgName = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();

		// step 2: navigate to organization module
		HomePage op = new HomePage(driver);
		op.getOrgLink().click();

		// step 3: click on "create Organization" button
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// step 4: enter all the details and create new organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrgwithind(orgName, eLib.getDataFromExcel("org", 4, 3));

		// verify the industry and type info
		String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actIndustries.equals(eLib.getDataFromExcel("org", 4, 3))) {
			System.out.println(eLib.getDataFromExcel("org", 4, 3) + " is created== pass");
		} else {
			System.out.println(eLib.getDataFromExcel("org", 4, 3) + " is not created== fail");
		}

	}

	@Test(groups = "regression")
	public void createOrgWithPhone() throws EncryptedDocumentException, IOException {
		// excel
		String orgName = eLib.getDataFromExcel("org", 7, 3) + jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("org", 7, 3);

		// step 2: navigate to organization module
		HomePage op = new HomePage(driver);
		op.getOrgLink().click();

		// step 3: click on "create Organization" button
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// step 4: enter all the details and create new organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName, phoneNumber);

		// verify header phone number expected result
		String actPhoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
		if (actPhoneNumber.equals(phoneNumber)) {
			System.out.println(phoneNumber + " is created== pass");
		} else {
			System.out.println(phoneNumber + " is not created== fail");
		}

	}
}

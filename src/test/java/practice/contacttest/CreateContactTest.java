package practice.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.generic.baseutility.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrgLookUpPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactTest extends BaseClass {

	@Test(groups="smokeTest")
	public void createContactTest() throws EncryptedDocumentException, IOException, InterruptedException {
		// excel
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		// step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step 3: click on "create Organization" button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// step 4: enter all the details and create new organization
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);

		cncp.getLastNameEdt().sendKeys(lastName);
	 	cncp.getSaveContactBtn().click();
	 	
	 	//verify header phone number info expected Result
	 	String actHeader=cp.getHeaderMsg().getText();
	 	boolean status= actHeader.contains(lastName);
	 	Assert.assertEquals(status, true);

		// verify header phone number expected result
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actLastName, lastName);

	}

	@Test(groups="regression")
	public void createContactWithsupportDateTest() throws EncryptedDocumentException, IOException {

		// excel
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();

		// step 2: navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step 3: click on "create Organization" button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// step 4: enter all the details and create new organization

		String startingDate = jLib.getSystemDateYYYYDDMM();
		String endingDate = jLib.getRequiredDateYYYYDDMM(30);
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.CreatingNewContactWithSupportDate(lastName, startingDate, endingDate);

		// verify header phone number expected result
		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if (actStartDate.equals(startingDate)) {
			System.out.println(startingDate + " is verified== pass");
		} else {
			System.out.println(startingDate + " is not verified== fail");
		}
		String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if (actEndDate.equals(endingDate)) {
			System.out.println(endingDate + " is verified== pass");
		} else {
			System.out.println(endingDate + " is not verified== fail");
		}

	}


	@Test(groups="regression")
	public void createContactWithOrgTest() throws EncryptedDocumentException, IOException	{
		//read test script data from Excel file
		String orgName = eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();
		String contactLastName = eLib.getDataFromExcel("contact", 7, 3) + jLib.getRandomNumber();

		//Navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		
		//click on create organization button
		OrganizationsPage cnp= new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();
		
		//enter all the details and create new organization
		CreatingNewOrganizationPage cnop= new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		System.out.println("==========done=========");
		
		//verify header msg Expected result
		OrganizationInfoPage oip= new  OrganizationInfoPage (driver);
		String actOrgName=oip.getHeaderMsg().getText();
		if(actOrgName.contains(orgName)) {
			System.out.println(orgName+"name is verified==PASS");
		} else {
			System.out.println(orgName+"name is not verified==PASS");
		}
		
				// navigate to organization module
				HomePage hp1 = new HomePage(driver);
				hp1.getContactLink().click();

				// click on "create contact" button
				ContactPage cp = new ContactPage(driver);
				cp.getCreateNewContactBtn().click();

				//enter all the details and create new organization
				CreatingNewContactPage cncp = new CreatingNewContactPage(driver);

				cncp.getLastNameEdt().sendKeys(contactLastName);
				
				cncp.getOrgLookUpBtn().click();
				String mainWindowID = driver.getWindowHandle();

				//switch to child window
				wLib.switchToWindow(driver, actOrgName);
				OrgLookUpPage OLU=new OrgLookUpPage(driver);
				OLU.getSearchtextlookUp().sendKeys(orgName);
				OLU.getSearchBtnlookUp().click();
								 
				driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();

				//switch to parent window
				wLib.switchToParentWindow(driver, mainWindowID);
				//wLi
				//wlib.switchToTabOnURL(driver, "Contacts&parenttab");


				cncp.getSaveContactBtn().click();



				//verify header msg expected result
				ContactInfoPage cip=new ContactInfoPage(driver);
				String headerinfo = cip.getContactHeaderText().getText();
				if(headerinfo.contains(contactLastName)) {
					System.out.println(contactLastName+" contact is Created==PASS");			
				}else {
					System.out.println(contactLastName+" contact is not created==FAIL");
				
				}
	}
}
				
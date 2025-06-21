package com.comcast.crm.documentsTest;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreateDocumentPage;
import com.comcast.crm.objectrepositoryutility.DocumentsPage;

public class CreateDocumentsTest extends BaseClass {
	
	@Test
	public void createDocumentsTest()	{
		//navigate to documents page
		DocumentsPage dp =new DocumentsPage(driver);
		dp.getCreateDocumentBtn().click();
		
		//click on create documents and enter the details
		CreateDocumentPage cdp= new CreateDocumentPage();
		
		
		//verify the details
	}

}

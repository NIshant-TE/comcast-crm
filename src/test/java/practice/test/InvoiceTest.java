package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)
public class InvoiceTest extends BaseClass{
	
	@Test
	public void createInvoiceTest() {
		System.out.println("execute createInvoiceTest");
		String actTitle =driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("Step 1");		
	}
	

	@Test
	public void createInvoiceWithContactTest() {
		System.out.println("execute createInvoiceTest");
		String actTitle =driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("Step 1");	
	}
	
	

}

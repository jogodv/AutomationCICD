package assignment;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import assignment.pageobjects.CartPageObjects;
import assignment.pageobjects.CheckoutPageObjects;
import assignment.pageobjects.ConfirmationPageObjects;
import assignment.pageobjects.ProductCataloguePageObjects;
import assignment.testcomponents.BaseTest;
import assignment.testcomponents.Retry;


public class ErrorValidations extends BaseTest{
	
	
	@Test(groups= {"Errorhandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() {
	ProductCataloguePageObjects productCataloguePageObject= loginPageObject.login("dummyuser15678@gmail.com", "dummypasSs1");
	String message= loginPageObject.getErrorMessage();
	Assert.assertEquals(message,"Incorrects email or password.");
	}
	
	@Test(groups= {"Errorhandling"})
	public void productErrorValidation() throws IOException, InterruptedException {
		
		String productname="ZARA COAT 3";
		
		ProductCataloguePageObjects productCataloguePageObject= loginPageObject.login("dummy76589dummy@gamil.com", "dummypasS1");
		
		boolean match =productCataloguePageObject.verifyProductInProductPage(productname);
		Assert.assertTrue(match, "product not matching");
		
		
		
		
	}

}

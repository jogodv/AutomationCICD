package assignment;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import assignment.pageobjects.CartPageObjects;
import assignment.pageobjects.CheckoutPageObjects;
import assignment.pageobjects.ConfirmationPageObjects;
import assignment.pageobjects.LoginPageObjects;
import assignment.pageobjects.OrderPageObjects;
import assignment.pageobjects.ProductCataloguePageObjects;
import assignment.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	
	@Test(dataProvider="getData",groups="purschaseOrder")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		ProductCataloguePageObjects productCataloguePageObject= loginPageObject.login(input.get("email"),input.get("password"));
		
		boolean match =productCataloguePageObject.verifyProductInProductPage(input.get("productName"));
		Assert.assertTrue(match, "product not matching");
		
		productCataloguePageObject.addToCart(input.get("productName"));
		
		Thread.sleep(2000);
		
		CartPageObjects cartPageObject=productCataloguePageObject.goToCart();

		boolean match1 =cartPageObject.verifyProductInCartPage(input.get("productName"));
		Assert.assertTrue(match1,"product not present");
		
		CheckoutPageObjects checkoutPageObject=cartPageObject.checkout();
		
		checkoutPageObject.selectCountry("india");
		ConfirmationPageObjects confirmationPageObject=checkoutPageObject.placeOrder();
		
		String message = confirmationPageObject.getMessage();
		Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");
		
		
	}
	
	@Test(dependsOnMethods={"submitOrder"})
	public void verifyOrderHistory() {
		String productname="ZARA COAT 3";
		
		ProductCataloguePageObjects productCataloguePageObject= loginPageObject.login("dummyuser15678@gmail.com", "dummypasS1");
		OrderPageObjects orderPageObject =productCataloguePageObject.goToOrders();
		Assert.assertTrue(orderPageObject.verifyProductInOrderPage(productname));
	}
	
}
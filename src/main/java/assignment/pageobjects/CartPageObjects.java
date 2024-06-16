package assignment.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import assignment.abstractcomponents.BaseComponents;

public class CartPageObjects extends BaseComponents{
	private WebDriver driver;
	
	public CartPageObjects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[class='cartSection'] h3")
	private List <WebElement> productsIncart;
	
	@FindBy(css=".totalRow button")
	private WebElement checkoutButton;
	
	private By productNameBy =By.cssSelector("div[class='cartSection'] h3");

	public boolean verifyProductInCartPage(String productName) {
		
		waitForElementsToAppear(productNameBy);
		boolean match = productsIncart.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	public CheckoutPageObjects checkout() {
		checkoutButton.click();
		CheckoutPageObjects checkoutPageObject =new CheckoutPageObjects(driver);
		return checkoutPageObject;
	}
}

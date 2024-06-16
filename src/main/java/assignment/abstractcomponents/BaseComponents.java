package assignment.abstractcomponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import assignment.pageobjects.CartPageObjects;
import assignment.pageobjects.OrderPageObjects;

public class BaseComponents {
	private WebDriver driver;
	
	public BaseComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	private WebElement cartPageButton;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	private WebElement orderPageButton;

	public void waitForElementsToAppear(By locator) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForElementsToDisappear(By locator) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void waitForElementsToAppear(WebElement element) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void waitForElementsToAppear(List<WebElement> elements) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	public CartPageObjects goToCart() {
		waitForElementsToAppear(cartPageButton);
		cartPageButton.click();
		CartPageObjects cartPageObject = new CartPageObjects(driver);
		return cartPageObject;
	}
	
	public OrderPageObjects goToOrders() {
		waitForElementsToAppear(orderPageButton);
		orderPageButton.click();
		OrderPageObjects orderPageObject =new OrderPageObjects(driver);
		return orderPageObject;
	}
	
	
}

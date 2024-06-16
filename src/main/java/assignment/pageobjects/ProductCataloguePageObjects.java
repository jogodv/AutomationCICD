package assignment.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import assignment.abstractcomponents.BaseComponents;

import org.openqa.selenium.support.FindBy;

public class ProductCataloguePageObjects extends BaseComponents{
	
	private WebDriver driver;
	
	public ProductCataloguePageObjects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".mb-3")
	private List <WebElement> products;
	
	
	
	private By productsBy = By.cssSelector(".mb-3");
	
	private By productText = By.tagName("b");
	
	private By addTocartButton = By.cssSelector("button:nth-last-of-type(1)");
	
	private By toastContainer = By.cssSelector("#toast-container");
	
	private By animation=By.cssSelector(".ng-animating");

	public boolean verifyProductInProductPage(String productname) {
		
		waitForElementsToAppear(products);
		
		boolean m =products.stream().anyMatch(s->s.findElement(productText).getText().equalsIgnoreCase(productname));
		System.out.println(m);
		return m;
		
	}
	
	public void addToCart(String productname) {
		
		products.stream().filter(s->s.findElement(productText).getText().equalsIgnoreCase(productname)).forEach(s->s.findElement(addTocartButton).click());
		waitForElementsToAppear(toastContainer);
		waitForElementsToDisappear(animation);
		
	}
	
	

}

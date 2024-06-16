package assignment.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import assignment.abstractcomponents.BaseComponents;

public class OrderPageObjects extends BaseComponents{
	private WebDriver driver;
	
	public OrderPageObjects(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr/td[2]")
	private List <WebElement> productNames;
	
	public boolean verifyProductInOrderPage(String productName) {
		waitForElementsToAppear(productNames);
		return productNames.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
	}

}

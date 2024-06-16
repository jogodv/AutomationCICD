package assignment.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import assignment.abstractcomponents.BaseComponents;

public class CheckoutPageObjects extends BaseComponents{
	private WebDriver driver;
	public CheckoutPageObjects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	private WebElement countryField;
	
	@FindBy(css=".ta-results button")
	private List<WebElement> countrySuggestions;
	
	@FindBy(css=".action__submit")
	private WebElement placeOrderButton;
	
	private By countryfieldBy=By.cssSelector("input[placeholder='Select Country']");
	
	private By countrySuggestionsBy=By.cssSelector(".ta-results button");

	public void selectCountry(String country) {
		waitForElementsToAppear(countryfieldBy);
		countryField.sendKeys(country);
		waitForElementsToAppear(countrySuggestions);
		countrySuggestions.stream().forEach(s->System.out.println(s.getText()));
		countrySuggestions.stream().filter(s->s.getText().equalsIgnoreCase(country)).limit(1).forEach(s->s.click());
	}
	
	public ConfirmationPageObjects placeOrder() {
		placeOrderButton.click();
		ConfirmationPageObjects confirmationPageObject =new ConfirmationPageObjects(driver);
		return confirmationPageObject;
	}

}

package assignment.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import assignment.abstractcomponents.BaseComponents;

public class ConfirmationPageObjects extends BaseComponents{
	private WebDriver driver;
	
	public ConfirmationPageObjects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="h1")
	private WebElement confirmationMessage;
	
	private By confirmationMessageBy=By.cssSelector("h1");

	public String getMessage() {
		waitForElementsToAppear(confirmationMessageBy);
		String message =confirmationMessage.getText();
		return message;
	}

}

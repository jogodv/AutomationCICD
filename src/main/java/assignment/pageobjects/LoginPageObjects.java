package assignment.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import assignment.abstractcomponents.BaseComponents;




public class LoginPageObjects extends BaseComponents
{
	private WebDriver driver;
	
	
	
	public LoginPageObjects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="userEmail")
	private WebElement userEmail;
	
	@FindBy(id="userPassword")
	private WebElement userPassword;
	
	@FindBy(id="login")
	private WebElement login;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	@FindBy(css=".toast-message")
	WebElement errorMessage;

	
    public ProductCataloguePageObjects login(String email,String password ) {
    	userEmail.sendKeys(email);
    	userPassword.sendKeys(password);
    	login.click();
    	ProductCataloguePageObjects productCataloguePageObject= new ProductCataloguePageObjects(driver);
    	return productCataloguePageObject;
    }
    
    public String getErrorMessage() {
    	waitForElementsToAppear(errorMessage);
    	return errorMessage.getText();
    	
    }
}

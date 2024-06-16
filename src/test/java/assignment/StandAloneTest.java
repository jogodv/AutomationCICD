package assignment;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {
	
	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches",
		     Arrays.asList("disable-popup-blocking"));
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("dummyuser15678@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("dummypasS1");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		products.stream().filter(s->s.findElement(By.tagName("b")).getText().equalsIgnoreCase("ZARA COAT 3")).forEach(s->System.out.println(s.findElement(By.tagName("b")).getText()));
		boolean m =products.stream().anyMatch(s->s.findElement(By.tagName("b")).getText().equalsIgnoreCase("ZARA COAT 3"));
		System.out.println(m);
		Assert.assertTrue(m, "product not matching");
		products.stream().filter(s->s.findElement(By.tagName("b")).getText().equalsIgnoreCase("ZARA COAT 3")).limit(1).forEach(s->s.findElement(By.xpath("//button[text()=' Add To Cart']")).click());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='cartSection'] h3")));
		List<WebElement> productsIncart = driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
		boolean n = productsIncart.stream().anyMatch(s->s.getText().equals("ZARA COAT 3"));
		Assert.assertTrue(n,"product not present");
		driver.findElement(By.cssSelector(".totalRow button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Select Country']")));
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results button")));
		driver.findElements(By.cssSelector(".ta-results button")).stream().forEach(s->System.out.println(s.getText()));
		driver.findElements(By.cssSelector(".ta-results button")).stream().filter(s->s.getText().equalsIgnoreCase("india")).limit(1).forEach(s->s.click());
		driver.findElement(By.cssSelector(".action__submit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
		String message = driver.findElement(By.cssSelector("h1")).getText();
		Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");
		//driver.close();
		
	}
	
}
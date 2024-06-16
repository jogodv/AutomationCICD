package assignment.testcomponents;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import assignment.pageobjects.LoginPageObjects;

public class BaseTest {
	public WebDriver driver;
	public LoginPageObjects loginPageObject;
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop =new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\assignment\\resources\\global.properties");
		prop.load(fis);
		
		String browservalue= System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browservalue= prop.getProperty("browser");
		
		
		
		if (browservalue.contains("chrome")) {
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches",
		     Arrays.asList("disable-popup-blocking"));
		
		if(browservalue.contains("headless")) {
			options.addArguments("headless");
		}
		     driver = new ChromeDriver(options);
		     driver.manage().window().setSize(new Dimension(1440,990));
		}
		
		else if(browservalue.contains("edge")){
			 driver = new EdgeDriver();
		}
		else if(browservalue.contains("firefox")){
			 driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LoginPageObjects launchApplication() throws IOException {
		driver= initializeDriver();
		
		loginPageObject = new LoginPageObjects(driver);
		loginPageObject.goTo();
		return loginPageObject;
	}
	
	@AfterMethod(alwaysRun=true)
	public void close() {
		driver.close();
	}
	
	public List<HashMap<String, String>> jsonToHashMapData(String filePath) throws IOException {
		String jsoncontent =FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List <HashMap<String,String>> data = mapper.readValue(jsoncontent, new TypeReference<List <HashMap<String,String>>>(){} );
		return data;
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		HashMap<String,String> map =new HashMap<String,String>();
		map.put("email", "dummyuser15678@gmail.com");
		map.put("password", "dummypasS1");
		map.put("productName", "ZARA COAT 3");
		
		HashMap<String,String> map1 =new HashMap<String,String>();
		map1.put("email", "dummy76589dummy@gamil.com");
		map1.put("password", "dummypasS1");
		map1.put("productName", "ADIDAS ORIGINAL");
		
		List <HashMap<String,String>> data = jsonToHashMapData(System.getProperty("user.dir")+"\\src\\test\\java\\assignment\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File file =new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
	}
}

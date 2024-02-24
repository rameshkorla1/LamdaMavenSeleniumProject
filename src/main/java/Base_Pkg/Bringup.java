package Base_Pkg;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Bringup {
	//public	RemoteWebDriver	driver			=	null;
	public	static	WebDriver		driver			=	null;
	
	public	String		browserName;
	public	String		browserVersion;
	public	String		osVersion;
	public	String		url;
	public	String		driverMode;
	
	public	Properties	prop;
	
	public	By			simpleFormDemoLink		=	By.linkText("Simple Form Demo");
	public	String		SubString				=	"simple-form-demo";
	public	By			getCheckedValueButton	=	By.id("showInput");
	public	By			enterMessageTextBox		=	By.id("user-message");
	public	String		MessageEntered			=	"Welcome to LambdaTest";
	public	By			yourMessageSection		=	By.xpath("//p[@id='message']");
	public	String		MessageDisplayed;
	
	public	By			InputFormSubmitLink		=	By.linkText("Input Form Submit");
	public	By			submitButton			=	By.xpath("//button[text()='Submit']");
	
	
	public void SetupDriver() throws IOException {
		
		prop				=	new Properties();
		FileInputStream fis	=	new FileInputStream (
			System.getProperty("user.dir") +
			"\\src\\test\\resources\\global.properties");
		prop.load(fis);
		
		browserName		=	prop.getProperty("browserName");
		browserVersion	=	prop.getProperty("browserVersion");
		osVersion		=	prop.getProperty("osVersion");
		//url			=	prop.getProperty("url");
		driverMode		=	prop.getProperty("driverMode");
		
		System.out.println("");
		System.out.println("Browser Name:    " + browserName);
		System.out.println("Browser Version: " + browserVersion);
		System.out.println("OS Version:      " + osVersion);
		//System.out.println("URL:             " + url);
		System.out.println("Driver Mode:     " + driverMode);
		System.out.println("");
		
		if (driverMode.toUpperCase().equals("LOCAL")) {
			setupLocalDriver();
		} else if (driverMode.toUpperCase().equals("REMOTE")) {
			setupRemoteDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
	//	setup Local Driver
	public void setupLocalDriver() {
		if (browserName.toUpperCase().equals("FIREFOX")) {
			//WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.toUpperCase().equals("EDGE")) {
			//WebDriverManager.edgedriver().setup();
			// Configure to prevent the "Prsonalize your web experience" pop up in Edge
			EdgeOptions opt = new EdgeOptions();
			opt.addArguments("--guest");
			driver = new EdgeDriver(opt);   //This PREVENTS the "Prsonalize your web experience" pop up
		} else if (browserName.toUpperCase().equals("CHROME")) {
				//WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
	}
	
	//	setup Remote Driver
	public void setupRemoteDriver() throws MalformedURLException {
		
		final	String hubURL = "https://hub.lambdatest.com/wd/hub";
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		
		ltOptions.put("user", System.getenv("LT_USERNAME"));
        ltOptions.put("accessKey", System.getenv("LT_ACCESS_KEY"));
		ltOptions.put("resolution", "1024x768");
		
		ltOptions.put("build", "LT_Parallel_Build");
		ltOptions.put("project", "LT_Parallel_Project");
		//ltOptions.put("smartUI.project", "LT_Parallel_SmartUI");
		
		ltOptions.put("selenium_version", "4.0.0");
		//ltOptions.put("selenium_version", "4.16.1");
		
		ltOptions.put("name", this.getClass().getName());
		ltOptions.put("networkThrottling", "Regular 4G");
        //ltOptions.put("geoLocation", "US/CHI");
		//ltOptions.put("timezone", "Chicago");
		
		//ltOptions.put("seCdp", true);
		ltOptions.put("network", true);
		ltOptions.put("tunnel", false);
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("w3c", true);
		
		//ltOptions.put("edge.popups", true);
		
		//ltOptions.put("console", "error");
		//ltOptions.put("plugin", "java-java");
		
		if (browserName.toUpperCase().equals("FIREFOX")) {
			FirefoxOptions browserOptions = new FirefoxOptions();
			browserOptions.setPlatformName(osVersion);
			browserOptions.setBrowserVersion(browserVersion);
			browserOptions.addArguments("--guest");
			browserOptions.setCapability("LT:Options", ltOptions);			
			
			driver = new RemoteWebDriver(new URL(hubURL), browserOptions);
			System.out.println("Started FIREFOX browser - from RemoteEebDriver; OS Version: " + osVersion);
		} else if (browserName.toUpperCase().equals("EDGE")) { 
			EdgeOptions browserOptions = new EdgeOptions();
			browserOptions.setPlatformName(osVersion);
			browserOptions.setBrowserVersion(browserVersion);
			browserOptions.addArguments("--guest");
			browserOptions.setCapability("LT:Options", ltOptions);
			
			driver = new RemoteWebDriver(new URL(hubURL), browserOptions);
			System.out.println("Started EDGE browser - from RemoteEebDriver; OS Version: " + osVersion);
		} else if (browserName.toUpperCase().equals("CHROME")) {
			ChromeOptions browserOptions = new ChromeOptions();
			browserOptions.setPlatformName(osVersion);
			browserOptions.setBrowserVersion(browserVersion);
			browserOptions.addArguments("--guest");
			browserOptions.setCapability("LT:Options", ltOptions);
			
			driver = new RemoteWebDriver(new URL(hubURL), browserOptions);
			System.out.println("Started CHROME browser - from RemoteEebDriver: " + osVersion);
		}
		/*
		 *	ChromeOptions browserOptions = new ChromeOptions();
			browserOptions.setPlatformName("Windows 10");
			browserOptions.setBrowserVersion("88.0");
			HashMap<String, Object> ltOptions = new HashMap<String, Object>();
			ltOptions.put("username", "satyasai1.bhagavatula");
			ltOptions.put("accessKey", "4AiQvEoj9hzF0OXfCMnLA89YrVsQQ8k8ue544jAa4ofn8ax0fI");
			ltOptions.put("geoLocation", "US/CHI");
			ltOptions.put("visual", true);
			ltOptions.put("video", true);
			ltOptions.put("network", true);
			ltOptions.put("project", "Untitled");
			ltOptions.put("tunnel", true);
			ltOptions.put("console", "error");
			ltOptions.put("selenium_version", "4.0.0");
			ltOptions.put("w3c", true);
			browserOptions.setCapability("LT:Options", ltOptions);
		 */
		/*
		 * 
		 */
	}
	
	@BeforeTest
	public void LaunchApp() throws IOException {
		SetupDriver();
		driver.get(prop.getProperty("url"));
	}
	
	
	@AfterTest
	 public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
	

}

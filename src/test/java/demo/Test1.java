package demo;

import static org.testng.Assert.assertTrue;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base_Pkg.Bringup;

public class Test1 extends Bringup {
	//public	RemoteWebDriver	driver			=	null;
	public		WebDriver		driver			=	null;
	
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
		
	@Test
	public void Test_1 () throws InterruptedException {
		//SetupDriver();
		//Open_LambdaTestSeleniumPlayground();
		Click_SimpleFormDemo_Link();
		Validate_URL_Contains_String(SubString);
		Set_WelcomeMessage(enterMessageTextBox, MessageEntered);
		Validate_CheckedValue();
	}
	
	// Action Methods	
	public void Open_LambdaTestSeleniumPlayground() throws InterruptedException {
		/*
		 * // Open LambdaTestâ€™s Selenium Playground from //
		 * https://www.lambdatest.com/selenium-playground // // "url" set to
		 * "https://www.lambdatest.com/selenium-playground" // in "global.properties"
		 * file located at: "/src/test/resources" //driver.get(url);
		 * //Thread.sleep(2000);
		 */	}
	public void Click_SimpleFormDemo_Link() throws InterruptedException {
		//	Click â€œSimple Form Demoâ€? under Input Forms
		driver.findElement(simpleFormDemoLink).click();
		Thread.sleep(2000);
	}
	public void Validate_URL_Contains_String(String string) {
		//	Validate that the URL contains â€œsimple-form-demoâ€?
		String currentURL = driver.getCurrentUrl();
		System.out.println("Current URL = " + currentURL);
		assertTrue(currentURL.contains(string));
	}
	public void Set_WelcomeMessage(By msgTextBox, String message) throws InterruptedException {
		//	Create a variable for a string value E.g: â€œWelcome to LambdaTestâ€?
		//	Declared String variable "MessageEntered" and
		//	initialized it to "Welcome to LambdaTest";
		WebElement msgBoxWebElement = driver.findElement(msgTextBox);
		msgBoxWebElement.sendKeys(message);
		Thread.sleep(2000);
		
		// get value attribute with getAttribute()
		String val = msgBoxWebElement.getAttribute("value");
		System.out.println("Message Entered: " + val);
	}
	public void Validate_CheckedValue() {
		//	Click â€œGet Checked Valueâ€?.
		driver.findElement(getCheckedValueButton).click();
		
		MessageDisplayed = driver.findElement(yourMessageSection).getText();
		System.out.println("Message Displayed: " + MessageDisplayed);
		
		//	Validate whether the same text message is displayed in the
		//	right-hand panel under the â€œYour Message:â€? section.
		Assert.assertEquals(MessageDisplayed, MessageEntered);
	}
}
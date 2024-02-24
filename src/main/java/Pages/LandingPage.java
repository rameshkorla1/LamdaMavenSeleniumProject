package Pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LandingPage {
	
	public WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public	By			simpleFormDemoLink		=	By.linkText("Simple Form Demo");
	public	String		SubString				=	"simple-form-demo";
	public	By			getCheckedValueButton	=	By.id("showInput");
	public	By			enterMessageTextBox		=	By.id("user-message");
	public	String		MessageEntered			=	"Welcome to LambdaTest";
	public	By			yourMessageSection		=	By.xpath("//p[@id='message']");
	public	String		MessageDisplayed;
	
	public	By			InputFormSubmitLink		=	By.linkText("Input Form Submit");
	public	By			submitButton			=	By.xpath("//button[text()='Submit']");
	
	
	public void Click_SimpleFormDemo_Link() throws InterruptedException {
		//	Click â€œSimple Form Demoâ€� under Input Forms
		driver.findElement(simpleFormDemoLink).click();
		Thread.sleep(2000);
	}
	public void Validate_URL_Contains_String(String string) {
		//	Validate that the URL contains â€œsimple-form-demoâ€�
		String currentURL = driver.getCurrentUrl();
		System.out.println("Current URL = " + currentURL);
		assertTrue(currentURL.contains(string));
	}
	public void Set_WelcomeMessage(By msgTextBox, String message) throws InterruptedException {
		//	Create a variable for a string value E.g: â€œWelcome to LambdaTestâ€�
		//	Declared String variable "MessageEntered" and
		//	initialized it to "Welcome to LambdaTest";
		WebElement msgBoxWebElement = driver.findElement(msgTextBox);
		msgBoxWebElement.sendKeys(message);
		Thread.sleep(2000);
		
		// get value attribute with getAttribute()
		String val = msgBoxWebElement.getAttribute("value");
		System.out.println("Message Entered: " + val);
	}
	public void Validate_CheckedValue() 
	{
		//	Click â€œGet Checked Valueâ€�.
		driver.findElement(getCheckedValueButton).click();		
		MessageDisplayed = driver.findElement(yourMessageSection).getText();
		System.out.println("Message Displayed: " + MessageDisplayed);		
		//	Validate whether the same text message is displayed in the
		//	right-hand panel under the â€œYour Message:â€� section.
		Assert.assertEquals(MessageDisplayed, MessageEntered);
	}
	
	// Action Methods	
		public void Click_InputFormSubmit_Link()throws InterruptedException 
		{
			//	Click â€œInput Form Submitâ€� under Input Forms
			driver.findElement(InputFormSubmitLink).click();
			Thread.sleep(2000);
		}
		public void Click_Submit_without_EnteringData() throws InterruptedException 
		{
			driver.findElement(submitButton).click();
			Thread.sleep(5000);
		}

}

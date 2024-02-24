package demo;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base_Pkg.Bringup;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test3	extends Bringup { 	
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
	public void Test_3 () throws InterruptedException {

		//SetupDriver();
		//Open_LambdaTestSeleniumPlayground()
		Click_InputFormSubmit_Link();
		Thread.sleep(2000);
		Click_Submit_without_EnteringData();
		/*
		3. Assert â€œPlease fill in the fieldsâ€? error message.
		4. Fill in Name, Email, and other fields.
		5. From the Country drop-down, select â€œUnited Statesâ€? using the text
		property.
		6. Fill all fields and click â€œSubmitâ€?.
		7. Once submitted, validate the success message â€œThanks for contacting
		us, we will get back to you shortly.â€? on the screen.
		*/
	}
	
	// Action Methods	
	public void Click_InputFormSubmit_Link()throws InterruptedException {
		//	Click â€œInput Form Submitâ€? under Input Forms
		driver.findElement(InputFormSubmitLink).click();
		Thread.sleep(2000);
	}
	public void Click_Submit_without_EnteringData() throws InterruptedException {
		driver.findElement(submitButton).click();
		Thread.sleep(5000);
	}
}
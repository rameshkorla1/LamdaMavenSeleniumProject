package Scenarios;

import org.testng.annotations.Test;

import Base_Pkg.Bringup;
import Pages.LandingPage;

public class TestScenario1 extends Bringup{
	
	LandingPage landingPage;
	
	@Test
	public void Test_1 () throws InterruptedException {
		//SetupDriver();
		//Open_LambdaTestSeleniumPlayground();
		landingPage = new LandingPage(driver);
		landingPage.Click_SimpleFormDemo_Link();
		landingPage.Validate_URL_Contains_String(SubString);
		landingPage.Set_WelcomeMessage(enterMessageTextBox, MessageEntered);
		landingPage.Validate_CheckedValue();
	}

}

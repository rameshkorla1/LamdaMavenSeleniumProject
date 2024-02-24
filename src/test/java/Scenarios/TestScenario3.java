package Scenarios;

import org.testng.annotations.Test;

import Base_Pkg.Bringup;
import Pages.LandingPage;

public class TestScenario3 extends Bringup {
	
	LandingPage landingPage;
	
	@Test
	public void Test_3 () throws InterruptedException {

		//SetupDriver();
		landingPage = new LandingPage(driver);
		landingPage.Click_InputFormSubmit_Link();
		Thread.sleep(2000);
		landingPage.Click_Submit_without_EnteringData();
	}

}

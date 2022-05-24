package com.org.tests.selectisa;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.util.Date;
import java.util.List;

import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_101 extends BaseTest {

	@Override
	@Test(description = "Login Page Test")
	public void Open_Browser_Window() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("app_url");
		System.out.println("URL::"+app_url);
		test.launchApplication(app_url); 
		test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(), getData("Auth.ip").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		TestDataPropertyReaderAndWriter.clearPropertyFile();
		
	}

}

package com.org.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.org.automation.getpageobjects.GetPage;

public class Login_Page_Actions extends GetPage {

	WebDriver driver;
	static String pagename = "BD_Login_Page";

	public Login_Page_Actions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}

	public void verifyUserIsOnBDLoginPage() {
		verifyPageTitleExact();
		logMessage("[ASSERTION PASSED]: Verified User is on BD Login Page !!");
	}

	public void LoginToTheBDApplication(String userName, String password, String ip) {
		_verifyUserNameInputFieldAndEnterUserName(userName);
		_verifyPasswordInputFieldAndEnterPassword(password);
		try {
			wait.waitForElementToBeClickable(element("btn_signin"));
			logMessage("Verified Sign In button on BD Login Page");
			element("btn_signin").click();
			logMessage("Clicked on Sign In button");
		} catch (Exception e) {
			e.printStackTrace();
			wait.waitForElementToBeClickable(element("btn_signin"));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("btn_signin"));
			logMessage("Clicked on Sign In button");
		}
		
	}
	
	public void clickNextButton() {
		isElementDisplayed("button_next");
		wait.waitForElementToBeClickable(element("button_next"));
		logMessage("Verified next button");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("button_next"));
		wait.hardWait(5);

		}

	private void _verifyIPInputFieldAndEnterIP(String ip) {
		isElementDisplayed("inp_ip");
		logMessage("Verified IP Address Input Field on BD Login Page");
		enterTextInField(element("inp_ip"), ip);
		logMessage("Input IP in IP Address Input Field on BD Login Page");
	}

	private void _verifyPasswordInputFieldAndEnterPassword(String password) {
		isElementDisplayed("inp_password");
		logMessage("Verified Password Input Field on BD Login Page");
		enterTextInField(element("inp_password"), password);
		logMessage("Input Password in Password Input Field on BD Login Page");

	}

	private void _verifyUserNameInputFieldAndEnterUserName(String userName) {
		isElementDisplayed("inp_username");
		logMessage("Verified User Name Input Field on BD Login Page");
		enterTextInField(element("inp_username"), userName);
		logMessage("Input User name in User Name Input Field on BD Login Page");
		wait.waitForElementToBeClickable(element("btn_signin"));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("btn_signin"));
	}

	public void _logoutApplication(String user, String button1, String button2) {
		
		isElementDisplayed("link_username", user);
		wait.waitForElementToBeClickable(element("link_username", user));
		logMessage("Verified User Name");
		
		click(element("link_username", user));
		hardWaitForChromeBrowser(5);
		
		isElementDisplayed("button_logout", button1);
		wait.waitForElementToBeClickable(element("button_logout", button1));
		logMessage("Verified logout button");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("button_logout", button1));
		hardWaitForChromeBrowser(5);
		
		isElementDisplayed("button_logout", button2);
		wait.waitForElementToBeClickable(element("button_logout", button2));
		logMessage("Verified confirm button");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("button_logout", button2));
		System.out.println("control reached here");
		//wait.applyFluentWait(getLocator("inp_username"), 80, 500);
	}

	
	public void selectValueFromDropDown(String fieldName, String data) {
		wait.waitForElementToBeClickable(element("second_sort", fieldName));
		selectProvidedTextFromDropDown(element("second_sort", fieldName), data);
		Assert.assertEquals(getSelectedTextFromDropDown(element("second_sort", fieldName)), data);
	}

	public void verifyErrorMessageForWrongCredentials() {
		isElementDisplayed("wrong_credentials");
		logMessage("Verified error message");
	}

	public void verifyUserIsOnBDRemoteWebOrderLoginPage() {
		
		Assert.assertEquals(driver.getTitle(), "Log in to continue");
		logMessage("[ASSERTION PASSED]: Verified User is on BD RWO Login Page !!");
		
	}
}

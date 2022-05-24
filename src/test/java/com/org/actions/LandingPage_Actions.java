package com.org.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;

import org.openqa.selenium.WebDriver;

import com.org.automation.getpageobjects.GetPage;

public class LandingPage_Actions extends GetPage {
	
	WebDriver driver;
	static String pagename = "Landing_Page";
	private boolean flag = false;

	public LandingPage_Actions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}

	public boolean verifyUserIsOnLandingPage(String text) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 210);
		wait.waitForElementToBeVisible(element("text_landing_page", text));
		isElementDisplayed("text_landing_page", text);
		element("text_landing_page", text);
		logMessage("User is navigated to Landing Page");
		flag = true;
		return flag;
	}

	public boolean navigateToMenu(String menu) {
		wait.waitForLoaderToBeInvisible(getLocator("loading"), 60);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 600);
		isElementDisplayed("link_menu", menu);
		wait.waitForElementToBeClickable(element("link_menu", menu));
		
		try {
			clickUsingXpathInJavaScriptExecutor(element("link_menu", menu));
		} catch (ElementClickInterceptedException e) {
			pageRefresh();
			clickUsingXpathInJavaScriptExecutorSingleClick(element("link_menu", menu));
		} catch (Exception e) {
			element("link_menu", menu).click();
		}
		logMessage("User clicked on Menu Link: " + menu);
		wait.waitForLoaderToBeInvisible(getLocator("loading"), 120);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		flag = true;
		return flag;
	}

	public void navigateToItemManagementFeature(String featureName) {
		wait.waitForLoaderToBeInvisible(getLocator("loading"), 40);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 2000);
		element("link_menu", "Settings").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 2000);
		isElementDisplayed("link_features_name", featureName.trim());
		wait.waitForElementToBeClickable(element("link_features_name", featureName.trim()));
		clickUsingXpathInJavaScriptExecutor(element("link_features_name", featureName.trim()));
		logMessage("User clicked on Link" + featureName.trim());
		wait.waitForLoaderToBeInvisible(getLocator("loading"), 210);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 210); // don't remove or comment this loader
	}

	public void navigateToFeature(String featureName) {
		wait.waitForLoaderToBeInvisible(getLocator("loading"), 40);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 2000);
		
		try {
			element("link_menu", "Settings").click();
		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("link_menu", "Settings"));
		}
		
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 2000);
		
		isElementDisplayed("link_features_name", featureName.trim());
		wait.waitForElementToBeClickable(element("link_features_name", featureName.trim()));
		clickUsingXpathInJavaScriptExecutor(element("link_features_name", featureName.trim()));
		
		if(isElementNotDisplayed("link_features_name", featureName.trim())) {
			element("link_features_name", featureName.trim()).click();
		}
		
		logMessage("User clicked on Link - " + featureName.trim());
		wait.waitForLoaderToBeInvisible(getLocator("loading"), 180);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 240); // don't remove or comment this loader
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 600);
		
	}

	public void navigateToFeaturewithSingleClick(String featureName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		isElementDisplayed("link_features_name", featureName.trim());
		wait.waitForElementToBeClickable(element("link_features_name", featureName.trim()));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("link_features_name", featureName.trim()));
		logMessage("User clicked on Link" + featureName.trim());

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120); // don't remove or comment this loader
	}

	public void verifyUserPermissions(String[] perm, String role) {

		for (String p : perm) {
			try {
				isElementDisplayed("link_features_name", p);
				logMessage(p + " " + "Permission is avaiable on UI for Role" + " " + role);

			} catch (Exception e) {
				logMessage(p + " " + "Permission is not avaiable on UI for Role" + " " + role);
				continue;
			}
		}

	}

	public boolean LandingOnLinkPageFromSetting() {

		try {
			wait.waitForElementToBeVisible(element("settingLandingHeading"));
			click(element("SettingMenu"));
			element("facilityLink").click();
			wait.waitForElementToBeVisible(element("facilityLanding"));
			boolean check = isElementDisplayed("facilityLanding");
			flag = true;
			return flag;

		} catch (Exception e) {
			flag = true;
			return flag;

		}

	}

	public boolean helpIconAvailabe() {
		wait.waitForElementToBeVisible(element("helpIcon"));
		isElementDisplayed("helpIcon");
		return true;
	}
	
	public void clickLinkWithText(String text) {
		
	}

}

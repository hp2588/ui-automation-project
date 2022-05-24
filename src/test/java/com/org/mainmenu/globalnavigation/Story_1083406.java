package com.org.mainmenu.globalnavigation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;

public class Story_1083406 extends BaseTest {

	String[] columnHeaders = { "Main Menu", "Transaction Queue", "Purchasing Dashboard", "Destination Fulfillment",
			"Item Management", "Settings" };
	String nothighlightedcolor, highlightcolor;

	@Test(priority = 1, description = "VPLX:Global Navigation:[UI] User checks the navigation order of the Menu Items")
	public void Test01_1129265() {

		test.siteConfigurationAction.verifyLinkHeader(Arrays.asList(columnHeaders));

	}

	@Test(priority = 2, description = "VPLX:Global Navigation:[UI] User checks the navigation order of the Menu Items")
	public void Test02_1129264() {

		nothighlightedcolor = test.siteConfigurationAction.getcolor("Transaction Queue");
		highlightcolor= test.siteConfigurationAction.getcolor("Main Menu");
		Assert.assertNotEquals(nothighlightedcolor, highlightcolor);

		
	}
	@Test(priority = 3, description = "VPLX:Global Navigation:[UI]User clicks on Main menu item and link takes them to the corresponding page")
	public void Test03_1127511() {
		test.landingPageActions.navigateToFeature("Custom Labels");
		test.supportDataActions.verifyLabelIsPresent("Custom Labels");
	}
	
	@Test(priority = 4, description = "VPLX: Global Navigation-[UI] On clicking a link on the side navigation panel the respective page should load in the settings tab")
	public void Test04_1092639() {
		//test.landingPageActions.navigateToFeaturewithSingleClick("Settings");
		nothighlightedcolor = test.siteConfigurationAction.getcolor("Transaction Queue");
		highlightcolor= test.siteConfigurationAction.getcolor("Settings");
		Assert.assertNotEquals(nothighlightedcolor, highlightcolor);
	}
	
	@Test(priority = 5, description = "VPLX: Global Navigation-[UI] On opening any link via side navigation panel only the page will refresh the side panel will not refresh")
	public void Test05_1094075() {
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Settings");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		
		
	}
	
	@Test(priority = 6, description = "VPLX: Global Navigation-[UI] Page opened  in the settings page should be highlighted in the side navigation panel")
	public void Test06_1094062() {
		test.landingPageActions.navigateToFeaturewithSingleClick("Settings");
		nothighlightedcolor = test.siteConfigurationAction.getcolor("Transaction Queue");
		highlightcolor= test.siteConfigurationAction.getcolor("Settings");
		Assert.assertNotEquals(nothighlightedcolor, highlightcolor);
		
	}
	
	@Test(priority = 7, description = "VPLX: Global Navigation-[UI] Side navigation panel should can be expanded and minimized")
	public void Test07_1092642() {
		
		test.landingPageActions.navigateToFeaturewithSingleClick("Settings");
	
		Assert.assertTrue(test.siteConfigurationAction.verifySettingsPageHeader("Settings per facility"),
				"[ASSERTION FAILED]: Page is not opened");
		test.landingPageActions.navigateToFeaturewithSingleClick("Settings");
		Assert.assertFalse(test.siteConfigurationAction.verifySettingsPageHeader("Settings per facility"),
				"[ASSERTION FAILED]: Page remains opened");
	}
	
	@Test(priority = 8, description = "VPLX: Global Navigation-[UI] Side navigation panel should can be expanded and minimized")
	public void Test08_1092602() {
	
		test.landingPageActions.navigateToFeaturewithSingleClick("Settings");
		Assert.assertTrue(test.siteConfigurationAction.verifySettingsPageHeader("Settings per facility"),
				"[ASSERTION FAILED]: Heading not displayed");
		Assert.assertTrue(test.siteConfigurationAction.verifySettingsPageHeader("Settings used across your Health System"),
				"[ASSERTION FAILED]: Heading not displayed");
		
		
		
	}
	
	@Test(priority = 9, description = "VPLX:Global Navigation:[UI]User clicks on Navigation links then text color changes and underlined also.")
	public void Test09_1129263() {
		test.landingPageActions.navigateToFeature("Item Management");
		nothighlightedcolor = test.siteConfigurationAction.getcolor("Transaction Queue");
		highlightcolor= test.siteConfigurationAction.getcolor("Item Management");
		Assert.assertNotEquals(nothighlightedcolor, highlightcolor);
		
			
	}
	
	
	
}

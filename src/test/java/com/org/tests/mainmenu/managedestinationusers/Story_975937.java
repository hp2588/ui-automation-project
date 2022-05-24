package com.org.tests.mainmenu.managedestinationusers;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;
import com.org.smoketests.Map_Facility_To_User;

public class Story_975937 extends BaseTest {
	
	String destinationName, destinationCode;
	List<String> actualUserNames;
	List<String> expectedUserNames;
	

	@Test(priority = 0, description = "VPLX:Manage Destinations-Users:[UI]:User verifies the Show All toggle while searching user on Add Destination screen.")
	public void Test00_1058605(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-Users:[UI]:User verifies the Show All toggle while searching user on Add Destination screen.");
		AddDestination();
		test.siteConfigurationAction.clickTabWithoutWait("Users");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.verifyUserAvailableInList();
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show All").contains("false"));
	}

	

	@Test(priority = 1, description = "VPLX:Manage Destinations-Users:[UI]:User Switch off the Show All toggle so Pop up message is displayed while no user is active.")
	public void Test01_1058609(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"PLX:Manage Destinations-Users:[UI]:User Switch off the Show All toggle so Pop up message is displayed while no user is active.");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		//test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Show All", false);
		Assert.assertTrue(test.siteConfigurationAction.verifyMessageWhenToggelIsOnOrOff(
				"There is no active user. Switch toggle to see list of inactive users and activate it."));
	}

	@Test(priority = 2, description = "VPLX:Manage Destinations-Users:[UI]:User verifies the Available Users headline while assigning users to the destination.")
	public void Test02_1058633(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-Users:[UI]:User verifies the Available Users headline while assigning users to the destination.");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.verifyUserAvailableInList();
		test.siteConfigurationAction.selectUserForRemoteOrder(getData("RemoteWebOrder.UserName"));
		
		//Assert.assertTrue(test.siteConfigurationAction.verifyLabelHeaderOnUserTab("Available Users"));
	}

	@Test(priority = 3, description = "VPLX:Manage Destinations-Users:[UI]:Verify the combined list of users when Show All toggle is on.")
	public void Test03_1058652(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-Users:[UI]:Verify the combined list of users when Show All toggle is on.");
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Show All", true);
		Assert.assertFalse(test.siteConfigurationAction.verifyMessageWhenToggelIsOnOrOff(
				"There is no active user. Switch toggle to see list of inactive users and activate it."));
		test.siteConfigurationAction.verifyTableIsDisplayedWhenToggleIsOn();
	}

	@Test(priority = 4, description = "VPLX:Manage Destinations-Users:[UI]: Verify that checkbox is associated alongside of each username on each row.")
	public void Test04_1058985(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-Users:[UI]: Verify that checkbox is associated alongside of each username on each row.");
		expectedUserNames = test.siteConfigurationAction.getListOfAvailableUsers();
		test.siteConfigurationAction.verifyCheckboxIsAssociatedWithEachUserName(expectedUserNames);
	}

	@Test(priority = 5, description = "VPLX:Manage Destinations-Users:[UI]: Verify that list of users is displayed in ascending alphabetical order.")
	public void Test05_1058986(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-Users:[UI]: Verify that list of users is displayed in ascending alphabetical order.");
		actualUserNames = test.siteConfigurationAction.getListOfAvailableUsers();
		Collections.sort(expectedUserNames);
		Assert.assertEquals(actualUserNames, expectedUserNames);
	}

	@Test(priority = 6, description = "VPLX:Manage Destinations-Users:[UI]: Verify that User clicks on sort arrow so list of users is displayed in ascending & descending order.")
	public void Test06_1058986(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-Users:[UI]: Verify that User clicks on sort arrow so list of users is displayed in ascending & descending order.");
		test.siteConfigurationAction.clickOnSortedIcon("Users", "desc");
		actualUserNames = test.siteConfigurationAction.getListOfAvailableUsers();
		Collections.sort(expectedUserNames, Collections.reverseOrder());
		Assert.assertEquals(actualUserNames, expectedUserNames);

		test.siteConfigurationAction.clickOnSortedIcon("Users", "asc");
		actualUserNames = test.siteConfigurationAction.getListOfAvailableUsers();
		Collections.sort(expectedUserNames);
		Assert.assertEquals(actualUserNames, expectedUserNames);
	}

	@Test(priority = 7, description = "VPLX:Manage Destinations-Users:[UI]: Verify the list of active users when Show All toggle is off.")
	public void Test07_1058995(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-Users:[UI]: Verify the list of active users when Show All toggle is off.");
		test.siteConfigurationAction.selectCheckboxCorresspondingToName(expectedUserNames.get(0), true);
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Show All", false);
		actualUserNames = test.siteConfigurationAction.getListOfAvailableUsers();
		Assert.assertTrue(actualUserNames.size() == 1);
		Assert.assertEquals(actualUserNames.get(0), expectedUserNames.get(0));
	}

	@Test(priority = 8, description = "PLX:Manage Destinations-Users:[UI]:Verify the list of users by searching user name.")
	public void Test08_1058637(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"PLX:Manage Destinations-Users:[UI]:Verify the list of users by searching user name.");
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Show All", true);
		test.siteConfigurationAction.VerifyAndSearchText(expectedUserNames.get(1));
		actualUserNames = test.siteConfigurationAction.getListOfAvailableUsers();
		System.out.println("List After Search::" + actualUserNames);
		Assert.assertEquals(actualUserNames.get(1), expectedUserNames.get(1));
	}

	@Test(priority = 9, description = "VPLX:Manage Destinations-Users:[UI]: User clicks on save button so changes updated successfully.")
	public void Test9_1059008(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-Users:[UI]: User clicks on save button so changes updated successfully.");
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
	}
	
	private void AddDestination() throws Throwable {
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.General")));
		Assert.assertTrue(test.siteConfigurationAction.toggleIsActiveOrNot("activeFlag"),
				"[ASSERTION FAILED]: Toggle is inactive in General Tab on Add destination screen");
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Distributor_Accounts")));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Contact")));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Users")));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Items")));
		test.siteConfigurationAction.selectFacilityForDestinationDropDown("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Contact")));

		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();

		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Distributor_Accounts")));
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Items")));
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Users")));

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");

		test.siteConfigurationAction.clickOnEditLinkCorresspondingToDestinationName(destinationName);
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"),
				"[ASSERTION FAILED]: Toggle is inactive in General Tab on Edit destination screen");
		test.siteConfigurationAction.clickActiveToggle("Active");
		Assert.assertFalse(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"),
				"[ASSERTION FAILED]: Toggle is active in General Tab on Edit destination screen");
		test.siteConfigurationAction.clickActiveToggle("Active");
		
	}

}

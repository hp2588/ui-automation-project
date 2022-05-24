package com.org.tests.mainmenu.computers;

import static com.org.automation.utils.YamlReader.getData;
import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_999275 extends BaseTest {
	
	String computerName_old, computerName_new;
	String nodataMessage = "No Matching Results.";
	String computerName, computerName1;

	ArrayList<String> previous_data, sorted_data;

	@Test(priority = 1, description = "VPLX:Manage Computers:Record is displaying when user search the data on search screen")
	public void Test01_1016938(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers:Record is displaying when user search the data on search screen");
		test.landingPageActions.navigateToFeature("Computers");
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.verifyAddComputerPopup("Add Computer");
		test.siteConfigurationAction.clickRadioComputerButton();
		test.siteConfigurationAction.verifyFields();
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);
		computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("computerName",
				"AutomationUI-Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(computerName);
	}
	
	
	@Test(priority = 2, description = "VPLX:Manage Computers: [UI]: Active records are displaying by default on page load")

	public void Test02_10169343(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers: [UI]: Active records are displaying by default on page load");
		test.supportDataActions.verifyDispenseUnitStatusAsActive();
	}

	@Test(priority = 3, description = "VPLX:Manage Computers:User verifies the search message when no records are found")
	public void Test03_1016946(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers:User verifies the search message when no records are found");
		test.supportDataActions.enterSearchTermInSearchField(computerName.concat("abc"), "search");
		computerName_new = test.supportDataActions.getNoDataText();
		Assert.assertEquals(computerName_new, nodataMessage);
		test.siteConfigurationAction.clearText("search");

	}

	@Test(priority = 4, description = "VPLX:Manage Computers:User verifies the records on list page as per the last updated date/time")
	public void Test04_1016947(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers:User verifies the records on list page as per the last updated date/time");
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.verifyAddComputerPopup("Add Computer");
		test.siteConfigurationAction.clickRadioComputerButton();
		test.siteConfigurationAction.verifyFields();
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);
		computerName1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("computerName",
				"AutomationUI-Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(computerName1);
	}

	@Test(priority = 5, description = "VPLX:Manage Computers:User verifies the sorting on the page on view and search computers")
	public void Test05_1016950(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers:User verifies the sorting on the page on view and search computers");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();

		test.siteConfigurationAction.verifyAndClickSortIcon("Status");
		previous_data = test.siteConfigurationAction.captureDataForParticularColumn("Status");
		sorted_data = test.siteConfigurationAction.sortDataForParticularColumnInAscendingOrder("Status");
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Status column data is not sorted in ascending order");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();

	}

	@Test(priority = 6, description = "VPLX: [UAT]: Manage Computers: [UI] User verifies the search on the page when marking the toggle as off and on")
	public void Test06_1037213(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: [UAT]: Manage Computers: [UI] User verifies the search on the page when marking the toggle as off and on");
		test.siteConfigurationAction.verifyAndClickSortIcon("Name");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();

	}

	@Test(priority = 7, description = "Test Case 1016943:VPLX:Manage Computers:Active records are displaying by default on page load")
	public void Test07_1016943(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"Test Case 1016943:VPLX:Manage Computers:Active records are displaying by default on page load");
		test.siteConfigurationAction.verifyAndClickSortIcon("Status");
		previous_data = test.siteConfigurationAction.captureDataForParticularColumn("Status");
		System.out.println(previous_data);
	}
	
	@Test(priority = 8, description = "VPLX:Manage Computers: [UI]: User verifies the validation on Search page for search field on computers")
	public void Test08_1016944_And_1129306(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers: [UI]: User verifies the validation on Search page for search field on computers");
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.verifyAddComputerPopup("Add Computer");
		test.siteConfigurationAction.clickRadioComputerButton();
		test.siteConfigurationAction.verifyFields();
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);
		computerName1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("computerName",
				"computer@12345@#" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(computerName1);
		test.supportDataActions.enterSearchTermInSearchField(computerName1, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(computerName1, "1"));
		
	}
	
	@Test(priority = 9, description = "VPLX:Manage Computers: UI :User clicks on mobile device option on add computer screen and check for the mandatory fields")
	public void Test09_1016989(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers: UI :User clicks on mobile device option on add computer screen and check for the mandatory fields");
		
		test.siteConfigurationAction.clearInputBox("scheduleSearch");
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.verifyAddComputerPopup("Add Computer");
		test.siteConfigurationAction.clickRadioMobileButton();
		test.siteConfigurationAction.verifyFields();
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownForExternalSystem("facilityModelKey", "Select");
		test.supportDataActions.verifyErrorMessageDosageCode("Facility cannot be empty");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);
		computerName1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("computerName",
				"AutomationUI-Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.clearInputBox("computerName");
		test.supportDataActions.verifyErrorMessageDosageCode("Computer Name cannot be empty");
		computerName1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("computerName",
				"AutomationUI-Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		test.siteConfigurationAction.clearInputBox("ipAddress");
		test.supportDataActions.verifyErrorMessageDosageCode("IP Address cannot be empty");
		
}
}

package com.org.tests.mainmenu.computers;

import org.testng.annotations.Test;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_999276 extends BaseTest {
	String[] mandatoryFields = { "Facility", "Computer Name", "IP Address" };
	String[] nonmandatoryfields = { "useScanFixFlag" };
	String ipAdd;
	String[] invalidmessagefields = { "IP Address", "Computer Name" };

	// Error message will not be displayed as Save button is disabled by default as
	// per new changes.

	@Test(priority = 1, description = "VPLX:Manage Computers:User verifies the mandatory fields on the Add computer screen")
	public void Test01_1016974(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers:User verifies the mandatory fields on the Add computer screen");
		test.landingPageActions.navigateToFeature("Computers");
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.verifyAddComputerPopup("Add Computer");
		test.siteConfigurationAction.verifyFields();
		test.siteConfigurationAction.verifyFieldIsMandatory("ipAddress");
		test.siteConfigurationAction.verifyFieldIsMandatory("computerName");
		test.siteConfigurationAction.verifyFieldIsMandatory("facilityModelKey");
		test.siteConfigurationAction.verifyInputFieldIsBlankforComputer("ipAddress");
		test.siteConfigurationAction.verifyInputFieldIsBlankforComputer("computerName");
		// test.siteConfigurationAction.clickSaveButton();
		// test.siteConfigurationAction.verifyErrorMessageForMandatoryFields(Arrays.asList(mandatoryFields));
		test.siteConfigurationAction.verifyAddPrinterPopupGetsClosedOnClickingCancelButton();

	}

	@Test(priority = 2, description = "VPLX:Manage Computers:User creates an active record on  add computer screen")
	public void Test02_1016975_And_1129311(Method method)
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers:User creates an active record on  add computer screen");
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.verifyAddComputerPopup("Add Computer");
		test.siteConfigurationAction.clickRadioComputerButton();
		test.siteConfigurationAction.verifyFields();
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);
		String computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewComputerNameInList(computerName);
	}

	@Test(priority = 3, description = "VPLX:Manage Computers:User creates an inactive record on  add computer screen for mobile device option")
	public void Test03_1016979(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers:User creates an inactive record on  add computer screen for mobile device option");
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.verifyAddComputerPopup("Add Computer");
		test.siteConfigurationAction.clickActiveComputerToggle();
		test.siteConfigurationAction.clickRadioMobileButton();
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		String computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		// test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.verifyNewComputerNameInList(computerName);
		test.supportDataActions.enterSearchTermInSearchField(computerName, "search");

		test.siteConfigurationAction.verifyNewlyAddedHoldReasonStatus(computerName, "Active");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.supportDataActions.clearSearchBoxField("search");

	}

	@Test(priority = 4, description = "VPLX:Manage Computers:User verifies the non mandatory fields on  add computer screen")
	public void Test04_1016983(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers:User verifies the non mandatory fields on  add computer screen");
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.verifyAddComputerPopup("Add Computer");
		test.siteConfigurationAction.clickRadioComputerButton();
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);
		String computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		// test.siteConfigurationAction.verifyCheckboxFieldOnAddNewComputerPopup("useScanFixFlag");
		test.siteConfigurationAction.verifyCheckboxFieldOnAddNewComputerPopup("controlCaraouselISA");
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(computerName);
		// test.siteConfigurationAction.verifyNewComputerNameInList(computerName);
		test.supportDataActions.enterSearchTermInSearchField(computerName, "search");
		test.siteConfigurationAction.verifyNewlyAddedHoldReasonStatus(computerName, "Active");
		test.supportDataActions.clearSearchBoxField("search");

	}

	@Test(priority = 5, description = "VPLX:Manage Computers:User clicks  on  cancel button or close icon on  add computer screen\r\n")
	public void Test05_1016988(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers:User verifies the non mandatory fields on  add computer screen");
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);
		test.siteConfigurationAction.verifyAddPrinterPopupGetsClosedOnClickingCancelButton();

	}

	@Test(priority = 6, description = "VPLX:Manage Computers:User clicks  on  mobile device option, verifies the non mandatory fields on add computer screen")
	public void Test06_1016990(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers:User verifies the non mandatory fields on  add computer screen");
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.verifyAddComputerPopup("Add Computer");
		test.siteConfigurationAction.clickRadioMobileButton();
		// Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("useScanFixFlag"));

	}

	@Test(priority = 7, description = "VPLX:Manage Computers:User creates an active record for mobile device  on  add computer screen")
	public void Test07_1016991(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers:User verifies the non mandatory fields on  add computer screen");
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.verifyAddComputerPopup("Add Computer");
		test.siteConfigurationAction.clickRadioMobileButton();
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		String computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(computerName);
		// test.siteConfigurationAction.verifyNewComputerNameInList(computerName);
		test.supportDataActions.enterSearchTermInSearchField(computerName, "search");
		test.siteConfigurationAction.verifyNewlyAddedHoldReasonStatus(computerName, "Active");
		test.supportDataActions.clearSearchBoxField("search");

	}

	@Test(priority = 8, description = "VPLX:Manage Computers:User creates an inactive record on  add computer screen")
	public void Test08_1016992(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers:User creates an inactive record on  add computer screen");
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.clickRadioComputerButton();
		test.siteConfigurationAction.clickActiveComputerToggle();
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		String computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "AutomationUI-Computer" + System.currentTimeMillis());
		// test.siteConfigurationAction.verifyCheckboxFieldOnAddNewComputerPopup("useScanFixFlag");
		// test.siteConfigurationAction.selectUseScanCheckboxForComputer();
		test.siteConfigurationAction.selectControlCarouselCheckboxForComputer();
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		// test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(computerName);
		// test.siteConfigurationAction.verifyNewComputerNameInList(computerName);
		test.supportDataActions.enterSearchTermInSearchField(computerName, "search");
		//test.siteConfigurationAction.verifyNewlyAddedHoldReasonStatus(computerName, "Inactive");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.supportDataActions.clearSearchBoxField("search");

	}

	@Test(priority = 9, description = "VPLX:Manage Computers:User clicks  on  cancel button or close icon on  add computer screen for mobile device option")
	public void Test09_1016993(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers:User clicks  on  cancel button or close icon on  add computer screen for mobile device option");
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.clickRadioMobileButton();
		test.siteConfigurationAction.verifyAddPrinterPopupGetsClosedOnClickingCancelButton();
	}

	@Test(priority = 10, description = "VPLX:Manage Computers: User is able to enter only 50 chars for computer name")

	public void Test10_1016998(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers: User is able to enter only 50 chars for computer name");
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.verifyFields();
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlankforComputer("computerName"),
				"[ASSERTION FAILED]: Input Field  is not blank by default");
		String computerText = test.siteConfigurationAction.getAlphaNumericString(50);
		String computerName = test.siteConfigurationAction.enterDataInInputField("computerName", computerText);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());

		test.siteConfigurationAction.clickSaveButton();
	}

	@Test(priority = 11, description = "VPLX:Manage Computers: Error displayed on entering invalid ip address on add computer screen")
	public void Test11_1016999(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers: Error displayed on entering more than 50 characters on computer name field on Add computer screen");
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.verifyFields();
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				getData("ComputerDetails.InvalidIpAddress"));
		String computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "AutomationUI-Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.verifyErrorMessageForIncorrectFieldsComputer(Arrays.asList(invalidmessagefields),
				getData("ComputerDetails.IncorrectIPMessage"));
		test.siteConfigurationAction.verifyAddPrinterPopupGetsClosedOnClickingCancelButton();
	}

	@Test(priority = 12, description = "VPLX:Manage Computers: [UI]: Mac Address input field is not visible on add computer screen.")
	public void Test12_1017000(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers: [UI]: Mac Address input field is not visible on add computer screen.");
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.verifyMacAddressFieldIsNotVisibleOnUI("macaddress_text");
	}

}

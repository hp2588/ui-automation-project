package com.org.tests.mainmenu.computers;

import org.testng.annotations.Test;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.Arrays;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

import junit.framework.Assert;

public class Story_992164 extends BaseTest {
	String[] mandatoryFields = { "Facility", "Computer Name", "IP Address" };
	String ipAdd;
	String computerName;
	String[] invalidmessagefields = { "IP Address" };

	@Test(priority = 1, description = "VPLX:Manage Computers:User verifies the mandatory fields on the Edit  computer screen")
	public void Test01_1016996(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers:User verifies the mandatory fields on the Edit  computer screen");
		test.landingPageActions.navigateToFeature("Computers");
		
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.verifyAddComputerPopup("Add Computer");
		test.siteConfigurationAction.clickRadioComputerButton();
		test.siteConfigurationAction.verifyFields();
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);
		computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Comp" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(computerName);
		//test.siteConfigurationAction.verifyNewComputerNameInList(computerName);
		test.supportDataActions.enterSearchTermInSearchField(computerName, "search");

		
//		String firstrowdata = test.siteConfigurationAction.getColumnFirstData("1");
		
		test.siteConfigurationAction.clickOnNameLink(computerName);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);

		// test.siteConfigurationAction.selectDropdownDefaultValue("drop_new");
		test.siteConfigurationAction.clearText("ipAddress");
		test.siteConfigurationAction.clearText("computerName");

		test.siteConfigurationAction.verifyErrorMessageForMandatoryFields(Arrays.asList(mandatoryFields));
		test.siteConfigurationAction.verifyAddPrinterPopupGetsClosedOnClickingCancelButton();

	}

	@Test(priority = 3, description = "VPLX:Manage Computers:User edit  an active record to inactive on  edit computer screen")
	public void Test02_1016997(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers:User verifies the mandatory fields on the Edit  computer screen");
		
		test.supportDataActions.enterSearchTermInSearchField(computerName, "search");
		String name = test.siteConfigurationAction.getColumnFirstData("1");

		test.siteConfigurationAction.clickOnNameLink(computerName);
		test.siteConfigurationAction.verifyFields();
		test.siteConfigurationAction.clickActiveComputerToggle();
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());

		test.siteConfigurationAction.clickSaveButton();
		test.supportDataActions.clearSearchBoxField("search");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(computerName);
		//test.siteConfigurationAction.verifyNewComputerNameInList(name);
		test.supportDataActions.enterSearchTermInSearchField(name, "search");
		test.siteConfigurationAction.verifyNewlyAddedHoldReasonStatus(name, "Inactive");

	}

	@Test(priority = 2, description = "VPLX:Manage Computers: User is able to enter maximum 50 characters for Computer Name on computer name field on edit computer screen")
	public void Test03_1017001(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers: User is able to enter maximum 50 characters for Computer Name on computer name field on edit computer screen");
		
		String firstrowdata = test.siteConfigurationAction.getColumnFirstData("1");
		test.siteConfigurationAction.clickOnNameLink(computerName);
		computerName = test.siteConfigurationAction.getAlphaNumericString(50);
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("computerName", computerName);
		test.siteConfigurationAction.clickSaveButton();
		test.supportDataActions.clearSearchBoxField("search");
		test.siteConfigurationAction.verifyNewComputerNameInList(computerName);
	}

	@Test(priority = 4, description = "VPLX:Manage Computers: Error displayed on entering invalid ip address on edit computer screen")
	public void Test04_1017002(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers: Error displayed on entering invalid ip address on edit computer screen");
		test.siteConfigurationAction.clickOnNameLink(computerName);
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress"
				,getData("ComputerDetails.InvalidIpAddress"));
		test.siteConfigurationAction.verifyErrorMessageForIncorrectFieldsComputer(Arrays.asList(invalidmessagefields),
				getData("ComputerDetails.IncorrectIPMessage"));

		test.siteConfigurationAction.verifyAddPrinterPopupGetsClosedOnClickingCancelButton();

	}

	@Test(priority = 5, description = "VPLX:Manage Computers: [UI]: Mac Address field is not visible on edit computer screen.")
	public void Test05_1017003(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers: [UI]: Mac Address field is not visible on edit computer screen.");
		test.siteConfigurationAction.clickOnNameLink(computerName);
		test.siteConfigurationAction.verifyMacAddressFieldIsNotVisibleOnUI("macaddress_text");

		test.siteConfigurationAction.verifyAddPrinterPopupGetsClosedOnClickingCancelButton();

	}

	@Test(priority = 6, description = "VPLX:Manage Computers:User creates an inactive to  active record for mobile device  on  edit  computer screen")
	public void Test06_1017005(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Computers:User creates an active to  inactive record for mobile device  on  edit  computer screen");
		test.siteConfigurationAction.clickOnNameLink(computerName);
		test.siteConfigurationAction.clickActiveComputerToggle();
		computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "AutomationUI-Com" + System.currentTimeMillis());

		test.siteConfigurationAction.clickSaveButton();
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.enterSearchTermInSearchField(computerName, "search");

		test.siteConfigurationAction.verifyNewlyAddedHoldReasonStatus(computerName, "Active");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();

	}
	/*
	 * @Test(priority = 7, description =
	 * "VPLX:Manage Computers:User verifies the mandatory fields on the Edit  computer screen when Mobile Device option is selected"
	 * ) public void Test07_1017321(Method method) {
	 * 
	 * ExtentTestManager.startTest(method.getName(),
	 * "VPLX:Manage Computers:User verifies the mandatory fields on the Edit  computer screen when Mobile Device option is selected"
	 * ); String computerName =
	 * test.siteConfigurationAction.getColumnFirstData("1");
	 * test.siteConfigurationAction.clickButton("edit");
	 * test.siteConfigurationAction.verifyFieldIsMandatory("ipAddress");
	 * test.siteConfigurationAction.verifyFieldIsMandatory("computerName");
	 * test.siteConfigurationAction.verifyFieldIsMandatory("facilityModelKey");
	 * test.siteConfigurationAction.clickRadioMobileButton();
	 * test.siteConfigurationAction.clearText("ipAddress");
	 * test.siteConfigurationAction.clearText("computerName");
	 * test.siteConfigurationAction.verifyErrorMessageForMandatoryFields(Arrays.
	 * asList(mandatoryFields));
	 * 
	 * }
	 */
}

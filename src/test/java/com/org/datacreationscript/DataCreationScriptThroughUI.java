package com.org.datacreationscript;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class DataCreationScriptThroughUI extends BaseTest {

	String[] external_sys_checkboxes_id = { "pisProvidesMedClassFlag", "pisProvidesTherapeuticClassFlag",
			"allowPISItemEditFlag", "editExternalScanCodeLinksFlag", "ignorePISItemDeleteFlag",
			"ignorePISItemUpdateFlag" };

	String external_System;
	String computerName;
	String printerName, serverPrinterName;
	String destinationName, destinationCode, facilityName, streetName, city, zipCode, country, state, emailID, phone,
			fax;
	String scheduleName;
	String dosageForm, descriptionForm;
	//String app_url;
	
	/*
	 * @BeforeClass public void Open_Browser_Window() { test = new
	 * TestSessionInitiator(this.getClass().getSimpleName()); app_url =
	 * getYamlValue("app_url"); test.launchApplication(app_url); //
	 * test.loginPageAction.verifyUserIsOnBDLoginPage();
	 * test.loginPageAction.LoginToTheBDApplication(getData("Auth.userName1").trim()
	 * , getData("Auth.password1").trim(), getData("Auth.ip").trim());
	 * test.landingPageActions.navigateToMenu("Main Menu");
	 * Assert.assertTrue(test.landingPageActions.
	 * verifyUserIsOnLandingPage("Key Destinations"),
	 * "[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
	 * 
	 * }
	 */
	
	@Test(priority = 1, description = "Create External System")
	public void Test01_Add_External_System(Method method) {
		ExtentTestManager.startTest(method.getName(), "Create External System");
		test.landingPageActions.navigateToFeature("External Systems");
		test.siteConfigurationAction.clickOnAddButtonToAddExternalSystem();
		test.siteConfigurationAction.selectAllCheckboxesOfExternalSystems();
		external_System = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"externalSystemName", getData("ExternalSystem.Name") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(external_System);
	}

	//@Test(priority = 2, description = "Create Computer")
	public void Test02_Add_Computer(Method method) {
		test.landingPageActions.navigateToMenu("Main Menu");
		ExtentTestManager.startTest(method.getName(), "Create Computer");
		test.landingPageActions.navigateToFeature("Computers");
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		test.siteConfigurationAction.verifyAddComputerPopup("Add Computer");
		test.siteConfigurationAction.clickRadioComputerButton();
		test.siteConfigurationAction.verifyFields();
		computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", getData("ComputerDetails.ComputerName"));
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		test.siteConfigurationAction.EnterValueInMACAddressField("macaddress_text",
				getData("ComputerDetails.MACAddress"));
		test.siteConfigurationAction.selectDropdown("drop_new", getData("ComputerDetails.FacilityName"));
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewComputerNameInList(computerName);
	}

	@Test(priority = 3, description = "VPLX:Manage Printers:Verify User is able to add new printer")
	public void Test03_Add_Printer_Test(Method method) {
		test.landingPageActions.navigateToMenu("Main Menu");
		ExtentTestManager.startTest(method.getName(), "VPLX:Manage Printers:Verify User is able to add new printer");
		test.landingPageActions.navigateToFeature("Printers");
		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		serverPrinterName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"serverPrinterName", getData("PrinterDetails.ServerPrinterName") + System.currentTimeMillis());
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printerName");

		printerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("printerName",
				getData("PrinterDetails.PrinterName") + System.currentTimeMillis());

		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("printerModelKey");
		test.siteConfigurationAction.selectValueForDropDown("printerModelKey", getData("PrinterDetails.Model"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				getData("PrinterDetails.IPaddress"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipPort",
				getData("PrinterDetails.PortNumber"));

		//test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("macAddress",getData("PrinterDetails.MACaddress"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaWidth",
				getData("PrinterDetails.PaperWidth"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaHeight",
				getData("PrinterDetails.PaperHeight"));
		test.siteConfigurationAction.clickSaveButton();
		// test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(printerName);

	}

	@Test(priority = 4, description = "VPLX:Manage Destinations-General:[UI]:Verify User Is able to add destinations")
	public void Test04_Add_Destination(Method method) {
		test.landingPageActions.navigateToMenu("Main Menu");
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:Verify User Is able to add destinations");
		test.landingPageActions.navigateToFeature("Pick Destinations");
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

		test.siteConfigurationAction.selectValueForDropDown("facilityKey", "abc");
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

	}

	@Test(priority = 5, description = "VPLX:Manage Printers:Verify User is able to add schedule")
	public void Test05_Add_Schedule_Test(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Manage Printers:Verify User is able to add schedule");
		test.landingPageActions.navigateToFeature("Pick Schedules");
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("scheduleName");
		scheduleName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
	}

	@Test(priority = 6, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: User is able add dosage form")
	public void Test06_1040230(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Add: User is able to add new dosage form");
		test.landingPageActions.navigateToFeature("Dosage Forms");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.GLAccount")),
				"[ASSERTION FAILED]: Toggle Button is Not Present on Add Dosage Form");
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("dosageFormCode"), 20);
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnTextAreaField("descriptionText"), 100);
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("sortValue"), 4);

		dosageForm = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("sortValue", "3");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystem("SelectPISType1",
				getData("DosageForm.SelectPISType2"));
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
	}

}

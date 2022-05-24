package com.org.tests.mainmenu.printers;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982699 extends BaseTest {

	String printerName, serverPrinterName;
	String[] mandatoryFields = { "Server Printer name", "Printer name", "Make/Model", "IP Address", "Port number" };
	
	// Error message are not shown as Save button is disabled by default in latest UI changes.
	//@Test(priority = 0, description = "VPLX:Manage Printers:Verify error message for mandatory fields on add a printer pop up")

	public void Test00_998802NewId_AND_1129281(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:Verify error message for mandatory fields on add a printer pop up");
		test.landingPageActions.navigateToFeature("Printers");
		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.siteConfigurationAction.verifyErrorMessageForMandatoryFields(Arrays.asList(mandatoryFields));
	}

	@Test(priority = 1, description = "VPLX:Manage Printers:User clicks on cancel button so pop up is closed")
	public void Test01_998803(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User clicks on cancel button so pop up is closed");
		test.landingPageActions.navigateToFeature("Printers");
		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		Assert.assertFalse(test.siteConfigurationAction.verifyAddPrinterPopupGetsClosedOnClickingCancelButton());
	}

	@Test(priority = 2, description = "VPLX:Manage Printers:User verifies the field Server Printer Name")
	public void Test02_998728(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Server Printer Name");
		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("serverPrinterName"),
				"[ASSERTION FAILED]: Input Field serverPrinterName is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("serverPrinterName"), 50,
				"[ASSERTION FAILED]: Max Length for input field serverPrinterName is not 50");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("serverPrinterName"),
				"[ASSERTION FAILED]: input field serverPrinterName is not mandatory");
	}

	@Test(priority = 3, description = "VPLX:Manage Printers:User verifies the field Printer Name")
	public void Test03_998733(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Manage Printers:User verifies the field Printer Name");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printerName");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("printerName"),
				"[ASSERTION FAILED]: Input Field printerName is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("printerName"), 50,
				"[ASSERTION FAILED]: Max Length for input field printerName is not 50");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("printerName"),
				"[ASSERTION FAILED]: Field printerName is not mandatory");
	}

	@Test(priority = 4, description = "VPLX:Manage Printers:User verifies the field Make/Model")
	public void Test04_998740_AND_1019060(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Manage Printers:User verifies the field Make/Model");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("printerModelKey");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("printerModelKey"),
				"[ASSERTION FAILED]: Field printerModelKey is not mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyOptionsForDropdownOnAddPrinterPopup("printerModelKey"),
				Arrays.asList(getData("Dropdown_Values.makeModelData").split(",")));
	}

	@Test(priority = 5, description = "VPLX:Manage Printers:User verifies the field IP Address")
	public void Test05_998743(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Manage Printers:User verifies the field IP Address");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("ipAddress");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("ipAddress"),
				"[ASSERTION FAILED]: Input Field ipAddress is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("ipAddress"), 15,
				"[ASSERTION FAILED]: Max Length for input field ipAddress is not 15");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddress", "123");
		test.siteConfigurationAction.verifyErrorMessageForValidInput("IP Address");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				getData("PrinterDetails.IPaddress"));
	}

	@Test(priority = 6, description = "VPLX:Manage Printers:User verifies the field Port Number")
	public void Test06_998751(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Manage Printers:User verifies the field Port Number");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("ipPort");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("ipPort"),
				"[ASSERTION FAILED]: Input Field ipPort is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("ipPort"), 5,
				"[ASSERTION FAILED]: Max Length for input field ipPort is not 5");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipPort", "65536");
		test.siteConfigurationAction.verifyErrorMessageForValidInput("port number");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipPort",
				getData("PrinterDetails.PortNumber"));
	}

	// Obsolete Test Case as Mac Address field is removed from UI for Printer
	// @Test(priority = 7, description = "VPLX:Manage Printers:User verifies the
	// field Mac Address")
	public void Test07_998755(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Manage Printers:User verifies the field Mac Address");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("macAddress");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("macAddress"),
				"[ASSERTION FAILED]: Input Field macAddress is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("macAddress"), 17,
				"[ASSERTION FAILED]: Max Length for input field macAddress is not 17");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("macAddress",
				getData("InvalidMacAddress.macAdd1"));
		test.siteConfigurationAction.verifyErrorMessageForValidInput("MAC address");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("macAddress",
				getData("InvalidMacAddress.macAdd2"));
		test.siteConfigurationAction.verifyErrorMessageForValidInput("MAC address");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("macAddress",
				getData("PrinterDetails.MACaddress"));
	}

	@Test(priority = 8, description = "VPLX:Manage Printers:User verifies the field Label barcode")
	public void Test08_998756(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Manage Printers:User verifies the field Label barcode");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("labelBarcode");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("labelBarcode"),
				"[ASSERTION FAILED]: Input Field labelBarcode is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("labelBarcode"), 1000,
				"[ASSERTION FAILED]: Max Length for input field labelBarcode is not 1000");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("labelBarcode",
				DateUtil.getAlphaNumericString(1001));
	}
	
	// This field is removed in the latest changes.
	//@Test(priority = 9, description = "VPLX:Manage Printers:User verifies the field This is a label printer, not a document printer")
	public void Test09_998760(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field This is a label printer, not a document printer");
		test.siteConfigurationAction.verifyCheckboxFieldOnAddNewPrinterPopup("labelPrinterFlag");
	}

	@Test(priority = 10, description = "VPLX:Manage Printers:User verifies the field Reverse label printing order")
	public void Test10_998763(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Reverse label printing order");
		test.siteConfigurationAction.verifyCheckboxFieldOnAddNewPrinterPopup("rotateLabelOrderFlag");
	}

	@Test(priority = 11, description = "VPLX:Manage Printers:User verifies the field Width")
	public void Test11_998795_1121633(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Manage Printers:User verifies the field Width");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printableAreaWidth");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("printableAreaWidth"),
				"[ASSERTION FAILED]: Input Field printableAreaWidth is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("printableAreaWidth"), 16,
				"[ASSERTION FAILED]: Max Length for input field printableAreaWidth is not 16");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaWidth",
				getData("PrinterDetails.PaperWidth"));
	}

	@Test(priority = 12, description = "VPLX:Manage Printers:User verifies the field Height")
	public void Test12_998797(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Manage Printers:User verifies the field Height");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printableAreaHeight");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("printableAreaHeight"),
				"[ASSERTION FAILED]: Input Field printableAreaHeight is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("printableAreaHeight"), 16,
				"[ASSERTION FAILED]: Max Length for input field printableAreaHeight is not 16");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaHeight",
				getData("PrinterDetails.PaperHeight"));
	}

	@Test(priority = 13, description = "VPLX:Manage Printers:User verifies the field Append form feed")
	public void Test13_998798(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Manage Printers:User verifies the field Append form feed");
		test.siteConfigurationAction.verifyCheckboxFieldOnAddNewPrinterPopup("appendFormFeedFlag");
	}

	@Test(priority = 14, description = "VPLX:Manage Printers:Verify Error message This printer name already exists.Please provide a unique printer name.")
	public void Test14_998733_Error_Message_For_Not_Providing_Unique_Name(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:Verify Error message This printer name already exists.Please provide a unique printer name."); 
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		serverPrinterName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"serverPrinterName", "Auto-UI-ServerPrinter" + System.currentTimeMillis());
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printerName");

		printerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("printerName",
				"Auto-UI-Printer" + System.currentTimeMillis());

		test.siteConfigurationAction.selectValueForDropDown("facilityModelKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("printerModelKey");
		test.siteConfigurationAction.selectValueForDropDown("printerModelKey", getData("PrinterDetails.Model"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				getData("PrinterDetails.IPaddress"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipPort",
				getData("PrinterDetails.PortNumber"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaWidth",
				getData("PrinterDetails.PaperWidth"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaHeight",
				getData("PrinterDetails.PaperHeight"));
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(printerName);

		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		test.siteConfigurationAction.selectValueForDropDown("facilityModelKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		serverPrinterName = test.siteConfigurationAction
				.EnterRandomValueInInputFieldOnAddNewPrinterPopup("serverPrinterName", serverPrinterName);
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printerName");

		printerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("printerName",
				printerName);

		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("printerModelKey");
		test.siteConfigurationAction.selectValueForDropDown("printerModelKey", getData("PrinterDetails.Model"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				getData("PrinterDetails.IPaddress"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipPort",
				getData("PrinterDetails.PortNumber"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("labelBarcode",
				DateUtil.getAlphaNumericString(1001));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaWidth",
				getData("PrinterDetails.PaperWidth"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaHeight",
				getData("PrinterDetails.PaperHeight"));
		test.siteConfigurationAction.clickSaveButton();

		test.siteConfigurationAction.verifyErrorMessageForAlreadyExistingName("printer name");

		printerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("printerName",
				"Auto-UI-Printer" + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();

		test.siteConfigurationAction.verifyErrorMessageForAlreadyExistingName("network printer name");
		serverPrinterName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"serverPrinterName", "Auto-UI-ServerPrinter" + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(printerName);
	}

}

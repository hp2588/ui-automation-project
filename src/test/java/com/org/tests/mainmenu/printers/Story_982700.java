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

public class Story_982700 extends BaseTest {

	String printerName, serverPrinterName;
	String printerName1, serverPrinterName1;
	String width,height;


	@Test(priority = 1, description = "VPLX:Manage Printers:User verifies the fields on Edit printer screen"
			+ "VPLX: Manage Printers - [UI]: Feature Testing:  On Clicking Edit button a pop up must appear with all the information of that printers")
	public void Test01_Test02_Test03_998886_999469_1019039(Method method) {

		ExtentTestManager.startTest(method.getName(),
				" VPLX:Manage Printers:User verifies the field Server Printer Name");
		test.landingPageActions.navigateToFeature("Printers");

		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		test.siteConfigurationAction.selectValueForDropDown("facilityModelKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		serverPrinterName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"serverPrinterName", "Auto-UI-ServerPrinter" + System.currentTimeMillis());
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printerName");

		printerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("printerName",
				"Auto-UI-Printer" + System.currentTimeMillis());

		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("printerModelKey");
		test.siteConfigurationAction.selectValueForDropDown("printerModelKey", getData("PrinterDetails.Model"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				getData("PrinterDetails.IPaddress"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipPort",
				getData("PrinterDetails.PortNumber"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("labelBarcode",
				DateUtil.getAlphaNumericString(1001));
	  width=test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaWidth",
				getData("PrinterDetails.PaperWidth"));
	  height=test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaHeight",
				getData("PrinterDetails.PaperHeight"));
		test.siteConfigurationAction.clickSaveButton();
		//test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(printerName);

		
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToPrinterName(printerName);
		
		test.siteConfigurationAction.verifyToggleOptionToActiveAndInactiveThePrinterOnEditPrinterPopUp();
	}

	@Test(priority = 2, description = "VPLX:Manage Printers:User verifies the field Server Printer Name  on edit printer screen")
	public void Test02_998890(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Server Printer Name  on edit printer screen");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("serverPrinterName"), 50,
				"[ASSERTION FAILED]: Max Length for input field serverPrinterName is not 50");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("serverPrinterName"),
				"[ASSERTION FAILED]: input field serverPrinterName is not mandatory");
	}

	@Test(priority = 3, description = "VPLX:Manage Printers:User verifies the field Printer Name on edit printer screen")
	public void Test03_998894(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Printer Name on edit printer screen");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printerName");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("printerName"), 50,
				"[ASSERTION FAILED]: Max Length for input field printerName is not 50");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("printerName"),
				"[ASSERTION FAILED]: Field printerName is not mandatory");
	}

	@Test(priority = 4, description = "VPLX:Manage Printers:User verifies the field Make/Model on edit printer screen")
	public void Test04_998901(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Make/Model on edit printer screen");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("printerModelKey");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("printerModelKey"),
				"[ASSERTION FAILED]: Field printerModelKey is not mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyOptionsForDropdownOnAddPrinterPopup("printerModelKey"),
				Arrays.asList(getData("Dropdown_Values.makeModelData").split(",")));
	}

	@Test(priority = 5, description = "VPLX:Manage Printers:User verifies the field IP Address on edit printer screen")
	public void Test05_998929(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field IP Address on edit printer screen");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("ipAddress");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("ipAddress"), 15,
				"[ASSERTION FAILED]: Max Length for input field ipAddress is not 15");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddress", "123");
		test.siteConfigurationAction.verifyErrorMessageForValidInput("IP Address");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				getData("PrinterDetails.IPaddress"));
	}

	@Test(priority = 6, description = "VPLX:Manage Printers:User verifies the field Port Number on edit printer screen")
	public void Test06_998987(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Port Number on edit printer screen");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("ipPort");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("ipPort"), 5,
				"[ASSERTION FAILED]: Max Length for input field ipPort is not 5");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipPort", "65536");
		test.siteConfigurationAction.verifyErrorMessageForValidInput("port number");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipPort",
				getData("PrinterDetails.PortNumber"));
	}

	// @Test(priority = 7, description = "VPLX:Manage Printers:User verifies the
	// field MAC Address on edit printer screen")
	public void Test07_999039(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field MAC Address on edit printer screen");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("macAddress");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("macAddress"), 17,
				"[ASSERTION FAILED]: Max Length for input field macAddress is not 17");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("macAddress", "45346456");
		test.siteConfigurationAction.verifyErrorMessageForValidInput("MAC address");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("macAddress",
				getData("PrinterDetails.MACaddress"));
	}

	@Test(priority = 8, description = "VPLX:Manage Printers:User verifies the field Label Barcode on edit printer screen")
	public void Test08_999316(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Label Barcode on edit printer screen");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("labelBarcode");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("labelBarcode"), 1000,
				"[ASSERTION FAILED]: Max Length for input field labelBarcode is not 1000");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("labelBarcode",
				DateUtil.getAlphaNumericString(1001));
	}
	
	// This field is removed in the latest changes.
	//@Test(priority = 9, description = "VPLX:Manage Printers:User verifies the field This is a label printer, not a document printer on edit printer screen")
	public void Test09_999317(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field This is a label printer, not a document printer on edit printer screen");
		test.siteConfigurationAction.verifyCheckboxFieldOnAddNewPrinterPopup("labelPrinterFlag");
		test.siteConfigurationAction.verifyCheckboxLabel("labelPrinterFlag",
				"This is a label printer, not a document printer");

	}

	@Test(priority = 10, description = "VPLX:Manage Printers:User verifies the field Reverse label printing order on edit printer screen")
	public void Test10_999319(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Reverse label printing order on edit printer screen");
		test.siteConfigurationAction.verifyCheckboxFieldOnAddNewPrinterPopup("rotateLabelOrderFlag");
		test.siteConfigurationAction.verifyCheckboxLabel("rotateLabelOrderFlag", "Reverse label printing order");
	}

	@Test(priority = 11, description = "VPLX:Manage Printers:User verifies the field Width on edit printer screen")
	public void Test11_999320_AND_1037299(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Width on edit printer screen");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printableAreaWidth");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("printableAreaWidth"), 16,
				"[ASSERTION FAILED]: Max Length for input field printableAreaWidth is not 16");
		test.siteConfigurationAction.verifyValueEditLocation("printableAreaWidth", width);
	}

	@Test(priority = 12, description = "VPLX:Manage Printers:User verifies the field Height on edit printer screen")
	public void Test12_999413(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Height on edit printer screen");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printableAreaHeight");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("printableAreaHeight"), 16,
				"[ASSERTION FAILED]: Max Length for input field printableAreaHeight is not 16");
		test.siteConfigurationAction.verifyValueEditLocation("printableAreaHeight", height);
	}

	@Test(priority = 13, description = "VPLX:Manage Printers:User verifies the field Append form feed on edit printer screen")
	public void Test13_999442(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Append form feed on edit printer screen");
		test.siteConfigurationAction.verifyCheckboxFieldOnAddNewPrinterPopup("appendFormFeedFlag");
		test.siteConfigurationAction.verifyCheckboxLabel("appendFormFeedFlag", "Append form feed");
	}

	@Test(priority = 14, description = "VPLX:Manage Printers:User clicks on cancel button so pop up is closed on edit printer screen")
	public void Test14_999444_And_998802_And_999458(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User clicks on cancel button so pop up is closed on edit printer screen");
		Assert.assertFalse(test.siteConfigurationAction.verifyAddPrinterPopupGetsClosedOnClickingCancelButton());
	}

	@Test(priority = 15, description = "VPLX:Manage Printers:Verify Error message This printer name already exists.Please provide a unique printer name.")
	public void Test15_Error_Message_For_Not_Providing_Unique_Name(Method method) {
		ExtentTestManager.startTest(method.getName(),
				" VPLX:Manage Printers:Verify Error message This printer name already exists.Please provide a unique printer name."); // test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		test.siteConfigurationAction.selectValueForDropDown("facilityModelKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		serverPrinterName1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"serverPrinterName", "Auto-UI-ServerPrinter" + System.currentTimeMillis());
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printerName");

		printerName1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("printerName",
				"Auto-UI-Printer" + System.currentTimeMillis());
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
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(printerName);

		test.siteConfigurationAction.clickOnEditLinkCorresspondingToPrinterName(printerName1);
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		serverPrinterName = test.siteConfigurationAction
				.EnterRandomValueInInputFieldOnAddNewPrinterPopup("serverPrinterName", serverPrinterName);
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printerName");

		printerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("printerName",
				printerName);

		test.siteConfigurationAction.clickSaveButton();

		test.siteConfigurationAction.verifyErrorMessageForAlreadyExistingName("printer name");

		printerName1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("printerName",
				"Auto-UI-Printer" + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();

		test.siteConfigurationAction.verifyErrorMessageForAlreadyExistingName("network printer name");
	}
	
}

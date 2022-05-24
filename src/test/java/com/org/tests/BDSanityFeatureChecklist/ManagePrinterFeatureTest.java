package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class ManagePrinterFeatureTest extends BaseTest {
	
	String printerName, serverPrinterName;
	
	
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
	public void Test04_998740(Method method) {
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
	
	@Test(priority = 7, description = "VPLX:Manage Printers:User verifies the field Label barcode")
	public void Test07_998756(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Manage Printers:User verifies the field Label barcode");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("labelBarcode");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("labelBarcode"),
				"[ASSERTION FAILED]: Input Field labelBarcode is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("labelBarcode"), 1000,
				"[ASSERTION FAILED]: Max Length for input field labelBarcode is not 1000");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("labelBarcode",
				DateUtil.getAlphaNumericString(1001));
	}
	
	@Test(priority = 8, description = "VPLX:Manage Printers:User verifies the field Reverse label printing order")
	public void Test08_998763(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Reverse label printing order");
		test.siteConfigurationAction.verifyCheckboxFieldOnAddNewPrinterPopup("rotateLabelOrderFlag");
	}

	@Test(priority = 9, description = "VPLX:Manage Printers:User verifies the field Width")
	public void Test09_998795(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Manage Printers:User verifies the field Width");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printableAreaWidth");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("printableAreaWidth"),
				"[ASSERTION FAILED]: Input Field printableAreaWidth is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("printableAreaWidth"), 16,
				"[ASSERTION FAILED]: Max Length for input field printableAreaWidth is not 16");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaWidth",
				getData("PrinterDetails.PaperWidth"));
	}

	@Test(priority = 10, description = "VPLX:Manage Printers:User verifies the field Height")
	public void Test10_998797(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Manage Printers:User verifies the field Height");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printableAreaHeight");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("printableAreaHeight"),
				"[ASSERTION FAILED]: Input Field printableAreaHeight is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("printableAreaHeight"), 16,
				"[ASSERTION FAILED]: Max Length for input field printableAreaHeight is not 16");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaHeight",
				getData("PrinterDetails.PaperHeight"));
	}

	@Test(priority = 11, description = "VPLX:Manage Printers:User verifies the field Append form feed")
	public void Test11_998798(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Manage Printers:User verifies the field Append form feed");
		test.siteConfigurationAction.verifyCheckboxFieldOnAddNewPrinterPopup("appendFormFeedFlag");
	}

	@Test(priority = 12, description = "VPLX:Manage Printers:Verify Error message This printer name already exists.Please provide a unique printer name.")
	public void Test12_998733(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:Verify Error message This printer name already exists.Please provide a unique printer name."); 
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		serverPrinterName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"serverPrinterName", "Auto-UI-ServerPrinter" + System.currentTimeMillis());
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printerName");

		printerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("printerName",
				"Auto-UI-Printer" + System.currentTimeMillis());

		test.siteConfigurationAction.selectValueForDropDown("facilityModelKey", getData("FacilityName"));
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
		test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(printerName);

		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		test.siteConfigurationAction.selectValueForDropDown("facilityModelKey", getData("FacilityName"));
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

		test.siteConfigurationAction.verifyErrorMessageForAlreadyExistingName("server printer name");
		serverPrinterName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"serverPrinterName", "Auto-UI-ServerPrinter" + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(printerName);
	}
	
	@Test(priority = 13, description = "VPLX:Manage Printers:User verifies the fields on Edit printer screen")
	public void Test13_998886(Method method) {
		ExtentTestManager.startTest(method.getName(),
				" VPLX:Manage Printers:User verifies the field Server Printer Name");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToPrinterName(printerName);
		test.siteConfigurationAction.verifyToggleOptionToActiveAndInactiveThePrinterOnEditPrinterPopUp();
	}
	
	@Test(priority = 14, description = "VPLX:Manage Printers:User verifies the field Printer Name on edit printer screen")
	public void Test14_998894(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Printer Name on edit printer screen");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printerName");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("printerName"), 50,
				"[ASSERTION FAILED]: Max Length for input field printerName is not 50");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("printerName"),
				"[ASSERTION FAILED]: Field printerName is not mandatory");
	}
	
	@Test(priority = 15, description = "VPLX:Manage Printers:User verifies the field Make/Model on edit printer screen")
	public void Test15_998901(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Make/Model on edit printer screen");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("printerModelKey");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("printerModelKey"),
				"[ASSERTION FAILED]: Field printerModelKey is not mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyOptionsForDropdownOnAddPrinterPopup("printerModelKey"),
				Arrays.asList(getData("Dropdown_Values.makeModelData").split(",")));
	}
	
	@Test(priority = 16, description = "VPLX:Manage Printers:User verifies the field IP Address on edit printer screen")
	public void Test16_998929(Method method) {
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
	
	@Test(priority = 17, description = "VPLX:Manage Printers:User verifies the field Port Number on edit printer screen")
	public void Test17_998987(Method method) {
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
	
	@Test(priority = 18, description = "VPLX:Manage Printers:User verifies the field Label Barcode on edit printer screen")
	public void Test18_999316(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Label Barcode on edit printer screen");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("labelBarcode");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("labelBarcode"), 1000,
				"[ASSERTION FAILED]: Max Length for input field labelBarcode is not 1000");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("labelBarcode",
				DateUtil.getAlphaNumericString(1001));
	}
	
	@Test(priority = 19, description = "VPLX:Manage Printers:User verifies the field Reverse label printing order on edit printer screen")
	public void Test19_999319(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Reverse label printing order on edit printer screen");
		test.siteConfigurationAction.verifyCheckboxFieldOnAddNewPrinterPopup("rotateLabelOrderFlag");
		test.siteConfigurationAction.verifyCheckboxLabel("rotateLabelOrderFlag", "Reverse label printing order");
	}
	
	@Test(priority = 20, description = "VPLX:Manage Printers:User verifies the field Width on edit printer screen")
	public void Test20_999320(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Width on edit printer screen");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printableAreaWidth");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("printableAreaWidth"), 16,
				"[ASSERTION FAILED]: Max Length for input field printableAreaWidth is not 16");
	}
	
	@Test(priority = 21, description = "VPLX:Manage Printers:User verifies the field Height on edit printer screen")
	public void Test21_999413(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Height on edit printer screen");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printableAreaHeight");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("printableAreaHeight"), 16,
				"[ASSERTION FAILED]: Max Length for input field printableAreaHeight is not 16");
	}
	
	@Test(priority = 22, description = "VPLX:Manage Printers:User verifies the field Append form feed on edit printer screen")
	public void Test22_999442(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Append form feed on edit printer screen");
		test.siteConfigurationAction.verifyCheckboxFieldOnAddNewPrinterPopup("appendFormFeedFlag");
		test.siteConfigurationAction.verifyCheckboxLabel("appendFormFeedFlag", "Append form feed");
	}
	
	@Test(priority = 23, description = "VPLX:Manage Printers:User verifies the field Server Printer Name  on edit printer screen")
	public void Test23_998890(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the field Server Printer Name  on edit printer screen");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("serverPrinterName"), 50,
				"[ASSERTION FAILED]: Max Length for input field serverPrinterName is not 50");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("serverPrinterName"),
				"[ASSERTION FAILED]: input field serverPrinterName is not mandatory");
	}
	
}

package com.org.tests.mainmenu.printers;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_984500 extends BaseTest {

	String[] listSortColumns = { "Name", "Facilities", "Make/Model", "Status" };
	String[] listButtons = { "add", "edit" };
	String printerName,serverPrinterName;

	@Test(priority = 0, description = "VPLX:Manage Printers:User verifies the printer list screen on load")
	public void Test00_999504(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the printer list screen on load");
		test.landingPageActions.navigateToFeature("Printers");
		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		test.siteConfigurationAction.hardWaitForChromeBrowser(6);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey",1);
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		serverPrinterName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"serverPrinterName", "Automation-UI-ServerPrinter" + System.currentTimeMillis());
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printerName");
		printerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("printerName",
				"Automation-UI-Printer" + System.currentTimeMillis());
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("printerModelKey");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("printerModelKey", 1);
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipPort",
				getData("PrinterDetails.PortNumber"));

		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaWidth",
				getData("PrinterDetails.PaperWidth"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaHeight",
				getData("PrinterDetails.PaperHeight"));
		test.siteConfigurationAction.clickSaveButton();
		Assert.assertTrue(test.siteConfigurationAction.getPrinterListFromPrinterPage().size()!=0);
		Assert.assertTrue(test.siteConfigurationAction.verifyPrinterNamesContainsOnlyAlphanumericCharacter());
		Assert.assertTrue(test.siteConfigurationAction.verifyPrinterStatus());

	}
	
	@Test(priority = 1, description = "VPLX: Manage Printers - [UI]: Feature Testing: Search button functionality on Manage Printer")
	public void Test01_1019115_And_1129288(Method method) {
		test.siteConfigurationAction.VerifyAndSearchText(printerName);
		test.siteConfigurationAction.VerifyAndSearchText(printerName);
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToPrinterName(printerName);
		Assert.assertTrue(test.siteConfigurationAction.VerifySearchedPrinterDetails("printerName",
				printerName));
		test.siteConfigurationAction.verifyAddPrinterPopupGetsClosedOnClickingCancelButton();
	}

	@Test(priority = 2, description = "VPLX:Manage Printers:User selects the toggle for Active/Inactive")
	public void Test02_999577(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User selects the toggle for Active/Inactive");
		test.siteConfigurationAction.clickToggleButton("true","toggle");
		test.siteConfigurationAction.verifyPrinterActiveInactiveStatus();

	}

	@Test(priority = 3, description = "VPLX:Manage Printers:User verifies  the columns on list screen")
	public void Test03_999584(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Manage Printers:User verifies  the columns on list screen");
		test.siteConfigurationAction.verifyColumnHeaders(Arrays.asList(listSortColumns));

	}

	@Test(priority = 4, description = "VPLX:Manage Printers:User verifies the search on list screen")
	public void Test04_999594(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Manage Printers:User verifies the search on list screen");
		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
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
	}
	
	

	@Test(priority = 5, description = "VPLX:Manage Printers:User verifies the buttons on the printers list screen")
	public void Test05_999624(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the buttons on the printers list screen");
		test.siteConfigurationAction.verifyButtonsOnPrinterPage();
	}



}
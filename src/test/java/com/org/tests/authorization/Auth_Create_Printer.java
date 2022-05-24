package com.org.tests.authorization;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Auth_Create_Printer extends BaseTest {
	
	String printerName, serverPrinterName;
	
	@Test(priority = 1, description = "VPLX: Authorization - [UI] User is able to Create Printer when user is having the permission for Manage Printer")
	public void Test01_1133589() {

		test.landingPageActions.navigateToFeature("Printers");
		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		test.siteConfigurationAction.selectValueForDropDown("facilityModelKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		serverPrinterName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"serverPrinterName", "ServerPrinter" + System.currentTimeMillis());
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printerName");
		printerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("printerName",
				"Printer" + System.currentTimeMillis());
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("printerModelKey");
		test.siteConfigurationAction.selectValueForDropDown("printerModelKey", getData("PrinterDetails.Model"));
		test.siteConfigurationAction.selectValueFromDropDownByIndex("printerModelKey", 1);
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipPort",
				getData("PrinterDetails.PortNumber"));

		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaWidth",
				getData("PrinterDetails.PaperWidth"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaHeight",
				getData("PrinterDetails.PaperHeight"));
		//test.siteConfigurationAction.selectCheckboxCorresspondingToField("labelPrinterFlag", true);
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("PrinterName", printerName);
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Printers");
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(printerName);
		TestDataPropertyReaderAndWriter.setProperty("PrinterName", printerName);
	}

}

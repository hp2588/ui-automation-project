package com.org.tests.mainmenu.printers;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Printers_Integration extends BaseTest {

	String  serverPrinterName;
	String printerName1, serverPrinterName1;
	String width,height;
	
	@Test(priority = 1, description = "VPLX: Manage Printers:[UI]: [Integration]: When a Printer is added/updated, it gets displayed under  Restock/Return Printer dropdown on Facility Settings tab")
	public void Test1_1154931(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),"VPLX: Manage Printers:[UI]: [Integration]: When a Printer is added/updated, it gets displayed under  Restock/Return Printer dropdown on Facility Settings tab");
		
		/*test.landingPageActions.navigateToFeature("Printers");
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

		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.enterSearchTermInSearchField(printerName, "search");
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(printerName);
	*/	
		test.landingPageActions.navigateToFeature("Facilities");
		 test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(),
				"search");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickTab("Settings");
		
		test.siteConfigurationAction.verifyValueExistinDropDown("manualRestockPrinterKey", TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());

		//test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(facilityName, "Edit Facility");

	}
	
	@Test(priority = 2, description = "VPLX: Manage Printers:[UI]: [Integration]: When a Printer is added/updated, it gets displayed under  Receiving Printer dropdown on Facility Settings tab")
	public void Test2_1154936(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),"VPLX: Manage Printers:[UI]: [Integration]: When a Printer is added/updated, it gets displayed under  Receiving Printer dropdown on Facility Settings tab");
	
		test.siteConfigurationAction.verifyValueExistinDropDown("receivingPrinterKey", TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());
	}
	
	@Test(priority = 3, description = "VPLX: Manage Printers:[UI]: [Integration]: When a Printer is added/updated, it gets displayed under  Exception Printer dropdown on Facility Settings tab")
	public void Test3_1154937(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),"VPLX: Manage Printers:[UI]: [Integration]: When a Printer is added/updated, it gets displayed under  Exception Printer dropdown on Facility Settings tab");
	
		test.siteConfigurationAction.verifyValueExistinDropDown("exceptionPrinterKey", TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());
	}
	
	@Test(priority = 4, description = "VPLX: Manage Printers:[UI]: [Integration]: When a Printer is added/updated, it gets displayed under  Bin Label Printer dropdown on Facility Settings tab")
	public void Test4_1154938(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),"VPLX: Manage Printers:[UI]: [Integration]: When a Printer is added/updated, it gets displayed under  Bin Label Printer dropdown on Facility Settings tab");
	
		test.siteConfigurationAction.verifyValueExistinDropDown("binLabelPrinterKey", TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());
	}
	
	@Test(priority = 5, description = "VPLX: Manage Printers:[UI]: [Integration]:When a printer is added/updated, the Printer name gets populated in dropdowns on Select ISA screen when user logs in from same facility")
	public void Test5_1130201(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),"VPLX: Manage Printers:[UI]: [Integration]:When a printer is added/updated, the Printer name gets populated in dropdowns on Select ISA screen when user logs in from same facility");
	
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		Assert.assertNotNull(
				test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		
		test.storageAreaAction.verifyValueExistinDropDown(TestDataPropertyReaderAndWriter.getProperty("ShortName").trim(), TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());
		
			

	}
	
	
	@Test(priority = 6, description = "VPLX: Manage Printers: [UI]: [Integration]: When a printer is made inactive, it is not populated in Printers dropdown on Edit Facility screen(Settings Tab)")
	public void Test6_1106893(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				" VPLX: Manage Printers: [UI]: [Integration]: When a printer is made inactive, it is not populated in Printers dropdown on Edit Facility screen(Settings Tab)");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.landingPageActions.navigateToFeature("Printers");
		 test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim(),
					"search");
		 test.siteConfigurationAction.clickOnEditLinkCorresspondingToPrinterName(TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());
		 test.siteConfigurationAction.clickActiveToggle("Active");
		 test.siteConfigurationAction.verifyToggleIsInActive("isActive");
		 test.siteConfigurationAction.clickSaveButton();
		/*
		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		
		test.siteConfigurationAction.selectValueForDropDown("facilityModelKey",getData("PrinterDetails.FacilityName"));
		
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		serverPrinterName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
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
	  width=test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaWidth",
				getData("PrinterDetails.PaperWidth"));
	  height=test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaHeight",
				getData("PrinterDetails.PaperHeight"));
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(printerName1);

		test.siteConfigurationAction.clickOnEditLinkCorresspondingToPrinterName(printerName1);
		test.siteConfigurationAction.clickActiveToggle("Active");
		test.siteConfigurationAction.clickButton("save");	
		/*
		 * 
		 */
		
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		 test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(),
				"search");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickTab("Settings");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("manualRestockPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim()), "[ASSERTION FAILED]: Inactive Printer is visible in Manual Restock Printer dropdown");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("receivingPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim()), "[ASSERTION FAILED]: Inactive Printer is visible in Receiving Printer dropdown");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("exceptionPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim()), "[ASSERTION FAILED]: Inactive Printer is visible in Exception Pritner dropdown");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("binLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim()), "[ASSERTION FAILED]: Inactive Printer is visible in Bin Label Printer dropdown");
		
}
}

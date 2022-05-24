package com.org.tests.ForcedCycleCount;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;



public class Support_Data extends BaseTest {
	
	String printerName, serverPrinterName,name,shortName,computerName;
	
	@Test(priority = 1, description = "VPLX:Manage Computers:Verify User is able to add computer")
	public void Test01_Add_Computer(Method method) {
		String IPAddress = DateUtil.getRandomIPAddress();
		test.landingPageActions.navigateToFeature("Computers");
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		test.siteConfigurationAction.clickRadioComputerButton();
		 computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				IPAddress);
		//test.siteConfigurationAction.EnterValueInMACAddressField("macaddress_text",getData("ComputerDetails.MACAddress"));
		//test.siteConfigurationAction.selectValueForDropDown("facilityModelKey",TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",TestDataPropertyReaderAndWriter.getProperty("FacilityName_ForcedCC").trim());
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("IPAddress_ForcedCC", IPAddress);
		TestDataPropertyReaderAndWriter.setProperty("ComputerName_ForcedCC", computerName);

}
	
	@Test(priority = 2, description = "VPLX:Manage Printers:Verify User is able to add new printer")
	public void Test02_Add_Printer_Test() {

		test.landingPageActions.navigateToFeature("Printers");
		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		test.siteConfigurationAction.selectValueForDropDown("facilityModelKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName_ForcedCC").trim());
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
		TestDataPropertyReaderAndWriter.setProperty("PrinterName", printerName);
		test.landingPageActions.navigateToMenu("Main Menu");
		
		TestDataPropertyReaderAndWriter.setProperty("PrinterName_ForcedCC", printerName);
	}
	
	
	
	
}

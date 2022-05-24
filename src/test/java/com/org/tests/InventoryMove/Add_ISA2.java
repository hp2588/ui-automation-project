package com.org.tests.InventoryMove;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Add_ISA2 extends BaseTest {

	String facility, facilityOnISA, name1, shortName, defaultComputer, defaultPrinter, type, deviceNumber,
	ipAddress, portNumber, carouselConnectionResetTime, app_url, resetInterval, name2;
	
	@Test(priority = 1, description = "VPLX: Manage ISAs: [UI]: Add and View ISA")
	public void Test01_1026541(Method method) throws InterruptedException {
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add Inventory Storage Area (ISAs)");
		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
				"30");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		name1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"INVMOVE2");
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"INVMOVE2");
		
		test.siteConfigurationAction.selectValueForDropDown("logisticsLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());
		test.siteConfigurationAction.clickTab("ISA Configuration");
		test.storageAreaAction.enterDataInInputField("maxBinNumber", "2");
		Thread.sleep(3000);
		test.siteConfigurationAction.clickTab("Display Settings");
		test.siteConfigurationAction.clickTab("Approved Computers");
		test.storageAreaAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("InvMoveISA2", name1);
	}


}

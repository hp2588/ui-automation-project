package com.org.tests.selectprinter;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_995854 extends BaseTest {

	String printerName;
	String scheduleName;
	String ISAName, facility, facilityOnISA, name, shortName, defaultComputer, defaultPrinter, type, deviceNumber,
	ipAddress, portNumber, carouselConnectionResetTime, serverPrinterName, name1, shortName1;
	
	@Test(priority = 1, description = "VPLX: Select Printers: [UI]: The default printer for an ISA is selected by default under the printer dropdown")
	public void Test01_1018281(Method method) throws InterruptedException {
		shortName = TestDataPropertyReaderAndWriter.getProperty("ShortName");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(shortName, 0);
		
		Assert.assertEquals(test.storageAreaAction.getSelectedPrinterOfISA(shortName), 
				TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
	}
	
	
	@Test(priority = 2, description = "VPLX: Select Printers: [UI]: The Printer dropdown list displays all the active printer names on WFA Screen,which are mapped to particular Facility")
	public void Test02_1018285(Method method)  {
		
		List<String> printerList = test.storageAreaAction.getListOfPrintersCorresspondingToISA(shortName);
		Assert.assertTrue(printerList.contains(TestDataPropertyReaderAndWriter.getProperty("PrinterName")),
				"[ASSERTION FAILED]: ISA printer dropdwon doesn't have printer " + TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		Assert.assertTrue(printerList.contains(TestDataPropertyReaderAndWriter.getProperty("PrinterName2")),
				"[ASSERTION FAILED]: ISA printer dropdwon doesn't have printer " + TestDataPropertyReaderAndWriter.getProperty("PrinterName2"));
		Assert.assertTrue(printerList.contains(TestDataPropertyReaderAndWriter.getProperty("PrinterName3")),
				"[ASSERTION FAILED]: ISA printer dropdwon doesn't have printer " + TestDataPropertyReaderAndWriter.getProperty("PrinterName3"));
	}
	
	
	@Test(priority = 3, description = "VPLX: Select Printers: [UI]: User is able to select the corresponding printer by pressing any key on the Printer dropdown for an ISA")
	public void Test03_1032664(Method method)  {
		
		Assert.assertEquals(test.storageAreaAction.selectPrinterUsingKeyboard(shortName,"N"), 
				TestDataPropertyReaderAndWriter.getProperty("PrinterName3"));
		test.siteConfigurationAction.clickButtonByTextWithoutWait("Cancel");
	}
	
	@Test(priority = 4, description = "VPLX: Select Printers: [UI]: The Default Printer value is displayed as 'Select' when the default printer for an ISA is marked as inactive")
	public void Test04_1018285(Method method)  {
		test.siteConfigurationAction.pageRefresh();
		
		test.landingPageActions.navigateToFeature("Printers");
		test.siteConfigurationAction.clickRecordNameToEdit(
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());
		test.siteConfigurationAction.clickToggleButton("false", "isActive");
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(shortName, 0);
		Assert.assertEquals(test.storageAreaAction.getSelectedPrinterOfISA(shortName), "Select" , 
				"[ASSERTION FAILED]: After deactivating default printer of ISA, default printer is not 'Select'");
	}
	
	@Test(priority = 5, description = "VPLX: Select Printers: [UI]: An error message is displayed if already configured Printer has become inactive and Start Task button is clicked")
	public void Test05_1017009(Method method) throws InterruptedException {
		
		test.storageAreaAction.selectCheckboxForISA(shortName, 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.siteConfigurationAction.verifyErrorMessageOnWFA("Please select a printer for" +" " +shortName);
		
	}
	
}
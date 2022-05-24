package com.org.tests.selectisa;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_995854 extends BaseTest {

	List<String> printerListISA = new ArrayList<String>();
	List<String> printerListSiteConfiguration = new ArrayList<String>();
	String shortName;

	@Test(priority = 1, description = "VPLX:Select Printers: The mapped printer for "
			+ "particular ISA is displayed by default")
	public void Test01_1018281(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Select Printers: The mapped printer for particular ISA is displayed by default");
		
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
	
	@Test(priority = 2, description = "VPLX:Select Printers: The dropdown list has all the printer names, which are mapped to particular Facility")
	public void Test02_1018285(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Select Printers: The dropdown list has all the printer names,which are mapped to particular Facility");
		
		List<String> printerList = test.storageAreaAction.getListOfPrintersCorresspondingToISA(shortName);
		Assert.assertTrue(printerList.contains(TestDataPropertyReaderAndWriter.getProperty("PrinterName")),
				"[ASSERTION FAILED]: ISA printer dropdwon doesn't have printer " + TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		Assert.assertTrue(printerList.contains(TestDataPropertyReaderAndWriter.getProperty("PrinterName2")),
				"[ASSERTION FAILED]: ISA printer dropdwon doesn't have printer " + TestDataPropertyReaderAndWriter.getProperty("PrinterName2"));
		
	}

}
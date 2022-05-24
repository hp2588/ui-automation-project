package com.org.tests.selectisa;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Sprint0_tests extends BaseTest {
	@Test(priority = 1, description = "VPLX: Select ISAs: [UI]: The Transaction Queue opens for the selected ISA when user goes back to Select ISA screen from Transaction Queue")
	public void Test01_1019187(Method method) {
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
	}

	@Test(priority = 2, description = "VPLX: Select ISAs: [UI]:The static ISA(having Approved Computer) are selected when logged in from Approved computer")
	public void Test02_1153193(Method method) {
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		
	}

	@Test(priority = 3, description = "VPLX: Select ISAs: [UI]:The Carousel ISA( Having approved comp) is greyed out when logged in from non-approved computer (For which flag for carousel control is not selected)")
	public void Test03_1153365(Method method) {
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		
	}
	
	
	@Test(priority = 4, description = "VPLX: Select ISAs: [UI]: Manual Use toggle option is displayed inside the carousel boundary")
	public void Test04_1019055(Method method) {
		
		
	}
	
	
	@Test(priority = 5, description = "VPLX: Select ISAs: [UI]:  The checkboxes are displayed as Selected on the basis of Last Selection made by user when user clicks on 'My Last Selected' button")
	public void Test05_1153873(Method method) {
		
		
	}
	
	
	@Test(priority = 6, description = "VPLX: Select ISAs: [UI]:The static ISA(having Approved Computer) are not available to take control when logged in from  non-Approved Computer")
	public void Test06_1153194(Method method) {
		
		
	}
	
	
	
	
	
	
	


}

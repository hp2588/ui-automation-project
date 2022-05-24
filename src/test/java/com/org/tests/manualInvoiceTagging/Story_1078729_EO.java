package com.org.tests.manualInvoiceTagging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1078729_EO extends BaseTest {
	
	String purchaseOrderDistributorName;
	ArrayList<String> priceTaggingList = new ArrayList<String>(Arrays.asList("Select", "WAC", "340B", "GPO")); 
	
	@Test(priority = 1, description = "VPLX : Manual Tagging [UI] : Verify by default type Select is present in drop down only for Electronic not for Manual")
	public void Test01_1098363() {
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.openPurchaseOrderElectroniccard();
		test.purchaseDashboardActions.verifyDefaultValueInDropDown("priceTagName", "Select");
	}
	
	@Test(priority = 2, description = "VPLX : Manual Tagging [UI] : Verify drop down Type have three values WAC , GPO and 340B")
	public void Test02_1098365() {
		/*
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.openPurchaseOrderElectroniccard();
		*/
		List<String> actualTagNames = test.purchaseDashboardActions.getAllDataFromDropDown("priceTagName");
		Collections.sort(actualTagNames);
		Collections.sort(priceTaggingList);
		Assert.assertEquals(actualTagNames, priceTaggingList);
		
	}
	
	@Test(priority = 3, description = "VPLX : Manual Tagging [UI] : Verify when user selects any of the drop down value it is saved.")
	public void Test03_1098366() {
		test.purchaseDashboardActions.selectValueFromDropDown("priceTagName","WAC");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.openPurchaseOrderElectroniccard();
		test.purchaseDashboardActions.verifyDefaultValueInDropDown("priceTagName", "WAC");
	}
	
}

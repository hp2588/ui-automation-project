package com.org.tests.manualInvoiceTagging;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1123738 extends BaseTest {

	String purchaseOrderDistributorName;
	 ArrayList<String> priceTaggingList = new ArrayList<String>(Arrays.asList("WAC","GPO", "340B")); 
	 ManualTagging manualTagging;
	
	@Test(priority = 1, description = "VPLX: Price Update and Invoice Tagging (of facility items): [UI]: "
			+ "Pending receive tags drop down for electronic orders.")
	public void Test01_1111432() {
		priceTaggingList.add("tagg4");
		priceTaggingList.add("tagg5");
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.openPurchaseOrderElectroniccard();
		test.purchaseDashboardActions.selectValueFromDropDown("priceTagName","Select");
	}
	
	@Test(priority = 2, description = "VPLX: Price Update and Invoice Tagging (of facility items): [UI]: "
			+ "Tags present under facility for pending receive cards.\r\n")
	public void Test02_1111433() {
		List<String> priceTagOptions = test.siteConfigurationAction.getAllDataFromDropDown("priceTagName");
		Collections.sort(priceTagOptions);
		Collections.sort(priceTaggingList);
		
		Assert.assertEquals(test.siteConfigurationAction.getAllDataFromDropDown("priceTagName"), priceTaggingList);
	}
	
	@Test(priority = 3, description = "VPLX: Price Update and Invoice Tagging (of facility items): [UI]: "
			+ "Minimum and Maximum tags present under facility for pending receive cards.")
	public void Test03_1111434() {
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickRecordNameToEdit(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickTabWithoutWait("Settings");
			
		// disable all 
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox0", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox1", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox2", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox3", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox4", false);
		test.siteConfigurationAction.clickSaveButton();
		
		// verify that we are still on same page - that is we can't save without selecting at least one tag
		test.siteConfigurationAction.verifycheckboxispresent("enablePriceTagging");
		// select one tag and save
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox0", true);
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.openPurchaseOrderElectroniccard();
		
		// check minimum tags - max tags checked in last test case 
		Assert.assertEquals(test.siteConfigurationAction.getAllDataFromDropDown("priceTagName").size(), 2, 
				"[ASSERTION FAILED]: Price tags dropdown doesn't have exactly two options");
	}
	
	
	@Test(priority = 4, description = "VPLX: Price Update and Invoice Tagging (of facility items): [UI]: State when tags must be selected in the invoice.")
	public void Test04_Test05_1111435_1111436() {
		test.supportDataActions.selectItemtoRecieve(TestDataPropertyReaderAndWriter.getProperty("ItemName_Electronic"));
		test.siteConfigurationAction.clickButtonUsingId_withoutwait("ReceivedandSent");
		Assert.assertEquals(test.purchaseDashboardActions.getPopupText(), 
				"A tag must be selected for the invoice before any of the items is received",
				"[ASSERTION FAILED]: Error popup message doesn't match");
		
		test.siteConfigurationAction.clickButtonUsingId_withoutwait("Received");
		Assert.assertEquals(test.purchaseDashboardActions.getPopupText(), 
				"A tag must be selected for the invoice before any of the items is received",
				"[ASSERTION FAILED]: Error popup message doesn't match");
	}
	
	
	@Test(priority = 5, description = "VPLX: Price Update and Invoice Tagging (of facility items): [UI]: Tag change when state of the invoice is changed to Received\" or \"Sent to Queue\" statuses .")
	public void Test06_1111437() {
	
		test.purchaseDashboardActions.selectValueFromDropDown("priceTagName","GPO");
		test.siteConfigurationAction.clickButton("ReceivedandSent");
		
		test.supportDataActions.openPurchaseOrderElectroniccard();
		Assert.assertFalse(test.purchaseDashboardActions.isDropdownAvailable("tag"),
				"[ASSERTION FAILED]: tag dropdown is available");
	}
	
	@Test(priority = 6, description = "VPLX: Price Update and Invoice Tagging (of facility items): [UI]: Tag change when state of the invoice is changed to Received\" or \"Sent to Queue\" statuses .")
	public void Test06_1111438() throws InterruptedException {
		
		manualTagging = new ManualTagging();
		manualTagging.createElectronicOrder();
		manualTagging.createmanualTaggingData();
		
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickRecordNameToEdit(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickTabWithoutWait("Settings"); 
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox0", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox1", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox2", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox3", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox4", true);
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.selectValueFromDropDown("priceTagName", "GPO");
		test.supportDataActions.openPurchaseOrderElectroniccard();
		test.siteConfigurationAction.clickButton("Bypass");
		
		test.supportDataActions.openPurchaseOrderElectroniccard();
		test.purchaseDashboardActions.verifyTypeDropdownIsAvailable();
		test.purchaseDashboardActions.selectDropDownValue("GPO");
		test.purchaseDashboardActions.verifyDefaultValueInDropDown("priceTagName","GPO");
	}
	
}

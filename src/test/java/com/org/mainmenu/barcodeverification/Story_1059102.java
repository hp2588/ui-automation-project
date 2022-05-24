package com.org.mainmenu.barcodeverification;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

//  test vanguard03 
public class Story_1059102 extends BaseTest{
	
	@Test(priority = 1, description = "VPLX: Barcode Management: Verification : [UI] A Button "
			+ "named as \"Unverified Links\" available on the bar code management screen")
	public void Test01_1077850(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Barcode Management: Verification : [UI] A Button named as 'Unverified Links' available "
				+ "on the bar code management screen.");
		
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Barcodes"));
		test.siteConfigurationAction.verifyPickRoutingRuleCancelButton("unverified");
	}
	
	
	@Test(priority = 2, description = "VPLX: Barcode Management: Verification : [UI] A Button "
			+ "for \"Unverified Links\" shows the Count of unverified links .")
	public void Test02_1077851(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Barcode Management: Verification : [UI] A Button for 'Unverified Links' "
				+ "shows the Count of unverified links .");
		
		Assert.assertTrue(test.supportDataActions.verifyUnverifiedLinks("unverified"));
	}
	
	
	@Test(priority = 3, description = "VPLX: Barcode Management: Verification : [UI] Unverified links "
			+ "shows the unverified items only for the ES of assigned Facilities which user has rights .")
	public void Test03_1077853(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Barcode Management: Verification : [UI] Unverified links shows the unverified items only " 
				+ "for the ES of assigned Facilities which user has rights .");
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("unverified");
		test.supportDataActions.barcodePopupHeading();
		Assert.assertTrue(test.supportDataActions.verifyItemListOnBarcode());
	}
	
	
	@Test(priority = 4, description = "VPLX: Barcode Management: Verification : [UI] No item get display "
			+ "if user click on the \"unverified Links \"button with count 0")
	public void Test04_1077854(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Barcode Management: Verification : [UI] No item get display if user click on the "
				+ "\"unverified Links \"button with count 0");
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("unverified");
		test.supportDataActions.barcodePopupHeading();
		Assert.assertTrue(test.supportDataActions.verifyItemListOnBarcode());
	}
	
	
	@Test(priority = 5, description = "VPLX: Barcode Management: Verification : [UI] "
			+ "Unverified link window get opened with search box .")
	public void Test05_1077876(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Barcode Management: Verification : [UI] Unverified link window get opened with search box .");
		
		test.supportDataActions.verifySearchBoxIsPresentOrNot("itemSearch");
	}
	
	
	@Test(priority = 6, description = "VPLX: Barcode Management: Verification : [UI] "
			+ "Visibility of columns on the \"unverified Links \" screen .")
	public void Test06_1077878(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Barcode Management: Verification : [UI] Visibility  of columns on the \"unverified Links \" screen .");
		
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Item");
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Item ID");
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Type");
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Linked By");
		test.supportDataActions.verifyColumnHeaderOnBarcode();
	}
	
	
	@Test(priority = 7, description = "VPLX: Barcode Management: Verification : [UI] Link detail screen "
			+ "get opened after clicking on the View button under the Action Column.")
	public void Test07_1077885(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Barcode Management: Verification : [UI] Link detail screen get opened after clicking on the View button  under the Action  Column.");
		
		test.supportDataActions.clickButton("edit");
		test.supportDataActions.barcodePopupHeading();
		test.supportDataActions.verifytextOnEditDistributor("Barcode");
		test.supportDataActions.verifytextOnEditDistributor("Linked Item");
		test.supportDataActions.verifytextOnEditDistributor("Linked By");
		test.supportDataActions.verifytextOnEditDistributor("Link Source");
		test.supportDataActions.verifytextOnEditDistributor("Item ID");
		test.supportDataActions.verifytextOnEditDistributor("Verified By");
		test.supportDataActions.verifytextOnEditDistributor("Product ID");
		test.supportDataActions.verifytextOnEditDistributor("Matched On");	
	}
	
	
	@Test(priority = 8, description = "VPLX: Barcode Management: Verification : [UI] User get redirect to "
			+ "\"Unverified Links\" screen after clicking on the Cancel button on verify linked item screen.")
	public void Test08_1077917(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Barcode Management: Verification : [UI] User get redirect to \"Unverified Links\" screen "
				+ "after clicking on the Cancel button  on verify linked item screen.");
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("Cancel");
		test.supportDataActions.barcodePopupHeading();
	}
	
	
	@Test(priority = 9, description = "VPLX: Barcode Management: Verification : [UI] User get redirect to "
			+ "\"Unverified Links\" screen after clicking on the Verify button on verify linked item screen.")
	public void Test09_1077942(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Barcode Management: Verification : [UI] User  get redirect to \"Unverified Links\" screen "
				+ "after clicking on the Verify button  on verify linked item screen.");
		
		String data = test.supportDataActions.fetchFirstBarcodeValue();
		test.supportDataActions.clickButton("edit");
		test.siteConfigurationAction.clickPickRoutingRuleButton("Verify");
		test.supportDataActions.barcodePopupHeading();
		test.supportDataActions.verifyNewlyRemovedRecord(data);
	}
	
}

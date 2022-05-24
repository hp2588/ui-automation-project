package com.org.tests.itemsparityapprovalqueue;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1084843 extends BaseTest  {
		
	String itemID, brandName,itemId;
	String itemID10,itemID11,genericName;
	String facilityOnWFAScreen = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(),
			External = TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim();
	
	@Test(priority = 1, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] The Location column under the item tab at the  PIS level.")
	public void Test01_1091631(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] The Location column under the item tab at the  PIS level.");
		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Location");
		
	}
	
	@Test(priority = 2, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] The Location column under the item tab at the PIS- Facility level")
	public void Test02_1091633(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] The Location column under the item tab at the PIS- Facility level");
		test.siteConfigurationAction.enterRandomValueInRichInputField(facilityOnWFAScreen);
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Location");
		
	}
	
	@Test(priority = 3, description = "VPLX: Item Setup - ES Parity (Approval Queue): [UI] : The Location number is visible under location column under the Items tab at the PIS- Facility level")
	public void Test03_1091636(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue): [UI] : The Location number is visible under location column under the Items tab at the PIS- Facility level");
		test.supportDataActions.codeListDosageForms("5");
		}
	
	@Test(priority = 4, description = "VPLX: Item Setup - ES Parity (Approval Queue)  [UI] : The Location column is showing all the mapped locations in the popup after clicking on the hyperlink for that number under the location column.")
	public void Test04_1091681_AND_1130982(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue)  [UI] : The Location column is showing all the mapped locations in the popup after clicking on the hyperlink for that number under the location column.");
	test.siteConfigurationAction.clicklocationnumberOnItemScreen();
	test.siteConfigurationAction.verifymappedlocationonItemScreen();
}
	
	@Test(priority = 5, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] Location Management Item Listing dialog box will get opened after clicking on the manage link on the location popup.")
	public void Test05_1091688_AND_1091690(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] Location Management Item Listing dialog box will get opened after clicking on the manage link on the location popup.");
		test.siteConfigurationAction.clickManageLinkOnItemScreen();
		test.siteConfigurationAction.verifyUSerIsOnEditLocationManagementPage();
		
}
	
	@Test(priority = 6, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] Location Management Item Listing dialog box have one back button to switch to Item management screen .")
	public void Test06_1091691(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] Location Management Item Listing dialog box have one back button to switch to Item management screen .");
		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.verifyPageHeader_Sanity("Item Management");
		
}
}
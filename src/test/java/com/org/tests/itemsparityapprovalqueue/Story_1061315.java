package com.org.tests.itemsparityapprovalqueue;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1061315 extends BaseTest{
	
	String itemID, brandName,itemId;
	String itemID1, brandName1,itemId1,itemID2,facilityOnWFAScreen=TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(),External=TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim();
	
	@Test(priority = 1, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] Item is available in respective PIS when user added new Items on PIS-level from the 'Add New Item' screen.")
	public void Test01_1092089(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] Item is available in respective PIS when user added new Items on PIS-level from the 'Add New Item' screen.");
				
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		//test.siteConfigurationAction.enterRandomValueInRichInputField(External);
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacility" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem" + System.currentTimeMillis());
		brandName = test.siteConfigurationAction.enterDataInInputField("brandName", "brand1");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickButton("cancel");		
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(itemID, "4"));
		test.supportDataActions.clearSearchBoxField("search");
		
}
	@Test(priority = 2, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] Approved Item is available in the respective Facility when use added new Items by selecting Facility from the 'Add New Item' screen.")
	public void Test02_1092096(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] Approved Item is available in the respective Facility when use added new Items by selecting Facility from the 'Add New Item' screen.");
		//test.siteConfigurationAction.ClickOnExternalSystemRichTextbox();
		//test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(facilityOnWFAScreen);
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacility" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem" + System.currentTimeMillis());
		brandName1 = test.siteConfigurationAction.enterDataInInputField("brandName", "brand1");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(facilityOnWFAScreen);
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickButton("cancel");		
		
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID1, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(itemID1, "4"));
		test.supportDataActions.clearSearchBoxField("search");
	
		}
	@Test(priority = 3, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] Pending Approval  Item is available in the respective Facility when user added new Items by selecting Facility from the 'Add New Item' screen.")
	public void Test03_1092092(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] Pending Approval  Item is available in the respective Facility when user added new Items by selecting Facility from the 'Add New Item' screen.");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacility" + System.currentTimeMillis());
		itemID2 = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem" + System.currentTimeMillis());
		brandName1 = test.siteConfigurationAction.enterDataInInputField("brandName", "brand1");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickButton("cancel");		
		test.siteConfigurationAction.clickPendingApprovalTabonItemScreen("Pending Approval");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID2, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(itemID2, "4"));
		test.supportDataActions.clearSearchBoxField("search");
	
}
	@Test(priority = 4, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] Item is not available in those facilities which was not selected while creating Items from the 'Add New Item' screen.")
	public void Test04_1092105(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] Item is not available in those facilities which was not selected while creating Items from the 'Add New Item' screen.");
		test.siteConfigurationAction.ClickOnExternalSystemRichTextbox();
		test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(facilityOnWFAScreen);
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID1, "search");
		Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Data Available.");
	
	}
}
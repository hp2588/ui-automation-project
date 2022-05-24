package com.org.tests.itemsetupGlobalEdit;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1104593 extends BaseTest {

	
	
	String itemID, brandName;
	@Test(priority = 1, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] Items of all the Facilities under the expended  PIS  are listed if \"My Facility\" is selected .")
	public void Test01_1105907_AND_1105900(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] Items of all the Facilities under the expended  PIS  are listed if \"My Facility\" is selected .");
		/*
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.ClickOnExternalSystemRichTextbox();
		test.siteConfigurationAction.enterRandomValueofMyFacilityInRichInputField(getData("ExternalSystem.Name7"));
		test.siteConfigurationAction.verifyItemsonItemscreen(getData("ExternalSystem.itemID"));
		*/
				
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		
		//test.siteConfigurationAction.enterRandomValueInRichInputField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
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
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickButton("cancel");		
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(itemID, "4"));
		
		test.supportDataActions.clearSearchBoxField("search");
	}
	
	@Test(priority = 2, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] Items of Selected Facility under the expended  PIS  is listed on selection of specific facility.")
	public void Test02_1106062(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] Items of Selected Facility under the expended  PIS  is listed on selection of specific facility.");
		//test.siteConfigurationAction.ClickOnExternalSystemRichTextbox();
		//test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(getData("ExternalSystem.Name8"));
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(itemID, "4"));
		
		test.supportDataActions.clearSearchBoxField("search");
	//	test.siteConfigurationAction.verifyItemsonItemscreen(getData("ExternalSystem.itemID"));
	
	}
	
	@Test(priority = 3, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] \"Edit Selected\" button for the Global Edit of items get enabled after selecting the items under the Facility .")
	public void Test03_1106261(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] \"Edit Selected\" button for the Global Edit of items get enabled after selecting the items under the Facility .");
		test.supportDataActions.selectMultipleCheckboxes("1");
		test.supportDataActions.selectMultipleCheckboxes("2");
		test.siteConfigurationAction.clickActionbutton("Actions");
		}
	
	@Test(priority = 4, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] \"Edit Multiple items \" window get opened after clicking on the\" Edit Selected\" button under the Actions tab for Facility level edit .")
	public void Test04_1106267(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] \"Edit Multiple items \" window get opened after clicking on the\" Edit Selected\" button under the Actions tab for Facility level edit .");
		test.siteConfigurationAction.clickActionbutton("Edit Selected");
		test.siteConfigurationAction.clickfacilityLevel("Facility Level");
	
		test.siteConfigurationAction.verifyfieldIsPresent("Active");
		test.siteConfigurationAction.verifyfieldIsPresent("Ignore ADC Stockout");
		test.siteConfigurationAction.verifyfieldIsPresent("Ignore ADC Critical Low");
		test.siteConfigurationAction.verifyfieldIsPresent("Enable Earliest Expiration Date");
		test.siteConfigurationAction.verifyfieldIsPresent("Send to Packager");
		test.siteConfigurationAction.verifyfieldIsPresent("Exclude from Inventory Report");
		test.siteConfigurationAction.verifyfieldIsPresent("Require Lot/Expiration During Restock");
		test.siteConfigurationAction.verifyfieldIsPresent("Consignment");
		test.siteConfigurationAction.verifyfieldIsPresent("ADC Quantity Rounding");
		test.siteConfigurationAction.verifyfieldIsPresent("Restock Rounding Factor");
		test.siteConfigurationAction.verifyfieldIsPresent("Cycle Count Interval (Days)");
		test.siteConfigurationAction.verifyfieldIsPresent("Average Cost (Eaches)");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		
}
	
	@Test(priority = 5, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] \"Edit Multiple items \" window get opened after clicking on the\" Edit Selected\" button under the Actions tab for Facility level edit .")
	public void Test05_1106268(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] \"Edit Multiple items \" window get opened after clicking on the\" Edit Selected\" button under the Actions tab for Facility level edit .");
		test.supportDataActions.clickButton("edit");
		
			test.siteConfigurationAction.clickfacilityonEditItem("2");
			test.siteConfigurationAction.clickCheckbox("admIgnoreStockOutFlag");
			test.siteConfigurationAction.clickButton("save");
			
			test.supportDataActions.selectMultipleCheckboxes("1");
			test.supportDataActions.selectMultipleCheckboxes("2");
			test.siteConfigurationAction.clickActionbutton("Actions");
			test.siteConfigurationAction.clickActionbutton("Edit Selected");
			test.siteConfigurationAction.clickfacilityLevel("Facility Level");
			test.siteConfigurationAction.verifyDefaultValueInDropDown("aduIgnoreStockOut", "--Mixed");
	
	}
	@Test(priority = 7, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] Respective value shows in the columns which has same values for the different items")
	public void Test07_1106277(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] Respective value shows in the columns which has same values for the different items");
		test.siteConfigurationAction.verifyDefaultValueInDropDown("aduIgnoreCriticalLow", "NO");
		
	}
	@Test(priority = 8, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] Items of respective facility updated successfully after save .")
	public void Test08_1106285(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] Items of respective facility updated successfully after save .");
		test.siteConfigurationAction.selectValueFromDropDownForExternalSystem("sentToPackager","Yes");
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		test.siteConfigurationAction.clickfacilityonEditItem("2");
		test.siteConfigurationAction.verifyDefaultValueInDropDown("sentToPackager","Yes");
	}
	
}
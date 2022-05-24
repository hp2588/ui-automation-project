package com.org.tests.itemsmanagement;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_975822 extends BaseTest {

	String itemID, brandName,theraputic_code,itemName;

	@Test(priority = 1, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to perform contains search on the basis of Item ,itemID and BrandName on Item management page."
			+ ""
			+ "VPLX: Item Setup (System and Facility): [UI]: User is able to select externalsystem from dropdown .")

	public void Test01_Test02_1048494_AND_1048545(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to perfrom a contains search on the itemID field.");
		
		
		test.landingPageActions.navigateToFeature("Therapeutic Classes");
		test.siteConfigurationAction.selectValueFromDropDown("Therapeutic", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.verifyAndClickProductID("Add Therapeutic Class");		
		theraputic_code=test.siteConfigurationAction.enterRandomValueInInputField("therapeuticClassCode", "Code"+System.currentTimeMillis());		
		test.storageAreaAction.clickSaveButton();		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		
		
	//	test.landingPageActions.navigateToItemManagementFeature("Item Management");
		//test.siteConfigurationAction.enterRandomValueInRichInputField(getData("ExternalSystem.Name7"));
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacility" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem" + System.currentTimeMillis());
		brandName = test.siteConfigurationAction.enterDataInInputField("brandName", "brand1");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		
		//test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		//Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(itemID, "4"));

	}


	@Test(priority = 4, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to clear the search text on search field."
			+ ""
			+ "VPLX: Item Setup (System and Facility): [UI]: User is able to view the list of all items on the Item Management screen.")
	public void Test03_Test04_1048498_AND_1048482(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to clear the search text on search field.");
		test.supportDataActions.clearSearchBox("search");
		test.supportDataActions.codeListDosageForms("2");
		//test.supportDataActions.codeListDosageForms("3");
		//test.supportDataActions.codeListDosageForms("4");
		//test.supportDataActions.codeListDosageForms("6");
		Assert.assertTrue(test.siteConfigurationAction.selectAllCheckboxesonItemScreen(),
				"[ASSERTION FAILED]: All checkbox is not clicked.");
		
	}

	@Test(priority = 6, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to perfrom sorting on columns Item , Brandname ,ProductId ,Location and ItemID."
			+ ""
			+ "VPLX: Item Setup (System and Facility): [UI]: User is able to validate the checkboxes on Item Management.")
	public void Test05_Test06_1048500_AND_1062811(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to perfrom sorting on columns Item , Brandname ,ProductId ,Location and ItemID."
				+ ""
				+ "VPLX: Item Setup (System and Facility): [UI]: User is able to validate the checkboxes on Item Management.");
		test.siteConfigurationAction.selectAllCheckboxesonItemScreen();
		test.siteConfigurationAction.verifyAndClickSortIcon("Item");
		test.siteConfigurationAction.verifyAndClickSortIcon("Brand Name");
		test.siteConfigurationAction.verifyAndClickSortIcon("Item ID");
	}

	@Test(priority = 7, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to perform sorting on the Product Id column of Item Management listing screen.")
	public void Test07_1048524(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to perform sorting on the Product Id column of Item Management listing screen.");
		test.siteConfigurationAction.verifyAndClickSortIcon("Product IDs");
	}

	//@Test(priority = 8, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to view the edit option under column Actions on the view screen of item management.em and Facility): [UI]: User is able to view the edit option on the view screen of item management.")
	public void Test08_1048616(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to view the edit option under column Actions on the view screen of item management.");
		test.siteConfigurationAction.verifyButtonIsPresent(itemName);
	}

	@Test(priority = 9, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to view the columns description , Brandname , ProductId  and Item ID on Item management view page.")
	public void Test09_1048578(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to view the columns description , Brandname , ProductId  and Item ID on Item management view page.");
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Item"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Brand Name"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Item ID"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Product IDs"));
	}

	@Test(priority = 10, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to view pagination on Item Management screen.")
	public void Test10_1048508(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to view pagination on Item Management screen.");
		test.siteConfigurationAction.verifyPaginationButtonIsPresent("1");
		test.siteConfigurationAction.verifyDefaultValueShowRowsItemDropDown("selectRowName", "25");

	}

	@Test(priority = 11, description = "VPLX: Item Setup (System and Facility): [UI]: User is not able to perfrom search when search text is entered as a value which does not exist in list.")
	public void Test11_1048531(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is not able to perfrom search when search text is entered as a value which does not exist in list.");
		test.supportDataActions.enterSearchTermInSearchField(DateUtil.getTommorrowsDate(), "search");
		Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Data Available.");
	}

	@Test(priority = 12, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to view the filter option on the view screen of item management.")
	public void Test12_1048550(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to view the filter option on the view screen of item management.");
		test.siteConfigurationAction.clickFilterButton();
		test.siteConfigurationAction.verifyFilterItemsPopup();
		test.siteConfigurationAction.clickButton("cancel");

	}
	@Test(priority = 13, description = "VPLX: Item Setup (System and Facility): [UI]: Item ID is unique for all items within the same PIS")
	public void Test13_1129971(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: Item ID is unique for all items within the same PIS");
			
	test.siteConfigurationAction.clickActionbutton("Actions");
	test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
	test.siteConfigurationAction.enterDataInInputField("genericName",
			"Systemlevelfacility" + System.currentTimeMillis());
	test.siteConfigurationAction.enterDataInInputField("itemId",
			itemID);
	brandName = test.siteConfigurationAction.enterDataInInputField("brandName", "brand1");
	test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
	//test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
	test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
    test.siteConfigurationAction.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());

	//test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
	test.siteConfigurationAction.clickButton("save");
	test.siteConfigurationAction.verifyErrorMessagemandaRoutingRule("This Item ID already exists. Please provide a unique Item ID.");
	//test.siteConfigurationAction.verifyErrorMessageonItemscreen("This Item ID already exists. Please provide a unique Item ID.");
	test.siteConfigurationAction.clickActionbutton("Cancel");
	}

	
	@Test(priority = 14, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to Add Enterprise Id for an item at system level."
			+ ""
			+ "VPLX: Item Setup (System and Facility): [UI]: User is able to Add/Edit PIS Alternate Id for an item at Pharmacy Item"
			+ ""
			+ "VPLX: Item Setup (System and Facility): [UI]: User is able to add multiple Label Tag values from dropdown.")
	public void Test14_Test15_Test16_1129274_AND_1129275_AND_1129970(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to Add Enterprise Id for an item at system level."
				+ ""
				+ "VPLX: Item Setup (System and Facility): [UI]: User is able to Add/Edit PIS Alternate Id for an item at Pharmacy Item"
				+ ""
				+ "VPLX: Item Setup (System and Facility): [UI]: User is able to add multiple Label Tag values from dropdown.");
		
		
	test.siteConfigurationAction.clickActionbutton("Actions");
	test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
	test.siteConfigurationAction.enterDataInInputField("genericName",
			"Systemlevelfacility" + System.currentTimeMillis());
		test.siteConfigurationAction.enterDataInInputField("itemId",
			"SystemlevelItem77x" + System.currentTimeMillis());
	brandName = test.siteConfigurationAction.enterDataInInputField("brandName", "brand1");
	test.siteConfigurationAction.enterDataInInputField("enterpriseId", "enterpriseid1");
	test.siteConfigurationAction.enterDataInInputField("alternateItemID", "alternatepisid1");
	test.siteConfigurationAction.clickTherapeuticdropdownonItemScreen("labeltags_trigger");
	//test.siteConfigurationAction.clickCheckboxTherapeuticClassitemlevel(getData("ExternalSystem.labeltag1"));
	test.siteConfigurationAction.clickCheckboxOfRichInputTextBox("labeltags-0");
	
	test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
	//test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
	test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
	test.siteConfigurationAction.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
//	test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
	test.siteConfigurationAction.clickButton("save");
	test.siteConfigurationAction.clickActionbutton("Cancel");
	}

	@Test(priority = 17, description = "VPLX: Item Setup (System & Facility) : [UI] -User enters cycle count interval on facility attributes tab.")
	public void Test17_1129978(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System & Facility) : [UI] -User enters cycle count interval on facility attributes tab.");
		
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName);
		test.siteConfigurationAction.clickfacilityonEditItem("2");
		test.siteConfigurationAction.enterDataInInputField("cycleCountIntervalDayAmount","12");
		test.siteConfigurationAction.clickButton("save");
	
}
	@Test(priority = 18, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to select only one facility at a time for a particular externalsystem.")
	public void Test18_1048561(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to select only one facility at a time for a particular externalsystem.");
		test.supportDataActions.clearSearchBoxField("search");
		//test.siteConfigurationAction.ClickOnExternalSystemRichTextbox();
		
		test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		//test.siteConfigurationAction.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		//test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(getData("ExternalSystem.Name8"));

	}


}
package com.org.tests.itemsparityapprovalqueue;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1061316 extends BaseTest {

	String itemID, brandName, itemId;
	String itemID10, itemID11, genericName,
			facilityOnWFAScreen = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(),
			External = TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim();

	@Test(priority = 1, description = "VPLX: Item Setup - ES Parity (Approval Queue)  [UI]: The 'Items' tab is available on the Item Management screen.")
	public void Test01_1090487(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue)  [UI]: The 'Items' tab is available on the Item Management screen.");

		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.verifyItemsTabonItemscreen("Items");
	}

	@Test(priority = 2, description = "VPLX: Item Setup - ES Parity (Approval Queue)  [UI]: Pending Approval tab is available to show Approved Formulay at Facility level ."
			+ ""
			+ "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] : The Item tab is default selected when the user navigates to the Facility-Level formulary.")

	public void Test02_Test03_1090496_And_1092577(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue)  [UI]: Pending Approval tab is available to show Approved Formulay at Facility level ."
				+ ""
				+ "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] : The Item tab is default selected when the user navigates to the Facility-Level formulary.");

		test.siteConfigurationAction.ClickOnExternalSystemRichTextbox();
		//test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(facilityOnWFAScreen);
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsTabonItemscreen("Items"));
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsTabonItemscreen("Pending Approval"));
	}

	@Test(priority = 4, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] : The inline actions (Edit) for all items get disabled when the user selects multiple items from the Item list either on a PIS or a Facility level.")

	public void Test04_1092582(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] : The inline actions (Edit) for all items get disabled when the user selects multiple items from the Item list either on a PIS or a Facility level.");
		test.siteConfigurationAction.selectAllCheckboxesonItemScreen();
		test.siteConfigurationAction.verifyButtonIsDisabled("edit");
		test.siteConfigurationAction.selectAllCheckboxesonItemScreen();
	}

	@Test(priority = 5, description = "VPLX: Item Setup - ES Parity (Approval Queue)  [UI] : New item  is available in the Pending Approval  tab by selecting the Facility.")
	public void Test05_1090527(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue)  [UI] : New item  is available in the Pending Approval  tab by selecting the Facility.");
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
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(facilityOnWFAScreen);
		test.siteConfigurationAction.clickButton("save");

		test.siteConfigurationAction.clickButton("cancel");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(itemID, "4"));
		test.supportDataActions.clearSearchBoxField("search");
	}

	@Test(priority = 6, description = "VPLX: Item Setup - ES Parity (Approval Queue) [UI] : The checkbox is available in front of every approved item."
			+ ""
			+ "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] The user is able to perform the search for the Items tab on the basis of the description field.")

	public void Test06_Test07_1090561_AND_1090598(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) [UI] : The checkbox is available in front of every approved item."
				+ ""
				+ "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] The user is able to perform the search for the Items tab on the basis of the description field.");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		genericName = test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacility" + System.currentTimeMillis());
		itemID10 = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickButton("cancel");

		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacility" + System.currentTimeMillis());
		itemID11 = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickButton("cancel");

		test.siteConfigurationAction.clickPendingApprovalTabonItemScreen("Pending Approval");
		test.supportDataActions.enterSearchTermInSearchFieldGl(genericName, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(genericName, "2"));
		test.supportDataActions.clearSearchBoxField("search");
		test.siteConfigurationAction.verifycheckbox1ispresent("1");
	}

	@Test(priority = 8, description = "VPLX: Item Setup - ES Parity (Approval Queue) [UI]: The columns under the Approved item tab display correctly on PIS Level.")

	public void Test08_1090593(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) [UI]: The columns under the Items tab display correctly on PIS Level.");
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Item");
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Brand Name");
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Item ID");
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Actions");

	}

	@Test(priority = 9, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] User can check multiple Formularies under the approved item .")

	public void Test09_1090568(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] User can check multiple Formularies under the approved item .");
		test.supportDataActions.selectMultipleCheckboxes("1");
		test.supportDataActions.selectMultipleCheckboxes("2");
	}

	@Test(priority = 10, description = "VPLX: Item Setup - ES Parity (Approval Queue) [UI] : The user is able to select all the approved items at a time.")

	public void Test10_1090588(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) [UI] : The user is able to select all the approved items at a time.");
		test.siteConfigurationAction.selectAllCheckboxesonItemScreen();

	}

	@Test(priority = 11, description = "VPLX: Item Setup - ES Parity (Approval Queue)  [UI]  : Pagination is available to select approved items on next page.")

	public void Test11_1090597(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue)  [UI]  : Pagination is available to select approved items on next page.");
		test.siteConfigurationAction.verifyPaginationButtonIsPresent("1");
		test.siteConfigurationAction.verifyDefaultValueShowRowsItemDropDown("selectRowName", "25");

	}

	@Test(priority = 12, description = "VPLX: Item Setup - ES Parity (Approval Queue)  [UI] : Item will not be available under the facility for which it is not selected during the item creation.")
	public void Test12_1090530(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue)  [UI] : Item will not be available under the facility for which it is not selected during the item creation.");
		// test.siteConfigurationAction.ClickOnExternalSystemRichTextbox();
		test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(facilityOnWFAScreen);
		test.siteConfigurationAction.clickPendingApprovalTabonItemScreen("Pending Approval");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Data Available.");

	}

}
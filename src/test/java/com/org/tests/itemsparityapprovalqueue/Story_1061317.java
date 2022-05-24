package com.org.tests.itemsparityapprovalqueue;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1061317 extends BaseTest {

	String itemID, itemID10, itemID11, itemID12, genericName;

	ArrayList<String> previous_data, sorted_data;
	String facilityOnWFAScreen = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(),
			External = TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim();

	@Test(priority = 1, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The Pending Approval tab is not available when the user navigates to the PIS-level Formulary")

	public void Test01_1100751(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The Pending Approval tab is not available when the user navigates to the PIS-level Formulary");

		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		Assert.assertFalse(test.siteConfigurationAction.verifyPendingApprovalTabonItemscreen("Pending Approval"));

	}

	@Test(priority = 2, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The Newly added Items on the PIS level and not checking it as Approved for a specific facility then the item is automatically marked as  Pending Approval tab for all not-specified Facilities.")

	public void Test02_1100820(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The Newly added Items on the PIS level and not checking it as Approved for a specific facility then the item is automatically marked as  Pending Approval tab for all not-specified Facilities.");
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
		genericName = test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacility" + System.currentTimeMillis());
		itemID11 = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickButton("cancel");

		test.siteConfigurationAction.ClickOnExternalSystemRichTextbox();
		test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(facilityOnWFAScreen);
		test.siteConfigurationAction.clickPendingApprovalTabonItemScreen("Pending Approval");
		test.supportDataActions.enterSearchTermInSearchFieldGl(genericName, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(genericName, "2"));
		test.supportDataActions.clearSearchBoxField("search");

	}

	@Test(priority = 3, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The 'Items' tab is available when the user navigates to the Facility-level Formulary.")

	public void Test03_1100750(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The 'Items' tab is available when the user navigates to the Facility-level Formulary.");
		test.siteConfigurationAction.clickPendingApprovalTabonItemScreen("Items");
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsTabonItemscreen("Items"),
				"[AASERTION FAILED]: Items tab is not visible.");

	}

	@Test(priority = 4, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - Attributes are available on the  Pending Approval screen as Description, Brand Name, Item ID.")

	public void Test04_1100753(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - Attributes are available on the  Pending Approval screen as Description, Brand Name, Item ID.");
		test.siteConfigurationAction.clickPendingApprovalTabonItemScreen("Pending Approval");
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Item"),
				"[ASSERTION FAILED]: Column Item is not displayed.");
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Brand Name"),
				"[ASSERTION FAILED]: Column 'Brand Name' is not displayed.");
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Item ID"),
				"[ASSERTION FAILED]: Column 'Item ID' is not displayed.");
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Actions"),
				"[ASSERTION FAILED]: Column 'Actions' is not displayed.");
	}

	@Test(priority = 5, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The newly added Items on the PIS level and checking it as Approved for a particular Facilities then Items is displayed inside the Items tab for all not-specified Facilities.")

	public void Test05_1100834(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The newly added Items on the PIS level and checking it as Approved for a particular Facilities then Items is displayed inside the Items tab for all not-specified Facilities.");

		// test.siteConfigurationAction.ClickOnExternalSystemRichTextbox();
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacilityx" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem77x" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.siteConfigurationAction.ClickOnExternalSystemRichTextbox();
		test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(facilityOnWFAScreen);
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(itemID, "4"));
		test.supportDataActions.clearSearchBoxField("search");

	}

	@Test(priority = 6, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The Item is moved into the Items tab when the user clicks on 'Approved' from the Pending Approval tab.")

	public void Test06_1100844(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The Item is moved into the Items tab when the user clicks on 'Approved' from the Pending Approval tab..");
		test.siteConfigurationAction.ClickOnExternalSystemRichTextbox();
		test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(facilityOnWFAScreen);
		test.siteConfigurationAction.clickPendingApprovalTabonItemScreen("Pending Approval");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID10, "search");
		test.siteConfigurationAction.clickActionbutton("Approve");
		test.siteConfigurationAction.clickPendingApprovalTabonItemScreen("Items");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID10, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(itemID10, "4"));
		test.supportDataActions.clearSearchBoxField("search");

	}

	@Test(priority = 7, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - Item is removed from the Pending Approval tab when the user clicks on the 'Rejected' button from the Pending Approval tab")

	public void Test07_1100848(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - Item is removed from the Pending Approval tab when the user clicks on the 'Rejected' button from the Pending Approval tab");
		test.siteConfigurationAction.clickPendingApprovalTabonItemScreen("Pending Approval");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID11, "search");
		test.siteConfigurationAction.clickActionbutton("Reject");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID11, "search");
		Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Data Available.");
		test.supportDataActions.clearSearchBoxField("search");

	}

	@Test(priority = 8, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The Approve/Reject buttons get disabled when the user selected multiple Items from the Pending Approval  screen.")
	public void Test08_1100858(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The Approve/Reject buttons get disabled when the user selected multiple Items from the Pending Approval  screen.");
		test.siteConfigurationAction.selectAllCheckboxesonItemScreen();
		test.siteConfigurationAction.verifyButtonIsDisabled("approve");
		test.siteConfigurationAction.verifyButtonIsDisabled("reject");
	}

	@Test(priority = 9, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The system is displayed all items inside the Pending Approval tab which does not approve on a Facility level yet.")
	public void Test09_1100865(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The system is displayed all items inside the Pending Approval tab which does not approve on a Facility level yet.");
		test.siteConfigurationAction.selectAllCheckboxesonItemScreen();
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		genericName = test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacility" + System.currentTimeMillis());
		itemID12 = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem" + System.currentTimeMillis());
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickButton("cancel");

		test.siteConfigurationAction.clickPendingApprovalTabonItemScreen("Pending Approval");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID12, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(itemID12, "4"));
		test.supportDataActions.clearSearchBoxField("search");

	}

	@Test(priority = 10, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The Items are listed in sorted alphabetically order inside Pending Approval tab.")
	public void Test10_1100773(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The Items are listed in sorted alphabetically order inside Pending Approval tab.");
		previous_data = test.supportDataActions.captureDataForParticularColumnDosageForm("4");
		System.out.println("Previous data :  " + previous_data);
		sorted_data = test.supportDataActions.sortDataForParticularColumnInAscendingOrderDosageForm("4");
		System.out.println("Sorted data :  " + sorted_data);
		Assert.assertNotEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Code column data is not sorted in ascending order");

	}
}
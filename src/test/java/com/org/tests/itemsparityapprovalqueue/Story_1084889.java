package com.org.tests.itemsparityapprovalqueue;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1084889 extends BaseTest {

  String itemID10,itemID11;
  String facilityOnWFAScreen = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(),
			External = TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim();
	@Test(priority = 1, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - 'Add from PIS' tab is available in the Action list under the System Items category.")

	public void Test01_1100674_AND_1100676(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - 'Add from PIS' tab is available in the Action list under the System Items category.");
		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacility" + System.currentTimeMillis());
		itemID10 = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		
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
		test.siteConfigurationAction.clickActionbutton("Cancel");
		
		test.siteConfigurationAction.clickPendingApprovalTabonItemScreen("Pending Approval");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID10, "search");
		test.siteConfigurationAction.clickActionbutton("Reject");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID11, "search");
		test.siteConfigurationAction.clickActionbutton("Reject");
		
	    test.siteConfigurationAction.clickActionbutton("Actions");
		
		test.siteConfigurationAction.verifyAddFromPISIsPresent("Add From PIS");
		/*
		test.supportDataActions.enterSearchTermInSearchFieldGl("item", "search");
		test.supportDataActions.selectMultipleCheckboxes(getData("ExternalSystem.pisitem1"));
		test.supportDataActions.selectMultipleCheckboxes(getData("ExternalSystem.pisitem2"));
		test.siteConfigurationAction.clickActionbutton("Add To Queue");
		*/
		}
	
	@Test(priority = 2, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - 'Add from PIS' dialog box is displayed when the user clicked on the 'Add from PIS' button.")

	public void Test01_1100731(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - 'Add from PIS' dialog box is displayed when the user clicked on the 'Add from PIS' button.");
		test.siteConfigurationAction.clickAddNewItemPOP("Add From PIS");
		test.siteConfigurationAction.verifyFilterItemsPopup();
}
	
	@Test(priority = 3, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - Attributes 'Description' and 'Item ID' on the Add from PIS screen.")

	public void Test03_1100734(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - Attributes 'Description' and 'Item ID' on the Add from PIS screen.");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID10, "itemSearch");
		test.siteConfigurationAction.verifyAddFromPIScolumnsIsPresent("Item");
		test.siteConfigurationAction.verifyAddFromPIScolumnsIsPresent("Item ID");
		}
	
	@Test(priority = 4, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] -  The System is allowed to select multiple items from the 'Add from PIS' screen.")
	public void Test04_1100736(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] -  The System is allowed to select multiple items from the 'Add from PIS' screen.");
	  test.supportDataActions.selectMultipleCheckboxes("1");
	  }
	
	@Test(priority = 5, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] -  The 'Add from PIS' dialog box is closed when user click on 'Cancel' button.")
	public void Test05_1100738(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] -  The 'Add from PIS' dialog box is closed when user click on 'Cancel' button.");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.supportDataActions.clearSearchBoxField("search");
	    test.siteConfigurationAction.verifyItemsTabonItemscreen("Items");
		}
	
	@Test(priority = 6, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The user is able to filter Formulary Items by using the “Contains” search criteria.")
	public void Test06_1100744_AND_1100747(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The user is able to filter Formulary Items by using the “Contains” search criteria.");
		 test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add From PIS");
		test.supportDataActions.enterSearchTermInSearchFieldGl("SystemlevelItem", "itemSearch");
		test.siteConfigurationAction.verifyItemsonItemscreen(itemID10);
	}
	@Test(priority = 7, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The item moves to Items tab when the user clicks on the 'Add to Queue' button from Add from PIS.")
	public void Test07_1100740(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The item moves to Items tab when the user clicks on the 'Add to Queue' button from Add from PIS.");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID10, "itemSearch");
		//test.supportDataActions.selectMultipleCheckboxes("1");
		//test.supportDataActions.selectMultipleCheckboxes("2");
		test.siteConfigurationAction.selectAllCheckBox("addFromPisAllCheckbox");
		//test.supportDataActions.selectMultipleCheckboxes(getData("ExternalSystem.multiplecheckboxes"));
		test.siteConfigurationAction.clickActionbutton("Add To Queue");
		test.siteConfigurationAction.clickPendingApprovalTabonItemScreen("Items");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID10, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(itemID10),
				"[ASSERTION FAILED]: Item is not displayed.");
		}
}
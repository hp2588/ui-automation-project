package com.org.tests.integrationSuite1;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class DosageForm_Integration extends BaseTest {

	String dosageFormCode1, itemName;

	@Test(priority = 1, description = "VPLX: Dosage Form: [UI]: [Integration]: When a Dosage form is updated , it gets populated in Dosage Form drop-down on Add/Edit Item page"
			+ "&"
			+ "VPLX: Item Setup (System and Facility): [UI]:When Generic name or Dosage Form is updated for an item, the updated Item Description gets displayed on Item Mgmt screen")
	public void Test01_1107002_1112408(Method method) {
		test.landingPageActions.navigateToFeature("Dosage Forms");
		test.siteConfigurationAction.selectValueFromDropDown("externalSystems",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DosageFormCode1"), "search");
		test.supportDataActions.clickAddButtonOnDistributor(TestDataPropertyReaderAndWriter.getProperty("DosageFormCode1"));
		dosageFormCode1 = "Code" + System.currentTimeMillis();
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", dosageFormCode1);
		test.supportDataActions.clickButton("save");
		TestDataPropertyReaderAndWriter.setProperty("DosageFormCode1", dosageFormCode1);

		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode1").trim());

		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());

		test.supportDataActions
				.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ItemID").trim(), "search");
		test.supportDataActions
				.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());

		test.supportDataActions.verifyDropdownElementsDefaultRule("dispensingFormKey",
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode1"));
		itemName = test.siteConfigurationAction.enterDataInInputField("genericName",
				"ItemName" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode1").trim());
		test.siteConfigurationAction.clickButton("save");
		TestDataPropertyReaderAndWriter.setProperty("ItemName", itemName);
		TestDataPropertyReaderAndWriter.setProperty("ItemName", itemName);

	//	test.landingPageActions.navigateToFeature("Main Menu");
	//	test.landingPageActions.navigateToItemManagementFeature("Item Management");
	//	test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions
				.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ItemID").trim(), "search");
		Assert.assertTrue(test.siteConfigurationAction.verifyUpdatedItemDescription(
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim(),
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode1").trim()));
	}
	
	@Test(priority = 2, description = "VPLX: Item Setup (System and Facility): [UI]:When Generic name or Dosage Form is updated for an item, the updated Item Description gets displayed on Location Management screen")
	public void Test02_1112797(Method method) {
		//test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName"),
				"search");
		test.supportDataActions
				.updatedDosageCodeDisplayed(TestDataPropertyReaderAndWriter.getProperty("DosageFormCode1").trim());
	}

	@Test(priority = 3, description = "VPLX: Dosage Form: [UI]: [Integration]: Dosage form,which is not mapped to any item is made inactive, then the inactive Dosage form is not displayed in drop-down on Add/Edit Ite")
	public void Test03_1107003(Method method) {
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Dosage Forms");
		test.siteConfigurationAction.selectValueFromDropDown("externalSystems",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DosageFormCode2"), "search");
		test.supportDataActions.clickAddButtonOnDistributor(TestDataPropertyReaderAndWriter.getProperty("DosageFormCode2"));
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Active", false);
		test.supportDataActions.clickButton("save");

		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertFalse(test.supportDataActions.verifyDropdownElementsDefaultRuleNotPresent("dispensingFormKey",
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode2")));

		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions
				.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ItemID").trim(), "search");
		test.supportDataActions
				.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		Assert.assertFalse(test.supportDataActions.verifyDropdownElementsDefaultRuleNotPresent("dispensingFormKey",
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode2")));
	}

	@Test(priority = 4, description = "VPLX: Item Setup (System and Facility): [UI]:When Generic name or Dosage Form is updated for an item, the updated Item Description gets displayed on Add Pick screen")
	public void Test04_1112409(Method method) {
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortISAName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultOnTQ("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim(),
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode1").trim());
		test.siteConfigurationAction.clickButton("cancel");
	}

	@Test(priority = 5, description = "VPLX: Item Setup (System and Facility): [UI]:When Generic name or Dosage Form is updated for an item, the updated Item Description gets displayed on Add Restock screen")
	public void Test05_1112410(Method method) {
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturnTQ("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim(),
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode1").trim());
		test.siteConfigurationAction.clickButton("cancel");
		test.siteConfigurationAction.clickButton("primary");
	}

	@Test(priority = 6, description = "VPLX: Item Setup (System and Facility): [UI]:When Generic name or Dosage Form is updated for an item, the updated Item Description gets displayed on Add Return screen")
	public void Test06_1112794(Method method) {
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturnTQ("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim(),
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode1").trim());
		test.siteConfigurationAction.clickButton("cancel");
		test.siteConfigurationAction.clickButton("primary");
	}

}

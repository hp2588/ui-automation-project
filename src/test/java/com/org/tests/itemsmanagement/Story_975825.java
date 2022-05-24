package com.org.tests.itemsmanagement;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_975825 extends BaseTest {

	String itemClass,itemID1, itemName, bulkitemdes, itemID;
	private WebElement bulkitemds;

	@Test(priority = 1, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to map a item to a facility when clicking on Add Item screen.")
	public void Test01_1064203(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to map a item to a facility when clicking on Add Item screen.");
		
		
		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		itemName=test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacilityx" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem77x" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName);
		test.siteConfigurationAction.clickfacilityonEditItem("2");

	}

	@Test(priority = 2, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to edit ADU Ignore stockout flag when item is mapped to a facility.")
	public void Test02_1064256(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to edit ADU Ignore stockout flag when item is mapped to a facility.");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("admIgnoreStockOutFlag"));
		test.siteConfigurationAction.selectCheckbox("admIgnoreStockOutFlag", true);

	}

	@Test(priority = 3, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to edit ADU Ignore critical low flag when item is mapped to a facility.")
	public void Test03_1064269(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to edit ADU Ignore critical low flag when item is mapped to a facility.");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("admIgnoreCriticalLowFlag"));
		test.siteConfigurationAction.selectCheckbox("admIgnoreCriticalLowFlag", true);
	}

	@Test(priority = 4, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to edit Enable earliest exp. date at facility level.")
	public void Test04_1064270(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to edit Enable earliest exp. date at facility level.");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("enableOldestExpirationDateFlag"));
		test.siteConfigurationAction.selectCheckbox("enableOldestExpirationDateFlag", true);
	}

	@Test(priority = 5, description = "VPLX: Item Setup (System and Facility): [UI] -When Consignment checkbox is selected, the value is saved as True in database")
	public void Test05_1064272(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -When Consignment checkbox is selected, the value is saved as True in database");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("consignmentFlag"));
		test.siteConfigurationAction.selectCheckbox("consignmentFlag", true);
	}

	@Test(priority = 6, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to edit Send to packager flag at facility level.")
	public void Test06_1064273(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to edit Send to packager flag at facility level.");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("forPackagerFlag"));
		test.siteConfigurationAction.selectCheckbox("forPackagerFlag", true);
	}

	@Test(priority = 7, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to edit Exclude from inventory report.")
	public void Test07_1064275(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to edit Exclude from inventory report.");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("excludeFromInventoryReportFlag"));
		test.siteConfigurationAction.selectCheckbox("excludeFromInventoryReportFlag", true);
	}

	@Test(priority = 8, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to edit Expiration during Restock.")
	public void Test08_1064277(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to edit Expiration during Restock.");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("requestRestockLotInfoFlag"));
		test.siteConfigurationAction.selectCheckbox("requestRestockLotInfoFlag", true);
	}

	@Test(priority = 9, description = "VPLX: Item Setup (System & Facility) : [UI] -User is able to edit ADC Quantity Rounding at facility level.")
	public void Test9_1129980(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System & Facility) : [UI] -User is able to edit ADC Quantity Rounding at facility level.");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("admQuantityRoundingFlag"));
		test.siteConfigurationAction.selectCheckbox("admQuantityRoundingFlag", true);
	}
	
	/*@Test(priority = 10, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to edit the prepack flag.")
	public void Test10_1064834(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to edit the prepack flag.");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("prepackFlag"));
		test.siteConfigurationAction.selectCheckbox("prepackFlag", true);
		test.siteConfigurationAction.selectCheckbox("prepackFlag", false);
	}

	@Test(priority = 11, description = "VPLX: Item Setup (System and Facility): [UI] -User checks the prepack flag bulkitemid becomes mandatory.")
	public void Test11_1066851(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User checks the prepack flag bulkitemid becomes mandatory.");
		test.siteConfigurationAction.selectCheckbox("prepackFlag", true);
		test.siteConfigurationAction.verifyFieldIsMandatory("bulkItemID");
	}

	@Test(priority = 12, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to select bulkitemid when clicking on set bulk item")
	public void Test12_1066858(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to select bulkitemid when clicking on set bulk item");
		test.siteConfigurationAction.clickBulkItemButton();
		test.siteConfigurationAction.verifyBulkItemPopup();

	}

	@Test(priority = 13, description = "VPLX: Item Setup (System and Facility): [UI] -Listing will consist Description and ItemId when user selects Set Bulk Item")
	public void Test13_1066874(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -Listing will consist Description and ItemId when user selects Set Bulk Item");
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Item");
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Item ID");

	}

	@Test(priority = 14, description = "VPLX: Item Setup (System and Facility): [UI] -Search works on Description column of Set Bulk Item.")
	public void Test14_1066863(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -Search works on Description column of Set Bulk Item.");
		bulkitemdes=test.supportDataActions.enterSearchTermInSearchFieldGl((getData("ExternalSystem.BulkItem")), "search");
	}

	@Test(priority = 15, description = "VPLX: Item Setup (System and Facility): [UI] -User selects the value from pop up and the value gets populated in text box.")
	public void Test15_1066864(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User selects the value from pop up and the value gets populated in text box.");
		test.supportDataActions.selectBulkItemFromList("1");
		test.siteConfigurationAction.clickButton("primary");
	}*/

	@Test(priority = 10, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to edit active checkbox.")
	public void Test10_1064211(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to edit active checkbox.");
		test.siteConfigurationAction.scrollUp();
		test.siteConfigurationAction.clickActiveToggle("Active");
	}

	@Test(priority = 11, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to edit GL account values from dropdown.")
	public void Test11_1064281(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to edit GL account values from dropdown.");		
		test.siteConfigurationAction.clickButton("save");
		

	}
    
	@Test(priority = 12, description = "VPLX: Item Setup (System and Facility): [UI]:When an Item ID is updated, it gets updated on Location Management screen.")
	public void Test12_1112407(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]:When an Item ID is updated, it gets updated on Location Management screen.");
		//test.siteConfigurationAction.clickActionbutton("OK");
		//test.siteConfigurationAction.clickActionbutton("Cancel");
		
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName);
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItemuu" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("save");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
	    test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
	    test.supportDataActions.enterSearchTermInSearchFieldGl(itemID1,"search");
	    test.supportDataActions.clickOnEditLinkCorresspondingToItemonItemLocations(itemName);
}
	
}

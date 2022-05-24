package com.org.tests.mainmenu.locassmanagingstoragebinoptions;


import static com.org.automation.utils.YamlReader.getData;

import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;


@Listeners(com.org.listeners.TestListener.class)

public class Story_1062137 extends BaseTest{

	String facility, facilityOnISA, name , name2, shortName, shortname2, defaultComputer, defaultPrinter, type, deviceNumber, ipAddress,
	portNumber, carouselConnectionResetTime, app_url,name1,itemName1,itemID1;
	
	@Test(priority = 1, enabled = true, description = "VPLX:Location-Assignment(Bin Actions): "
			+ "[UI] - User is able to view the option group with right bin when clicking on actions button on bin.")
	public void Test01_1062137_1124240() throws InterruptedException {
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue", "30");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		name1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"ISA" + System.currentTimeMillis());
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());
		test.siteConfigurationAction.clickTab("ISA Configuration");
		test.storageAreaAction.enterDataInInputField("maxBinNumber", "4");
		Thread.sleep(3000);
		test.siteConfigurationAction.clickTabWithoutWait("Display Settings");
		test.siteConfigurationAction.clickTabWithoutWait("Approved Computers");
		test.storageAreaAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		Assert.assertTrue(test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item"));
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey", TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName", "ItemName" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId", "ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey", TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		
		
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemName1, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName1);
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa", name1);
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
		
		/*
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemName1, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName1);
		*/
	    Assert.assertTrue(test.siteConfigurationAction.clickEditItemAction());
		test.siteConfigurationAction.clickBinActions();
		test.siteConfigurationAction.verifyGroupAction("Group with Right Bin");
		
	}
	
	
	@Test(priority = 2, enabled = true, description = "VPLX:Location-Assignment(Bin Actions): "
			+ "[UI] -User is able to group multiple bins at a time.")
	public void Test02_1062137_1124247() {	
		
		test.siteConfigurationAction.clickBinOption("Group with Right Bin");
		test.siteConfigurationAction.verifySuccessMessageOnAddDivider("Successfully grouped Bin.");
		test.siteConfigurationAction.clickLastBinAction();
		test.siteConfigurationAction.verifyGroupAction("Group with Left Bin");
		test.siteConfigurationAction.clickBinOption("Group with Left Bin");
		test.siteConfigurationAction.verifySuccessMessageOnAddDivider("Successfully grouped Bin.");
		
	}
	
	
	@Test(priority = 3, enabled = true, description = "VPLX:Location-Assignment(Bin Actions): "
			+ "[UI] -Original bin numbers are displayed when user groups multiple bins.")
	public void Test03_1062137_1124248() {	
		
		test.siteConfigurationAction.verifyBinNumber();
		
	}
	
	
	@Test(priority = 4, enabled = true, description = "VPLX:Location-Assignment(Bin Actions): [UI] - "
			+ "Inventory values are retained from the original location and carried over to "
			+ "the new location when item is assigned to another location.")
	public void Test04_1062137_1124246() {
		test.siteConfigurationAction.clickOtherLocation();
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.verifyQuantity("refillPointQuantity", "40");
		test.siteConfigurationAction.verifyQuantity("parQuantity", "400");
		test.siteConfigurationAction.verifyQuantity("inventoryQuantity", "200");
	}
	
	
	@Test(priority = 5, enabled = true, description = "VPLX:Location-Assignment(Bin Actions): [UI] - Original grouped bins are automatically ungrouped and item is unassigned when item is assigned to another location.")
	public void Test05_1062137_1124245() {	
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.clickApplyChangesButton();
	}
	
}				

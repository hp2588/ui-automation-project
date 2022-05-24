package com.org.tests.mainmenu.locassmanagingstoragebinoptions;


import static com.org.automation.utils.YamlReader.getData;

import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;


@Listeners(com.org.listeners.TestListener.class)

public class Story_1062136 extends BaseTest{

	String itemName1,itemID1,name1,itemName2,itemID2;

	String facility, facilityOnISA, name, shortName, defaultComputer, defaultPrinter, type, deviceNumber, ipAddress,
	portNumber, carouselConnectionResetTime, app_url;


	@Test(priority = 1,enabled = true, description = "VPLX:Location Assignment - Managing Storage Area(Bin Actions): "
			+ "[UI] - User is allowed to remove a bin if there are no items assigned to the locations within the bin")
	public void Test01_1108668() throws InterruptedException {
		
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
		test.siteConfigurationAction.clickTab("Display Settings");
		test.siteConfigurationAction.clickTab("Approved Computers");
		test.storageAreaAction.clickSaveButton();
		
		test.landingPageActions.navigateToFeature("Main Menu");
		test.siteConfigurationAction.pageRefresh();
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item");
		
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName", "ItemName" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId", "ItemID" + System.currentTimeMillis());
		
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey", TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey", TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save"); 
		
		
		test.landingPageActions.navigateToMenu("Main Menu");
		
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",  TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemName1, "search");
		
		
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName1);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa", name1);
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.clickLastBinAction();
		Assert.assertTrue(test.siteConfigurationAction.verifyRemoveBin());
		test.siteConfigurationAction.clickRemoveBin();
		
	}
	
	
	@Test(priority = 2,enabled = true, description = "VPLX:Location-Assignment(Bin Actions): [UI] -User is able to "
			+ "view another item location on layout screen as grey colour.")
	public void Test02_1062136_1108671() {			
		
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
		
		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item");
		
		itemName2 = test.siteConfigurationAction.enterDataInInputField("genericName", "ItemName" + System.currentTimeMillis());
		itemID2 = test.siteConfigurationAction.enterDataInInputField("itemId", "ItemID" + System.currentTimeMillis());
		
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey", TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey", TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		
		
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemName2, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName2);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
			TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",  name1);
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		
		test.siteConfigurationAction.verifyGreyColor(getData("Location.Grey"));
		
	}
	
	
	@Test(priority = 3, enabled = true, description = "VPLX:Location-Assignment(Bin Actions): "
			+ "[UI] - User is allowed to remove a bin if it is the last bin of the shelf and is a grouped bin with item assigned to the left bin.")
	public void Test03_1108705() {
		
		test.siteConfigurationAction.clickAssignLocationButton();
        test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
        test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
        test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();	
		
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemName2, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName2);

		Assert.assertTrue(test.siteConfigurationAction.clickEditItemAction());

		test.siteConfigurationAction.clickSecondBinActions();
		test.siteConfigurationAction.verifyGroupAction("Group with Right Bin");
		test.siteConfigurationAction.clickBinOption("Group with Right Bin");
		test.siteConfigurationAction.verifySuccessMessageOnAddDivider("Successfully grouped Bin.");
		test.siteConfigurationAction.clickThirdBinAction();
		test.siteConfigurationAction.clickRemoveBin();
		test.siteConfigurationAction.verifySuccessMessageOnAddDivider("Successfully removed Bin.");
		
	}
	
	
	@Test(priority = 4,enabled = true, description = "VPLX:Location Assignment - Managing Storage Area(Bin Actions): "
			+ "[UI] - User is not allowed to remove a bin if it is the last bin of the shelf with item assigned to it.")
	public void Test04_1062136_1108704() {			
	
		test.siteConfigurationAction.clickSecondBinActions();
		test.siteConfigurationAction.verifyRemoveBinNotDisplayed();
		
	}
	

	@Test(priority = 5,enabled = true, description = "VPLX:Location-Assignment(Bin Actions): "
			+ "[UI] - User is not  allowed to remove the left bin in a grouped bin if item is assigned to a grouped bin .")
	public void Test05_1062136_1108707() {			
		
		test.siteConfigurationAction.verifyRemoveBinNotDisplayed();
		
	}

}

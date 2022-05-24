package com.org.tests.mainmenu.locassmanagingstoragebinoptions;


import static com.org.automation.utils.YamlReader.getData;

import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;


@Listeners(com.org.listeners.TestListener.class)

public class Story_1077129 extends BaseTest{

	String name, shortName, deviceNumber, ipAddress, portNumber, itemName1, itemID1;
	
	@Test(priority = 1,enabled = true, 
			description = "VPLX:Location-Assignment(Bin Actions): "
					+ "[UI] -User is able to view the option Bin Properties on the actions button of bin.")
	public void Test01_1077129_1124249() {
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		name = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());
		
		test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");
		test.siteConfigurationAction.clickTab("Carousel Settings");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("carouselKey", 1);
		deviceNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("devicenumber", "123456");
		ipAddress = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddressValue", "10.11.22.34");
		portNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("portNumber", "46345");
		
		test.siteConfigurationAction.clickTab("ISA Configuration");
		test.siteConfigurationAction.clickTab("Display Settings");
		test.siteConfigurationAction.clickTab("Approved Computers");
		
		test.storageAreaAction.clickSaveButton();
		
		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item");
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
		
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemName1,	"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName1);
		
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());  
		test.siteConfigurationAction.selectValueForDropDown("isa", name);
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.clickBinActions();
		test.siteConfigurationAction.verifyGroupAction("Bin Properties");
		
	}
	
	
	@Test(priority = 2,enabled = true, description = "VPLX:Location-Assignment(Bin Actions): [UI] -User selects bin properties and is able to configure tickbar left offset value.")
	public void Test02_1077129_1124256() {
		
		test.siteConfigurationAction.verifyGroupAction("Tick Bar Left Offset");
		//	Assert.assertTrue(test.siteConfigurationAction.verifyTickbarLeftProperties());
		Assert.assertTrue(test.siteConfigurationAction.clickTickbarLeftProperties());
		
	}
	
	
	@Test(priority = 3,enabled = true, description = "VPLX:Location Assignment - Managing Storage Area(Bin Actions): "
			+ "[UI] -User selects bin properties and is able to configure tickbar left offset value for carousel type ISA.")
	public void Test03_Test04_1077129_1124257_1124250() {
		
		test.siteConfigurationAction.clickBinActions();
		test.siteConfigurationAction.verifyGroupAction("Tick Bar Left Offset");
		Assert.assertTrue(test.siteConfigurationAction.clickTickbarLeftProperties());
		
		test.siteConfigurationAction.enterValueInTestTicbar();
	
	}
	
	
	@Test(priority = 5,enabled = true, description = "VPLX:Location-Assignment(Bin Actions): "
			+ "[UI] -User selects bin properties and validates ISA name.")
	public void Test05_1077129_1124251() {
		
		test.siteConfigurationAction.clickBinActions();
		test.siteConfigurationAction.verifyGroupAction("Bin Properties");
		test.siteConfigurationAction.clickBinOption("Bin Properties");
		test.siteConfigurationAction.verifyISANameBinProperties(name);
		
	}
	
	
	@Test(priority = 6,enabled = true, description = "VPLX:Location-Assignment(Bin Actions): "
			+ "[UI] -User selects bin properties and validates Rack")
	public void Test06_1077129_1124252() {
		
		Assert.assertTrue(test.siteConfigurationAction.verifyRackBinProperties());
		
	}
	
	
	@Test(priority = 7,enabled = true, description = "VPLX:Location-Assignment(Bin Actions): "
			+ "[UI] -User selects bin properties and validates Shelf")
	public void Test07_1077129_1124253() {
		
		Assert.assertTrue(test.siteConfigurationAction.verifyShelfBinProperties());
		
	}
	
	
	@Test(priority = 8,enabled = true, description = "VPLX:Location-Assignment(Bin Actions): "
			+ "[UI] -User selects bin properties and is able to set the value of bin width .")
	public void Test08_1077129_1124254() {
		
		//  Assert.assertTrue(test.siteConfigurationAction.clickBinOptionsLeftMost());
		//	Assert.assertTrue(test.siteConfigurationAction.clickBinProperties());
		Assert.assertTrue(test.siteConfigurationAction.editBinWidthBinProperties("4"));
		test.siteConfigurationAction.clickApplyChangesButton();
	}
	
	
	@Test(priority = 9,enabled = true, description = "VPLX:Location-Assignment(Bin Actions): "
			+ "[UI] -User is not able to see Bin Properties for static ISAs .")
	public void Test09_1077129_1124259() {
		test.landingPageActions.navigateToMenu("Main Menu");
		
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());  
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemName1, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName1);
		
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		
		test.siteConfigurationAction.selectValueForDropDown("isa", TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		
		test.siteConfigurationAction.clickBinActions();
		test.siteConfigurationAction.verifyBinPropertiesNotAvailable("Bin Properties");
		
	}	
	
}

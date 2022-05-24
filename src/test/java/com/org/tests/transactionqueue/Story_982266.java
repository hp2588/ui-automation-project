package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982266 extends BaseTest {
	 String itemID,brandName,facilityName;
	 
/*	@Test(priority=1, description= "VPLX:Forced Cycle Count:[UI]: Cycle count interval is set at Facility Item Setup screen for active items")
	public void Test01_1131153(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Forced Cycle Count:[UI]: Cycle count interval is set at Facility Item Setup screen for active items");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterRandomValueInRichInputFieldForExternalSystem(getData("CycleCount.FacilityName"));
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
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("CycleCount.FacilityName"));
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID, "Edit Item");
		test.siteConfigurationAction.clickfacilityonEditItem("2");
		test.siteConfigurationAction.enterDataInInputField("cycleCountIntervalDayAmount","2");
		test.siteConfigurationAction.clickButton("save");
	} 
	*/
	@Test(priority=1, description= "VPLX:Forced Cycle Count:[UI]: Cycle count interval is set at Location level")
	public void Test02_1131156(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Forced Cycle Count:[UI]: Cycle count interval is set at Location level");
	/*	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");

		facilityName = test.supportDataActions.enterSearchTermInSearchFieldGl(getData("CycleCount.FacilityName"), "search");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(facilityName);
		
		test.siteConfigurationAction.clickTab("Cycle Counts");
		test.siteConfigurationAction.selectCheckbox("autoGenCycleCountFlag", true);
		test.siteConfigurationAction.clickSaveButton();

		
		
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterRandomValueInRichInputFieldForExternalSystem(getData("CycleCount.FacilityName"));
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
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("CycleCount.FacilityName"));
		test.siteConfigurationAction.clickButton("save");
	/*	test.siteConfigurationAction.clickActionbutton("Cancel");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID, "Edit Item");
		test.siteConfigurationAction.clickfacilityonEditItem("2");
		test.siteConfigurationAction.enterDataInInputField("cycleCountIntervalDayAmount","2");
		test.siteConfigurationAction.clickButton("save"); */
		
	/*	test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",getData("CycleCount.FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID,"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID, "1544186059");
		
		
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		
		test.siteConfigurationAction.selectValueForDropDown("facility", "GoldFacility");
		test.siteConfigurationAction.selectValueForDropDown("isa", "ISAGG");
		
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		
	
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("cycleCountInterval", "2");
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("CycleCount.IPAddresss").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyCycleCountTransactionInTQ();
		
		
		test.transactionQueueActions.clickOnPickNow_Sanity("Cycle Count");
	   	Assert.assertTrue(test.transactionQueueActions.clickScanOverride(),"[Assertion Failed]: Error while processing transaction");
        test.transactionQueueActions.verifyandEnterQuantityForCycleCount();*/
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP("20.20.05.21");
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		Assert.assertTrue(test.transactionQueueActions.verifyCycleCountTransactionOnWFA(),
				"\"[ASSERTION FAILED]:No Cycle Count Transaction found\"");
	} 

}

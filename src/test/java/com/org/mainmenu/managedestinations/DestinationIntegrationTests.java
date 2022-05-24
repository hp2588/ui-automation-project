package com.org.mainmenu.managedestinations;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class DestinationIntegrationTests extends BaseTest {

	String destinationName,destinationNameUpdated, destinationCode,
	facilityOnWFAScreen,app_url, ISAName,routingRule,itemName,itemName1,firstname,FacilityName,transaction_type;
	
	@Test(priority = 1, description = "VPLX:Manage Destinations-General:[UI]:Enable ADC Quantity Rounding Flag can be select or unselect while ading the destination"
			)
	public void Test01_1130443() {
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		facilityOnWFAScreen = test.siteConfigurationAction.getFacilityFromISAScreen();

		ISAName = test.storageAreaAction.getISANameOnWFAScreen();
		test.siteConfigurationAction.clickActionbutton("Cancel");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.General")));
		Assert.assertTrue(test.siteConfigurationAction.checkCheckBoxDestination("admQuantiyRoundingFlag"));
		Assert.assertFalse(test.siteConfigurationAction.checkCheckBoxDestination("admQuantiyRoundingFlag"));
		
	}

	@Test(priority = 3, description = "VPLX: Manage Destinations-General: [UI] : When a new Destination is added, it gets populated in Destinations dropdown on Add Pick Screen"
			+ "VPLX:Manage Destinations-General:[UI]:When a new Destination is added, it gets populated in Destinations dropdown on Add Pick Screen")
	public void Test02_Test03_1111456_1130391() {

		
		test.siteConfigurationAction.selectFacilityForDestinationDropDown("facilityKey", facilityOnWFAScreen);
		destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());

		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();

		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.clickButton("primary");

		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(destinationName);
// to save the flag value which required for 5th test case
		test.landingPageActions.navigateToFeature("Facilities");
		 test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(),
				"search");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.checkCheckBoxDestination("requestRestockDestinationFlag");
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions.verifyAndClickAddPick();

	
		test.transactionQueueActions.searchItemValue("itemID");
		itemName = test.transactionQueueActions.getAddedItemNameFromAddRestockForm();
		 
		 itemName1 = itemName.split(" ")[0];
		 test.transactionQueueActions.verifySearchResults(itemName1,
				  "1");
		test.transactionQueueActions.clicksearchedItemValue(itemName1, "1");
		
		
		test.siteConfigurationAction.selectValueFromDropDown("destination", destinationName);

	}
	
	
	
	@Test(priority = 4, description = "VPLX:Manage Destinations-General:[UI]:The Destination name is displayed on active transaction window for an active transaction")
	public void Test04_1130438() {
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("quantity", getData("AddPick.Quantity"));
		transaction_type = test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("priority", 1).replaceAll(" ", "");
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.siteConfigurationAction.enterRandomValueInInputField("firstname", firstname);

		test.transactionQueueActions.verifyButtonAddPick("save_close_btn", "Save & Close");
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTransaction(firstname, destinationName, transaction_type);

		test.transactionQueueActions.clickButtonCorrespondingToPatientName(firstname,"Pick Now");
	
		Assert.assertTrue(test.transactionQueueActions.verifyDestinationInCurrentPickWindow(destinationName),""
				+ "[ASSERTION FAILED]: Destination added is not displayed on Current pick Window");

	}
	
	@Test(priority = 5, description = "VPLX:Manage Destinations-General:[UI]:When a new Destination is added, it gets populated in Add Return screen")
	public void Test05_1130432() {
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
		test.transactionQueueActions.searchItemValue(itemName1);
		
		 test.transactionQueueActions.verifySearchResults(itemName1,
				  "1");
		test.transactionQueueActions.clicksearchedItemValue(itemName1, "1");
		
		
		test.siteConfigurationAction.selectValueFromDropDown("sourceLocation", destinationName);

		
	}
	
	@Test(priority = 6, description = "VPLX:Manage Destinations-General:[UI]:When a new Destination is added, it gets populated in Destinations list on Add/Edit Routing Rule screen")
	public void Test06_1130404() {
		
		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.clickButton("primary");
		test.siteConfigurationAction.pageRefresh();
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.supportDataActions.verifyLabelIsPresent("Routing Rules");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", facilityOnWFAScreen);
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.supportDataActions.verifyLabelIsPresent("Add Routing Rule");
		
		test.siteConfigurationAction.verifyScheduleExist(destinationName);
	
	}
	
	@Test(priority = 7, description = "VPLX: Manage Destinations-General: [UI]: When a new Destination code is updated, it gets updated in Destinations list on Add/Edit Routing Rule")
	public void Test07_1111463() {
		test.siteConfigurationAction.verifyScheduleExist(destinationCode);
		
	}
	
	@Test(priority = 8, description = "VPLX: Manage Destinations-General: [UI] : When a new Destination is added, it gets populated in Destinations list on View Rules on Item Locationscreen")
	
	public void Test08_1111457() {
		
	
		routingRule = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule" + System.currentTimeMillis());
		
		
		test.siteConfigurationAction.clickToggleOfAddedRecord(destinationName);
		//test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		test.supportDataActions.clickButtonWithOutAnyWait("SaveText");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(routingRule, "search");
				
		test.siteConfigurationAction.verifyScheduleExist(destinationName);
		
	}
	
	

	@Test(priority = 10, description = "VPLX: Manage Destinations-General: [UI] : When a Destination name is updated, it gets updated in Destinations dropdown on Add Pick Screen"
		+ "VPLX:Manage Destinations-General:[UI]:When a new Destination is Updated, it gets populated in Destinations dropdown on Add Pick Screen")
	public void Test09_Test10_1111459_1130395() {
		
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");

		test.siteConfigurationAction.clickOnEditLinkCorresspondingToDestinationName(destinationName);
		
		destinationNameUpdated=test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "UpdatedDestination" + System.currentTimeMillis());
		
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions.verifyAndClickAddPick();

		test.transactionQueueActions.searchItemValue("ItemID");
		
		test.transactionQueueActions.verifySearchedResult("Item Name", "ItemID");
		test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName").trim());
		test.siteConfigurationAction.selectValueFromDropDown("destination", destinationNameUpdated);

		

	}
	
	@Test(priority = 12, description = "VPLX:Manage Destinations-General:[UI]:When a Destination is Updated, it gets populated in Destinations list on View Routing Rule screen"
		+ ""
			+ "VPLX: Manage Destinations-General: [UI]: When a Destination name is updated, it gets updated in Destination details on View Routing Rule screen")
	public void Test11_Test12_1130423_1111460() {
		
		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.clickButton("primary");
		test.siteConfigurationAction.pageRefresh();
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.supportDataActions.verifyLabelIsPresent("Routing Rules");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", facilityOnWFAScreen);
		test.supportDataActions.enterSearchTermInSearchFieldGl(routingRule, "search");
		//Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(dosageCode, "1"));
		//test.supportDataActions.clearSearchBoxField("search");
		
		test.siteConfigurationAction.verifyScheduleExist(destinationNameUpdated);
		
	}
		
	
	@Test(priority = 14, description = "VPLX:Manage Destinations-General:[UI]:When a Destination is Updated, it gets populated in Destinations list on Add/Edit Routing Rule screen"
			+ "VPLX: Manage Destinations-General: [UI]: When a new Destination code is updated, it gets updated in Destinations list on Add/Edit Routing Rule")
	public void Test13_Test14_1130404_1111462() {
		
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.supportDataActions.verifyLabelIsPresent("Add Routing Rule");
		
		test.siteConfigurationAction.verifyScheduleExist(destinationNameUpdated);
		
		
	
	}
	
	@Test(priority = 15, description = "VPLX:Manage Destinations-General:[UI]:When a Destination status is changed to inactive, it is not displayed in Add/Edit/view Routuing Rule screen"
			)
	public void Test15_1130440() throws Throwable {
	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.clickEditLinkCorrespondingToAddedRecord(destinationNameUpdated);
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"),
				"[ASSERTION FAILED]: Toggle is inactive in General Tab on Edit destination screen");
		test.siteConfigurationAction.clickActiveToggle("Active");
		Assert.assertFalse(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"),
				"[ASSERTION FAILED]: Toggle is active in General Tab on Edit destination screen");
		test.supportDataActions.clickButton("save");
		
		/*test.closeBrowserSession();
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(), TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");*/
		
		//test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.supportDataActions.verifyLabelIsPresent("Routing Rules");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", facilityOnWFAScreen);
		test.supportDataActions.enterSearchTermInSearchFieldGl(routingRule, "search");
		//Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(dosageCode, "1"));
		//test.supportDataActions.clearSearchBoxField("search");
		/*Assert.assertTrue(test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("externalSystems",
				externalSystemUpdated), "[ASSERTION FAILED]: Value exist in dropdown");*/

		test.siteConfigurationAction.verifyDistributorIsNotAddedForParticularDestination(destinationNameUpdated);
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.supportDataActions.verifyLabelIsPresent("Add Routing Rule");
		
		test.siteConfigurationAction.verifyDistributorIsNotAddedForParticularDestination(destinationNameUpdated);
		
	}
	
	@Test(priority = 17, description = "VPLX: Manage Destinations-General: [UI] : When a Destination status is changed to inactive, it is not displayed in Destinations dropdown on Add Pick Screen"
			)
	public void Test16_Test17_1111466_1130442() throws Throwable {
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		// test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"),
		// 0);
		// test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		// test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA6"),
		// 5);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions.verifyAndClickAddPick();

		//test.transactionQueueActions.searchItemValue("ItemID");
		// test.transactionQueueActions.searchItemValue(itemID);
		// test.transactionQueueActions.verifySearchedResult("Item Name",
		// itemID);
		
		//test.transactionQueueActions.verifyActionItemsAndClick("Add Pick");
		test.transactionQueueActions.searchItemValue("itemID");
		//itemName = test.transactionQueueActions.getAddedItemNameFromAddRestockForm();
		 
		// itemName1 = itemName.split(" ")[0];
		 test.transactionQueueActions.verifySearchResults(itemName1,
				  "1");
		test.transactionQueueActions.clicksearchedItemValue(itemName1, "1");
		
		/*test.transactionQueueActions.verifySearchedResult("Item Name", "ItemID");
		test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName").trim());*/
		// = test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("destination", 1);
		//test.siteConfigurationAction.selectValueFromDropDown("destination", destinationName);
		Assert.assertTrue(test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("destination",
				destinationNameUpdated), "[ASSERTION FAILED]: Value exist in dropdown");
	}
	

}

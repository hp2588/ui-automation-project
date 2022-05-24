package com.org.tests.astarratedbugs;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1121528 extends BaseTest  {
	
	String facilityOnWFAScreen,ISAName=TestDataPropertyReaderAndWriter.getProperty("ISAName").trim(),itemID=TestDataPropertyReaderAndWriter.getProperty("ItemdID").trim();
	
	@BeforeClass
	public void Open_Browser_Window() {
	test = new TestSessionInitiator(this.getClass().getSimpleName());
	String app_url = getYamlValue("app_url");
	test.launchApplication(app_url);
	test.loginPageAction.LoginToTheBDApplication(getData("Auth.userName7").trim(),
	getData("Auth.password7").trim(), getData("Auth.ip").trim());
	test.loginPageAction.selectValueFromDropDown("Tenant", getData("IDM.tenantName"));
	test.loginPageAction.clickNextButton();
	test.landingPageActions.navigateToMenu("Main Menu");
	Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
	"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
	}
	
	@Test(priority = 1, description = "VPLX : System does not allow to delete Cycle Count Transactions without permissions.")
	public void Test01_1060086(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : System does not allow to delete Cycle Count Transactions without permissions.");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		Assert.assertNotNull(
				test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		facilityOnWFAScreen=test.siteConfigurationAction.getFacilityFromISAScreen();
		
		//ISAName=test.storageAreaAction.getISANameOnWFAScreen();

		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();


		test.landingPageActions.navigateToFeature("Facilities");
		test.supportDataActions.verifyLabelIsPresent("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToAddedRecord("Edit Facility", facilityOnWFAScreen);
		test.siteConfigurationAction.clickTab("Cycle Counts");
		//test.siteConfigurationAction.verifyOtherfieldsOptionsareVisible();
		test.siteConfigurationAction.selectCheckboxItemsTab("enableOldestExpirationDateFlag", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("autoGenCycleCountFlag", true);	 
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifySuccessMessageOnViewPageWithLoader("Success! Changes have been saved.", 90, 1);
		
		/*Enter cycle count interval for facility mapped item */
		test.landingPageActions.navigateToMenu("Item Management");
		test.siteConfigurationAction.selectFacilityDropDownOnItemManagement(
				"Fac1588002935976");
		test.siteConfigurationAction.clickEditLinkOnItemManagement(itemID);
		test.siteConfigurationAction
				.clickOnItemManagementFacility(facilityOnWFAScreen);
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("cycleCountIntervalDayAmount", "1");
		test.siteConfigurationAction.clickButton("save");
		
		test.landingPageActions.navigateToFeature("Item Locations");	
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", facilityOnWFAScreen);
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID,"search");

		//test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",facilityOnWFAScreen );
		test.siteConfigurationAction.selectValueForDropDown("isa", ISAName);
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("cycleCountInterval", "2");
		test.siteConfigurationAction.clickSaveButton();
		
		
		/*===================Navigate to TQ to verify Cycle Count transaction is created==============*/
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		Thread.sleep(300000);
		
		test.transactionQueueActions.holdManualPickTransactionBasedOnPriortiyAndItemName("CYCLECOUNT", "test");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("On Hold");
		
		test.transactionQueueActions.deleteholdedManualPickTransactionBasedOnPriortiyAndItemName("CYCLECOUNT", "test");

	}
}

package com.org.tests.locationmanagement;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_992149 extends BaseTest {
	String itemID;
	String ISAName, facility, facilityOnISA, itemName,name, shortName, defaultComputer, defaultPrinter, type, deviceNumber,
	ipAddress, portNumber, carouselConnectionResetTime;
	ArrayList<String> previous_data, sorted_data;
	
	@Test(priority = 1, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up"
			+ ""
			+ "VPLX:Location-Storage Area: UI: User performs contains search on the basis of Item, Brand Name , Facility Name,Item Id.")
	public void Test01_1060086_AND_1057896(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up"
				+ ""
				+ "VPLX:Location-Storage Area: UI: User performs contains search on the basis of Item, Brand Name , Facility Name,Item Id.");
	
		/*test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim() = test.siteConfigurationAction.getFacilityFromISAScreen();
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.landingPageActions.navigateToFeature("Main Menu"); 
		test.landingPageActions.navigateToFeature("Facilities");*/
		
		//String External = test.siteConfigurationAction.getExternalSystemMappedToFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		
		
		
		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		//test.siteConfigurationAction.enterRandomValueInRichInputField(getData("ExternalSystem.Name7"));
	    //test.siteConfigurationAction.enterRandomValueInRichInputField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
	    test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		itemName=test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacilityx" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem77x" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
	//	test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickButton("cancel");
		
		test.landingPageActions.navigateToFeature("Main Menu"); 

		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");
		//facility=test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", getData("AddISA.FacilityName"));
		facility=test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim() );
		ISAName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
		"Name" + System.currentTimeMillis());
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
		"shortName" + System.currentTimeMillis());
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("workstationComputerKey",1);
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("logisticsLabelPrinterKey",1);

		test.siteConfigurationAction.clickTab("Carousel Settings");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("carouselKey", 1);

		deviceNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("devicenumber",getData("AddISA.Device")
				);
		
		ipAddress = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddressValue",
				getData("AddISA.IPAddress"));

		portNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("portNumber", getData("AddISA.Port"));		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("connectionResetMinutes", "5");
		test.siteConfigurationAction.clickTab("ISA Configuration");
		//test.storageAreaAction.enterDataInInputField("maxBinNumber","1");
		test.storageAreaAction.enterDataInInputField("maxRackNumber", "2");
		test.storageAreaAction.enterDataInInputField("maxShelvesNumber", "2");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersHorizontalNumber", "2");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersVerticalNumber", "2");
		

	    test.siteConfigurationAction.clickTab("Display Settings");
		test.siteConfigurationAction.clickTab("Approved Computers");
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ISALocation", ISAName);
	
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
	//   test.siteConfiguration	Action.selectValueFromDropDown("FacilityDropdown",getData("AddISA.FacilityName"));
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		
	//test.supportDataActions.enterSearchTermInSearchFieldGl("SystemlevelItem77x1592461990622" ,"search");
    //test.supportDataActions.clickOnEditLinkCorresspondingToItemonItemLocations("SystemlevelItem77x1592461990622");
		
		
  test.supportDataActions.enterSearchTermInSearchFieldGl(itemID,"search");
 test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName);
		
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		
		}
	
	
	@Test(priority = 2, description = "VPLX:Location-Storage Area: UI:Verify item id and Facility displayed on the top")
	public void Test02_1059552(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify item id and Facility displayed on the top");
		Assert.assertTrue(test.siteConfigurationAction.verifyItemDetailsEditLocation(itemID));
		Assert.assertTrue(test.siteConfigurationAction.verifyItemDetailsEditLocation("My Facilities"));
		
		}
	

	@Test(priority = 3, description = "VPLX:Location-Storage Area: UI:Verify for an item in the listing page  having no location by clicking on edit link user land on the page where  'Assign Location button' present")
	public void Test03_1059550(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify item id and External  displayed on the top");
		Assert.assertFalse(test.siteConfigurationAction.verifyAssignedLocation());
		}
	
	
	
	@Test(priority = 4, description = "VPLX:Location-Storage Area: UI:Verify Assign location popup is displayed after clicking on Assign button")
	public void Test04_1059555(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify Assign location popup is displayed after clicking on Assign button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		 
		test.siteConfigurationAction.verifyAssignLocationPopup(getData("EditItemLocation.Header"));
		
		}
	
	@Test(priority = 5, description = "VPLX:Location-Storage Area: UI:Verify facility and ISA displayed as drop down in assign location pop-up")
	public void Test05_1059560(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify facility and ISA displayed as drop down in assign location pop-up");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewFacility("facility");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewFacility("ISA");
		
		}
	

	@Test(priority = 6, description = "VPLX:Location-Storage Area: UI:Verify cancel and save button displayed on Assign location pop-up")
	public void Test06_1059563(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify cancel and save button displayed on Assign location pop-up");
		test.siteConfigurationAction.verifyButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.verifyButtonOnEditLocation("cancel_button_edit_location");
	}
	

	@Test(priority = 7, description = "VPLX:Location-Storage Area: UI:Verify when click on cancel user navigated on the back screen page")
	public void Test07_1060098(Method method) {
		ExtentTestManager.startTest(method.getName(),
			"VPLX:Location-Storage Area: UI:Verify when click on cancel user navigated on the back screen page");
		
		test.siteConfigurationAction.selectValueForDropDown("facility", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
	//	test.siteConfigurationAction.selectValueForDropDown("facility",getData("AddISA.FacilityName"));
		//test.siteConfigurationAction.selectValueForDropDown("ISA", getData("AddISA.Isa1"));
		  test.siteConfigurationAction.selectValueFromDropDown("ISA", TestDataPropertyReaderAndWriter.getProperty("ISALocation").trim());

	   test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
	  // test.siteConfigurationAction.clickButtonOnEditLocation("btn_cancel");
	   test.supportDataActions.clickButton("cancel");
    	//test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
	   Assert.assertTrue(test.supportDataActions.verifyBreadCrumb("Edit Location"),
			   "[ASSERTION FAILED]: Edit Locatio nscreen is not displayed");


	}
	

	@Test(priority = 8, description = "VPLX:Location-Storage Area: UI:Verify user lands on screen with ISA configuration after clicking on Save button on Assign location popup")
	public void Test08_1059574(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify user lands on screen with ISA configuration after clicking on Save button on Assign location popup");
	  // test.siteConfigurationAction.clickButtonOnEditLocation1("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		
		test.siteConfigurationAction.selectValueForDropDown("facility", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		
		//test.siteConfigurationAction.selectValueForDropDown("facility",getData("AddISA.FacilityName"));
	//	test.siteConfigurationAction.selectValueForDropDown("ISA", getData("AddISA.Isa1"));
		test.siteConfigurationAction.selectValueFromDropDown("ISA", TestDataPropertyReaderAndWriter.getProperty("ISALocation").trim()); 
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("isa", 1);

	   test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		}  

	@Test(priority = 9, description = "VPLX:Location-Storage Area: UI:Verify when click on save ,location of the item saved by default as mentioned in ISA")
	public void Test09_1060153(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify when click on save ,location of the item saved by default as mentioned in ISA");
		//test.siteConfigurationAction.clickSlotAssignLocation();
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "1");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "1000");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "1000");
		test.siteConfigurationAction.clickSaveButton();
		}
	
	
	@Test(priority = 10, description = "VPLX:Location-Storage Area: UI:Verify unassigned button is present for unassigned the location")
	public void Test10_1060188(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify unassigned button is present for unassigned the location");

		test.siteConfigurationAction.verifyUnassignButton();
		}
	
	@Test(priority = 11, description = "VPLX:Location-Storage Area: UI:Verify System displays confirmation message when deleting a location")
	public void Test11_1060288_1060296(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify System displays confirmation message when deleting a location");
		test.siteConfigurationAction.verifyUnassignButton();
		test.siteConfigurationAction.clickButtonOnEditLocation("unassign_btn");
		test.siteConfigurationAction.confirmPopupOnUnassign(getData("EditItemLocation.UnassignHeader"));

		}
	
	@Test(priority = 12, description = "VPLX:Location-Storage Area: UI:Verify when click on save ,location of the item saved by default as mentioned in ISA")
	public void Test12_1060153(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify when click on save ,location of the item saved by default as mentioned in ISA");
		test.siteConfigurationAction.verifyAddedLocation();
	} 
	
}

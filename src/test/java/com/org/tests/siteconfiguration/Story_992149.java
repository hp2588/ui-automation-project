package com.org.tests.siteconfiguration;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_992149 extends BaseTest {
	String itemID;
	String ISAName, facility, facilityOnISA, name, shortName, defaultComputer, defaultPrinter, type, deviceNumber,
	ipAddress, portNumber, carouselConnectionResetTime;
	ArrayList<String> previous_data, sorted_data;
	
	@Test(priority = 1, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test01_1060086(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.verifyPageHeader("fs-24", "Item Management");
		test.siteConfigurationAction.enterRandomValueInRichInputFieldForExternalSystem(getData("ExternalSystem.Name5"));
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName","Systemlevelfacilityx"+System.currentTimeMillis());
		itemID= test.siteConfigurationAction.enterDataInInputField("itemId","SystemlevelItem77x"+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacilityx" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem77x" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.selectCheckboxForFacilityItem();
		test.siteConfigurationAction.clickButton("save");
		
		
		test.landingPageActions.navigateToFeature("Main Menu"); 

		
		test.landingPageActions.navigateToFeature("Inventory Storage Areas (ISAs)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", getData("AddISA.FacilityName"));
		ISAName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
		"Name" + System.currentTimeMillis());
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
		"shortName" + System.currentTimeMillis());
		 test.siteConfigurationAction.selectValueFromDropDownByIndex("workstationComputerKey",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("logisticsLabelPrinterKey",1);

		test.siteConfigurationAction.clickTab("Carousel Settings");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("carouselKey", 1);

		deviceNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("devicenumber",getData("AddISA.Device")
				);
		
		ipAddress = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddressValue",
				getData("AddISA.IPAddress"));

		portNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("portNumber", getData("AddISA.Port"));		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("connectionResetMinutes", "5");
		test.siteConfigurationAction.clickTab("ISA Configuration");
		test.storageAreaAction.enterDataInInputField("maxBinNumber","1");

	    test.siteConfigurationAction.clickTab("Display Settings");
		test.siteConfigurationAction.clickTab("Approved Computers");
		test.siteConfigurationAction.clickSaveButton();
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectFacilityDropdown("SunFacility");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID,"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		
		}
	
	
	@Test(priority = 2, description = "VPLX:Location-Storage Area: UI:Verify item id and External  displayed on the top")
	public void Test02_1059552(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify item id and External  displayed on the top");
		test.siteConfigurationAction.verifyItemDetailsEditLocation(itemID);
		test.siteConfigurationAction.verifyItemDetailsEditLocation("My Facilities");
		
		}
	
	@Test(priority = 3, description = "VPLX:Location-Storage Area: UI:Verify for an item in the listing page  having no location by clicking on edit link user land on the page where  'Assign Location button' present")
	public void Test02_1059550(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify item id and External  displayed on the top");
		Assert.assertFalse(test.siteConfigurationAction.verifyAssignedLocation());
		
		
		}
	
	
	
	@Test(priority = 4, description = "VPLX:Location-Storage Area: UI:Verify Assign location popup is displayed after clicking on Assign button")
	public void Test03_1059555(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify Assign location popup is displayed after clicking on Assign button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.verifyAssignLocationPopup(getData("EditItemLocation.Header"));
		
		}
	
	@Test(priority = 5, description = "VPLX:Location-Storage Area: UI:Verify facility and ISA displayed as drop down in assign location pop-up")
	public void Test04_1059560(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify facility and ISA displayed as drop down in assign location pop-up");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewFacility("facility");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewFacility("isa");
		
		}
	

	@Test(priority = 6, description = "VPLX:Location-Storage Area: UI:Verify cancel and save button displayed on Assign location pop-up")
	public void Test05_1059563(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify cancel and save button displayed on Assign location pop-up");
		test.siteConfigurationAction.verifyButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.verifyButtonOnEditLocation("cancel_button_edit_location");
	}
	

	@Test(priority = 7, description = "VPLX:Location-Storage Area: UI:Verify when click on cancel user navigated on the back screen page")
	public void Test06_1060098(Method method) {
		ExtentTestManager.startTest(method.getName(),
			"VPLX:Location-Storage Area: UI:Verify when click on cancel user navigated on the back screen page");
		
		test.siteConfigurationAction.selectValueForDropDown("facility", getData("AddISA.FacilityName"));
	    test.siteConfigurationAction.selectValueFromDropDownByIndex("isa", 1);

	   test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
	   test.siteConfigurationAction.clickButtonOnEditLocation("btn_cancel");
    	test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");


	}
	
	

	@Test(priority = 8, description = "VPLX:Location-Storage Area: UI:Verify user lands on screen with ISA configuration after clicking on Save button on Assign location popup")
	public void Test07_1059574(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify user lands on screen with ISA configuration after clicking on Save button on Assign location popup");
	    test.siteConfigurationAction.clickButtonOnEditLocation1("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility", getData("AddISA.FacilityName"));
	    test.siteConfigurationAction.selectValueFromDropDownByIndex("isa", 1);
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		}  

	@Test(priority = 9, description = "VPLX:Location-Storage Area: UI:Verify when click on save ,location of the item saved by default as mentioned in ISA")
	public void Test08_1060153(Method method) {
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
	public void Test09_1060188(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify unassigned button is present for unassigned the location");

		test.siteConfigurationAction.verifyUnassignButton();
		}
	
	@Test(priority = 11, description = "VPLX:Location-Storage Area: UI:Verify System displays confirmation message when deleting a location")
	public void Test10_1060288_1060296(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify System displays confirmation message when deleting a location");
		test.siteConfigurationAction.verifyUnassignButton();
		test.siteConfigurationAction.clickButtonOnEditLocation("unassign_btn");
		test.siteConfigurationAction.confirmPopupOnUnassign(getData("EditItemLocation.UnassignHeader"));

		}
	
	@Test(priority = 12, description = "VPLX:Location-Storage Area: UI:Verify when click on save ,location of the item saved by default as mentioned in ISA")
	public void Test04_1060153(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify when click on save ,location of the item saved by default as mentioned in ISA");
		test.siteConfigurationAction.verifyAddedLocation();
	} 
}

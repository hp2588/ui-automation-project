package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class ItemLocation_Feature extends BaseTest {

	String itemID;
	String ISAName, facility, facilityOnISA, name, shortName, defaultComputer, defaultPrinter, type, deviceNumber,
	ipAddress, portNumber, carouselConnectionResetTime;
	ArrayList<String> previous_data, sorted_data;
	
	@Test(priority = 1, description = "VPLX:Location-Storage Area: [UI]: User is able to view all the Active items in the list which are created in the item setup.")
	public void Test01_1129488_AND_1071043_AND_1071044(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: [UI]: User is able to view all the Active items in the list which are created in the item setup.");
		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.verifyPageHeader("fs-24", "Item Management");
		test.siteConfigurationAction.enterRandomValueInRichInputFieldForExternalSystem(getData("ExternalSystem.Name7"));
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacilityx" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem77x" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
		//test.siteConfigurationAction.selectCheckboxForFacilityItem();
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickButton("cancel");
		//test.siteConfigurationAction.clickActionbutton("Cancel");
		
		
		test.landingPageActions.navigateToFeature("Main Menu"); 

		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");
		facility=test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", getData("AddISA.FacilityName"));
		System.out.println(facility);
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
		test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.siteConfigurationAction.verifyButtonOnEditLocation("filter_location_btn");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",getData("AddISA.FacilityName"));
		
		//test.supportDataActions.enterSearchTermInSearchFieldGl("SystemlevelItem77x1588006517124","search");
		//test.supportDataActions.clickOnEditLinkCorresspondingToItem("SystemlevelItem77x1588006517124", "1587451");
		
		
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID,"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		Assert.assertFalse(test.siteConfigurationAction.verifyAssignedLocation());
		test.siteConfigurationAction.clickActionbutton("Cancel");
		
		/*
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.verifyAssignLocationPopup(getData("EditItemLocation.Header"));
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewFacility("facility");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewFacility("isa");
		test.siteConfigurationAction.verifyButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.verifyButtonOnEditLocation("cancel_button_edit_location");

		test.siteConfigurationAction.selectValueForDropDown("facility", getData("AddISA.FacilityName"));
	    test.siteConfigurationAction.selectValueFromDropDownByIndex("isa", 1);

	   test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
	   test.siteConfigurationAction.clickButtonOnEditLocation("btn_cancel");
    	test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
    	
	    test.siteConfigurationAction.clickButtonOnEditLocation1("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility", getData("AddISA.FacilityName"));
	    test.siteConfigurationAction.selectValueFromDropDownByIndex("isa", 1);
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "1");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "1000");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "1000");
		test.siteConfigurationAction.clickSaveButton();
		
		*/
		
		
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
	
	@Test(priority = 5, description = "VPLX:Location-Storage Area: UI:Verify cancel and save button displayed on Assign location pop-up")
	public void Test05_1059563(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify cancel and save button displayed on Assign location pop-up");
		test.siteConfigurationAction.verifyButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.verifyButtonOnEditLocation("cancel_button_edit_location");
	}
	
	
	@Test(priority = 6, description = "VPLX:Location-Storage Area: UI:Verify when click on cancel user navigated on the back screen page")
	public void Test06_1060098(Method method) {
		ExtentTestManager.startTest(method.getName(),
			"VPLX:Location-Storage Area: UI:Verify when click on cancel user navigated on the back screen page");
		
		test.siteConfigurationAction.selectValueForDropDown("facility", getData("AddISA.FacilityName"));
	    test.siteConfigurationAction.selectValueFromDropDownByIndex("isa", 1);

	   test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
	   test.siteConfigurationAction.clickButtonOnEditLocation("btn_cancel");
    	test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");


	}
	

	@Test(priority = 7, description = "VPLX:Location-Storage Area: UI:Verify user lands on screen with ISA configuration after clicking on Save button on Assign location popup")
	public void Test07_1059574(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify user lands on screen with ISA configuration after clicking on Save button on Assign location popup");
	    test.siteConfigurationAction.clickButtonOnEditLocation1("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility", getData("AddISA.FacilityName"));
	    test.siteConfigurationAction.selectValueFromDropDownByIndex("isa", 1);
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		}  
	@Test(priority = 8, description = "VPLX:Location-Storage Area: UI:Verify  user should search on the basis of Description, Brand Name , Facility Name,Item Id")
	public void Test08_1057896(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify  user should search on the basis of Description, Brand Name , Facility Name,Item Id");
		test.supportDataActions.enterSearchTermInSearchFieldGl("SystemlevelItem77x1587457545466","search");
		test.siteConfigurationAction.verifySearchResults("SystemlevelItem77x1587457545466", "1");
		test.supportDataActions.clearSearchBox("search");
		
	}
	
	@Test(priority = 9, description = "VPLX:Location-Storage Area: UI:Verify my facility dropdown list and for search text box is present with filter functionality")
	public void Test09_1057894(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify my facility dropdown list and for search text box is present with filter functionality");
		
		test.siteConfigurationAction.verifyButtonOnEditLocation("facility_dropdown_edit_location");
		test.siteConfigurationAction.verifyButtonOnEditLocation("search_location");
		test.siteConfigurationAction.verifyButtonOnEditLocation("filter_location_btn");
	}
	@Test(priority = 10, description = "VPLX:Location-Storage Area: UI:Verify enabled filter icon displayed on location management page")
	public void Test10_1059503(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify enabled filter icon displayed on location management page");
		test.siteConfigurationAction.verifyButtonOnEditLocation("filter_location_btn");

	}
	@Test(priority = 11, description = "VPLX:Location-Storage Area: UI:Verify filter items by pop up should be displayed on clicking  on filter icon")
	public void Test11_1059505(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify filter items by pop up should be displayed on clicking  on filter icon");
		test.siteConfigurationAction.clickButtonOnEditLocation("filter_location_btn");
		test.siteConfigurationAction.verifyFilterItemsPopup();

	}
	@Test(priority = 12, description = "VPLX:Location-Storage Area: UI:Verify User  able to filter based on the following fields: Attribute as active True or False,Dosage form,Dispense unit,medication class")
	public void Test12_1059517(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify User  able to filter based on the following fields: Attribute as active True or False,Dosage form,Dispense unit,medication class");
		test.siteConfigurationAction.verifyDefaultValueinFiltersPopuponItemScreen("field",
				getData("FilterItemsLocation.attribute"));
		test.siteConfigurationAction.verifyDefaultValueinFiltersPopuponItemScreen("type",
				getData("FilterItemsLocation.operator"));
		test.siteConfigurationAction.verifyDefaultValueinFiltersPopuponItemScreen("filter",
				getData("FilterItemsLocation.value"));
		test.siteConfigurationAction.selectValueFromDropDownForFilterItemFields("field", "1",
				getData("FilterItemsLocation.attribute1"));
		test.siteConfigurationAction.selectValueFromDropDownForFilterItemFields("type", "1",
				getData("FilterItemsLocation.operator2"));
		test.siteConfigurationAction.selectValueFromDropDownForFilterItemFields("filter", "1",
				getData("FilterItemsLocation.value3"));
		test.siteConfigurationAction.clickButton("add");

		test.siteConfigurationAction.selectValueFromDropDownForMultipleFilterItemFields("field",
				getData("FilterItemsLocation.attribute1"));
		test.siteConfigurationAction.selectValueFromDropDownForMultipleFilterItemFields("type",
				getData("FilterItemsLocation.operator2"));
		test.siteConfigurationAction.selectValueFromDropDownForMultipleFilterItemFields("filter",
				getData("FilterItemsLocation.value3"));
		test.siteConfigurationAction.clickButton("cancel");

	}
	
	
	
	@Test(priority = 13, description = "VPLX:Location Assignment - Managing Storage Area-Edit Location: UI: System displays all the fields for each PLX location")
	public void Test13_1071045(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area-Edit Location: UI: System displays all the fields for each PLX location");
		
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown","AAAFac");
		test.supportDataActions.enterSearchTermInSearchFieldGl("121","search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem("121");
		test.siteConfigurationAction.verifyFieldsOnEditLocation("Location");
		test.siteConfigurationAction.verifyFieldsOnEditLocation("Type");
		test.siteConfigurationAction.verifyFieldsOnEditLocation("Rule");
		test.siteConfigurationAction.verifyFieldsOnEditLocation("Replenish");
		test.siteConfigurationAction.verifyFieldsOnEditLocation("Min");

		test.siteConfigurationAction.verifyFieldsOnEditLocation("On Hand");

		}
	
	@Test(priority = 14, description = "VPLX:Location Assignment - Managing Storage Area-Edit Location: UI:Quantity On Hand, Cycle Count, Min and Max values default to the values that were previously entered during the assignation on edit location screen")
	public void Test14_1071048(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area-Edit Location: UI:Quantity On Hand, Cycle Count, Min and Max values default to the values that were previously entered during the assignation on edit location screen");
		
		test.siteConfigurationAction.verifyValueEditLocation("refillPointQuantity_0", "10");
		test.siteConfigurationAction.verifyValueEditLocation("parQuantity_0", "100");
		test.siteConfigurationAction.verifyValueEditLocation("inventoryQuantity_0", "9900");
	

		}
	
	
	@Test(priority = 15, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: Validation message is displayed if ISA Setting for racks is less than Racks in Map on adding Racks")
	public void Test015_1076663(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: Validation message is displayed if ISA Setting for racks is less than Racks in Map on adding Racks");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown","AAAFac");		
		test.supportDataActions.clickOnEditLinkCorresspondingToItem("345");
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility", "AAAFac");
		test.siteConfigurationAction.selectValueForDropDown("isa", "OldISA1");
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.verifyUserIsOnLayoutPage();
		test.siteConfigurationAction.verifyRackDropdownOnLocationPage();
		test.siteConfigurationAction.selectValueFromDropDown("selectedRack", "Rack 1");
		
		test.siteConfigurationAction.clickDotsOnItemLocation("isa_actions");
		test.siteConfigurationAction.clickAddRacksOrShelfOrBin("Add Rack");
		test.siteConfigurationAction.verifyErrorMessageForRacks("This Rack cannot be added. Maximum limit exceeded for adding Rack.");
		}
	
	
	
	
	@Test(priority = 16, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User is allowed to add Racks up to the Max Rack setting in the ISA")
	
	public void Test16_1076664(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User is allowed to add Racks up to the Max Rack setting in the ISA");
		test.siteConfigurationAction.clickAddRacksOrShelfOrBin("Add Rack");

		test.siteConfigurationAction.verifySuccessMessageOnAddRack();
		}
	
	
	
	@Test(priority = 2, description = "VPLX:Location Assignment - Managing Storage Area: [UI]:  User is allowed to remove a Rack if there are no items assigned to the Rack."
			)
				public void Test02_1077207(Method method) {
					ExtentTestManager.startTest(method.getName(),
							"VPLX:Location Assignment - Managing Storage Area: [UI]:  User is allowed to remove a Rack if there are no items assigned to the Rack");	
					test.landingPageActions.navigateToFeature("Item Locations");
					test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown","AAAFac");		
					test.supportDataActions.clickOnEditLinkCorresspondingToItem("345");
					test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
					test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
					test.siteConfigurationAction.selectValueForDropDown("facility", "AAAFac");
					test.siteConfigurationAction.selectValueForDropDown("isa", "OldISA1");
					test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
					test.siteConfigurationAction.clickAssignLocationButton();
					test.siteConfigurationAction.verifyUserIsOnLayoutPage();
					test.siteConfigurationAction.selectValueFromDropDown("selectedRack", "Rack 2");
					test.siteConfigurationAction.clickDotsOnItemLocation("rack_actions");
					test.siteConfigurationAction.verifyActionsOnDots("Remove rack");
					
					test.siteConfigurationAction.clickAddRacksOrShelfOrBin("Remove rack");
				
					}
	@Test(priority = 3, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User is allowed to remove a Shelf if there are no items assigned to the Rack."
			)
				public void Test03_1077208(Method method) {
					ExtentTestManager.startTest(method.getName(),
							"VPLX:Location Assignment - Managing Storage Area: [UI]: User is allowed to remove a Shelf if there are no items assigned to the Rack.");	
					//test.siteConfigurationAction.clickShelf();
					test.siteConfigurationAction.clickAddRacksOrShelfOrBin("Remove Shelf");
				
					}
	@Test(priority = 17, description = "VPLX:Location Assignment - Managing Storage Area(Bin Actions): [UI] - User is allowed to remove vertical dividers from the selected bin if there are no items assigned to slots within the bin.")
	public void Test17_1108664(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area(Bin Actions): [UI] - User is allowed to remove vertical dividers from the selected bin if there are no items assigned to slots within the bin.");
		test.landingPageActions.navigateToItemManagementFeature("Item Locations");
		
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",getData("ExternalSystem.Name8"));
		
		test.supportDataActions.enterSearchTermInSearchFieldGl(getData("ExternalSystem.addedItem2"),"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(getData("ExternalSystem.addedItem2"));
		test.siteConfigurationAction.clickButton("edit");
		test.siteConfigurationAction.clickBinDotsOnItemLocation("bin_actions","4");
		test.siteConfigurationAction.clickverifygroupwithBin("Remove Vertical Divider");
	     	
		
		
	}

@Test(priority = 18, description = "VPLX:Location Assignment - Managing Storage Area(Bin Actions): [UI] -  User is allowed to remove horizontal dividers from the selected bin if there are no items assigned to slots within the bin.")
public void Test18_1108666(Method method) {
ExtentTestManager.startTest(method.getName(),
"VPLX:Location Assignment - Managing Storage Area(Bin Actions): [UI] -  User is allowed to remove horizontal dividers from the selected bin if there are no items assigned to slots within the bin.");

test.siteConfigurationAction.clickBinDotsOnItemLocation("bin_actions","4");
test.siteConfigurationAction.clickverifygroupwithBin("Remove Horizontal Divider");



}
		@Test(priority = 19, description = "VPLX:Location-Assignment(Bin Actions): [UI] - User is able to view the option group with left bin when clicking on actions button on bin.")
	public void Test19_1124239(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: [UI]:  User lands on map screen and the first empty slot should be selected which is found by the system.");
		test.siteConfigurationAction.clickBinDotsOnItemLocation("bin_actions","3");
		test.siteConfigurationAction.clickverifygroupwithBin("Remove Vertical Divider");
		test.siteConfigurationAction.clickBinDotsOnItemLocation("bin_actions","3");
		test.siteConfigurationAction.clickverifygroupwithBin("Remove Horizontal Divider");
		
		test.siteConfigurationAction.clickBinDotsOnItemLocation("bin_actions","4");
	    test.siteConfigurationAction.verifygroupwithBin("Group with Left bin");
	
	
	}
	
	@Test(priority = 20, description = "VPLX:Location-Assignment(Bin Actions): [UI] - User is able to view the option group with right bin when clicking on actions button on bin.")
	public void Test21_1124241(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Assignment(Bin Actions): [UI] - User is able to view the option group with right bin when clicking on actions button on bin.");
		test.siteConfigurationAction.clickBinDotsOnItemLocation("bin_actions","3");
		test.siteConfigurationAction.verifygroupwithBin("Group with Right bin");
		
	}
	
	@Test(priority = 22, description = "VPLX:Location-Assignment(Bin Actions): [UI] - Bins can be grouped if it consist of one slot per bin, bins are adjacent, bins are empty and unassigned.")
	public void Test22_1124243(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Assignment(Bin Actions): [UI] - Bins can be grouped if it consist of one slot per bin, bins are adjacent, bins are empty and unassigned.");
	    //test.siteConfigurationAction.clickBinDotsOnItemLocation("bin_actions","2");
		//test.siteConfigurationAction.verifygroupwithBin("Group with Right bin");
		test.siteConfigurationAction.clickVerifySelectedSlot();
		
		
	}
	
	@Test(priority = 23, description = "VPLX:Location-Assignment(Bin Actions): [UI] - Items in a grouped bin location can be assigned to another location.")
	public void Test23_1124244(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Assignment(Bin Actions): [UI] - Items in a grouped bin location can be assigned to another location.");
		test.siteConfigurationAction.clickBinDotsOnItemLocation("bin_actions","4");
		test.siteConfigurationAction.clickverifygroupwithBin("Group with Left bin");
        test.siteConfigurationAction.clickBinDotsOnItemLocation("bin_actions","3");
		test.siteConfigurationAction.clickverifygroupwithBin("Group with Right bin");
		
		test.siteConfigurationAction.clickBinDotsOnItemLocation("bin_actions","2");
		test.siteConfigurationAction.clickverifygroupwithBin("Remove Vertical Divider");
		test.siteConfigurationAction.clickBinDotsOnItemLocation("bin_actions","2");
		test.siteConfigurationAction.clickverifygroupwithBin("Remove Horizontal Divider");
		test.siteConfigurationAction.clickBinDotsOnItemLocation("bin_actions","2");
		test.siteConfigurationAction.clickverifygroupwithBin("Group with Right bin");
	}
	
	@Test(priority = 24, description = "VPLX:Location-Assignment(Bin Actions): [UI] -User is able to view the option for print slot label.")
	public void Test24_1125312(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Assignment(Bin Actions): [UI] -User is able to view the option for print slot label.");
		test.siteConfigurationAction.clickDotsOnItemLocation("slot_actions");
		test.siteConfigurationAction.verifygroupwithBin("Print Slot Label");
	}
	
	@Test(priority = 25, description = "VPLX:Location Assignment - Managing Storage Area(Bin Actions): [UI] -User is able to assign a item to a available slot .")
	public void Test25_1125289(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area(Bin Actions): [UI] -User is able to assign a item to a available slot .");
		test.siteConfigurationAction.clickemptyslot("3");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "1");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "100");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "10");
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyHeader("Confirmation");
		test.siteConfigurationAction.clickCancelButton();
		
		test.siteConfigurationAction.clickBinDotsOnItemLocation("bin_actions","1");
		
		
		test.siteConfigurationAction.clickDotsOnItemLocation("slot_actions");
		test.siteConfigurationAction.verifygroupwithBin("Print Slot Label");
	}
	
	@Test(priority = 17, description = "VPLX:Location-Storage Area: [UI]:  User lands on map screen and the first empty slot should be selected which is found by the system.")
	public void Test17_1129491(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: [UI]:  User lands on map screen and the first empty slot should be selected which is found by the system.");
	
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.verifyPageHeader("fs-24", "Item Management");
		test.siteConfigurationAction.enterRandomValueInRichInputFieldForExternalSystem(getData("ExternalSystem.Name7"));
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacilityx" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem77x" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
		//test.siteConfigurationAction.selectCheckboxForFacilityItem();
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickButton("cancel");
		//test.siteConfigurationAction.clickActionbutton("Cancel");
		
		
		test.landingPageActions.navigateToFeature("Main Menu"); 

		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");
		facility=test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", getData("AddISA.FacilityName"));
		System.out.println(facility);
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
		test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.siteConfigurationAction.verifyButtonOnEditLocation("filter_location_btn");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",getData("AddISA.FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID,"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility", getData("AddISA.FacilityName"));
	    test.siteConfigurationAction.selectValueFromDropDownByIndex("isa", 1);

	   test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
	   
	  // test.siteConfigurationAction.verify
		
}
}
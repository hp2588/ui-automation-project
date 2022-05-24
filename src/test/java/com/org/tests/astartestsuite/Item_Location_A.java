package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Item_Location_A extends BaseTest {

	String itemID, itemID1,itemID2,brandName,barcode,productID2;

	
	@Test(priority = 1, description = "VPLX : Item Management - System should be saving selected Distributor if it is selected.")
	public void Test01_1121491(Method method) {
		
	ExtentTestManager.startTest(method.getName(),
			"VPLX : Item Management - System should be saving selected Distributor if it is selected.");
	test.landingPageActions.navigateToItemManagementFeature("Item Management");
	test.siteConfigurationAction.enterRandomValueInRichInputField(getData("ExternalSystem.Name7"));
	test.siteConfigurationAction.clickActionbutton("Actions");
	test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
	test.siteConfigurationAction.enterDataInInputField("genericName",
			"Systemlevelfacility1" + System.currentTimeMillis());
	itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
			"SystemlevelItem1" + System.currentTimeMillis());
	brandName = test.siteConfigurationAction.enterDataInInputField("brandName", "brand55");
	test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
	test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
	test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
	test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
	test.siteConfigurationAction.clickButton("save");
	test.siteConfigurationAction.verifyAndClickProductID("Product ID");
	test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
	barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
			"01003" + System.currentTimeMillis() + "0171005032328717621abcd123456789");
	//test.siteConfigurationAction.enterFixedValueInInputField("barcodeValue",getData("ItemProductIdDetails.Barcodevalue1"));
	
	productID2 = test.siteConfigurationAction.getParsedProductID();
	System.out.println("productID=" + productID2);
	test.siteConfigurationAction.clickButton("link");
	test.siteConfigurationAction.verifyAddedProductID(productID2);
	
	test.siteConfigurationAction.clickButton("add");
	test.siteConfigurationAction.verifyAddedProductID(productID2);
	test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
	test.siteConfigurationAction.clickDistributorInfo("preferredDistributor", "2");
	test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "2", "" + System.currentTimeMillis());
	
	test.siteConfigurationAction.clickButton("primary");
	
	
//	test.siteConfigurationAction.clickButton("search");
	//test.siteConfigurationAction.clickButton("link");
	
	/*
	test.siteConfigurationAction.enterFixedValueInInputField("packageSize", "12");
	test.siteConfigurationAction.verifyAndClickAddProductID("Add Preferred Distributor");
	test.siteConfigurationAction.clickOnDistributorInfo(getData("ItemProductIdDetails.Distributor1"));
	test.siteConfigurationAction.enterDistributorItemCode(getData("ItemProductIdDetails.Distributor1"),"2233");
	test.siteConfigurationAction.clickButton("primary");
	*/
	test.siteConfigurationAction.verifyAddedPreferdDistributorName(getData("ItemProductIdDetails.Distributor1"));
	test.siteConfigurationAction.clickActionbutton("Cancel");
	}

	
	
   //Bug-1136288	
	@Test(priority = 2, description = "VPLX : System should  not display the inactive items for selected facility on item management")
	public void Test02_1121637(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : System should  not display the inactive items for selected facility on item management");
		
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacilityx" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem77x" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID1, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID1);
		test.siteConfigurationAction.clickfacilityonEditItem("2");
		test.siteConfigurationAction.clickActiveToggle("Active");
		test.siteConfigurationAction.clickButton("save");	
		/*
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID1, "search");
		Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Data Available.");
		*/
		
}

	@Test(priority = 3, description = "VPLX : Cycle Count date for Rack level and shelf level are matched.")
	public void Test03_1121539(Method method) {
		
	ExtentTestManager.startTest(method.getName(),
			"VPLX : Cycle Count date for Rack level and shelf level are matched.");
			
	test.landingPageActions.navigateToFeature("Main Menu");
	test.landingPageActions.navigateToItemManagementFeature("Item Locations");
	
	test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",getData("ExternalSystem.Name8"));
	
	test.supportDataActions.enterSearchTermInSearchFieldGl(getData("ExternalSystem.itemId1"),"search");
	test.supportDataActions.clickOnEditLinkCorresspondingToItem(getData("ExternalSystem.itemId1"));
	
	
	test.siteConfigurationAction.clickButton("edit");
	
	test.siteConfigurationAction.clickLocationRackButton();
	test.siteConfigurationAction.clickLocationRackcyclecountdateButton();
	
	String rackdate=test.siteConfigurationAction.verifyprfilledvalueoflocationdatefield();
	test.siteConfigurationAction.clickLocationshelfButton();
	  test.siteConfigurationAction.clickLocationshelfButton1();
	  test.siteConfigurationAction.clickLocationshelfcyclecountdateButton();
	  
	String shelfdate=test.siteConfigurationAction.verifyprfilledvalueoflocationdatefield();
	
	Assert.assertEquals(rackdate, shelfdate, "Rack & shelf dates are equal");
	
	}

}	
	
	
	/*
	test.landingPageActions.navigateToItemManagementFeature("Item Management");
	test.siteConfigurationAction.verifyPageHeader("fs-24", "Item Management");
	test.siteConfigurationAction.enterRandomValueInRichInputFieldForExternalSystem(getData("ExternalSystem.Name7"));
	test.siteConfigurationAction.clickActionbutton("Actions");
	test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
	test.siteConfigurationAction.enterDataInInputField("genericName",
			"Systemlevelfacilityx" + System.currentTimeMillis());
	itemID2 = test.siteConfigurationAction.enterDataInInputField("itemId",
			"SystemlevelItem77x" + System.currentTimeMillis());
	test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
	test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
	test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
	test.siteConfigurationAction.selectCheckboxForFacilityItem();
	test.siteConfigurationAction.clickButton("save");
	test.siteConfigurationAction.clickButton("cancel");
	
	
	
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
	
	

/*
	
	test.siteConfigurationAction.clickLocationcalenderbutton("d-flex date-button");
	String date=test.siteConfigurationAction.getCurrentMonth_Year();
	
	test.siteConfigurationAction.ClicklocationCurrentDate(getData("ItemLocationDetails.Calenderdate1"),date);
    String a=test.siteConfigurationAction.getCurrentMonth_Year();
   test.siteConfigurationAction.clickLocationshelfButton("selectedShelf");
   test.siteConfigurationAction.clickLocationshelfButton1("shelf_actions");
   test.siteConfigurationAction.clickLocationshelfcyclecountdateButton();
   test.siteConfigurationAction.clickLocationcalenderbutton("d-flex date-button");
  
   String date1=test.siteConfigurationAction.getCurrentMonth_Year();
   test.siteConfigurationAction.ClicklocationCurrentDate(getData("ItemLocationDetails.Calenderdate1"),date);
   String a1=test.siteConfigurationAction.getCurrentMonth_Year();
   Assert.assertEquals(a, a1, "Rack & shelf dates are equal");
   
	
	
}
	
*/

	
	/*
	@Test(priority = 2, description = "VPLX : Next Cycle  Count  Date  when set to one Rack or Shelf  should not appies to all other Racks or Shelves respectively.")
	public void Test02_1121504(Method method) {
		
		
		String itemID;
		String ISAName, facility, facilityOnISA, name, shortName, defaultComputer, defaultPrinter, type, deviceNumber,
		ipAddress, portNumber, carouselConnectionResetTime;
		ArrayList<String> previous_data, sorted_data;
		
	ExtentTestManager.startTest(method.getName(),
			"VPLX : Next Cycle  Count  Date  when set to one Rack or Shelf  should not appies to all other Racks or Shelves respectively.");
	
	test.landingPageActions.navigateToFeature("Main Menu");
	test.landingPageActions.navigateToItemManagementFeature("Item Locations");
	
	test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",getData("ExternalSystem.Name8"));
	
	test.supportDataActions.enterSearchTermInSearchFieldGl("SystemlevelItem11587549200646","search");
	test.supportDataActions.clickOnEditLinkCorresspondingToItem("SystemlevelItem11587549200646", "1587451");
	test.siteConfigurationAction.clickButton("edit");
	
	test.siteConfigurationAction.selectValueFromRackDropdownByIndexForitemlocation(1);
	//test.siteConfigurationAction.selectValueFromrackDropDownonItemLocation("Rack 1");
	
	test.siteConfigurationAction.clickLocationRackButton();
	test.siteConfigurationAction.clickLocationRackcyclecountdateButton();
	
	test.siteConfigurationAction.clickLocationcalenderbutton("d-flex date-button");
	String date=test.siteConfigurationAction.getCurrentMonth_Year();
	
	test.siteConfigurationAction.ClicklocationCurrentDate(getData("ItemLocationDetails.Date"),date);
    String a=test.siteConfigurationAction.getCurrentMonth_Year();
    
   
    test.siteConfigurationAction.clickLocationshelfsfield("Shelf 1");
    test.siteConfigurationAction.clickLocationshelfsmenu("1");
 //  test.siteConfigurationAction.clickLocationshelfButton("selectedShelf");
  // test.siteConfigurationAction.clickLocationshelfButton1("shelf_actions");
   test.siteConfigurationAction.clickLocationshelfcyclecountdateButton();
   test.siteConfigurationAction.clickLocationcalenderbutton("d-flex date-button");
  
   String date1=test.siteConfigurationAction.getCurrentMonth_Year();
   test.siteConfigurationAction.ClicklocationCurrentDate(getData("ItemLocationDetails.Date"),date);
   String a1=test.siteConfigurationAction.getCurrentMonth_Year();
   
   
   
 }
	
}
	
	/*
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
	test.siteConfigurationAction.selectCheckboxForFacilityItem();
	test.siteConfigurationAction.clickButton("save");
	test.siteConfigurationAction.clickButton("cancel");

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
	test.storageAreaAction.enterDataInInputField("maxRackNumber","4");
	test.storageAreaAction.enterDataInInputField("maxShelvesNumber","4");
	test.storageAreaAction.enterDataInInputField("shelfWidthValue","4");
	test.storageAreaAction.enterDataInInputField("maxBinNumber","4");
	test.storageAreaAction.enterDataInInputField("defaultBinWidthValue","2");
	test.storageAreaAction.enterDataInInputField("defaultBinDividersHorizontalNumber","1");
	test.storageAreaAction.enterDataInInputField("defaultBinDividersVerticalNumber","1");
	
    test.siteConfigurationAction.clickTab("Display Settings");
	test.siteConfigurationAction.clickTab("Approved Computers");
	test.siteConfigurationAction.clickSaveButton();
	*/



	
 

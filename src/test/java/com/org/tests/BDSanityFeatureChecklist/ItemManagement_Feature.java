package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class ItemManagement_Feature extends BaseTest  {

	String itemID, brandName,itemId;
	String barcode,productID;
	String itemID10,itemID11,itemID1,itemID2;
	
	
	@Test(priority = 1, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to perfrom a contains search on the itemID field.")

	public void Test01_1048494(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to perfrom a contains search on the itemID field.");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		//test.siteConfigurationAction.verifyPageHeader("fs-24", "Item Management");
		
		test.siteConfigurationAction.enterRandomValueInRichInputField(getData("ExternalSystem.Name7"));
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacility" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem" + System.currentTimeMillis());
		brandName = test.siteConfigurationAction.enterDataInInputField("brandName", "brand1");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID1, "search");
	
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacility" + System.currentTimeMillis());
		itemID2 = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem" + System.currentTimeMillis());
		brandName = test.siteConfigurationAction.enterDataInInputField("brandName", "brand1");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		

	}

	@Test(priority = 2, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to select PIS option from dropdown .")
	public void Test02_1048545(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to select PIS option from dropdown .");
		test.siteConfigurationAction.clearInputBox("scheduleSearch");
		test.siteConfigurationAction.ClickOnExternalSystemRichTextbox();
		test.siteConfigurationAction.enterRandomValueInRichInputField(getData("ExternalSystem.Name7"));

	}
	
	@Test(priority = 3, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to select only one facility at a time for a particular externalsystem.")
	public void Test3_1048561(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to select only one facility at a time for a particular externalsystem.");
		test.siteConfigurationAction.ClickOnExternalSystemRichTextbox();
		test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(getData("ExternalSystem.Name8"));

	}
	
	@Test(priority = 4, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] \"Edit Multiple items \" window get opened after clicking on the\" Edit Selected\" button under the Actions tab for Facility level edit .")
	public void Test4_1106267(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] \"Edit Multiple items \" window get opened after clicking on the\" Edit Selected\" button under the Actions tab for Facility level edit .");
				
		        test.supportDataActions.selectMultipleCheckboxes(getData("ExternalSystem.multicheckbox1"));
				test.supportDataActions.selectMultipleCheckboxes(getData("ExternalSystem.multicheckbox2"));
				test.siteConfigurationAction.clickActionbutton("Actions");
				test.siteConfigurationAction.clickActionbutton("Edit Selected");
				test.siteConfigurationAction.clickfacilityLevel("Facility Level");
				
				test.siteConfigurationAction.verifyfieldIsPresent("Active");
				test.siteConfigurationAction.verifyfieldIsPresent("Ignore ADC Stockout");
				test.siteConfigurationAction.verifyfieldIsPresent("Ignore ADC Critical Low");
				test.siteConfigurationAction.verifyfieldIsPresent("Enable Earliest Expiration Date");
				test.siteConfigurationAction.verifyfieldIsPresent("Send to Packager");
				test.siteConfigurationAction.verifyfieldIsPresent("Exclude from Inventory Report");
				test.siteConfigurationAction.verifyfieldIsPresent("Require Lot/Expiration During Restock");
				test.siteConfigurationAction.verifyfieldIsPresent("Consignment");
				test.siteConfigurationAction.verifyfieldIsPresent("ADC Quantity Rounding");
				test.siteConfigurationAction.verifyfieldIsPresent("Restock Rounding Factor");
				test.siteConfigurationAction.verifyfieldIsPresent("Cycle Count Interval (Days)");
				test.siteConfigurationAction.verifyfieldIsPresent("Average Cost (Eaches)");
				test.siteConfigurationAction.clickActionbutton("Cancel");
				}
	
	@Test(priority = 5, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] \"Mixed\" shows the columns which has different values for the different items")
	public void Test5_1106268(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] \"Mixed\" shows the columns which has different values for the different items");
	//	test.landingPageActions.navigateToItemManagementFeature("Item Management");	
		test.siteConfigurationAction.ClickOnExternalSystemRichTextbox();
		test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(getData("ExternalSystem.Name8"));

		test.supportDataActions.clickButton("edit");
		//test.siteConfigurationAction.clickActionbutton("Edit");
			test.siteConfigurationAction.clickfacilityonEditItem("2");
			test.siteConfigurationAction.clickCheckbox("admIgnoreStockOutFlag");
			test.siteConfigurationAction.clickButton("save");
			
			test.supportDataActions.selectMultipleCheckboxes(getData("ExternalSystem.multicheckbox1"));
			test.supportDataActions.selectMultipleCheckboxes(getData("ExternalSystem.multicheckbox2"));
			test.siteConfigurationAction.clickActionbutton("Actions");
			test.siteConfigurationAction.clickActionbutton("Edit Selected");
			test.siteConfigurationAction.clickfacilityLevel("Facility Level");
			test.siteConfigurationAction.verifyDefaultValueInDropDown("aduIgnoreStockOut", "--Mixed");

	}
	@Test(priority = 6, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] Respective value shows in the columns which has same values for the different items")
	public void Test6_1106277(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] Respective value shows in the columns which has same values for the different items");
		test.siteConfigurationAction.verifyDefaultValueInDropDown("aduIgnoreCriticalLow", "NO");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		
	}
	
	@Test(priority = 7, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] -  The System is allowed to select multiple items from the 'Add from PIS' screen.")
	public void Test7_1100853_AND__1100736_AND_1100741(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) : [UI] -  The System is allowed to select multiple items from the 'Add from PIS' screen.");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacility" + System.currentTimeMillis());
		itemID10 = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacility" + System.currentTimeMillis());
		itemID11 = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		
		test.siteConfigurationAction.clickPendingApprovalTabonItemScreen("Pending Approval");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID10, "search");
		//test.supportDataActions.selectMultipleCheckboxes(getData("ExternalSystem.pendingitem1"));
		test.siteConfigurationAction.clickActionbutton("Reject");
		//test.siteConfigurationAction.clickButton("reject");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID11, "search");
		test.siteConfigurationAction.clickActionbutton("Reject");
		//test.siteConfigurationAction.clickButton("reject");
		
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add From PIS");
		test.supportDataActions.enterSearchTermInSearchFieldGl("SystemlevelItem", "search");
		test.supportDataActions.selectMultipleCheckboxes(getData("ExternalSystem.multiplecheckboxes"));
		//test.supportDataActions.selectMultipleCheckboxes(getData("ExternalSystem.pisitem1"));
		//test.supportDataActions.selectMultipleCheckboxes(getData("ExternalSystem.pisitem2"));
		test.siteConfigurationAction.clickActionbutton("Add To Queue");
		
		
		}
	
	@Test(priority = 8, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to view the list of all items on the Item Management screen.")
	public void Test8_1048482(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to view the list of all items on the Item Management screen.");
		test.siteConfigurationAction.ClickOnExternalSystemRichTextbox();
	    test.siteConfigurationAction.enterRandomValueInRichInputField(getData("ExternalSystem.Name7"));
		test.supportDataActions.codeListDosageForms("2");
		test.supportDataActions.codeListDosageForms("3");
		test.supportDataActions.codeListDosageForms("4");
		test.supportDataActions.codeListDosageForms("6");

	}
	
	@Test(priority = 9, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to click on Add item under the Actions tab and Add screen gets displayed.")
	public void Test09_1062862(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to click on Add item under the Actions tab and Add screen gets displayed.");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		}
	
	@Test(priority = 10, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to add the Strength and Strength UOM Fields.")
	public void Test10_1064113(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to add the Strength and Strength UOM Fields.");
		test.siteConfigurationAction.enterDataInInputField("strengthAmount","112");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("strengthUnitOfMessureKey", 2);
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldsNotMandatoryOnItemscreen("strengthAmount"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("strengthUnitOfMessureKey"),
				"[ASSERTION FAILED]: input field is not mandatory");
		
	}
	@Test(priority = 11, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to add Concentration Volume and Concentration Volume UOM Fields.")
	public void Test11_1064120(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to add Concentration Volume and Concentration Volume UOM Fields.");
		test.siteConfigurationAction.enterDataInInputField("concentrationVolumeAmount","110");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldsNotMandatoryOnItemscreen("concentrationVolumeAmount"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("concentrationVolumeUnitOfMessureKey"),
				"[ASSERTION FAILED]: input field is not mandatory");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("concentrationVolumeUnitOfMessureKey", 2);
	}
	
	@Test(priority = 12, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to add Total Volume and Total Volume UOM Fields.")
	public void Test12_1064145(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to add Total Volume and Total Volume UOM Fields.");
		test.siteConfigurationAction.enterDataInInputField("totalVolumeAmount","112");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldsNotMandatoryOnItemscreen("totalVolumeAmount"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("totalVolumeUnitOfMessureKey"),
				"[ASSERTION FAILED]: input field is not mandatory");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("totalVolumeUnitOfMessureKey", 2);
	}
	
	
	@Test(priority = 13, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to Add/Edit generic name for an item at system level.")
	public void Test13_1062876(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to Add/Edit generic name for an item at system level.");
		test.siteConfigurationAction.enterDataInInputField("genericName","Systemlevelfacilityaa");
		test.siteConfigurationAction.clearInputBox("genericName");
		test.siteConfigurationAction.enterDataInInputField("genericName","Systemlevelfacilitybb");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("genericName"), 50,
				"[ASSERTION FAILED]: Max Length for input field Generic Name is not 50");
		}
	
	@Test(priority = 14, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to add Item ID for an item at system level.")
	public void Test14_1062881(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to add Item ID for an item at system level.");
		test.siteConfigurationAction.enterDataInInputField("itemId","item1");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("itemId"), 50,
				"[ASSERTION FAILED]: Max Length for input field itemId is not 50");
		}
	@Test(priority = 15, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to add/edit Brand name for an item at system level.")
	public void Test15_1062878(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to add/edit Brand name for an item at system level.");
		test.siteConfigurationAction.enterDataInInputField("brandName","brand1");
		test.siteConfigurationAction.clearInputBox("brandName");
		test.siteConfigurationAction.enterDataInInputField("brandName","brand88");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldsNotMandatoryOnItemscreen("brandName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("brandName"), 50,
				"[ASSERTION FAILED]: Max Length for input field Generic Name is not 50");
		}
	
	@Test(priority = 16, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to add custom fields when adding a new Item .")
	public void Test16_1064174(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to add custom fields when adding a new Item .");
		test.siteConfigurationAction.enterDataInInputField("customField1Text","custom11");
		test.siteConfigurationAction.enterDataInInputField("customField2Text","custom12");
		test.siteConfigurationAction.enterDataInInputField("customField3Text","custom13");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldsNotMandatoryOnItemscreen("customField1Text"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldsNotMandatoryOnItemscreen("customField2Text"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldsNotMandatoryOnItemscreen("customField3Text"),
				"[ASSERTION FAILED]: input field is not mandatory");
	}
	
	@Test(priority = 17, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to add Mediaction class values from dropdown.")
	public void Test17_1064154(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to add Mediaction class values from dropdown.");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");
	}
	
	@Test(priority = 18, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to add Theraupetic class values from dropdown.")
	public void Test18_1129694(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to add Theraupetic class values from dropdown.");
		test.siteConfigurationAction.clickTherapeuticdropdownonItemScreen("search");
		test.siteConfigurationAction.clickCheckboxTherapeuticClassitemlevel("ExternalSystem.Itemtherapeutic2");
		
	}
	
	@Test(priority = 19, description = "VPLX: Item Setup (System and Facility): [UI]: User checks Dosage Form and Dispense Unit as mandatory fields.")
	public void Test19_1064107(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User checks Dosage Form and Dispense Unit as mandatory fields.");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueForDropDown("dispensingUnitKey","Select");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.verifybuttonisDisabled("save");
       }

	
	@Test(priority = 20, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to Add item at system level .")
	public void Test20_1062865(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to Add item at system level .");
		test.siteConfigurationAction.enterDataInInputField("genericName","Systemlevelfacilityx"+System.currentTimeMillis());
		itemID=test.siteConfigurationAction.enterDataInInputField("itemId","SystemlevelItem77x"+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
		test.siteConfigurationAction.clickButton("save");
	}
	
	@Test(priority = 21, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to edit facility by clicking facility under system level")
	public void Test21_1064203(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to edit facility by clicking facility under system level");
		//test.landingPageActions.navigateToItemManagementFeature("Item Management");
		
		test.siteConfigurationAction.clickActionbutton("Cancel");
		/*
		test.siteConfigurationAction.enterRandomValueInRichInputField(getData("ExternalSystem.Name7"));
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacilityx" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem77x" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		*/
	
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		test.siteConfigurationAction.clickfacilityonEditItem("2");

	}
	
	@Test(priority = 22, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to edit Consignment checkbox.")
	public void Test22_1064272(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to edit Consignment checkbox.");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("consignmentFlag"));
		test.siteConfigurationAction.selectCheckbox("consignmentFlag", true);
	}
	@Test(priority = 23, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to edit Expiration during Restock.")
	public void Test23_1064277(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to edit Expiration during Restock.");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("requestRestockLotInfoFlag"));
		test.siteConfigurationAction.selectCheckbox("requestRestockLotInfoFlag", true);
	}
	@Test(priority = 24, description = "VPLX: Item Setup (System & Facility) : [UI] -User is able to edit ADC Quantity Rounding at facility level.")
	public void Test24_1129980(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System & Facility) : [UI] -User is able to edit ADC Quantity Rounding at facility level.");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("admQuantityRoundingFlag"));
		test.siteConfigurationAction.selectCheckbox("admQuantityRoundingFlag", true);
	}
	
	@Test(priority = 25, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to edit the prepack flag.")
	public void Tes25_1064834(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to edit the prepack flag.");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("prepackFlag"));
		test.siteConfigurationAction.selectCheckbox("prepackFlag", true);
		test.siteConfigurationAction.selectCheckbox("prepackFlag", false);
	}
	@Test(priority = 26, description = "VPLX: Item Setup (System and Facility): [UI] -User checks the prepack flag bulkitemid becomes mandatory.")
	public void Test26_1066851(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User checks the prepack flag bulkitemid becomes mandatory.");
		test.siteConfigurationAction.selectCheckbox("prepackFlag", true);
		test.siteConfigurationAction.verifyFieldIsMandatory("bulkItemID");
	}
	@Test(priority = 27, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to select bulkitemid when clicking on set bulk item")
	public void Test27_1066858(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to select bulkitemid when clicking on set bulk item");
		test.siteConfigurationAction.clickBulkItemButton();
		test.siteConfigurationAction.verifyBulkItemPopup();

	}
	@Test(priority = 28, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to edit active checkbox.")
	public void Test28_1064211(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to edit active checkbox.");
		test.siteConfigurationAction.scrollUp();
		test.siteConfigurationAction.clickActiveToggle("Active");
	}
	@Test(priority = 29, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to edit GL account values from dropdown.")
	public void Test29_1064281(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to edit GL account values from dropdown.");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("glAccountKey", 1);
		test.siteConfigurationAction.clickButton("save");

	}
	
	@Test(priority = 30, description = "VPLX: Item Setup (System & Facility) : [UI] -User is able to view \"Copy Facility\" option in the Action tab.")
	public void Test30_1125257(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System & Facility) : [UI] -User is able to view \"Copy Facility\" option in the Action tab.");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.supportDataActions.verifyCopyFacilityIsPresentonItemscreen("Copy Facility");
	}

	@Test(priority = 31, description = "VPLX: Item Setup (System & Facility) : [UI] -User clicks on copy facility and a pop-up will be displayed with the list of facilities.")
	public void Test31_1125258(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System & Facility) : [UI] -User clicks on copy facility and a pop-up will be displayed with the list of facilities.");
		test.siteConfigurationAction.clickActionbutton("Copy Facility");
		test.siteConfigurationAction.verifyoptionHeaderonItemscreen("Copy Facility");
		test.siteConfigurationAction.verifyFacilityListonItemScreen();
		
	}
	
	@Test(priority = 32, description = "VPLX: Item Setup (System & Facility) : [UI] -User can only select one Facility from the list.")
	public void Test32_1125259(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System & Facility) : [UI] -User can only select one Facility from the list.");
		 test.siteConfigurationAction.clickcopyfacilitylist();
		 test.siteConfigurationAction.clickCancelButton();
	}
	
	@Test(priority = 33, description = "VPLX: Item Setup (System & Facility) : [UI] -User is able to find \"Set Participating Facility\" option in the Action tab.")
	public void Test33_1125210(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System & Facility) : [UI] -User is able to find \"Set Participating Facility\" option in the Action tab.");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.supportDataActions.verifyCopyFacilityIsPresentonItemscreen("Set Participating Facilities");
		
	}
	@Test(priority = 34, description = "VPLX: Item Setup (System & Facility) : [UI] -User clicks on Set Participating Facility\"  a pop will be displayed with the list of facilities.")
	public void Test34_1125211(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System & Facility) : [UI] -User clicks on Set Participating Facility\"  a pop will be displayed with the list of facilities.");
		test.siteConfigurationAction.clickActionbutton("Set Participating Facilities");
		test.siteConfigurationAction.verifyoptionHeaderonItemscreen("Set Participating Facilities");
		test.siteConfigurationAction.verifyFacilityListonItemScreen();
		
	}
	@Test(priority = 35, description = "VPLX: Item Setup (System & Facility) : [UI] -User is able to select facility and click on save button.")
	public void Test35_1125212(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System & Facility) : [UI] -User is able to select facility and click on save button.");
		test.siteConfigurationAction.clickCheckboxSetParticipatingFacilty();
		test.siteConfigurationAction.clickButton("Save");
	}
	
	@Test(priority = 36, description = "VPLX: Item Setup (System and Facility): [UI]: Item ID is unique for all items within the same PIS")
	public void Test36_1129971(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: Item ID is unique for all items within the same PIS");
	
	test.siteConfigurationAction.clickActionbutton("Actions");
	test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
	test.siteConfigurationAction.enterDataInInputField("genericName",
			"Systemlevelfacility" + System.currentTimeMillis());
	test.siteConfigurationAction.enterDataInInputField("itemId",
			itemID);
	brandName = test.siteConfigurationAction.enterDataInInputField("brandName", "brand1");
	test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
	test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
	test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
	test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
	test.siteConfigurationAction.clickButton("save");
	test.siteConfigurationAction.verifyErrorMessageonItemscreen("This Item ID already exists. Please provide a unique Item ID.");
	test.siteConfigurationAction.clickActionbutton("Cancel");
	}
	
	
	@Test(priority = 37, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to Add Enterprise Id for an item at system level.")
	public void Test37_1129274_AND_1129275_AND_1129970(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to Add Enterprise Id for an item at system level.");
	
	test.siteConfigurationAction.clickActionbutton("Actions");
	test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
	test.siteConfigurationAction.enterDataInInputField("genericName",
			"Systemlevelfacility" + System.currentTimeMillis());
	test.siteConfigurationAction.enterDataInInputField("itemId",
			"SystemlevelItem77x" + System.currentTimeMillis());
	brandName = test.siteConfigurationAction.enterDataInInputField("brandName", "brand1");
	test.siteConfigurationAction.enterDataInInputField("enterpriseId", "enterpriseid1");
	test.siteConfigurationAction.enterDataInInputField("alternateItemID", "alternatepisid1");
	test.siteConfigurationAction.clickTherapeuticdropdownonItemScreen("search");
	test.siteConfigurationAction.clickCheckboxTherapeuticClassitemlevel("ExternalSystem.labeltag1");
	
	test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
	test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
	test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
	test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
	test.siteConfigurationAction.clickButton("save");
	test.siteConfigurationAction.clickActionbutton("Cancel");
	}
}
	/*
	@Test(priority = 31, description = "VPLX: Item Setup (System & Facility) : [UI] -User enters cycle count interval on facility attributes tab.")
	public void Test31_1129978(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System & Facility) : [UI] -User enters cycle count interval on facility attributes tab.");
	/*
	test.siteConfigurationAction.clickActionbutton("Actions");
	test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
	test.siteConfigurationAction.enterDataInInputField("genericName",
			"Systemlevelfacility" + System.currentTimeMillis());
	itemId=test.siteConfigurationAction.enterDataInInputField("itemId",
			"SystemlevelItem77x" + System.currentTimeMillis());
//	brandName = test.siteConfigurationAction.enterDataInInputField("brandName", "brand1");
//	test.siteConfigurationAction.enterDataInInputField("enterpriseId", "enterpriseid1");
	//test.siteConfigurationAction.enterDataInInputField("alternateItemID", "alternatepisid1");
//	test.siteConfigurationAction.clickTherapeuticdropdownonItemScreen("search");
	//test.siteConfigurationAction.clickCheckboxTherapeuticClassitemlevel("ExternalSystem.labeltag1");
	
	test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
	test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
	test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
	test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
	test.siteConfigurationAction.clickButton("save");
	test.siteConfigurationAction.clickActionbutton("Cancel");
	*/
	
	
	
	/*
	test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
	test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID, "Edit Item");
	test.siteConfigurationAction.clickfacilityonEditItem("2");
	test.siteConfigurationAction.enterDataInInputField("cycleCountIntervalDayAmount","123");
	}
	
	@Test(priority = 32, description = "VPLX: Item Setup (System & Facility) : [UI] -User enters Average cost on facility attributes.")
	public void Test32_1129979(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System & Facility) : [UI] -User enters Average cost on facility attributes.");
		test.siteConfigurationAction.enterDataInInputField("averageWholesalePriceAmount","12");
		
	}
		/*
	 * 
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	
	@Test(priority = 23, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI]: User validates the mandatory fields of attributes tab at system level.")
	public void Test23_1078800(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - Management of NDCs and setting Preferred: [UI]: User validates the mandatory fields of attributes tab at system level.");
		//test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.clickActionbutton("Cancel");
	    test.siteConfigurationAction.enterRandomValueInRichInputField(getData("ExternalSystem.Name7"));
	    test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
//test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		// test.siteConfigurationAction.enterDataInInputField("genericName","Systemlevelfacilitybb");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");

		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);

	}

	@Test(priority = 24, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI]: User fills all the details in the attributes tab,so ProductID tab is enabled.")
	public void Test24_1078805(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - Management of NDCs and setting Preferred: [UI]: User fills all the details in the attributes tab,so ProductID tab is enabled.");
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacilityx" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem77x" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickButton("save");
		 test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));
		//test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);
		//test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		//test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID, "Edit Item");
		 test.siteConfigurationAction.verifyAndClickProductID("Product ID");
	}
	@Test(priority = 25, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI]: User clicks on Add ProductID link and Barcode management screen is opened.")
	public void Test25_1078900(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - Management of NDCs and setting Preferred: [UI]: User clicks on Add ProductID link and Barcode management screen is opened.");
		//test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
	}
	
	@Test(priority = 26, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -First product ID will be set as preferred automatically.")
	public void Test26_1129982_AND_1098652_AND_1098591(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -First product ID will be set as preferred automatically.");
		barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
				"01003" + System.currentTimeMillis() + "0171005077328717621abcd123456789");

		test.siteConfigurationAction.clickButton("search");

		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);

		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		test.siteConfigurationAction.enterRandomValueInInputField("tradeName","brand123");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("manufacturerKey", 1);
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.verifyAddedProductID(productID);

		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickDistributorInfo("preferredDistributor", "1");
		//test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorCode"));
		test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "1", "" + System.currentTimeMillis());
	
		
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);
	
	
}
	
	@Test(priority = 27, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -User is able to add multiple productID at system level.")
	public void Test27_1098513_AND_1098681(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -User is able to add multiple productID at system level.");
		
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
				"01003" + System.currentTimeMillis() + "01710050326628717621abcd123456789");

		test.siteConfigurationAction.clickButton("search");

		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);

		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		test.siteConfigurationAction.enterRandomValueInInputField("tradeName","brand123");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("manufacturerKey", 1);
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.verifyAddedProductID(productID);

		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickDistributorInfo("preferredDistributor", "1");
		//test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorCode"));
		test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "1", "" + System.currentTimeMillis());
	
		//test.siteConfigurationAction.clickDistributorInfo("preferredDistributor", "3");
		//test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "3", "" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
	//	test.siteConfigurationAction.clickSaveButtonForISA();
		//test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		//test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);
	
	
	}
	
	
	@Test(priority = 28, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -User is able to change the preferred ProductID at facility level if it is not checked in \"Set Participating facilities\"")
	public void Test28_1125201_AND_1098603(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -User is able to change the preferred ProductID at facility level if it is not checked in \"Set Participating facilities\"");
		
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
				"01003" + System.currentTimeMillis() + "0171005077328717621abcd123456789");

		test.siteConfigurationAction.clickButton("search");

		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);

		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		test.siteConfigurationAction.enterRandomValueInInputField("tradeName","brand123");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("manufacturerKey", 1);
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.verifyAddedProductID(productID);

		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickDistributorInfo("preferredDistributor", "1");
		//test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorCode"));
		test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "1", "" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.selectRadioOption("preferred");
		
		test.siteConfigurationAction.clickSaveButtonForISA();
		
		
}
	*/
	


package com.org.tests.itemsetupGlobalEdit;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1098049 extends BaseTest  {
	
	
	String itemID, brandName,ItemName;
	
	@Test(priority = 1, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] Edit selected  button not visible  by default .")
	public void Test01_1105850(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] Edit selected  button not visible  by default .");
		
			test.landingPageActions.navigateToItemManagementFeature("Item Management");
			test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		/*
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(getData("ExternalSystem.Name7"));
*/
		test.siteConfigurationAction.clickActionbutton("Actions");		
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		//test.siteConfigurationAction.enterDataInInputField("genericName",
				//"Systemlevelfacility" + System.currentTimeMillis());
		ItemName  = test.siteConfigurationAction.enterRandomValueInInputField("genericName",
				"ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem" + System.currentTimeMillis());
		brandName = test.siteConfigurationAction.enterDataInInputField("brandName", "brand1");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickButton("cancel");	
		test.siteConfigurationAction.clickActionbutton("Actions");
		Assert.assertFalse(test.siteConfigurationAction.verifyEditSelectedbutton());
	}
	@Test(priority = 2, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] User has option as \"Edit selected\" under the Actions tab to perform the Global Edit .")
	public void Test02_1105849(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] User has option as \"Edit selected\" under the Actions tab to perform the Global Edit .");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(ItemName);
		
		
		/*
		test.supportDataActions.enterSearchTermInSearchFieldGl(getData("ExternalSystem.itemsearch"), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(getData("ExternalSystem.itemsearch"));
		*/
	
	
		test.siteConfigurationAction.selectValueFromDropDownForExternalSystem("dispensingFormKey", "Select");
		
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.clickButton("save");

		test.siteConfigurationAction.clearInputBox("scheduleSearch");
		test.supportDataActions.selectMultipleCheckboxes("1");
		test.supportDataActions.selectMultipleCheckboxes("2");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.verifyAddFromPISIsPresent("Edit Selected");
		}
	
	
	@Test(priority = 3, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] Edit button in front of item at Facility level got disable after clicking on the Edit selected")
	public void Test03_1105851(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] Edit selected button get  enable after selecting the items under the PIS.");
		//
		Assert.assertFalse(test.supportDataActions.verifyEditButtonIsDisabledOnItemManagement("1"));
		Assert.assertFalse(test.supportDataActions.verifyEditButtonIsDisabledOnItemManagement("2"));
	}

	@Test(priority = 4, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] Edit multiple items window will get opened after clicking on the Edit selected  button under the Actions tab .")
	public void Test04_1105852(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] Edit multiple items window will get opened after clicking on the Edit selected  button under the Actions tab .");
		test.siteConfigurationAction.clickActionbutton("Edit Selected");
		test.siteConfigurationAction.verifycheckboxispresent("dosageFormKey");
	test.siteConfigurationAction.verifycheckboxispresent("dispenseUnitKey");
	test.siteConfigurationAction.verifycheckboxispresent("medicationClassKey");
	}
	
	@Test(priority = 5, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] \"Mixed\" value get visible for the columns if selected items have different values .")
	public void Test05_1105854(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] \"Mixed\" value get visible for the columns if selected items have different values .");
		test.siteConfigurationAction.verifyDefaultValueInDropDown("dispenseUnitKey", "--Mixed");
}
	

	@Test(priority = 7, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] Changes get applied to selected items at PIS level  after clicking on the save.")
	public void Test06_Test07_1105858_AND_1105855(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] Changes get applied to selected items at PIS level  after clicking on the save.");
		test.siteConfigurationAction.clickButton("cancel");
		
		//test.siteConfigurationAction.ClickOnExternalSystemRichTextbox();
		
		//test.landingPageActions.navigateToItemManagementFeature("Item Management");	
	    //test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
	    test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
	   
		// test.supportDataActions.enterSearchTermInSearchFieldGl("SystemlevelItem1592044215897", "search");
		//	test.supportDataActions.clickOnEditLinkCorresspondingToItem("SystemlevelItem1592044215897");
		 
	    test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(ItemName);
		test.siteConfigurationAction.verifyDefaultValueInDropDown("dispensingFormKey", (getData("ExternalSystem.dosagevalue2")));
		}

	@Test(priority = 8, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] User is not able to perform edit at PIS level if \"Allow Edit from pharmacy \" is false while creation of external system.")
	public void Test08_1105865(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] User is not able to perform edit at PIS level if \"Allow Edit from pharmacy \" is false while creation of external system.");		
		test.siteConfigurationAction.clickButton("cancel");
		test.siteConfigurationAction.clearInputBox("scheduleSearch");
		//test.siteConfigurationAction.ClickOnExternalSystemRichTextbox();
	    //test.siteConfigurationAction.enterRandomValueInRichInputField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
	    test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
	    test.supportDataActions.selectMultipleCheckboxes("1");
		test.supportDataActions.selectMultipleCheckboxes("2");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Edit Selected");	
		test.siteConfigurationAction.verifyDropdownFieldIsDisabled("dosageFormKey");
		test.siteConfigurationAction.verifyDropdownFieldIsDisabled("dispenseUnitKey");
		test.siteConfigurationAction.verifyDropdownFieldIsDisabled("medicationClassKey");
	}
	
	
}
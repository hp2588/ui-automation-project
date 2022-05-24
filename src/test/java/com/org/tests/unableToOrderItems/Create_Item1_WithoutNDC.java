package com.org.tests.unableToOrderItems;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;


public class Create_Item1_WithoutNDC extends BaseTest{
	
	
		String itemID, itemName, barcode, productID, itemID1, itemName1, barcode1, productID1;

		@Test(priority = 1, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
		public void Test01_1054082(Method method) {
			
			test.landingPageActions.navigateToItemManagementFeature("Item Management");
			test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName_UnableToOrder").trim());
			test.siteConfigurationAction.clickActionbutton("Actions");
			test.siteConfigurationAction.clickActionbutton("Add New Item");
			test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
			Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
					"[ASSERTION FAILED]: input field is not mandatory");
			Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
					"[ASSERTION FAILED]: input field is not mandatory");
			Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
					"[ASSERTION FAILED]: dropdown is not mandatory");
			test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode_U").trim());
			itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
			itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
			test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode_U").trim());
			test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName_UnableToOrder").trim());
			test.siteConfigurationAction.clickButton("save");
			
			
			test.landingPageActions.navigateToFeature("Main Menu");
			test.landingPageActions.navigateToFeature("Item Locations");
			test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
					TestDataPropertyReaderAndWriter.getProperty("FacilityName_UnableToOrder"));
			//test.supportDataActions.resetSearch();
			test.supportDataActions.enterSearchTermInSearchFieldGl(itemName1,	"search");
			
			test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName1);
			
		//	test.purchaseDashboardActions.clickOnEditLinkCorresspondingToItem(itemName1);
			test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
			test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
			test.siteConfigurationAction.selectValueForDropDown("facility",
					TestDataPropertyReaderAndWriter.getProperty("FacilityName_UnableToOrder").trim());
			test.siteConfigurationAction.selectValueForDropDown("isa",
					TestDataPropertyReaderAndWriter.getProperty("ISAName3"));
			test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
			test.siteConfigurationAction.clickAssignLocationButton();
			test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
			test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
			test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
			test.siteConfigurationAction.clickSaveButton();
		}


}

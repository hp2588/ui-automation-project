package com.org.tests.mainmenu.locassmanagingstoragebinoptions;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class AddItem_AssignLocation extends BaseTest {

	String itemID, itemName, barcode, productID, itemID1, itemName1, barcode1, productID1;

	@Test(priority = 1, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test01_1054082(Method method) {
		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		Assert.assertTrue(test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item"));
		
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName", "ItemName" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId", "ItemID" + System.currentTimeMillis());
		
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey", 
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey", 
				TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		
		TestDataPropertyReaderAndWriter.setProperty("ItemName", itemName1);
		TestDataPropertyReaderAndWriter.setProperty("ItemCode", itemName1);
		
	}


}



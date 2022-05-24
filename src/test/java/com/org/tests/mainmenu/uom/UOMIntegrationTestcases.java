package com.org.tests.mainmenu.uom;

import static com.org.automation.utils.YamlReader.getData;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class UOMIntegrationTestcases extends BaseTest {
	
	String[] columnHeaders = { "Code", "Description", "Predefined", "Role","Sort Order","Status","Actions" };
	String unit_code1, unit_Description1,unit_code2,unit_Description2,facilityOnWFAScreen=TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(),unit_code1Updated,unit_code2Updated;
	
	@Test(priority = 1, description = "VPLX: Unit of Measure - Internal UOM  : [UI] Get the List of predefined UOM's.")
	public void Test01_1084410() {
		/*test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		facilityOnWFAScreen = test.siteConfigurationAction.getFacilityFromISAScreen();
		test.siteConfigurationAction.clickActionbutton("Cancel");*/
		test.landingPageActions.navigateToFeature("Units of Measure");
		test.supportDataActions.verifyRecordList();
	}
	
	@Test(priority = 2, description = "Test Case VPLX: Unit of Measure - Internal UOM  : [UI] List of Columns on the unit of measure screen.")
	public void Test02_1084413() {
		
		test.siteConfigurationAction.verifyColumnHeaders(Arrays.asList(columnHeaders));
	}

	@Test(priority = 3, description = "VPLX: Unit of Measure - Internal UOM  : [UI] User navigate to Add UOM screen after clicking on the Add button")
	public void Test03_1084456() {
		
		test.supportDataActions.clickOnAddButtonToAddNewUOM("Add Unit of Measure");
	}
	
	@Test(priority = 4, description = "VPLX: Unit of Measure - Internal UOM  : [UI]: When a new UOM is added, the UOM code gets populated in Stength UOM , Concentration volume UOM ,Total UOM dropdown on Add /edit Item ")
	public void Test04_1112287() {
		
		unit_code1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("displayCode",
				"Code" + System.currentTimeMillis());
		unit_Description1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Des" + System.currentTimeMillis());
		test.siteConfigurationAction.clickCheckboxRoleofUOM("1");
		//test.siteConfigurationAction.clickCheckboxfacilityitemlevel2("1","Medication Strength");
		test.siteConfigurationAction.clickButton("save");
		
		test.supportDataActions.clickOnAddButtonToAddNewUOM("Add Unit of Measure");
		
		unit_code2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("displayCode",
				"Code" + System.currentTimeMillis());
		unit_Description2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Des" + System.currentTimeMillis());
		test.siteConfigurationAction.clickCheckboxRoleofUOM("2");
		//test.siteConfigurationAction.clickCheckboxfacilityitemlevel2("1","Medication Strength");
		test.siteConfigurationAction.clickButton("save");
		
		//test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(facilityOnWFAScreen);
		
		
		test.siteConfigurationAction.selectValueFromDropDown("strengthUnitOfMessureKey", unit_code1);
		Assert.assertTrue(test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("concentrationVolumeUnitOfMessureKey",
				unit_code1), "[ASSERTION FAILED]: Value exist in dropdown");
		Assert.assertTrue(test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("totalVolumeUnitOfMessureKey",
				unit_code1), "[ASSERTION FAILED]: Value exist in dropdown");
		
		
		test.siteConfigurationAction.selectValueFromDropDown("concentrationVolumeUnitOfMessureKey", unit_code2);
		test.siteConfigurationAction.selectValueFromDropDown("totalVolumeUnitOfMessureKey", unit_code2);
		Assert.assertTrue(test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("strengthUnitOfMessureKey",
				unit_code2), "[ASSERTION FAILED]: Value exist in dropdown");


				/**/

	}
	
	@Test(priority = 5, description = "VPLX: Unit of Measure - Internal UOM  : [UI]: When a  UOM code is updated, the updated UOM code gets populated in Stength UOM , Concentration volume UOM ,Total UOM  dropdown on Add/edit ITem screen")
	public void Test05_1112292() {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Units of Measure");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(unit_code1);
		test.supportDataActions.clickOnAddButtonToAddNewUOM("Edit Unit of Measure");
		unit_code1Updated=test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("displayCode",
				"UpdatedCode1" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("save");
		
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(unit_code2);
		test.supportDataActions.clickOnAddButtonToAddNewUOM("Edit Unit of Measure");

		unit_code2Updated=test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("displayCode",
				"UpdatedCode2" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("save");
		
		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterRandomValueInRichInputField(facilityOnWFAScreen);
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(facilityOnWFAScreen);
		
		
		test.siteConfigurationAction.selectValueFromDropDown("strengthUnitOfMessureKey", unit_code1Updated);
		Assert.assertTrue(test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("concentrationVolumeUnitOfMessureKey",
				unit_code1Updated), "[ASSERTION FAILED]: Value "+unit_code1Updated+" exist in concentration Volume dropdown");
		Assert.assertTrue(test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("totalVolumeUnitOfMessureKey",
				unit_code1Updated), "[ASSERTION FAILED]: Value " +unit_code1Updated+"exist in total Volume Unit dropdown");
		
		
		test.siteConfigurationAction.selectValueFromDropDown("concentrationVolumeUnitOfMessureKey", unit_code2Updated);
		test.siteConfigurationAction.selectValueFromDropDown("totalVolumeUnitOfMessureKey", unit_code2Updated);
		Assert.assertTrue(test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("strengthUnitOfMessureKey",
				unit_code2Updated), "[ASSERTION FAILED]: Value exist in dropdown");

		
	}
	
	@Test(priority = 6, description = "VPLX: Unit of Measure - Internal UOM  : [UI]: When a UOM , is mapped to item ,the inactive toggle gets disabled for uom")
	public void Test06_1112294() {
		
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		
		test.siteConfigurationAction.enterRandomValueInInputField("genericName",
				"ItemName" + System.currentTimeMillis());
		test.siteConfigurationAction.enterRandomValueInInputField("itemId",
				"ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);

		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();

		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Units of Measure");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(unit_code1Updated);
		test.supportDataActions.clickOnAddButtonToAddNewUOM("Edit Unit of Measure");
		
		test.supportDataActions.verifytoggleButtonIsDisabled("activeFlag");
		
		
	}
	
	
	
	
}

package com.org.tests.therapeuticclasses;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1087094  extends BaseTest{
	
	String facilityOnWFAScreen=TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(),External=TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim(),itemID, brandName,theraclass,labeltag;
	String medclass;
	String dataEnteredCode, dataEnteredDescription, dataEnteredSort,dataEnteredCode1;
	int lenCode, lenSort;
	@Test(priority = 1, description = "VPLX :Item Setup - ES Parity (Formulary Reference Data): [UI]: Medication class drop down is present in the Item setup for selected PIS"
			+ ""
			+ "VPLX :Item Setup - ES Parity (Formulary Reference Data): [UI]: Therapeutic class drop down is present in the Item setup for selected PIS"
			+ ""
			+ "VPLX :Item Setup - ES Parity (Formulary Reference Data): [UI]: All Label Tags created drop down is present in the Item setup")
	public void Test01_1050028_AND_1117362_AND_1117363(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Item Setup - ES Parity (Formulary Reference Data): [UI]: Medication class drop down is present in the Item setup for selected PIS");
				
		test.landingPageActions.navigateToFeature("Label Tags");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Label Tag");
		dataEnteredCode1 = test.siteConfigurationAction.verifyMaxLengthOfAnInputFieldLabelTag("labelTagCode", DateUtil.getAlphaNumericString(21),20);
		test.storageAreaAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("LabelName", dataEnteredCode1);		
		
		
		test.landingPageActions.navigateToMenu("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item");
		
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacility" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem" + System.currentTimeMillis());
		brandName = test.siteConfigurationAction.enterDataInInputField("brandName", "brand1");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
	    test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		
		test.siteConfigurationAction.clickTherapeuticdropdownonItemScreen("therapeuticClass_trigger");
		theraclass= test.siteConfigurationAction.clickCheckboxTherapeuticClassitemlevel(TestDataPropertyReaderAndWriter.getProperty("TherapeuticClass").trim());
		
		test.siteConfigurationAction.clickTherapeuticdropdownonItemScreen("labeltags_trigger");
		labeltag= test.siteConfigurationAction.clickCheckboxTherapeuticClassitemlevel(TestDataPropertyReaderAndWriter.getProperty("LabelName").trim());
		
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(facilityOnWFAScreen);
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickActionbutton("Cancel");		
		}
	
}

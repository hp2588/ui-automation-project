package com.org.tests.integration;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class IntMedicationClassTest extends BaseTest {
String dataEnteredCode, dataEnteredDescription, dataEnteredSort, external_System;
	
	@Test(priority = 1, description = "VPLX:Medication Classes:UI:Verify User is able to add new medication class")
	public void Test01_MedicationClass_Test(Method method) {
		test.landingPageActions.navigateToFeature("Medication Classes");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Medication Classes"));
		test.siteConfigurationAction.selectDropdownDispenseExternal(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"), "medication");
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Medication Class");
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("medicationClassCode", "1");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("medicationClassOrderInternalCode",1);
		dataEnteredDescription = test.supportDataActions.enterValueOnDescriptionMedicationPage("medicationClassDescription",
				"UI_Medication_Desc_");
		dataEnteredSort = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassSortOrder", "3");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		//test.supportDataActions.verifyNewlyAddedRecordNameInList(dataEnteredCode);
		TestDataPropertyReaderAndWriter.setProperty("MedClassCode", dataEnteredCode);
		TestDataPropertyReaderAndWriter.setProperty("MedClassDescription", dataEnteredDescription);
	}
	
	@Test(priority = 2, description = "VPLX:Medication Classes:UI:Verify User is able to add new medication class")
	public void Test02_MedicationClass_Test(Method method) {
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Medication Classes"));
		test.siteConfigurationAction.selectDropdownDispenseExternal(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"), "medication");
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Medication Class");
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("medicationClassCode", "3");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("medicationClassOrderInternalCode",1);
		dataEnteredDescription = test.supportDataActions.enterValueOnDescriptionMedicationPage("medicationClassDescription",
				"UI_Medication_Desc_");
		dataEnteredSort = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassSortOrder", "2");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		//test.supportDataActions.verifyNewlyAddedRecordNameInList(dataEnteredCode);
		TestDataPropertyReaderAndWriter.setProperty("MedClassCode1", dataEnteredCode);
		TestDataPropertyReaderAndWriter.setProperty("MedClassDescription1", dataEnteredDescription);
	}


}

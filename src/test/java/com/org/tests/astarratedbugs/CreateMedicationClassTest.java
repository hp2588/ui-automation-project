package com.org.tests.astarratedbugs;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class CreateMedicationClassTest extends BaseTest {
String dataEnteredCode, dataEnteredDescription, dataEnteredSort, external_System;
	
	@Test(priority = 2, description = "VPLX:Medication Classes:UI:Verify User is able to add new medication class")
	public void Test02_MedicationClass_Test(Method method) {
		test.landingPageActions.navigateToFeature("Medication Classes");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Medication Classes"));
		test.siteConfigurationAction.selectDropdownDispenseExternal(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"), "medication");
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Medication Class");
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("medicationClassCode", "2");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("medicationClassOrderInternalCode",1);
		dataEnteredDescription = test.supportDataActions.enterValueOnDescriptionMedicationPage("medicationClassDescription",
				"UI_Medication_Desc_");
		dataEnteredSort = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassSortOrder", "3");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		TestDataPropertyReaderAndWriter.setProperty("MedClassCode", dataEnteredCode);
		TestDataPropertyReaderAndWriter.setProperty("MedClassDescription", dataEnteredDescription);
	}


}

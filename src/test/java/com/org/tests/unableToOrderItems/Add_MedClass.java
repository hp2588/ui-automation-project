package com.org.tests.unableToOrderItems;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Add_MedClass extends BaseTest {
String dataEnteredCode, dataEnteredDescription, dataEnteredSort, external_System;
	
	@Test(priority = 2, description = "VPLX:Medication Classes:UI:Verify User is able to add new medication class")
	public void Test02_MedicationClass_Test(Method method) {
		test.landingPageActions.navigateToFeature("Medication Classes");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Medication Classes"));
		test.siteConfigurationAction.selectDropdownDispenseExternal(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName_UnableToOrder"), "medication");
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Medication Class");
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("medicationClassCode", "2");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("medicationClassOrderInternalCode",1);
		//dataEnteredDescription = test.supportDataActions.enterValueOnDescriptionMedicationPage("medicationClassDescription","UI_Medication_Desc_");
		//dataEnteredDescription = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassDescription", "UI_Medication_Desc_");
		dataEnteredSort = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassSortOrder", "3");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		//test.supportDataActions.verifyNewlyAddedRecordNameInList(dataEnteredCode);
		TestDataPropertyReaderAndWriter.setProperty("MedClassCode_U", dataEnteredCode);
		//TestDataPropertyReaderAndWriter.setProperty("MedClassDescription", dataEnteredDescription);
	}

}
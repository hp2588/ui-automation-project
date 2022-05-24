package com.org.tests.integrationSuite1;

import java.lang.reflect.Method;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class IntDispenseUnitTest extends BaseTest {

	String DispenseUnitCode, descriptionForm, sortOrder, external_System, DispenseUnitCode1, descriptionForm1;

	@Test(priority = 1, description = "VPLX: Dispense Unit [UI]:Verify User is able to add new dispense unit")
	public void Test01_Add_DispenseUnit_Test(Method method) {
		test.landingPageActions.navigateToFeature("Dispense Units");
		test.supportDataActions.clickOnAddButtonToAddNewRecord1("Add Dispense Unit");
		DispenseUnitCode = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"UI_Code" + System.currentTimeMillis());
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"UI_Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterRandomValueInInputField("sortValue", "3");
		test.siteConfigurationAction.selectValueForDropDown("externalSystemKey",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.clickButton("save");
		// test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		// test.supportDataActions.verifyNewlyAddedRecordNameInList(codeValue);
		TestDataPropertyReaderAndWriter.setProperty("DispenseUnitDesc", descriptionForm);
		TestDataPropertyReaderAndWriter.setProperty("DispenseUnitCode", DispenseUnitCode);
	}
	
	@Test(priority = 2, description = "VPLX: Dispense Unit [UI]:Verify User is able to add new dispense unit")
	public void Test02_Add_DispenseUnit_Test(Method method) {
		test.supportDataActions.clickOnAddButtonToAddNewRecord1("Add Dispense Unit");
		DispenseUnitCode1 = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"UI_Code" + System.currentTimeMillis());
		descriptionForm1 = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"UI_Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterRandomValueInInputField("sortValue", "3");
		test.siteConfigurationAction.selectValueForDropDown("externalSystemKey",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.clickButton("save");
		// test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		// test.supportDataActions.verifyNewlyAddedRecordNameInList(codeValue);
		TestDataPropertyReaderAndWriter.setProperty("DispenseUnitDesc1", descriptionForm1);
		TestDataPropertyReaderAndWriter.setProperty("DispenseUnitCode1", DispenseUnitCode1);
	}

}

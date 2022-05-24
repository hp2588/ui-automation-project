package com.org.tests.integrationSuite3;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IntDosageFormTest extends BaseTest {

	String codeValue, descriptionForm;

	@Test(priority = 1, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: User is able add and view dosage form")
	public void Test01_NewDosage(Method method) {
		test.landingPageActions.navigateToFeature("Dosage Forms");
		
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");	
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.GLAccount")),
				"[ASSERTION FAILED]: Toggle Button is Not Present on Add Dosage Form");
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
		String dosageFormCode = "Code" + System.currentTimeMillis();
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", dosageFormCode);
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("sortValue", "3");
		test.siteConfigurationAction.selectValueForDropDown("externalSystemKey",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.clickButton("save");
		
		TestDataPropertyReaderAndWriter.setProperty("DosageFormCode", dosageFormCode);
		TestDataPropertyReaderAndWriter.setProperty("DosageFormDesc", descriptionForm);
		
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("externalSystems",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dosageFormCode);
	}
	
	@Test(priority = 2, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: User is able add and view dosage form")
	public void Test02_NewDosage(Method method) {
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.GLAccount")),
				"[ASSERTION FAILED]: Toggle Button is Not Present on Add Dosage Form");
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
		String dosageFormCode = "Code" + System.currentTimeMillis();
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", dosageFormCode);
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("sortValue", "3");
		test.siteConfigurationAction.selectValueForDropDown("externalSystemKey",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.clickButton("save");
		
		TestDataPropertyReaderAndWriter.setProperty("DosageFormCode1", dosageFormCode);
		TestDataPropertyReaderAndWriter.setProperty("DosageFormDesc1", descriptionForm);
		
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dosageFormCode);
	}
	
	@Test(priority = 3, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: User is able add and view dosage form")
	public void Test03_NewDosage(Method method) {
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.GLAccount")),
				"[ASSERTION FAILED]: Toggle Button is Not Present on Add Dosage Form");
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
		String dosageFormCode = "Code" + System.currentTimeMillis();
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", dosageFormCode);
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("sortValue", "3");
		test.siteConfigurationAction.selectValueForDropDown("externalSystemKey",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.clickButton("save");
		
		TestDataPropertyReaderAndWriter.setProperty("DosageFormCode2", dosageFormCode);
		TestDataPropertyReaderAndWriter.setProperty("DosageFormDesc2", descriptionForm);
		
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dosageFormCode);
	}

}

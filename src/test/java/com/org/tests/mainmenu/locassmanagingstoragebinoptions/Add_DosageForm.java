package com.org.tests.mainmenu.locassmanagingstoragebinoptions;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Add_DosageForm extends BaseTest {

	String dosageFormCode, descriptionForm;
	
	@Test(priority = 1, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: User is able add and view dosage form")
	public void Test01_1040230(Method method) {
		
		test.landingPageActions.navigateToFeature("Dosage Forms");
		
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("externalSystemKey",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
		
		dosageFormCode = "Code" + System.currentTimeMillis();
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("dosageFormCode", dosageFormCode);
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("sortValue", "3");
		test.storageAreaAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("DosageFormCode", dosageFormCode);
		TestDataPropertyReaderAndWriter.setProperty("DosageFormDesc", descriptionForm);
		
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("externalSystems",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dosageFormCode);
		
	}
}

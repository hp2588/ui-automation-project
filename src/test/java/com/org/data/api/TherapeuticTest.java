package com.org.data.api;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class TherapeuticTest extends BaseTest {

	String Code, description, sortOrder;
	

	@Test(priority = 1, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] : On main home page link is present of \"Therapeutic Class\"")
	public void Test01_1108936(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] : On main home page link is present of \"Therapeutic Class\"");
		test.landingPageActions.navigateToFeature("Therapeutic Classes");
		
		test.siteConfigurationAction.selectValueFromDropDown("Therapeutic",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewClassonTherapeuticClass("Add Therapeutic Class");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("therapeuticClassCode");
		test.siteConfigurationAction.verifydescriptionInputField("therapeuticClassDescription");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("therapeuticClassSortOrder");
		
		Code = test.siteConfigurationAction.enterDataInInputField("therapeuticClassCode",
				"code" + System.currentTimeMillis());
		description = test.siteConfigurationAction.enterDataInTextAreaField("therapeuticClassDescription",
				"des" + System.currentTimeMillis());
		sortOrder = test.siteConfigurationAction.enterDataInInputField("therapeuticClassSortOrder", "5");
		test.storageAreaAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList("Code");
		// test.siteConfigurationAction.verifyButtonIsPresent("Edit");
		test.siteConfigurationAction.verifyButtonIsPresent("Delete");	
	}

}

package com.org.tests.packageshare;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Create_DosageForm_Pkg_Shr_Data extends BaseTest{

	String codeValue, descriptionForm;

	@Test(priority = 1, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: User is able add and view dosage form")
	public void Test01_Create_Dosage_Form(Method method) {
		test.landingPageActions.navigateToItemManagementFeature("Dosage Forms");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.GLAccount")),
				"[ASSERTION FAILED]: Toggle Button is Not Present on Add Dosage Form");
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
		String dosageFormCode = "Code" + System.currentTimeMillis();
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", dosageFormCode);
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("sortValue", "3");
		test.siteConfigurationAction.selectValueForDropDown("externalSystemKey", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		TestDataPropertyReaderAndWriter.setProperty("DosageFormDesc", descriptionForm);
		TestDataPropertyReaderAndWriter.setProperty("DosageFormCode", dosageFormCode);
	}
}
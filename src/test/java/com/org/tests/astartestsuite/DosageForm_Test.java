package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class DosageForm_Test extends BaseTest{

	String codeValue, descriptionForm;

	@Test(priority = 1, description = "VPLX: Dosage Form [UI]: Dosage Form Description with a unique name is acceptable upon saving")
	public void Test01_1040230(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Add: Dosage Form Description with a unique name is acceptable upon saving");
		test.landingPageActions.navigateToItemManagementFeature("Dosage Forms");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.GLAccount")),
				"[ASSERTION FAILED]: Toggle Button is Not Present on Add Dosage Form");
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("dosageFormCode"), 20);
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnTextAreaField("descriptionText"), 100);
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("sortValue"), 4);
		
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", "Code" + System.currentTimeMillis());
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("sortValue", "3");
		test.supportDataActions.selectValueFromDropdownByIndex("externalSystemKey", 0);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(descriptionForm);
	}
}

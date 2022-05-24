package com.org.tests.mainmenu.dispenseunits;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997963 extends BaseTest {
	String codeValue, descriptionForm, sortOrder;

	@Test(priority = 1, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: User is able to toggle to set a record as Active while adding Dispense Unit")
	public void Test01_1047622(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: User is able to toggle to set a record as Active while adding Dispense Unit");
		test.landingPageActions.navigateToFeature("Dispense Units");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.GLAccount")),
				"[ASSERTION FAILED]: Toggle Button is Not Present on Add Dosage Form");
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
	}

	@Test(priority = 2, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: User is able to toggle to set a record as Inactive while adding Dispense Unit")
	public void Test02_1047625(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: User is able to toggle to set a record as Inactive while adding Dispense Unit");
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Active", false);
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("false"));
	}

	@Test(priority = 3, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Save Button is Disabled if user tries to add Dispense Unit without providing Code")
	public void Test03_1047628(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: Save Button is Disabled if user tries to add Dispense Unit without providing Code");
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Active", true);
		Assert.assertFalse(test.supportDataActions.verifyButtonIsEnabledOrDisabled("save"));
	}

	@Test(priority = 4, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Code greater than length 20 is not allowed")
	public void Test04_1047630(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: Code greater than length 20 is not allowed");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("dispenseUnitCode",
				DateUtil.getAlphaNumericString(21), 20);
	}

	@Test(priority = 5, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Description greater than length 100 is not allowed")
	public void Test05_1047638(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: Description greater than length 100 is not allowed");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputFieldDescriptionText("descriptionText",
				DateUtil.getAlphaNumericString(101), 100);
	}

	@Test(priority = 6, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Max Length of field Sort Order is 4")
	public void Test06_1047659(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: Max Length of field Sort Order is 4");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("sortValue", DateUtil.generateRandomDigits(5), 4);
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
	}

	@Test(priority = 7, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Sort Order less than 1 is not allowed")
	public void Test07_1047667(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: Sort Order less than 1 is not allowed");
		test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", "Code" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("sortValue", "-2");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName("Enter numeric value only");
	//	test.supportDataActions.EnterRandomValueInInputField("sortValue", "0");
	//	test.supportDataActions.verifyErrorMessageForAlreadyExistingName("Enter numeric value only");
		test.supportDataActions.clickButton("cancel");
	}

	@Test(priority = 8, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: user is able to add Dispense Unit with unique Code successfully")
	public void Test08_1047637_And_1047643(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: user is able to add Dispense Unit with unique Code successfully");
		//test.supportDataActions.clickButton("cancel");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		codeValue = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterRandomValueInInputField("sortValue", "3");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(codeValue);
	}

	@Test(priority = 9, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: user is able to add Dispense Unit without providing Description")
	public void Test09_1047657(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: user is able to add Dispense Unit without providing Description");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		codeValue = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(codeValue);
	}

	@Test(priority = 10, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: user is able to select PIS from drop-down while adding Dispense Unit")
	public void Test10_1047691(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: user is able to select PIS from drop-down while adding Dispense Unit");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", "Code" + System.currentTimeMillis());
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("externalSystemKey", 1);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
	}

	@Test(priority = 11, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Error message appears for adding Dispense Unit with already existing Code")
	public void Test11_1047632(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: Error message appears for adding Dispense Unit with already existing Code");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", codeValue);
		test.supportDataActions.clickButton("save");
		test.supportDataActions
				.verifyErrorMessageForAlreadyExistingName("This Code already exists. Please provide a unique Code.");
	}

	@Test(priority = 12, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Error message appears for adding Dispense Unit with already existing Code in upper case")
	public void Test12_1047633(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: Error message appears for adding Dispense Unit with already existing Code in upper case");
		test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", codeValue.toUpperCase());
		test.supportDataActions.clickButton("save");
		test.supportDataActions
				.verifyErrorMessageForAlreadyExistingName("This Code already exists. Please provide a unique Code.");
	}

	@Test(priority = 13, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Error message appears for adding Dispense Unit with already existing Description")
	public void Test13_1047640(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: Error message appears for adding Dispense Unit with already existing Description");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", "Code" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText", descriptionForm);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.DispenseUnitName"));
	}

	@Test(priority = 14, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Error message appears for adding Dispense Unit with already existing Description in upper case")
	public void Test14_1047641(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: Error message appears for adding Dispense Unit with already existing Description in upper case");
		test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", "Code" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText", descriptionForm.toUpperCase());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.DispenseUnitName"));
	}

	@Test(priority = 15, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Dispense Unit can be added with existing Sort Order")
	public void Test15_1047680(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: Dispense Unit can be added with existing Sort Order");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		codeValue = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("sortValue", sortOrder);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(codeValue);
	}

	@Test(priority = 16, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: user is able to add Dispense Unit without providing Sort Order")
	public void Test16_1047751(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: user is able to add Dispense Unit without providing Sort Order");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		codeValue = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(codeValue);
	}

}

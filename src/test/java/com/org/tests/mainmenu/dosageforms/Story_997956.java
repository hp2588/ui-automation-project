package com.org.tests.mainmenu.dosageforms;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997956 extends BaseTest {

	String codeValue, descriptionForm;

	@Test(priority = 1, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: User is able to toggle to set a record as Active while adding Dosage Form")
	public void Test01_1040230_1040980(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Add: User is able to toggle to set a record as Active while adding Dosage Form");
		test.landingPageActions.navigateToFeature("Dosage Forms");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		test.supportDataActions.getFirstPISValue();
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.GLAccount")),
				"[ASSERTION FAILED]: Toggle Button is Not Present on Add Dosage Form");
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
	}

	@Test(priority = 2, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: User is able to toggle to set a record as Inactive while adding Dosage Form")
	public void Test02_1040249(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Add: User is able to toggle to set a record as Inactive while adding Dosage Form");
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Active", false);
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("false"));
	}

	@Test(priority = 3, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: Error message appears if user tries to add Dosage Form without providing Code")
	public void Test03_1040874(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Add: Error message appears if user tries to add Dosage Form without providing Code");
		test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Active", true);
		//test.supportDataActions.clickButton("save");
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		test.supportDataActions.clearDosagecodeInputBox("dosageFormCode");
		test.supportDataActions.verifyErrorMessageDosageCode(getData("ErrorMessage.Code"));
	}

	@Test(priority = 4, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: Code greater than length 20 is not allowed")
	public void Test04_1040875(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Add: Code greater than length 20 is not allowed");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("dosageFormCode"), 20);
	}

	@Test(priority = 5, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: Description greater than length 100 is not allowed")
	public void Test05_1040886(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Add: Description greater than length 100 is not allowed");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnTextAreaField("descriptionText"), 100);
	}

	@Test(priority = 6, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: Max Length of field Sort Order is 4")
	public void Test06_1040936(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Add: Max Length of field Sort Order is 4");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("sortValue"), 4);
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
	}

	@Test(priority = 7, description = "VPLX: Dosage Form [UI]: Dosage Form-Edit: Sort Order less than 1 is not allowed")
	public void Test07_1040941(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Edit: Sort Order less than 1 is not allowed");
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", "Code" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("sortValue", "-2");
		test.supportDataActions.verifyErrorMessageDosageCode("Enter numeric value only");
		test.supportDataActions.clickButton("cancel");
	}

	@Test(priority = 8, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: user is able to add Dosage Form with unique Code successfully")
	public void Test08_1040884_And_1040893_1045159_1041324(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Add: user is able to add Dosage Form with unique Code successfully");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		codeValue = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
	}

	@Test(priority = 9, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: user is able to add Dosage Form without providing Description")
	public void Test09_1040928(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Add: user is able to add Dosage Form without providing Description");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.verifyErrorMessageDosageCode(getData("ErrorMessage.Code"));
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", "Code" + System.currentTimeMillis());
	}

	@Test(priority = 10, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: user is able to edit PIS from drop-down while editting Dosage Form")
	public void Test10_1040966(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Edit: user is able to edit PIS from drop-down while editting Dosage Form");
		test.supportDataActions.selectValueFromDropdownByIndex("externalSystemKey", 0);
		test.supportDataActions.clickButton("save");
	}

	@Test(priority = 11, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: Error message appears for adding Dosage Form with already existing Code")
	public void Test11_1040877(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Add: Error message appears for adding Dosage Form with already existing Code");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", codeValue);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.CodeAlreadyExist"));
	}

	@Test(priority = 12, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: Error message appears for adding Dosage Form with already existing Code in upper case")
	public void Test12_1040878(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Add: Error message appears for adding Dosage Form with already existing Code in upper case");
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", codeValue.toUpperCase());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.CodeAlreadyExist"));
	}

	@Test(priority = 13, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: Error message appears for adding Dosage Form with already existing Code by putting spaces before or after the code")
	public void Test13_1040879(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Add: Error message appears for adding Dosage Form with already existing Code by putting spaces before or after the code");
		String s = " " + codeValue + " ";
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", s);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.CodeAlreadyExist"));
		test.supportDataActions.clickButton("cancel");
	}
	
	@Test(priority = 14, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: Sort Order with decimal value is not allowed")
	public void Test14_1040961(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Add: Sort Order with decimal value is not allowed");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		codeValue = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", "Code" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("sortValue", "-2");
		test.supportDataActions.verifyErrorMessageDosageCode("Enter numeric value only");
		test.supportDataActions.clickButton("cancel");
	}

}

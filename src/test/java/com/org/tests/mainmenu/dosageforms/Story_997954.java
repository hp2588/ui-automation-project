package com.org.tests.mainmenu.dosageforms;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997954 extends BaseTest {

	String codeValue, descriptionForm;
	String codeValue1, descriptionForm1;
	ArrayList<String> previous_data, sorted_data;

	@Test(priority = 1, description = "VPLX: Dosage Form [UI]: Dosage Form-View: The user is able to view the list of Dosage Form")
	public void Test01_1037798_And_1129290(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-View: The user is able to view the list of Dosage Form");
		test.landingPageActions.navigateToFeature("Dosage Forms");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		codeValue = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		codeValue = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(codeValue);
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonOnDosageForm("toggle"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Code"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Description"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Sort Order"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Status"));
		Assert.assertTrue(test.supportDataActions.verifyDropdownOnDosageForm());
	}

	@Test(priority = 2, description = "VPLX: Dosage Form [UI]: Dosage Form-View: The list of Dosage Form displays only the active ones by default")
	public void Test02_1037826_1045195_1044927(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-View: The list of Dosage Form displays only the active ones by default");
		test.supportDataActions.verifyDosageFormsStatusAsActive();
	}

	@Test(priority = 3, description = "VPLX: Dosage Form [UI]: Dosage Form-View: The user is able to toggle and include the inactive Dosage Form in the listing")
	public void Test03_1037850(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-View: The user is able to toggle and include the inactive Dosage Form in the listing");
		test.supportDataActions.clickToggleButton("true", "toggle");
		AddInactiveDosageForm();
		Assert.assertTrue(test.supportDataActions.verifyDosageFormsStatusAsInactive());
	}

	private void AddInactiveDosageForm() {
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		codeValue1 = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		descriptionForm1 = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		codeValue1 = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		test.supportDataActions.clickToggleButton("false", "activeFlag");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(codeValue);
	}

	@Test(priority = 4, description = "VPLX: Dosage Form [UI]: Dosage Form-View: The combined list of active & inactive Dosage Form is displayed in alphanumeric order")
	public void Test04_1037859_1037839_1037853(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-View: The combined list of active & inactive Dosage Form is displayed in alphanumeric order");
		test.supportDataActions.clickToggleButton("false", "toggle");
		test.supportDataActions.verifyDosageFormsStatusAsActive();
		test.supportDataActions.clickToggleButton("true", "toggle");
		Assert.assertTrue(test.supportDataActions.verifyDosageFormsStatusAsInactive());
	}
	
	@Test(priority = 5, description = "VPLX: Dosage Form [UI]: Search works correctly on search screen for unmatched results")
	public void Test05_1045330(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Search works correctly on search screen for unmatched results");
		test.supportDataActions.enterSearchTermInSearchField(DateUtil.getTommorrowsDate(), "search");
		Assert.assertTrue(test.supportDataActions.verifyRecordListEmpty());
		test.supportDataActions.resetSearch();
	}

	@Test(priority = 6, description = "VPLX: Dosage Form [UI]: Dosage Form-View: The default PIS is the first PIS in the drop-down")
	public void Test06_1037885(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-View: The default PIS is the first PIS in the drop-down");
		test.supportDataActions.getFirstPISValue();
	}

	@Test(priority = 7, description = "VPLX: Dosage Form [UI]: Dosage Form-View: The user is able to select PIS from drop-down and result is displayed according to PIS selected")
	public void Test07_1037881(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-View: The user is able to select PIS from drop-down and result is displayed according to PIS selected");
		test.supportDataActions.getFirstPISValue();
		test.supportDataActions.selectPISFromDropdown(3);
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		codeValue = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		codeValue = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(codeValue);
	}

}

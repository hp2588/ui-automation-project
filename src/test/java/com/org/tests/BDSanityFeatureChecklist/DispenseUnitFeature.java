package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class DispenseUnitFeature extends BaseTest {
	String codeValue, descriptionForm, sortOrder;
	ArrayList<String> previous_data, sorted_data;
	String dispenseCode, dispenseCode1, description, Inactive_code, dropDown_external_system;
	String description1, facilityOnWFAScreen, ISAName, itemID, dispenseUsedElseWhere,externalSystem;

	/*
	 * ==================================DISPENSE UNIT -
	 * VIEW==============================================
	 */

	@Test(priority = 1, description = "VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to view the list of Dispense Unit")
	public void Test01_1046972(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to view the list of Dispense Unit");
		test.landingPageActions.navigateToFeature("Dispense Units");
		externalSystem=test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("externalSystems", 0);
		test.supportDataActions.clickOnAddButtonToAddNewRecord1("Add Dispense Unit");
		codeValue = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterRandomValueInInputField("sortValue", "3");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(codeValue);
		test.supportDataActions.codeListDosageForms("1");
		test.supportDataActions.descriptionFormDosageForms("2");
		test.supportDataActions.sortOrderDispenseUnit();
		test.supportDataActions.verifyDispenseUnitStatusAsActive();
		test.supportDataActions.verifyEditActionDosageForms("5");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonOnDosageForm("toggle"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Code"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Description"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Sort Order"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Status"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Actions"));
		Assert.assertTrue(test.supportDataActions.verifyDropdownOnDosageForm());

	}

	@Test(priority = 2, description = "VPLX: Dispense Unit [UI]: Dispense Unit-View: The list of Dispense Unit displays only the active ones by default")
	public void Test02_1046980(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to view the list of Dispense Unit");
		test.supportDataActions.verifyDispenseUnitStatusAsActive();
	}

	@Test(priority = 3, description = "VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to toggle to exclude the inactive Dispense Unit in the listing")
	public void Test03_1046981(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to toggle to exclude the inactive Dispense Unit in the listing");
		test.supportDataActions.clickToggleButton("false", "toggle");
		test.supportDataActions.verifyDispenseUnitStatusAsActive();

	}

	@Test(priority = 4, description = "VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to toggle and include the inactive Dispense Unit in the listing")
	public void Test04_1046982(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to toggle and include the inactive Dispense Unit in the listing");
		test.supportDataActions.clickToggleButton("true", "toggle");
		Assert.assertFalse(test.supportDataActions.verifyDispenseUnitStatusAsInactive());

	}

	@Test(priority = 5, description = "VPLX: Dispense Unit [UI]: Dispense Unit-View: The list of Dispense Unit is sorted alphanumerically in ascending order of the field Name by default")
	public void Test05_1046991(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-View: The list of Dispense Unit is sorted alphanumerically in ascending order of the field Name by default");
		test.supportDataActions.clickToggleButton("false", "toggle");
		previous_data = test.supportDataActions.captureDataForParticularColumnDosageForm("1");
		System.out.println("Previous data :  " + previous_data);
		sorted_data = test.supportDataActions.sortDataForParticularColumnInAscendingOrderDosageForm("1");
		System.out.println("Sorted data :  " + sorted_data);
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Code column data is not sorted in ascending order");
	}

	@Test(priority = 6, description = "VPLX: Dispense Unit [UI]: Dispense Unit-View: The combined list of active & inactive Dispense Unit is displayed in alphanumeric order")
	public void Test06_1046998(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-View: The combined list of active & inactive Dispense Unit is displayed in alphanumeric order");
		test.supportDataActions.clickToggleButton("true", "toggle");
		previous_data = test.supportDataActions.captureDataForParticularColumnDosageForm("1");
		System.out.println("Previous data :  " + previous_data);
		sorted_data = test.supportDataActions.sortDataForParticularColumnInAscendingOrderDosageForm("1");
		System.out.println("Sorted data :  " + sorted_data);
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Code column data is not sorted in ascending order");
		;
	}

	@Test(priority = 7, description = "VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to select PIS from drop-down and result is displayed according to PIS selected")
	public void Test07_1047002(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to select PIS from drop-down and result is displayed according to PIS selected");
		test.supportDataActions.clickToggleButton("false", "toggle");
		test.supportDataActions.getFirstPISValue();
		test.supportDataActions.selectPISFromDropdown(1);
		test.supportDataActions.codeListDosageForms("1");
		test.supportDataActions.descriptionFormDosageForms("2");
		test.supportDataActions.sortOrderDispenseUnit();
		test.supportDataActions.verifyDispenseUnitStatusAsActive();
		test.supportDataActions.verifyEditActionDosageForms("5");
	}

	@Test(priority = 8, description = "VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to sort each column of list of Dispense Unit")
	public void Test08_1046995_1129450(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to sort each column of list of Dispense Unit");
		previous_data = test.supportDataActions.captureDataForParticularColumnDosageForm("2");
		System.out.println("Previous data :  " + previous_data);
		sorted_data = test.supportDataActions.sortDataForParticularColumnInAscendingOrderDosageForm("2");
		System.out.println("Sorted data :  " + sorted_data);
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Description column data is not sorted in ascending order");
	}

	/*
	 * ====================================DISPENSE UNIT -
	 * SEARCH===========================================
	 */

	@Test(priority = 9, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Search: The user is able to search Dispense Unit on basis of columns- Code ,Description Form,Sort Order,Status")
	public void Test09_1047034(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Search: The user is able to search Dispense Unit on basis of columns- Code ,Description Form,Sort Order,Status");

		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		dispenseCode = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"NewCode" + System.currentTimeMillis());
		description = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "9999");

		test.supportDataActions.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchFieldGl(dispenseCode, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(dispenseCode, "1"));

		test.supportDataActions.clearSearchBox("search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(description, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(description, "2"));

		test.supportDataActions.clearSearchBox("search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(sortOrder, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(sortOrder, "3"));
		test.supportDataActions.clearSearchBox("search");

	}

	@Test(priority = 10, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Search: The Dispense Unit search displays the message \"No matches found\" if the search does not return any results")
	public void Test10_1047049(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Search: The Dispense Unit search displays the message \"No matches found\" if the search does not return any results");
		test.supportDataActions.clearSearchBox("search");
		test.supportDataActions.enterSearchTermInSearchField(DateUtil.getTommorrowsDate(), "search");
		Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Matching Results.");

		Assert.assertTrue(test.supportDataActions.verifySearchCrossIcon());
	}

	@Test(priority = 11, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Search: The Dispense Unit search is reset on clicking 'x' inside search box")
	public void Test11_1047040(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Search: The Dispense Unit search is reset on clicking 'x' inside search box");
		test.supportDataActions.clearSearchBox("search");
	}

	/*
	 * ==========================DISPENSE UNIT
	 * -ADD=========================================================
	 */

	@Test(priority = 12, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: User is able to toggle to set a record as Active while adding Dispense Unit")
	public void Test12Check_1047622(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: User is able to toggle to set a record as Active while adding Dispense Unit");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.GLAccount")),
				"[ASSERTION FAILED]: Toggle Button is Not Present on Add Dosage Form");
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
	}

	@Test(priority = 13, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: User is able to toggle to set a record as Inactive while adding Dispense Unit")
	public void Test13Check_1047625(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: User is able to toggle to set a record as Inactive while adding Dispense Unit");
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Active", false);
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("false"));

		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Active", true);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.Code"));
	}

	@Test(priority = 14, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Code greater than length 20 is not allowed")
	public void Test14_1047630(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: Code greater than length 20 is not allowed");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("dispenseUnitCode"), 20);
	}

	@Test(priority = 15, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Description greater than length 100 is not allowed")
	public void Test15_1047638(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: Description greater than length 100 is not allowed");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnTextAreaField("descriptionText"), 100);
	}

	@Test(priority = 16, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Max Length of field Sort Order is 4")
	public void Test16_1047659(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: Max Length of field Sort Order is 4");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("sortValue"), 4);
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
	}

	@Test(priority = 17, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: user is able to add Dispense Unit without providing Description")
	public void Test17_1047657(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: user is able to add Dispense Unit without providing Description");
		test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", "Code" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("sortValue", "-2");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName("Enter numeric value only");
		test.supportDataActions.clickButton("cancel");

		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		codeValue = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterRandomValueInInputField("sortValue", "3");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(codeValue);

		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.Code"));
		test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", "Code" + System.currentTimeMillis());
	}

	@Test(priority = 18, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Error message appears for adding Dispense Unit with already existing Code")
	public void Test18_1047632(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: Error message appears for adding Dispense Unit with already existing Code");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", "Code" + System.currentTimeMillis());
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("externalSystemKey", 1);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));

		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", codeValue);
		test.supportDataActions.clickButton("save");
		test.supportDataActions
				.verifyErrorMessageForAlreadyExistingName("This Code already exists. Please provide a unique Code.");
	}

	@Test(priority = 19, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Error message appears if user tries to add Dispense Unit without providing Code")
	public void Test19_1047628(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: Error message appears if user tries to add Dispense Unit without providing Code");
		test.siteConfigurationAction.clearInputBox("dispenseUnitCode");
		test.supportDataActions.clickButton("save");
		test.siteConfigurationAction.verifyErrorMessageForBlankField("Code cannot be empty");

	}

	@Test(priority = 19, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Error message appears for adding Dispense Unit with already existing Code in upper case")
	public void Test19_1047633(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: Error message appears for adding Dispense Unit with already existing Code in upper case");
		test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", codeValue.toUpperCase());
		test.supportDataActions.clickButton("save");
		test.supportDataActions
				.verifyErrorMessageForAlreadyExistingName("This Code already exists. Please provide a unique Code.");
	}

	@Test(priority = 20, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Error message appears for adding Dispense Unit with already existing Description")
	public void Test20_1047640(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: Error message appears for adding Dispense Unit with already existing Description");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", "Code" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText", descriptionForm);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.DispenseUnitName"));
	}

	@Test(priority = 21, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Error message appears for adding Dispense Unit with already existing Description in upper case")
	public void Test21_1047641(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Add: Error message appears for adding Dispense Unit with already existing Description in upper case");
		test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", "Code" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText", descriptionForm.toUpperCase());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.DispenseUnitName"));
	}

	@Test(priority = 22, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: Dispense Unit can be added with existing Sort Order")
	public void Test22_1047680(Method method) {
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

	@Test(priority = 23, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Add: user is able to add Dispense Unit without providing Sort Order")
	public void Test23_1047751(Method method) {
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

	/*
	 * ==============================DISPENSE UNIT -
	 * EDIT=============================================
	 */

	@Test(priority = 24, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Sort Order greater than 9999 is not allowed")
	public void Test24_1047833(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Sort Order greater than 9999 is not allowed");

		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		dispenseCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		description = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "2");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));
		// test.supportDataActions.enterSearchTermInSearchFieldGl(dispenseCode,
		// "search");
		// test.supportDataActions.refreshPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord(dispenseCode, "Edit Dispense Unit");
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Active", false);
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("false"));

		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("dispenseUnitCode"), 20);

		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnTextAreaField("descriptionText"), 100);

		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("sortValue"), 4);
		description = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
	}

	@Test(priority = 25, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: user is able to edit Dispense Unit with unique Code successfully")
	public void Test25_1047790(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: user is able to edit Dispense Unit with unique Code successfully");
		test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", "Code" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("sortValue", "-2");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName("Enter numeric value only");
		test.supportDataActions.clickButton("cancel");

		// test.supportDataActions.refreshPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord(dispenseCode, "Edit Dispense Unit");
		dispenseCode = test.siteConfigurationAction.enterRandomValueInInputField("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dispenseCode);

	}

	@Test(priority = 26, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: user is able to edit Dispense Unit with unique Description successfully")
	public void Test26_1047800(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: user is able to edit Dispense Unit with unique Description successfully");
		test.supportDataActions.refreshPage();
		// test.supportDataActions.enterSearchTermInSearchFieldGl(dispenseCode,
		// "search");
		// Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(dispenseCode,
		// "1"));
		test.supportDataActions.clickEditLink(dispenseCode);
		/*
		 * test.supportDataActions.selectValueFromDropDownForDosagePISSystem(
		 * "SelectPISType1", getData("DosageForm.SelectPISType2"));
		 */ test.supportDataActions.selectValueFromDropdownByIndex("externalSystemKey", 2);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));

		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		dispenseCode = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		description = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "2");
		test.supportDataActions.clickButton("save");
		// test.supportDataActions.enterSearchTermInSearchFieldGl(dispenseCode,
		// "search");

		test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord(dispenseCode, "Edit Dispense Unit");
		description = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description1" + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));

	}

	@Test(priority = 27, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: user is able to edit Dispense Unit without providing Description")
	public void Test27_1047830(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: user is able to edit Dispense Unit without providing Description");
		// test.supportDataActions.enterSearchTermInSearchFieldGl(dispenseCode,
		// "search");

		test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord(dispenseCode, "Edit Dispense Unit");
		dispenseCode = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		test.supportDataActions.clearDosageDescriptionBox("descriptionText");
		test.supportDataActions.clickButton("save");

		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));

	}

	@Test(priority = 28, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Error message appears if user tries to edit Dispense Unit by deleting existing Code")
	public void Test28_1047781(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Error message appears if user tries to edit Dispense Unit by deleting existing Code");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		dispenseCode = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		description = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "2");
		test.supportDataActions.clickButton("save");
		// test.supportDataActions.enterSearchTermInSearchFieldGl(dispenseCode,
		// "search");
		// test.supportDataActions.refreshPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord(dispenseCode, "Edit Dispense Unit");
		test.supportDataActions.clearDosagecodeInputBox("dispenseUnitCode");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.Code"));

		test.supportDataActions.clickButton("cancel");
	}

	// Bug-1109091

	@Test(priority = 29, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Error message appears on editting Dispense Unit with already existing Code")
	public void Test29_1047786(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Error message appears on editting Dispense Unit with already existing Code");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		dispenseCode1 = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		description1 = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", getData("DosageForm.SortOrder"));
		// test.supportDataActions.selectValueFromDropDownForDosagePISSystem("SelectPISType1",
		// getData("DosageForm.SelectPISType2"));
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dispenseCode1);
		// test.supportDataActions.enterSearchTermInSearchFieldGl(dispenseCode,
		// "search");
		// Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(dispenseCode,
		// "1"));
		test.supportDataActions.clickEditLink(dispenseCode);
		test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", dispenseCode1);
		test.supportDataActions.clickButton("save");
		// Thread.sleep(10000);
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.CodeAlreadyExist"));
	}

	@Test(priority = 30, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Error message appears on editting Dispense Unit with already existing Code")
	public void Test30_1047789(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Error message appears on editting Dispense Unit with already existing Code");
		{
			test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", " " + dispenseCode1 + " ");
			test.supportDataActions.clickButton("save");
			// Thread.sleep(10000);
			test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.CodeAlreadyExist"));
			test.siteConfigurationAction.clickButton("cancel");

		}
	}

	
	/*=================Automated========================*/
	
	@Test(priority = 31, description = "VPLX: Dispense Unit [UI] [Integration]: Dispense Unit-Edit: User is not able to toggle to set a record as Inactive while editing Dispense Unit if it is being used elsewhere in the system")
	public void Test31_1047849(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI] [Integration]: Dispense Unit-Edit: User is not able to toggle to set a record as Inactive while editing Dispense Unit if it is being used elsewhere in the system");
		/*test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		facilityOnWFAScreen = test.siteConfigurationAction.getFacilityFromISAScreen();

		ISAName = test.storageAreaAction.getISANameOnWFAScreen();

		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();*/

		test.landingPageActions.navigateToMenu("Item Management");
		

		test.siteConfigurationAction.enterRandomValueInRichInputField(externalSystem);
		//test.siteConfigurationAction.selectFacilityDropDownOnItemManagement(facilityOnWFAScreen);
		// test.siteConfigurationAction.selectFacilityDropDownOnItemManagement("Fac1588002935976");
		// test.siteConfigurationAction.clickEditLinkOnItemManagement("ItemID1588005335184");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		// test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New
		// Item");
		// test.siteConfigurationAction.enterDataInInputField("genericName","Systemlevelfacilitybb");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");

		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		
		dispenseUsedElseWhere = test.siteConfigurationAction
				.selectAndReturnValueFromDropDownByIndex("dispensingUnitKey", 1);
		
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacilityx" + System.currentTimeMillis());
		
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem77x" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));
		test.siteConfigurationAction.clickButton("cancel");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Dispense Units");
		test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord(dispenseUsedElseWhere, "Edit Dispense Unit");

		test.siteConfigurationAction.clickActiveToggle("Active");
		test.siteConfigurationAction.verifyToggleIsInActive("activeFlag");
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName("This Dispense Unit is associated with an Item. You cannot modify this Dispense Unit.");
		
		
	}

}

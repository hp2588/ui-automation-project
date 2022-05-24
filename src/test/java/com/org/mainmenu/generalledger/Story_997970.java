package com.org.mainmenu.generalledger;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997970 extends BaseTest {

	String glAccountName, glAccountNumber, externalSystem;
	String[] external_sys_checkboxes_id = { "pisProvidesMedClassFlag", "pisProvidesTherapeuticClassFlag",
			"allowPISItemEditFlag", "editExternalScanCodeLinksFlag", "ignorePISItemDeleteFlag",
			"ignorePISItemUpdateFlag" };

	@Test(priority = 1, description = "VPLX: GL Account: [UI] : Pop-up appears when user clicks on Add button")
	public void Test01_1037857(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : Pop-up appears when user clicks on Add button");
		test.landingPageActions.navigateToFeature("General Ledger");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add General Ledger");
	}

	@Test(priority = 2, description = "VPLX: GL Account: [UI] : Two fields AddName AddNumber fields are present in pop-up appears when user clicks on Add button")
	public void Test02_1037860(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : Two fields AddName AddNumber fields are present in pop-up appears when user clicks on Add button");
		test.supportDataActions.verifyInputFieldOnAddPopup("description");
		Assert.assertTrue(test.supportDataActions.verifyInputFieldIsBlank("description"),
				"[ASSERTION FAILED]: Input Field  is not blank by default");
		test.supportDataActions.verifyInputFieldOnAddPopup("number");
		Assert.assertTrue(test.supportDataActions.verifyInputFieldIsBlank("number"),
				"[ASSERTION FAILED]: Input Field is not blank by default");
		test.supportDataActions.verifyFieldIsMandatoryGL("description");
		test.supportDataActions.verifyFieldIsMandatoryGL("number");
	
	
	}

	@Test(priority = 3, description = "VPLX: GL Account: [UI] : Toggle for Active - Inactive/Active toggle is present in pop-up appears when user clicks on Add/Edit button")
	public void Test03_1037867(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : Toggle for Active - Inactive/Active toggle is present in pop-up appears when user clicks on Add button");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.GLAccount")),
				"[ASSERTION FAILED]: Toggle Button is Not Present");
	}

	@Test(priority = 4, description = "GL Account: [UI] : User is able to Enter Alphanumeric value (upto 80 characters) in text 'Name' field on Add/Edit page")
	public void Test04_1037875(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : User is able to Enter name in Add name field as Alphanumeric data present in pop-up appears when user clicks on Add button");
		glAccountName = test.supportDataActions.EnterRandomValueInInputField("description",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("description"), 80,
				"[ASSERTION FAILED]: Max Length for input field Name is not 80");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("description", DateUtil.getAlphaNumericString(81),80);
	}

	@Test(priority = 5, description = "VPLX: GL Account: [UI] : User is able to Enter numneric in Add number field as Interger present in pop-up appears when user clicks on Add button")
	public void Test05_1037878(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : User is able to Enter numneric in Add number field as Interger present in pop-up appears when user clicks on Add button");
		glAccountNumber = test.supportDataActions.EnterRandomValueInInputField("number",
				getData("GLAccountDetails.GLAccountDetailsNumberWithSpecialCharacters"));

	}

	@Test(priority = 7, description = "VPLX: Manage GL Account: [UI] : Warning must appear if user is using already existing Account name")
	public void Test07_1039601(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage GL Account: [UI] : Warning must appear if user is using already existing Account name");
		glAccountName = test.supportDataActions.EnterRandomValueInInputField("description",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		glAccountNumber = test.supportDataActions.EnterRandomValueInInputField("number",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		test.supportDataActions.selectValueFromDropDownForPISSystem("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(glAccountName);
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add General Ledger");
		test.supportDataActions.EnterRandomValueInInputField("description", glAccountName);
		test.supportDataActions.EnterRandomValueInInputField("number",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		test.supportDataActions.selectValueFromDropDownForPISSystem("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.clickButton("save");
		test.supportDataActions
				.verifyErrorMessageForAlreadyExistingRecord(getData("GLAccountDetails.ErrorMsg_DuplicateName"));
		test.supportDataActions.clickButton("cancel");
	}

	@Test(priority = 8, description = "VPLX: Manage GL Account: [UI] : Warning must appear if user is using already existing Account NUmber")
	public void Test08_1039602(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage GL Account: [UI] : Warning must appear if user is using already existing Account NUmber");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add General Ledger");
		test.supportDataActions.EnterRandomValueInInputField("description",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		test.supportDataActions.EnterValueInInputField("number", glAccountNumber);
		test.supportDataActions.selectValueFromDropDownForPISSystem("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingRecord(
				getData("GLAccountDetails.ErrorMsg_DuplicateAccountNumber"));
		test.supportDataActions.clickButton("cancel");

	}

	@Test(priority = 9, description = "VPLX: GL Account: [UI] : Cancel button to discard changes is present in pop-up appears when user clicks on Add button")
	public void Test09_1039604(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : Cancel button to discard changes is present in pop-up appears when user clicks on Add button");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add General Ledger");
		test.supportDataActions.EnterRandomValueInInputField("description",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("number",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		test.supportDataActions.selectValueFromDropDownForPISSystem("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.verifyAddGLAccountPopupGetsClosedOnClickingCancelButton();

	}

	@Test(priority = 10, description = "VPLX: GL Account: [UI] : Check more than Max length : 25 characters is not allowed for Account number")
	public void Test10_1039630(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : Check more than Max length : 25 characters is not allowed for Account number");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add General Ledger");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("number"), 25,
				"[ASSERTION FAILED]: Max Length for input field Hold Reason Name is not 25");
		test.supportDataActions.clickButton("cancel");

	}

	@Test(priority = 11, description = "VPLX: GL Account: [UI] : Check more than Max length : 80 characters is not allowed for Account Name")
	public void Test11_1039684(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : Check more than Max length : 80 characters is not allowed for Account Name");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add General Ledger");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("description"), 80,
				"[ASSERTION FAILED]: Max Length for input field Hold Reason Name is not 80");
		test.supportDataActions.clickButton("cancel");

	}

	@Test(priority = 12, description = "VPLX: Manage GL Account: [UI] : Save Button is disabled until user Enters all the mandatory details or correct details")
	public void Test12_1040221(Method method) {
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add General Ledger");
		test.supportDataActions.verifyInputFieldOnAddPopup("description");
		test.supportDataActions.verifyInputFieldOnAddPopup("number");
		test.supportDataActions.clickButton("save");
		Assert.assertFalse(test.supportDataActions.verifyButtonIsEnabledOrDisabled("save"));
		test.supportDataActions.clickButton("cancel");

	}

	@Test(priority = 13, description = "VPLX: Manage GL Account [UI]: GL Account-Add: popup appears on adding GL Account with spaces only")
	public void Test13_1045846(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage GL Account [UI]: GL Account-Add: popup appears on adding GL Account with spaces only");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add General Ledger");
		test.supportDataActions.verifyInputFieldOnAddPopup("description");
		test.supportDataActions.verifyInputFieldOnAddPopup("number");
		test.supportDataActions.EnterValueInInputField("description", "    ");
		test.supportDataActions.EnterValueInInputField("number", "     ");
		Assert.assertFalse(test.supportDataActions.verifyButtonIsEnabledOrDisabled("save"));
		test.supportDataActions.clickButton("cancel");
	}

}

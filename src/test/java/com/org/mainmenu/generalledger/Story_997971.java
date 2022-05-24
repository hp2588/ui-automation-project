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

public class Story_997971 extends BaseTest {
	String glAccountName, glAccountName_old, glAccountNumber_old, glAccountNumber, externalSystem;
	String[] external_sys_checkboxes_id = { "pisProvidesMedClassFlag", "pisProvidesTherapeuticClassFlag",
			"allowPISItemEditFlag", "editExternalScanCodeLinksFlag", "ignorePISItemDeleteFlag",
			"ignorePISItemUpdateFlag" };

	
	@Test(priority = 1, description = "VPLX: GL Account: [UI] : GL Account number can be updated and saved again")
	public void Test01_1040294(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : GL Account number can be updated and saved again");
		test.landingPageActions.navigateToFeature("General Ledger");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add General Ledger");
		glAccountName = test.supportDataActions.EnterRandomValueInInputField("description",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		glAccountNumber = test.supportDataActions.EnterRandomValueInInputField("number",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		test.supportDataActions.selectValueFromDropDownForPISSystem("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.clickButton("save");
		test.siteConfigurationAction.enterSearchTermInSearchField(glAccountName, "search");
		test.supportDataActions.clickAddButtonOnDistributor(glAccountName);
		//test.supportDataActions.clickEditLink(glAccountName);
		test.supportDataActions.verifyInputFieldOnAddPopup("description");
		test.supportDataActions.verifyInputFieldOnAddPopup("number");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("number"), 25,
				"[ASSERTION FAILED]: Max Length for input field Hold Reason Name is not 25");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("number", DateUtil.getAlphaNumericString(26),25);
		glAccountNumber = test.supportDataActions.EnterRandomValueInInputField("number",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		//test.supportDataActions.verifyNewlyAddedRecordNameInList(glAccountNumber);
	}

	@Test(priority = 3, description = "VPLX: GL Account: [UI] : If any mandatory field is left empty,the following message must be displayed when user tries to save- <Field Name> cannot be empty")
	public void Test03_1040339(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : If any mandatory field is left empty,the following message must be displayed when user tries to save- <Field Name> cannot be empty");
		//test.supportDataActions.clickEditLink(glAccountName);
		test.siteConfigurationAction.enterSearchTermInSearchField(glAccountName, "search");
		test.supportDataActions.clickAddButtonOnDistributor(glAccountName);
		test.supportDataActions.verifyInputFieldOnAddPopup("description");
		test.supportDataActions.clearInputBox("description");
		test.supportDataActions.verifyInputFieldOnAddPopup("number");
		test.supportDataActions.clearInputBox("number");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageonAlert(getData("GLAccountDetails.ErrorMsg_Name"), "description");
		test.supportDataActions.verifyErrorMessageonAlert(getData("GLAccountDetails.ErrorMsg_AccountNumber"), "number");
		Assert.assertFalse(test.supportDataActions.verifyButtonIsEnabledOrDisabled("save"));
		test.supportDataActions.clickButton("cancel");
	}

	@Test(priority = 4, description = "VPLX: GL Account: [UI] : Check more than Max length : 25 characters is not allowed for Account number in edit")
	public void Test04_1040348(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : Check more than Max length : 25 characters is not allowed for Account number in edit");
		test.siteConfigurationAction.enterSearchTermInSearchField(glAccountName, "search");
		test.supportDataActions.clickAddButtonOnDistributor(glAccountName);
		//test.supportDataActions.clickEditLink(glAccountName);
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("number"), 25,
				"[ASSERTION FAILED]: Max Length for input field Hold Reason Name is not 25");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("number", DateUtil.getAlphaNumericString(26),25);

	}

	@Test(priority = 5, description = "VPLX: GL Account: [UI] : Check more than Max length : 80 characters is not allowed for Account Name in edit")
	public void Test05_1040356(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : Check more than Max length : 80 characters is not allowed for Account Name in edit");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("description"), 80,
				"[ASSERTION FAILED]: Max Length for input field Hold Reason Name is not 80");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("description", DateUtil.getAlphaNumericString(81),80);
		test.supportDataActions.clickButton("cancel");
	}

	@Test(priority = 6, description = "VPLX: GL Account: [UI] : GL Account status(Active/Inactive) can be updated from Toggle")
	public void Test06_1040306(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : GL Account status(Active/Inactive) can be updated from Toggle");
		test.siteConfigurationAction.enterSearchTermInSearchField(glAccountName, "search");
		test.supportDataActions.clickAddButtonOnDistributor(glAccountName);
		//test.supportDataActions.clickEditLink(glAccountName);
		test.supportDataActions.verifyInputFieldOnAddPopup("description");
		test.supportDataActions.verifyInputFieldOnAddPopup("number");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.GLAccount"));
		test.supportDataActions.selectValueFromDropDownForPISSystem("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(glAccountName, "search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(glAccountName);
	}

}
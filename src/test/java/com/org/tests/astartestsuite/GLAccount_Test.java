package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class GLAccount_Test extends BaseTest{
	String glAccountName, glAccountName_old, glAccountNumber_old, glAccountNumber, externalSystem;
	String[] external_sys_checkboxes_id = { "pisProvidesMedClassFlag", "pisProvidesTherapeuticClassFlag",
			"allowPISItemEditFlag", "editExternalScanCodeLinksFlag", "ignorePISItemDeleteFlag",
			"ignorePISItemUpdateFlag" };
	
	@Test(priority = 1, description = " VPLX : Support Data: Authorized user is allowed to edit a GL Account's attributes")
	public void Test01_1114530(Method method) {
		ExtentTestManager.startTest(method.getName()," VPLX : Support Data: Authorized user is allowed to edit a GL Account's attributes");
	test.landingPageActions.navigateToFeature("General Ledger");
	test.supportDataActions.clickOnAddButtonToAddNewRecord("Add General Ledger");
	test.supportDataActions.selectPISValueFromDropDownForGLAccount("externalSystemKey",TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
	glAccountName = test.supportDataActions.EnterRandomValueInInputField("description",
			getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
	glAccountNumber = test.supportDataActions.EnterRandomValueInInputField("number",
			getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
	test.supportDataActions.clickButton("save");
	test.supportDataActions.clickEditLink(glAccountName);
	glAccountName = test.supportDataActions.EnterRandomValueInInputField("description",
			getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
	glAccountNumber = test.supportDataActions.EnterRandomValueInInputField("number",
			getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
	test.supportDataActions.clickButton("save");
	test.supportDataActions.verifyNewlyAddedRecordNameInList(glAccountName);
	test.supportDataActions.verifyNewlyAddedRecordNameInList(glAccountNumber);
	}
}

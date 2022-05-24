package com.org.tests.integrationSuite1;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class IntGlAccountTest extends BaseTest {
	
String glAccountName, glAccountNumber, externalSystem;
	
	@Test(priority = 1, description = "VPLX: GL Account: [UI] : GL account is saved when user enter all the details and Click on Save button")
	public void Test01_GLAccount(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : GL account is saved when user enter all the details and Click on Save button");
		test.landingPageActions.navigateToFeature("General Ledger");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add General Ledger");
		glAccountName = test.supportDataActions.EnterRandomValueInInputField("description",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		glAccountNumber = test.supportDataActions.EnterRandomValueInInputField("number",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		test.supportDataActions.selectValueFromDropDownForPISSystem("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.clickButton("save");
		// test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(glAccountName);
		TestDataPropertyReaderAndWriter.setProperty("GlAccountName", glAccountName);
		TestDataPropertyReaderAndWriter.setProperty("GlAccountName", glAccountName);
	
	}

}

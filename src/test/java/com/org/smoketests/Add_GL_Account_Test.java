package com.org.smoketests;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Add_GL_Account_Test extends BaseTest {
	
	String glAccountName, glAccountNumber;

	@Test(priority = 1, description = "VPLX: GL Account: [UI] : User is able add GL Account")
	public void Test01_Add_GL_Account(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX: GL Account: [UI] : User is able add GL Account");
		
		test.landingPageActions.navigateToFeature("General Ledger Setup (GL)");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add General Ledger Account");
		
		glAccountName = test.supportDataActions.EnterRandomValueInInputField("description",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		glAccountNumber = test.supportDataActions.EnterRandomValueInInputField("number",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(glAccountName);

	}

}

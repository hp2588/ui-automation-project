package com.org.data.packageshareapi;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class GLAccountTest extends BaseTest {
	
	String old_data, new_data;
	String glAccountName, glAccountNumber; 
	
	@Test(priority = 1, description = "VPLX: GL Account: [UI] : Add GL Account Test")
	public void Test01_1038440(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : Record is displaying when user search the data on search screen");
		test.landingPageActions.navigateToFeature("General Ledger");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add General Ledger");
		glAccountName = test.supportDataActions.EnterRandomValueInInputField("description",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		glAccountNumber = test.supportDataActions.EnterRandomValueInInputField("number",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		test.supportDataActions.selectValueFromDropDownForPISSystem("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchFieldGl(glAccountName, getData("SearchFieldValue.GlAccount"));
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(glAccountName, new_data);
	}

}

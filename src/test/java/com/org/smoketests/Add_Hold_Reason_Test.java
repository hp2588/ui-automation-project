package com.org.smoketests;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Add_Hold_Reason_Test extends BaseTest{

	String holdReasonName;
	
	@Test(priority = 1, description = "VPLX:Hold Reason: UI: Verify User is able to Add Hold Reason")
	public void Test01_Add_Hold_Reason(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI: Verify User is able to Add Hold Reason");
		test.landingPageActions.navigateToFeature("Hold Reasons");
		test.siteConfigurationAction.clickOnAddButtonToAddParticular("Add Hold Reason");
		holdReasonName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("descriptionText", getData("HoldReasonDetails.HoldReasonName")+System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInTextAreaField("holdReasonSummaryText",getData("HoldReasonDetails.HoldReasonName")+System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(holdReasonName);
	}  
}

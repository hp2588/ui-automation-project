
package com.org.tests.mainmenu.holdreasons;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_998001 extends BaseTest {
	String holdReasonName_old, holdReasonName_new;
	String holdReasonName;
	
	@Test(priority = 1, description = "VPLX: Hold Reason UI: Search: The Hold Reasons "
			+ "can be searched successfully by Name")
	public void Test01_1027588(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"To verify,  Hold Reason is searched successfully on the basis of Name");
		
		test.landingPageActions.navigateToFeature("Hold Reasons");
		test.supportDataActions.clickOnAddButtonToAddNewRecord(getData("HoldReasonDetails.Add_label").trim());
		holdReasonName = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.HoldReason"));
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(holdReasonName);
		
		holdReasonName_old = test.supportDataActions.getColumnFirstData("1");
		test.supportDataActions.enterSearchTermInSearchField(holdReasonName_old, "search");
		holdReasonName_new = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(holdReasonName_new, holdReasonName_old);
	}
	
	
	@Test(priority = 2, description = "VPLX: Hold Reason UI: Search: The Hold Reason is not found "
			+ "when searching with a Name that does not exists")
	public void Test02_1027590(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:HoldReason: UI - Search a Hold Reason on the basis of Name that does not exists in DB");
		
		holdReasonName_old = test.supportDataActions.getColumnFirstData("1");
		test.supportDataActions.enterSearchTermInSearchField(holdReasonName_old.concat(DateUtil.getTommorrowsDate()),
				"search");
		test.supportDataActions.pressKeyUsingAction(Keys.ENTER);
		String holdReasonName_error = test.supportDataActions.getNoDataText();
		Assert.assertEquals(holdReasonName_error, getData("HoldReasonDetails.Error_NoData"));
		test.supportDataActions.resetSearch();
	}
	
	
	@Test(priority = 3, description = "VPLX: Hold Reason: UI: Search: The Hold Reasons can be "
			+ "searched by an existing Description")
	public void Test03_1029437(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - Hold Reason is found  on searching on the basis of Description that exists");
		
		String holdReasonDescription_old = test.supportDataActions.getColumnFirstData("2");
		holdReasonName_old = test.supportDataActions.getColumnFirstData("1");
		test.supportDataActions.enterSearchTermInSearchField(holdReasonDescription_old, "search");
		test.supportDataActions.pressKeyUsingAction(Keys.ENTER);
		holdReasonName_new = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(holdReasonName_new, holdReasonName_old);
		test.supportDataActions.resetSearch();
	}
	
	
	@Test(priority = 4, description = "VPLX: Hold Reason UI: Search: The hold reason is not found "
			+ "when searching for a description that does not exist")
	public void Test04_1029441(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:HoldReason: UI - Search a Hold Reason on the basis of Description that does not exists in DB");
		
		String holdReasonDescription_old = test.supportDataActions.getColumnFirstData("2");
		holdReasonName_old = test.supportDataActions.getColumnFirstData("1");
		test.supportDataActions.enterSearchTermInSearchField(holdReasonDescription_old.concat(DateUtil.getTommorrowsDate()), "search");
		test.supportDataActions.pressKeyUsingAction(Keys.ENTER);
		
		holdReasonName_new = test.supportDataActions.getNoDataText();
		Assert.assertNotEquals(holdReasonName_old, holdReasonName_new);
		Assert.assertEquals(holdReasonName_new, getData("HoldReasonDetails.Error_NoData"));
		test.supportDataActions.resetSearch();
	}
	
	
	@Test(priority = 5, description = "VPLX: Hold Reason UI: Search: The Search box is reset when cross button is clicked")
	public void Test05_1040367(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI: Feature Testing- Search box is reset when cross button is clicked");
		
		holdReasonName_old = test.supportDataActions.getColumnFirstData("1");
		test.supportDataActions.enterSearchTermInSearchField(holdReasonName_old, "search");
		test.supportDataActions.pressKeyUsingAction(Keys.ENTER);
		test.supportDataActions.resetSearch();
		Assert.assertTrue(test.supportDataActions.getValueOfInputField("search").isEmpty());	
	}

}


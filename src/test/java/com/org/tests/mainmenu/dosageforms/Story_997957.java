package com.org.tests.mainmenu.dosageforms;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997957 extends BaseTest {

	String dosageCode, description, sortOrder, dosageCode1, description1;
	String[] listSortColumns = { "Code", "Description", "Sort Order", "Status", "Actions" };

	@Test(priority = 1, description = "VPLX: Dosage Form [UI]: Dosage Form-Edit: User is able to toggle to set a record as Active while editting Dosage Form")
	public void Test01_1041163(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				" VPLX: Dosage Form [UI]: Dosage Form-Edit: User is able to toggle to set a record as Active while editting Dosage Form");
		test.landingPageActions.navigateToFeature("Dosage Forms");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		dosageCode = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		description = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "2");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchFieldGl(dosageCode, "search");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToPrinterName(dosageCode);
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
	}
	

	@Test(priority = 2, description = "VPLX: Dosage Form [UI]: Dosage Form-Edit: User is able to toggle to set a record as Inactive while editting Dosage Form")
	public void Test02_1041164(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Edit: User is able to toggle to set a record as Inactive while editting Dosage Form");
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Active", false);
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("false"));
	}

	@Test(priority = 3, description = "VPLX: Dosage Form [UI]: Dosage Form-Edit: Code greater than length 20 is not allowed")
	public void Test03_1041221(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Edit: Code greater than length 20 is not allowed");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("dosageFormCode"), 20);
	}

	@Test(priority = 4, description = "VPLX: Dosage Form [UI]: Dosage Form-Edit: Description greater than length 100 is not allowed")
	public void Test04_1041297(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Edit: Description greater than length 100 is not allowed");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnTextAreaField("descriptionText"), 100);
	}

	@Test(priority = 5, description = "VPLX: Dosage Form [UI]: Dosage Form-Edit: Sort Order greater than 9999 is not allowed")
	public void Test05_1041305(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Edit: Sort Order greater than 9999 is not allowed");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("sortValue"), 4);
		description = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
	}

	@Test(priority = 6, description = "VPLX: Dosage Form [UI]: Dosage Form-Edit: Sort Order less than 1 is not allowed")
	public void Test06_1041306(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Edit: Sort Order less than 1 is not allowed");
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", "Code" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("sortValue", "-2");
		test.supportDataActions.verifyErrorMessageDosageCode("Enter numeric value only");
		test.supportDataActions.clickButton("cancel");
	}

	@Test(priority = 7, description = "VPLX: Dosage Form [UI]: Dosage Form-Edit: user is able to edit Dosage Form with unique Code successfully")
	public void Test07_1041296_1041326(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Edit: user is able to edit Dosage Form with unique Code successfully");
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(dosageCode, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(dosageCode, "1"));
		test.supportDataActions.clickEditLink(dosageCode);
		dosageCode = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dosageCode);
	}

	@Test(priority = 8, description = "VPLX: Dosage Form [UI]: Dosage Form-Edit: user is able to edit PIS from drop-down while editting Dosage Form")
	public void Test08_1041310(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Edit: user is able to edit PIS from drop-down while editting Dosage Form");
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(dosageCode, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(dosageCode, "1"));
		test.supportDataActions.clickEditLink(dosageCode);
		test.supportDataActions.selectValueFromDropdownByIndex("externalSystemKey", 0);
		test.supportDataActions.clickButton("save");
	}

	@Test(priority = 9, description = "VPLX: Dosage Form [UI]: Dosage Form-Edit: user is able to edit Dosage Form with unique Description successfully")
	public void Test09_1041300(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Edit: user is able to edit Dosage Form with unique Description successfully");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		dosageCode = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		description = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "2");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(dosageCode, "search");
		test.supportDataActions.clickEditLink(dosageCode);
		dosageCode = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		description = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description" + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");

	}

	@Test(priority = 10, description = "VPLX: Dosage Form [UI]: Dosage Form-Edit: user is able to edit Dosage Form without providing Description")
	public void Test10_1041304(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Edit: user is able to edit Dosage Form without providing Description");
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(dosageCode, "search");
		test.supportDataActions.clickEditLink(dosageCode);
		dosageCode = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		test.supportDataActions.clearDosageDescriptionBox("descriptionText");
		test.supportDataActions.clickButton("save");
	}


	@Test(priority = 11, description = "VPLX: Dosage Form [UI]: Dosage Form-Edit: Error message appears if user tries to edit Dosage Form by deleting existing Code")
	public void Test11_1041166(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Edit: Error message appears if user tries to edit Dosage Form by deleting existing Code");
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		dosageCode = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		description = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "2");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchFieldGl(dosageCode, "search");
		test.supportDataActions.clickEditLink(dosageCode);
		test.supportDataActions.clearDosagecodeInputBox("dosageFormCode");
		test.supportDataActions.verifyErrorMessageDosageCode("Code cannot be empty");
		test.supportDataActions.clickButton("cancel");
	}

	@Test(priority = 12, description = "VPLX: Dosage Form [UI]: Dosage Form-Edit: Error message appears on editting Dosage Form with already existing Code")
	public void Test12_1041291(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Edit: Error message appears on editting Dosage Form with already existing Code");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		dosageCode1 = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		description1 = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", getData("DosageForm.SortOrder"));
		test.supportDataActions.selectValueFromDropdownByIndex("externalSystemKey", 0);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(dosageCode, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(dosageCode, "1"));
		test.supportDataActions.clickEditLink(dosageCode);
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", dosageCode1);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.CodeAlreadyExist"));
	}

	@Test(priority = 13, description = "VPLX: Dosage Form [UI]: Dosage Form-Edit: Error message appears on editting Dosage Form with already existing Code")
	public void Test13_1041293(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Edit: Error message appears on editting Dosage Form with already existing Code");
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", dosageCode1.toUpperCase());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.CodeAlreadyExist"));
	}

	@Test(priority = 14, description = "VPLX: Dosage Form [UI]: Dosage Form-Edit: Error message appears on editting Dosage Form with already existing Code")
	public void Test14_1041294(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Edit: Error message appears on editting Dosage Form with already existing Code");
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", dosageCode1);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.CodeAlreadyExist"));
	}

	@Test(priority = 15, description = "VPLX: Dosage Form [UI]: Dosage Form-Edit: User is not able to toggle to set a record as Inactive while editting Dosage Form if it is being used elsewhere in the system")
	public void Test15_1041322(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Edit: User is not able to toggle to set a record as Inactive while editting Dosage Form if it is being used elsewhere in the system");
		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(dosageCode, "search");
		test.supportDataActions.clickEditLink(dosageCode);
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", dosageCode1);
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Active", false);
		test.supportDataActions.clickButton("save");
	}
	
	@Test(priority = 16, description = "VPLX: Dosage Form [UI]: Dosage Form-Edit: Dosage Form edits with existing Sort Order")
	public void Test16_1041307(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Edit: Dosage Form edits with existing Sort Order");
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(dosageCode, "search");
		test.supportDataActions.clickEditLink(dosageCode);
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", dosageCode1);
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "63");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.clickButton("cancel");
	}
	
	@Test(priority = 17, description = "VPLX: Dosage Form [UI]: Dosage Form-Edit: user is unable to enter Sort Order with decimal value")
	public void Test17_1041308(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Edit: user is unable to enter Sort Order with decimal value");
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(dosageCode, "search");
		test.supportDataActions.clickEditLink(dosageCode);
		test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", dosageCode1);
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "2.4");
		test.supportDataActions.verifyErrorMessageDosageCode("Enter numeric value only");
		test.supportDataActions.clickButton("cancel");
	}
	
	@Test(priority = 18, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: Dosage Form can be added with existing Sort Order")
	public void Test18_1040959(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Add: Dosage Form can be added with existing Sort Order");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		dosageCode = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		description = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "2");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchFieldGl(dosageCode, "search");
		test.supportDataActions.clickEditLink(dosageCode);
		test.supportDataActions.clickButton("cancel");
	}
	
	@Test(priority = 19, description = "VPLX:Dosage Form:An option to sort must be available by clicking on the header.")
	public void Test19_1037855(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Dosage Form:An option to sort must be available by clicking on the header.");
		test.siteConfigurationAction.verifyColumnHeaderForDosageForm(listSortColumns);

	}
}

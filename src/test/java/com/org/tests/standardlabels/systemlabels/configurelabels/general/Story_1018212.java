package com.org.tests.standardlabels.systemlabels.configurelabels.general;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1018212 extends BaseTest{
	String systemLabelName;
	String addStandardLabelTitle = "Add Standard Label";
	
	@Test(priority = 1, description = "VPLX:Configure Labels- General:UI:Verify duplicate link displayed on System label screen")
	public void Test01_1069384(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify duplicate link displayed on System label screen");
		
		test.landingPageActions.navigateToFeature("Standard Labels");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		
		systemLabelName=test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName") + System.currentTimeMillis());
		//test.siteConfigurationAction.selectDropdown("facility", getData("SystemLabel.FacilityName"));
		//test.siteConfigurationAction.selectDropdown("stock", getData("SystemLabel.StockName"));
		//test.siteConfigurationAction.selectDropdown("category", getData("SystemLabel.CategoryName"));
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("category",1);
		test.siteConfigurationAction.clickSaveButton();
		//test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SystemLabel.SuccessMessage"));
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		
		test.siteConfigurationAction.enterSearchTermInSearchField(systemLabelName);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.verifyEditLinkAgainstSystemLabels("Duplicate");
		
	}
	
	@Test(priority = 2, description = "VPLX:Configure Labels- General:UI:Verify pop-up for duplicate label open while click on duplicate link")
	public void Test02_1069385(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify pop-up for duplicate label open while click on duplicate link");
		
		test.siteConfigurationAction.clickStandardLabelLink(systemLabelName,"Duplicate Label","Duplicate");
		
	}
	
	@Test(priority = 3, description = "VPLX:Configure Labels- General:UI:Verify pre-filled entries present in the duplicate label pop-up")
	public void Test03_1069403(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify pre-filled entries present in the duplicate label pop-up");
		
		Assert.assertEquals(test.siteConfigurationAction.verifyInputFieldIsAutopopulated("labelName"), systemLabelName + " - Copy");
		Assert.assertTrue(!test.siteConfigurationAction.verifyDefaultValueinSystemLabel("stock").isEmpty());
		Assert.assertTrue(!test.siteConfigurationAction.verifyDefaultValueinSystemLabel("facility").isEmpty());
		
	}
	
	@Test(priority = 4, description = "VPLX:Configure Labels- General:UI:Verify in duplicate label name displayed with name(copy)")
	public void Test04_1069410(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify in duplicate label name displayed with name(copy)");
		
		Assert.assertEquals(test.siteConfigurationAction.verifyInputFieldIsAutopopulated("labelName"), systemLabelName + " - Copy");
		
	}
	
	
	@Test(priority = 5, description = "VPLX:Configure Labels- General:UI:Verify in duplicate label name,stock,category present as mandatory field while assign priority as non mandatory field")
	public void Test05_1069421(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify in duplicate label name,stock,category present as mandatory field while assign priority as non mandatory field");
		
		test.siteConfigurationAction.verifyFieldIsMandatory("name");
		test.siteConfigurationAction.verifyFieldIsMandatory("stock");
		test.siteConfigurationAction.verifyFieldIsMandatory("category");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("priority"));
		
	}
	
	@Test(priority = 6, description = "VPLX:Configure Labels- General:UI:Verify cancel and new design button displayed on duplicate label")
	public void Test06_1069583(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify cancel and new design button displayed on duplicate label");
		
		test.supportDataActions.verifyButtons("cancel");
		test.supportDataActions.verifyButtons("save");
		
	}
	
	//combit needed- cannot be automated
	/*
	 * @Test(priority = 7, description =
	 * "VPLX:Configure Labels- General:UI:Verify copy label added using duplicate link"
	 * ) public void Test07_1069585(Method method) {
	 * ExtentTestManager.startTest(method.getName(),
	 * "VPLX:Configure Labels- General:UI:Verify copy label added using duplicate link"
	 * );
	 * 
	 * 
	 * }
	 */
}

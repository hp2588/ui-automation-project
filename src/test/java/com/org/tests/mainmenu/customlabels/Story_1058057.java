package com.org.tests.mainmenu.customlabels;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1058057 extends BaseTest {
	
	String labelName,stockName,firstData, parsed_stockName_array[],parsed_stockName;
	List<String> actualData, expectedData;
	@Test(priority = 1, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:Enabled search text box is present to search Custom labels present at listing page")
	public void Test01_1086083(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:Enabled search text box is present to search Custom labels present at listing page");
		//server.newHar("Google Privacy Testing");
		
		/*test.landingPageActions.navigateToFeature("Label Size Definitions");
		test.supportDataActions.verifyLabelIsPresent("Label Size Definitions");
		if(!(test.siteConfigurationAction.verifyInactiveAddedRecordNameInList("Stock1585056163566"))){
			test.supportDataActions.verifyAddButtonOnPage();
			test.supportDataActions.clickAddButtonOnDistributor("add");
			stockName = test.supportDataActions.enterValueOnAddDistributorPage("stockLabelName",
					"Stock"+System.currentTimeMillis());
			test.supportDataActions.enterValueOnAddDistributorPage("stockLabelWidth", "3");
			test.supportDataActions.enterValueOnAddDistributorPage("stockLabelHeight", "4");
			test.supportDataActions.clickAddButtonOnDistributor("save");
			Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Stock Definitions"));
			test.supportDataActions.enterSearchTermInSearchField(stockName, "search");
			firstData = test.supportDataActions.firstDataOnDistributor("1");
		Assert.assertEquals(stockName, firstData);*/
		
		
		
		//test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Custom Labels");
		test.supportDataActions.verifyLabelIsPresent("Custom Labels");
		test.siteConfigurationAction.verifyInputField("scheduleSearch");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Custom Label");
		labelName = test.siteConfigurationAction.enterRandomValueInInputField("labelName",
				"Name" + System.currentTimeMillis());
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("facility", 1);
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		 stockName=test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("stock", 1);
		 parsed_stockName_array=stockName.split(" ");
		 parsed_stockName=parsed_stockName_array[0];
		
		 test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditAdhocLabel"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(labelName);
	}
	
	@Test(priority = 2, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:Searching for Custom labels is based upon Label name,Stock name.")
	public void Test02_1086090(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:Searching for Custom labels is based upon Label name,Stock name.");
		test.supportDataActions.clickOnEditLinkCorresspondingToCustomLabel(labelName, labelName);
		//test.supportDataActions.clickButton("edit");
		//test.supportDataActions.verifyLabelIsPresent("Edit Ad-Hoc Labels");
		labelName=test.siteConfigurationAction.enterRandomValueInInputField("labelName", "Name"+System.currentTimeMillis());
	
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditAdhocLabel"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(labelName);
		
		test.supportDataActions.enterSearchTermInSearchFieldGl(labelName, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(labelName, "1"));
        
		test.supportDataActions.clearSearchBoxField("search");
		String stock=test.supportDataActions.enterSearchTermInSearchFieldGl(parsed_stockName, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(stock, "2"));

	
	}
	
	@Test(priority = 3, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:Sorting for Custom labels is based on label name and stock name at the listing page")
	public void Test03_1086097(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:Sorting for Custom labels is based on label name and stock name at the listing page");
		
		//test.supportDataActions.clearSearchBoxField("search");
		//test.siteConfigurationAction.refreshPage();
		test.siteConfigurationAction.verifyAndClickSortIcon("Name");
		//test.siteConfigurationAction.verifyAndClickSortIcon("Name");
		//test.siteConfigurationAction.clickOnSortedIcon("Name", "asc");
		actualData = test.siteConfigurationAction.getColumnData("1");
		expectedData = test.siteConfigurationAction.getColumnData("1");
		Collections.sort(expectedData);
		//Assert.assertEquals(actualData, expectedData);
		
		test.siteConfigurationAction.verifyAndClickSortIcon("Stock Name");
		//test.siteConfigurationAction.clickOnSortedIcon("Stock Name", "asc");
		actualData = test.siteConfigurationAction.getColumnData("2");
		
		expectedData = test.siteConfigurationAction.getColumnData("2");
		Collections.sort(expectedData);
		//Assert.assertEquals(actualData, expectedData);
				
	}
	
	@Test(priority = 4, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:When not any record found a message displayed is getting displayed 'no record found'.")
	public void Test04_1086103(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:When not any record found a message displayed is getting displayed 'no record found'.");
		test.supportDataActions.enterSearchTermInSearchFieldGl(DateUtil.getTommorrowsDate(), "search");
		Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Matching Results.");
		test.supportDataActions.clearSearchBoxField("search");

	}
}

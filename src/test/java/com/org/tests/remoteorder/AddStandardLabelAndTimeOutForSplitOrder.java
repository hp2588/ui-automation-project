package com.org.tests.remoteorder;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class AddStandardLabelAndTimeOutForSplitOrder extends BaseTest {
	
	String OrderName_1,OrderName_2,itemName,FacilityName,DestinationName,ItemCode,DistributorName,ExternalSystemName,IPAddress,ISAName,barcode,productID,OrderName1;
	ArrayList<String> previous_data, sorted_data;
	String systemLabelName;
	
	@Test(priority = 1, description = "VPLX : Create Label for split order")
	public void Test01_00(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX : User can log in to Remote Order Form after UserID change.");
		
		
		itemName= TestDataPropertyReaderAndWriter.getProperty("ItemName").trim();
		FacilityName = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim();
		DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Standard Labels");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel("Add Standard Label");
		
		systemLabelName=test.siteConfigurationAction.enterDataInInputField("labelName", getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDown("facility",TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.selectValueFromDropDown("category","Auto Dispensing Cabinet");
		test.siteConfigurationAction.selectPriority("Destination Orders", true);
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.enterSearchTermInSearchField(systemLabelName);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		
	}
	
	@Test(priority = 2, description = "VPLX : Increase max locked time for Destination Orders")
	public void Test02_00(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX : User can log in to Remote Order Form after UserID change.");
		
		
		itemName= TestDataPropertyReaderAndWriter.getProperty("ItemName").trim();
		FacilityName = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim();
		DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Priorities");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", FacilityName);
		test.siteConfigurationAction.enterSearchTermInSearchField("Destination Orders", "search");
		test.siteConfigurationAction.clickButton("edit");
		test.siteConfigurationAction.enterRandomValueInInputField("maxHoldMinutes", "2400");
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		
		test.siteConfigurationAction.enterSearchTermInSearchField("Destination Orders", "search");
		test.siteConfigurationAction.clickButton("edit");
		test.siteConfigurationAction.enterRandomValueInInputField("maxLockedSeconds", "2400");
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		
	}

}

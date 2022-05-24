package com.org.tests.siteconfiguration;

import static com.org.automation.utils.YamlReader.getData;
import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1021003 extends BaseTest {
	
	String facilityName,  clickedDate;
	
	 @Test(priority = 1, description = "VPLX:Specific facility Settings:[UI]:Check that user is able to select date from the calender")	 		
		public void Test01_1055516(Method method) throws Throwable
{
			
			ExtentTestManager.startTest(method.getName(),
					"VPLX:Specific facility Settings:[UI]:Check that user is able to select date from the calender");
			test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations");
			test.landingPageActions.navigateToFeature("Facilities Setup");
		  
			test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName("abc");
			
			test.siteConfigurationAction.clickTab("Cycle Counts");
			test.siteConfigurationAction.verifyPlaceHolderForCycleCount(getData("Facility.PlaceHolder"));
			test.siteConfigurationAction.clickCycleCountCalendar();
			String date=test.siteConfigurationAction.getCurrentMonth_Year();
			int day=test.siteConfigurationAction.getCurrentDate();
			test.siteConfigurationAction.ClickCurrentDate(date);
		String a=	test.siteConfigurationAction.getCurrentMonth_Year();
}
	 
	 
	 @Test(priority = 2, description = "VPLX:Specific facility Settings:[UI]:User clicks on add button so date is added in the list of disabled dates")	 		

		public void Test02_1055555(Method method) throws Throwable
{
			
			ExtentTestManager.startTest(method.getName(),
					"VPLX:Specific facility Settings:[UI]:User clicks on add button so date is added in the list of disabled dates");
			test.siteConfigurationAction.clickAddButton();
}
	 //Bug -1112592 logged for IE Browser issue
	 @Test(priority = 3, description = "VPLX:Specific facility Settings:[UI]:Verify that added date is visible in the list of disabled dates")
		public void Test03_1055530(Method method) throws Throwable
{
			
			ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:Verify that added date is visible in the list of disabled dates");
			 clickedDate=test.siteConfigurationAction.getCurrentMonth_Year();
			Assert.assertFalse(test.siteConfigurationAction.verifyDisableDate(clickedDate));
}
	 
	 @Test(priority = 4, description = "VPLX:Specific facility Settings:[UI]:User verifies the Remove button in front of disabled dates")
		public void Test04_1055532(Method method) throws Throwable
{
			
			ExtentTestManager.startTest(method.getName(),
					"VPLX:Specific facility Settings:[UI]:User verifies the Remove button in front of disabled dates");
			test.siteConfigurationAction.verifyRemoveButton();
}
	 
	 @Test(priority = 5, description = "VPLX:Specific facility Settings:[UI]:Verify that removed date is not visible in the list of disabled dates")
		public void Test05_1055533(Method method) throws Throwable
{
			
			ExtentTestManager.startTest(method.getName(),
					"VPLX:Specific facility Settings:[UI]:Verify that removed date is not visible in the list of disabled dates");
			test.siteConfigurationAction.clickRemoveButton();
		Assert.assertTrue(test.siteConfigurationAction.verifyDisableDate(clickedDate));

}
	 
	

	 @Test(priority = 6, description = "VPLX:Specific facility Settings:[UI]:Verify that placeholder value of Disable cycle count on these dates textbox with VD")
		public void Test06_1055541(Method method) throws Throwable
{
			
			ExtentTestManager.startTest(method.getName(),
					"VPLX:Specific facility Settings:[UI]:Verify that placeholder value of Disable cycle count on these dates textbox with VD");
			//test.siteConfigurationAction.clickCycleCountCalendar();

			test.siteConfigurationAction.verifyPlaceHolderForCycleCount(getData("Facility.PlaceHolder"));

}
	
}

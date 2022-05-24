package com.org.tests.Astar_RO;
import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class RO_1121514_obsolete extends BaseTest {

	String OrderName_1,OrderName_2,itemName,FacilityName,DestinationName,ItemCode,DistributorName,ExternalSystemName,IPAddress,ISAName,barcode,productID,OrderName1;
	ArrayList<String> previous_data, sorted_data;
	
	@Test(priority = 3, description = "VPLX : Remote order should be sorted by descending order date.")
	public void Test03_1121514(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX : Remote order should be sorted by descending order date .");
		
		
		itemName= TestDataPropertyReaderAndWriter.getProperty("ItemName").trim();
		FacilityName = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim();
		DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim();
		ItemCode= TestDataPropertyReaderAndWriter.getProperty("ItemCode").trim();
		DistributorName= TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim();
		ExternalSystemName= TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim();
		IPAddress= TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim();
		ISAName= TestDataPropertyReaderAndWriter.getProperty("ISAName").trim();
		
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(FacilityName);
		//Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(),true);
		//test.siteConfigurationAction.verifyOrderInPODashboard("New",DistributorName);
		test.supportDataActions.openPurchaseOrderManualcard();
		previous_data = test.supportDataActions.captureDataForParticularColumnPO();
		sorted_data = test.supportDataActions.sortDataForParticularColumnInDescendingOrderPO();
		Assert.assertEquals(previous_data, sorted_data,	"[ASSERTION FAILED] : PO data is not sorted in descending order");
	}
	
	
}

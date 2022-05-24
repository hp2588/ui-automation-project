package com.org.automation.dev;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;


public class ItemLocationEditEvent extends BaseTest {
	List<String> names;
	String minQty, maxQty;
	int itemCount, editBtnCount;
	
	
	@Test(priority = 1)
	public void Test01_(Method method) {
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.verifyHeader("Item Locations");
		// test.siteConfigurationAction.selectValueByTextUsingIdField("selectRowName", "100");
		
		while(true) {
			// names = test.siteConfigurationAction.captureDataForNameColumn("");
			
			itemCount = test.siteConfigurationAction.getNumberOfButtons_UsingId("edit");
			for(int itemIdx = 1; itemIdx <= itemCount; itemIdx++) {
				// clicking item name (edit button) using id and count
				test.siteConfigurationAction.clickButton_UsingNameAndCount("edit", String.valueOf(itemIdx));
				
				if(test.siteConfigurationAction.isParaWithTextVisible("This item is not assigned to a location")) {
					editBtnCount = 0;
					
				} else {
					editBtnCount = test.siteConfigurationAction.getNumberOfButtons_UsingId("edit");
				}
				
				for(int editBtnIdx = 1; editBtnIdx <= editBtnCount; editBtnIdx++) {
					// clicking location Edit button using id and count
					if(test.siteConfigurationAction.isButtonEnabled_UsingNameAndCount("edit", String.valueOf(editBtnIdx))) {
						test.siteConfigurationAction.clickButton_UsingNameAndCount("edit", String.valueOf(editBtnIdx));
						
						minQty = test.siteConfigurationAction.getCurrentValueOfInputField("refillPointQuantity");
						test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", minQty);
						
						// maxQty = test.siteConfigurationAction.getCurrentValueOfInputField("parQuantity");
						// test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", maxQty);
						
						Assert.assertTrue(test.siteConfigurationAction.isElementEnabled_UsingId("save"));
						test.siteConfigurationAction.clickButtonUsingId("save");
					}
				}
				
				test.siteConfigurationAction.clickButtonUsingId("editCancel");
				// test.siteConfigurationAction.verifyHeader("Item Locations");
			}
			
			if(test.siteConfigurationAction.isElementEnabled_UsingId("nextbtn")) {
				test.siteConfigurationAction.clickButtonUsingId("nextbtn");
			} else {
				break;
			}
		}
	}
	
}

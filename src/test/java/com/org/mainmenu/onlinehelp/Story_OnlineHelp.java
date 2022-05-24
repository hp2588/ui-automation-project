package com.org.mainmenu.onlinehelp;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_OnlineHelp extends BaseTest {
	
	
	@Test(priority = 1, description = "VPLX:Online help:[UI]:Help Page is opened while clicking on Help Icon")
	public void Test01_1108917(Method method) {

		test.siteConfigurationAction.verifyOnlineHelpPageIcon();
		
	} 
	
	@Test(priority = 2, description = "VPLX:Online help:[UI]:User is able to see a help icon on the landing page")
	public void Test02_1108916(Method method) {

		test.siteConfigurationAction.clickOnHelpIcon();
		test.supportDataActions.switchToReportTab(1);
		test.supportDataActions.switchToFrame("topic");
		test.siteConfigurationAction.verifyOnlineHelpPageIsOpened();
	} 
	
	
	


}

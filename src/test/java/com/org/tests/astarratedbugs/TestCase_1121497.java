package com.org.tests.astarratedbugs;

import java.lang.reflect.Method;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class TestCase_1121497 extends BaseTest{
	
	@Test(priority = 1, description = "VPLX: Manage ISAs: [UI]: Add and View ISA")
	public void Test01_1121497(Method method) throws InterruptedException {
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		//test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ISAName1"), "search");
		test.supportDataActions.clickEditButtonOnDistributor(TestDataPropertyReaderAndWriter.getProperty("ISAName1"));
		//test.siteConfigurationAction.clickEditButtonOnISAPage();
		test.storageAreaAction.clickTab("ISA Configuration".trim());
		test.storageAreaAction.verifyPageTitleContains("ISA Configuration".trim());
		test.siteConfigurationAction.clickTab("Approved Computers");
		test.storageAreaAction.verifyPageTitleContains("Approved Computers".trim());
	}

}

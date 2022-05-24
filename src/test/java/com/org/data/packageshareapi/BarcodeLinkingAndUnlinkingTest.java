package com.org.data.packageshareapi;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class BarcodeLinkingAndUnlinkingTest extends BaseTest {

	@Test(priority = 1, description = "VPLX: GL Account: [UI] : Barcode Linking and Unlinking  Test")
	public void Test01_BarcodeLinkingAndUnlinking(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : Record is displaying when user search the data on search screen");

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		String barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		Assert.assertTrue(test.siteConfigurationAction.clickRelinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
		Assert.assertTrue(test.siteConfigurationAction.clickResetButton());

		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());

	}

}

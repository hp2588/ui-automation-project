package com.org.sampletest;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;


public class Sample_Test extends BaseTest{
	
	
	@Test(priority = 1, description = "sample test")
	public void Test01_1016383(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"sample test");
		
	} 
	
	

}



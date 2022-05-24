package com.org.tests.mainmenu.dispenseunits;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997961 extends BaseTest{
	String codeValue, descriptionForm, sortOrder;
	ArrayList<String> previous_data, sorted_data;

	@Test(priority = 1, description = "VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to view the list of Dispense Unit")
	public void Test01_1046972(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to view the list of Dispense Unit");
		test.landingPageActions.navigateToFeature("Dispense Units");
	    test.supportDataActions.clickOnAddButtonToAddNewRecord1("Add Dispense Unit");
	    codeValue = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterRandomValueInInputField("sortValue", "3");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(codeValue);
		test.supportDataActions.codeListDosageForms("1");
		test.supportDataActions.descriptionFormDosageForms("2");
	    test.supportDataActions.sortOrderDispenseUnit();
		test.supportDataActions.verifyDispenseUnitStatusAsActive();
		test.supportDataActions.verifyEditActionDosageForms("2");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonOnDosageForm("toggle"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Code"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Description"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Sort Order"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Status"));
	//	Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Actions"));
		Assert.assertTrue(test.supportDataActions.verifyDropdownOnDosageForm()); 

	}
	
	@Test(priority =2, description = "VPLX: Dispense Unit [UI]: Dispense Unit-View: The list of Dispense Unit displays only the active ones by default")
	public void Test02_1046980(Method method)
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to view the list of Dispense Unit");
		test.supportDataActions.verifyDispenseUnitStatusAsActive(); 
	}
	
	@Test(priority =3, description = "VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to toggle to exclude the inactive Dispense Unit in the listing")
	public void Test03_1046981(Method method)
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to toggle to exclude the inactive Dispense Unit in the listing");
		test.supportDataActions.clickToggleButton("false", "toggle");
		test.supportDataActions.verifyDispenseUnitStatusAsActive();

	}
	
	
	@Test(priority =4, description = "VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to toggle and include the inactive Dispense Unit in the listing")
	public void Test04_1046982(Method method)
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to toggle and include the inactive Dispense Unit in the listing");
		test.supportDataActions.clickToggleButton("true", "toggle");
		Assert.assertFalse(test.supportDataActions.verifyDispenseUnitStatusAsInactive()); 
		
	} 
	
	@Test(priority =5, description = "VPLX: Dispense Unit [UI]: Dispense Unit-View: The list of Dispense Unit is sorted alphanumerically in ascending order of the field Name by default"
)
	public void Test05_1046991(Method method)
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-View: The list of Dispense Unit is sorted alphanumerically in ascending order of the field Name by default");
		test.supportDataActions.clickToggleButton("false", "toggle");
		sorted_data = test.supportDataActions.sortDataForParticularColumnInAscendingOrderDosageForm("2");
		System.out.println("Sorted data :  " + sorted_data);
		test.supportDataActions.clickOnColumnHeaderDispenseUnit("Description");
		previous_data = test.supportDataActions.captureSortedDataForParticularColumnDispenseUnit("2");
		System.out.println("Previous data :  " + previous_data);
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Code column data is not sorted in ascending order");
	}
	
	@Test(priority =6, description = "VPLX: Dispense Unit [UI]: Dispense Unit-View: The combined list of active & inactive Dispense Unit is displayed in alphanumeric order")
	public void Test06_1046998(Method method)
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-View: The combined list of active & inactive Dispense Unit is displayed in alphanumeric order");
		test.supportDataActions.refreshPage();
		test.supportDataActions.clickToggleButton("true", "toggle");
		sorted_data = test.supportDataActions.sortDataForParticularColumnInAscendingOrderDosageForm("1");
		System.out.println("Sorted data :  " + sorted_data);
		test.supportDataActions.clickOnColumnHeaderDispenseUnit("Description"); 
		previous_data = test.supportDataActions.captureSortedDataForParticularColumnDispenseUnit("1");
		System.out.println("Previous data :  " + previous_data);
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Code column data is not sorted in ascending order");
	}
	
	@Test(priority =7, description = "VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to select PIS from drop-down and result is displayed according to PIS selected")
	public void Test07_1047002(Method method)
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to select PIS from drop-down and result is displayed according to PIS selected");
		test.supportDataActions.clickToggleButton("false", "toggle");
		test.supportDataActions.getFirstPISValue();
		test.supportDataActions.selectPISFromDropdown(1);
		test.supportDataActions.codeListDosageForms("1");
		test.supportDataActions.descriptionFormDosageForms("2");
		test.supportDataActions.sortOrderDispenseUnit();
		test.supportDataActions.verifyDispenseUnitStatusAsActive();
		test.supportDataActions.verifyEditActionDosageForms("2");
	}
	
	@Test(priority =8, description ="VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to sort each column of list of Dispense Unit")
	public void Test08_1046995(Method method)
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-View: The user is able to sort each column of list of Dispense Unit");
		previous_data = test.supportDataActions.captureDataForParticularColumnDosageForm("2");
		System.out.println("Previous data :  " + previous_data);
		sorted_data = test.supportDataActions.sortDataForParticularColumnInAscendingOrderDosageForm("2");
		System.out.println("Sorted data :  " + sorted_data);
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Description column data is not sorted in ascending order");
	}

}

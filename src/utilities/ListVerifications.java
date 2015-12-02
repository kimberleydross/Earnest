package utilities;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.BaseTestCase;
import common.SeleniumCommon;
import static common.BaseTestCase.LocatorType; 

public class ListVerifications extends BaseTestCase {
	
	 

	//private static final common.SeleniumCommon.LocatorType XPATH;
	 
	public static void dropdownOptions(List<String> allOptionsExpected, String dropdownLocator, String dropdownLocatorType) throws AssertionError {
		
		//Need to make this more dynamic
		WebElement we = SeleniumCommon.objectLocator(common.BaseTestCase.LocatorType.NAME, dropdownLocator);
		
		//since we have the drop down element, grab the options from the drop down
		String actual = we.getText();
		
		//split the string on any white space
		List<String> actualStatesDropDown = Arrays.asList(actual.split("\\s*,\\s*"));
		
		//Assert the actual and expected lists are the same - throws AE if not
		Assert.assertNotNull(allOptionsExpected);
		Assert.assertNotNull(actualStatesDropDown);
		Assert.assertTrue(allOptionsExpected.contains(actualStatesDropDown));
		Assert.assertTrue(allOptionsExpected.size() == actualStatesDropDown.size());		
	}		
	}


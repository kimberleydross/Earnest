package junglesocks;

import java.util.Arrays;
import java.util.List;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import common.BaseTestCase;
import common.SeleniumCommon;

public class TaxPercentages extends BaseTestCase {
	
	  @Override
	  @Before
	  public void setUp() throws Exception {
	//		super.setUp();
	  }

	  @Test
	  public void testTaxValue() throws Exception {
	    
		double percent = 0.05;
		  
		//hit the back button
		driver.navigate().back();
	    
		//wait for page to load
		SeleniumCommon.waitTilPageLoads("https://jungle-socks.herokuapp.com");
		
		//split the state string into a list so we can spin thru the list and submit the page for each state	
		String states = driver.findElement(By.name("state")).getText();
		//split the string on any white space
		List<String> actualStatesDropDown = Arrays.asList(states.split("\n"));
		for (String state : actualStatesDropDown){
			//bypass all the white space - TODO:  there's a better way to do this
			if (!state.trim().isEmpty() && !state.equals("\n") && !state.equals(null)){
				Select stateDD = new Select(driver.findElement(By.name("state")));
				//select the state option from the drop down
				System.out.println("state variable = " + state);
				stateDD.selectByVisibleText(state.trim());
				//.selectByValue(state);
				if (state.equalsIgnoreCase("California")){
					percent = 0.08;
				}
				
				if (state.equalsIgnoreCase("New York")){
					percent = 0.06;
				}
				
				if (state.equalsIgnoreCase("Minnesota")){
					percent = 0.00;
				}
				
				
				//resubmit the page
			    driver.findElement(By.name("commit")).click();
			    //wait for page to load
				SeleniumCommon.waitTilPageLoads("https://jungle-socks.herokuapp.com/checkout/create");
				//verify tax amounts per state
				verifyTaxAmount(percent);
				//set the percent back to default of 5%
				percent = 0.05;
				
				//hit the back button - home page - enter next state
				driver.navigate().back();
				//wait for page to load
				SeleniumCommon.waitTilPageLoads("https://jungle-socks.herokuapp.com");
			
			}
		 }
	  }
	  
	  private void verifyTaxAmount(double giveMeFive){
		  
		  //if five, tax amount should be 
		  
		  if (giveMeFive == 0.05){
			  verifyEquals("$11.30", driver.findElement(By.id("taxes")).getText());
			  verifyEquals("$237.30", driver.findElement(By.id("total")).getText());	
		  }
		  
		 //California
		  if (giveMeFive == 0.08){
			   verifyEquals("$18.08", driver.findElement(By.id("taxes")).getText());	
			   verifyEquals("$244.08", driver.findElement(By.id("total")).getText());
		  }
		  
		//New York
		  if (giveMeFive == 0.06){
			   verifyEquals("$13.56", driver.findElement(By.id("taxes")).getText());	
			   verifyEquals("$239.56", driver.findElement(By.id("total")).getText());
		  }
		  
		//Minnesota
		  if (giveMeFive == 0.00){
			   verifyEquals("$0.00", driver.findElement(By.id("taxes")).getText());	
			   verifyEquals("$226.00", driver.findElement(By.id("total")).getText());
		  }
	  }
}

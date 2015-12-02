package junglesocks;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import utilities.ListVerifications;
import common.BaseTestCase;
import common.SeleniumCommon;
import database.ExpectedValues;

public class OrderPage_AssertElements extends BaseTestCase {

	 @Override
	  @Before
	  public void setUp() throws Exception {
		 super.setUp();
	  }

	  @Test
	  public void testAssertJungleSocksOrderPage() throws Exception {
		 
		//List to hold expected values of the State drop down
		ExpectedValues ev = new ExpectedValues();
		  
	    driver.get(baseUrl + "/");
	    assertEquals("Welcome To Jungle Socks!", driver.findElement(By.cssSelector("h1")).getText());
	    assertEquals("Please enter the quantiy of each kind of sock and then click the checkout button", driver.findElement(By.cssSelector("p")).getText());
	    assertEquals("Name", driver.findElement(By.cssSelector("th")).getText());
	    assertEquals("Price", driver.findElement(By.xpath("//th[2]")).getText());
	    assertEquals("In Stock", driver.findElement(By.xpath("//th[3]")).getText());
	    assertEquals("Quantity", driver.findElement(By.xpath("//th[4]")).getText());
	    assertEquals("zebra", driver.findElement(By.cssSelector("td")).getText());
	    assertEquals("13", driver.findElement(By.xpath("//td[2]")).getText());
	    assertEquals("23", driver.findElement(By.xpath("//td[3]")).getText());
	    assertTrue(SeleniumCommon.isElementPresent(By.id("line_item_quantity_zebra")));
	    assertEquals("lion", driver.findElement(By.cssSelector("tr.line_item.lion > td")).getText());
	    assertEquals("20", driver.findElement(By.xpath("//tr[3]/td[2]")).getText());
	    assertEquals("12", driver.findElement(By.xpath("//tr[3]/td[3]")).getText());
	    assertTrue(SeleniumCommon.isElementPresent(By.id("line_item_quantity_lion")));
	    assertEquals("elephant", driver.findElement(By.cssSelector("tr.line_item.elephant > td")).getText());
	    assertEquals("35", driver.findElement(By.xpath("//tr[4]/td[2]")).getText());
	    assertEquals("3", driver.findElement(By.xpath("//tr[4]/td[3]")).getText());
	    assertEquals("", driver.findElement(By.id("line_item_quantity_elephant")).getText());
	    assertTrue(SeleniumCommon.isElementPresent(By.cssSelector("tr.line_item.giraffe > td")));
	    assertTrue(SeleniumCommon.isElementPresent(By.xpath("//tr[5]/td[2]")));
	    assertTrue(SeleniumCommon.isElementPresent(By.xpath("//tr[5]/td[3]")));
	    assertTrue(SeleniumCommon.isElementPresent(By.id("line_item_quantity_giraffe")));
	    verifyEquals("Ship to State:", driver.findElement(By.xpath("//table[2]/tbody/tr/td")).getText());
	    
	    //pass the locator string to retrieve actual values from the state drop down
	    String dropdownLocator = "state";
	    String locatorType = "name";
	    //compare expected values with actual values
	    try {
	    	ListVerifications.dropdownOptions(ev.stateDropDownList(), dropdownLocator, locatorType);
	    }catch (AssertionError ae){
	    	ae.printStackTrace();
	    }
	    	    
	    assertTrue(SeleniumCommon.isElementPresent(By.name("commit")));
	    
	    //Fill in quantity values
	    driver.findElement(By.id("line_item_quantity_zebra")).clear();
	    driver.findElement(By.id("line_item_quantity_zebra")).sendKeys("1");
	    driver.findElement(By.id("line_item_quantity_lion")).clear();
	    driver.findElement(By.id("line_item_quantity_lion")).sendKeys("2");
	    driver.findElement(By.id("line_item_quantity_elephant")).clear();
	    driver.findElement(By.id("line_item_quantity_elephant")).sendKeys("3");
	    driver.findElement(By.id("line_item_quantity_giraffe")).clear();
	    driver.findElement(By.id("line_item_quantity_giraffe")).sendKeys("4");
	    new Select(driver.findElement(By.name("state"))).selectByVisibleText("Alabama");
	    
	    //submit page
	    driver.findElement(By.name("commit")).click();
	    checkForVerificationErrors();
		 
	  }  
}

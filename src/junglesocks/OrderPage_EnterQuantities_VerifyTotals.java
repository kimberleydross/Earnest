package junglesocks;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.thoughtworks.selenium.Wait;

import common.BaseTestCase;
import common.SeleniumCommon;

public class OrderPage_EnterQuantities_VerifyTotals extends BaseTestCase {
	
	 @Override
	  @Before
	  public void setUp() throws Exception {

	  }

	  @Test
	  public void testVerifyOrderPage() throws Exception {
      
		String expectedURL = "https://jungle-socks.herokuapp.com/checkout/create";
		//refresh and wait until the page loads
		SeleniumCommon.waitTilPageLoads(expectedURL);
		
		//workaround for staleelementexception - need to catch this and log
		driver.navigate().refresh();
						
	    assertEquals("Please Confirm Your Order", driver.findElement(By.cssSelector("h1")).getText());
	    //staleexception
	    assertEquals("Name", driver.findElement(By.cssSelector("th")).getText());
	    assertTrue(SeleniumCommon.isElementPresent(By.xpath("//th[2]")));
	    assertTrue(SeleniumCommon.isElementPresent(By.xpath("//th[3]")));
	    assertEquals("zebra", driver.findElement(By.cssSelector("td")).getText());
	    assertEquals("13", driver.findElement(By.xpath("//td[2]")).getText());
	    assertEquals("1", driver.findElement(By.xpath("//td[3]")).getText());
	    assertEquals("lion", driver.findElement(By.cssSelector("tr.line_item.lion > td")).getText());
	    assertEquals("20", driver.findElement(By.xpath("//tr[3]/td[2]")).getText());
	    assertEquals("2", driver.findElement(By.xpath("//tr[3]/td[3]")).getText());
	    assertEquals("elephant", driver.findElement(By.cssSelector("tr.line_item.elephant > td")).getText());
	    assertEquals("35", driver.findElement(By.xpath("//tr[4]/td[2]")).getText());
	    assertEquals("3", driver.findElement(By.xpath("//tr[4]/td[3]")).getText());
	    assertEquals("giraffe", driver.findElement(By.cssSelector("tr.line_item.giraffe > td")).getText());
	    assertEquals("17", driver.findElement(By.xpath("//tr[5]/td[2]")).getText());
	    assertEquals("4", driver.findElement(By.xpath("//tr[5]/td[3]")).getText());
	    assertEquals("Subtotal:", driver.findElement(By.xpath("//tr[7]/td")).getText());
	    assertEquals("$226.00", driver.findElement(By.id("subtotal")).getText());
	    assertEquals("Taxes:", driver.findElement(By.xpath("//tr[8]/td")).getText());
	    verifyEquals("$11.30", driver.findElement(By.id("taxes")).getText());
	    assertEquals("Total:", driver.findElement(By.xpath("//tr[9]/td")).getText());
	    verifyEquals("$237.30", driver.findElement(By.id("total")).getText());
	    checkForVerificationErrors();
	  }
}



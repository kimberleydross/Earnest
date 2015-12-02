package common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.condition.Condition;
import com.thoughtworks.selenium.condition.ConditionRunner.Context;

public class SeleniumCommon extends BaseTestCase{

	  private boolean acceptNextAlert = true;
	      
	  public static WebElement objectLocator(common.BaseTestCase.LocatorType type, String ref) {
		    switch(type) {
		    case ID:
		        return driver.findElement(By.id(ref));
		    case CLASSNAME:
		        return driver.findElement(By.className(ref));
		    case XPATH:
		        return driver.findElement(By.xpath(ref));
		    case CSS:
		        return driver.findElement(By.cssSelector(ref));
		    case LINK:
		        return driver.findElement(By.linkText(ref));
		    case NAME:
		        return driver.findElement(By.name(ref));
		    case TAGNAME:
		        return driver.findElement(By.tagName(ref));
		    }
		    return null;
		    }
	  
	  public WebElement objectLocator(String identifier) {
		    String typeString = identifier.substring(0, identifier.indexOf('='));
		    String ref = identifier.substring(identifier.indexOf('=')+1, identifier.length());
		    if (typeString.toLowerCase().contains("classname")) {
		        return objectLocator(LocatorType.CLASSNAME, ref);
		    } else if (typeString.toLowerCase().contains("css")) {
		        return objectLocator(LocatorType.CSS, ref);
		    } else if (typeString.toLowerCase().contains("id")) {
		        return objectLocator(LocatorType.ID, ref);
		    } else if (typeString.toLowerCase().contains("link")) {
		        return objectLocator(LocatorType.LINK, ref);
		    } else if (typeString.toLowerCase().contains("name")) {
		        return objectLocator(LocatorType.NAME, ref);
		    } else if (typeString.toLowerCase().contains("tagname")) {
		        return objectLocator(LocatorType.TAGNAME, ref);
		    } else if (typeString.toLowerCase().contains("xpath")) {
		        return objectLocator(LocatorType.XPATH, ref);
		    } else {
		        return null;
		    }
	  }
	  
	  public static By getLocator(String ElementName) throws Exception {
	         //Read value using the logical name as Key
	         String locator = properties.getProperty(ElementName);
	         //Split the value which contains locator type and locator value
	         String locatorType = locator.split(":")[0];
	         String locatorValue = locator.split(":")[1];
	         //Return a instance of By class based on type of locator
	           if(locatorType.toLowerCase().equals("id"))
	                 return By.id(locatorValue);
	           else if(locatorType.toLowerCase().equals("name"))
	                 return By.name(locatorValue);
	           else if((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
	                 return By.className(locatorValue);
	           else if((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
	                 return By.className(locatorValue);
	           else if((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
	                 return By.linkText(locatorValue);
	           else if(locatorType.toLowerCase().equals("partiallinktext"))
	                 return By.partialLinkText(locatorValue);
	           else if((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
	                 return By.cssSelector(locatorValue);
	           else if(locatorType.toLowerCase().equals("xpath"))
	                 return By.xpath(locatorValue);
	           else
	                   throw new Exception("Locator type '" + locatorType + "' not defined!!");
	         }
	  
	  public static void waitTilPageLoads(String expectedURL){
		 // TODO:  make this dynamic - pass in amount of time to wait
			//wait til the newly navigated to page loads
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//check and see if we're at least on the correct page yet
			String currentURL = driver.getCurrentUrl();
			if (!currentURL.equals(expectedURL)){
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
	  }
	  
	  public void staleElementWait(common.BaseTestCase.LocatorType type, String webElementName, int timeout){
		  	  
		  final Condition hidden = new Condition("hidden", true) {
			    public boolean apply(WebElement element) {
			      try {
			        return !element.isDisplayed();
			      } catch (StaleElementReferenceException elementHasDisappeared) {
			        return true;
			      }
			    }

				@Override
				public boolean isTrue(Context arg0) {
					// TODO Auto-generated method stub
					return false;
				}
			  };
		
			  /*
		  WebDriverWait wait = new WebDriverWait(driver,10);
		    wait.until(new ExpectedCondition<Boolean>() {
		        public Boolean apply(WebDriver driver) {
		            WebElement button = driver.findElement(By
		                    .name("createForm:dateInput_input"));

		            if (button.isDisplayed())
		                return true;
		            else
		                return false;

		        }
		    });

		  */
		  
	  }
	  
	  public static boolean isElementPresent(By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		  }
	  
	  public boolean isAlertPresent() {
		    try {
		      driver.switchTo().alert();
		      return true;
		    } catch (NoAlertPresentException e) {
		      return false;
		    }
		  }
	  
	  public String closeAlertAndGetItsText() {
		    try {
		      Alert alert = driver.switchTo().alert();
		      String alertText = alert.getText();
		      if (acceptNextAlert) {
		        alert.accept();
		      } else {
		        alert.dismiss();
		      }
		      return alertText;
		    } finally {
		      acceptNextAlert = true;
		    }
		  }

}

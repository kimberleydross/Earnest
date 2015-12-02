package common;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.SeleneseTestBase;;

public class BaseTestCase extends SeleneseTestBase {
	
//	protected static WebDriver driver = new FirefoxDriver();
	protected static WebDriver driver;
	protected String baseUrl = "https://jungle-socks.herokuapp.com/";
	protected StringBuffer verificationErrors = new StringBuffer();
	
    public static enum LocatorType {
        CLASSNAME, CSS, ID, LINK, NAME, TAGNAME, XPATH ;
   }
	static Properties properties = new Properties();

	//protected String host = "http://cs.mfoundry.com/csapp1-dev/saml/";
	//protected String hostToRunOn = "CS";
	
	/*	
	//When running from Eclipse, set these variables.  See the setUp() method below for expected values
	protected static String hostToRunOn = "Saavis";
	protected static String host = "https://mb.mbankhost.com/mfb1/saml/";;
	protected static String browser = "*iehta";
*/
	//When building for deployment, these host variables will be set via UI
	protected static String host = null;
	protected static String hostToRunOn = null;
	protected static String browser = null;

	String phonenumber = "13335551212";
	final String fiusertoken = "demo11";
	final String bankcode = "tmb";

	@Override
	public void setUp() throws Exception {
	
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
		driver = new ChromeDriver();

		//default timeout is 30 seconds
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
	    
	    //maximize the browser window
	    driver.manage().window().maximize();
	    
//		selenium.start();

	

	}

	@Override
	public void tearDown() throws Exception {
		
	}
	
	public void setEnvironment(String hostSelected){
		hostToRunOn = hostSelected;
		
		if (hostToRunOn.equals("MySQL"))
			host = "https://vmdev2.mfoundry.com/mb21/saml/";

		if (hostToRunOn.equals("Oracle"))
			host = "https://vmdev2.mfoundry.com/mb21ora/saml/";

		if (hostToRunOn.equals("Saavis"))
			host = "https://mb.mbankhost.com/mfb1/saml/";   
		
		if (hostToRunOn.equals("CS"))
			host = "http://cs.mfoundry.com/csapp1-dev/saml/";
	}
	
	public void setBrowser(String browserSelected){
		browser = "*" + browserSelected.toLowerCase();
	}
}
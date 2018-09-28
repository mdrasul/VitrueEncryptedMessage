package driverutil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

public class initdriver 
{
	//===== Sauce Connect Credentials 
 	public static final String USERNAME = "MDRasul";
 	public static final String ACCESS_KEY = "1df585b2-88bc-4578-8a98-c920e9eaf383";
 	public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
 	public static  SessionId mySessionID;
 	static String myScreenResolutionCapability = "screenresolution";

 	public static WebDriver driver;

 	
 	public static WebDriver getlocaldriver()
 	{
		//DesiredCapabilities cap=null;
		//cap = DesiredCapabilities.chrome();	
		//myBrowserCapability.setCapability(myScreenResolutionCapability,"1600x1200"); 
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//SeleniumDrivers//chromedriver");
		driver= new ChromeDriver();
		return driver;
 		
 	}
	
	public static RemoteWebDriver getRemoteWebDriver(String browserName) throws MalformedURLException 
	{	
		DesiredCapabilities cap=null;
		if(browserName.equals("Firefox"))
		{
			cap = DesiredCapabilities.firefox();
			cap.setCapability("name", browserName);
			cap.setBrowserName("firefox");
			cap.setJavascriptEnabled(true);
			cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		}
		else if(browserName.equals("Chrome"))
		{
			 cap = DesiredCapabilities.chrome();
			 cap.setCapability("name", browserName);
			 cap.setBrowserName("chrome");
			 cap.setCapability(myScreenResolutionCapability,"1600x1200");    			
			 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		}
		else if(browserName.equals("IE"))
		{
			 cap = DesiredCapabilities.internetExplorer();
			 cap.setCapability("name", browserName);
			// cap.setBrowserName("internetExplorer");
			 cap.setCapability(myScreenResolutionCapability,"1600x1200");    	
			 //cap.setCapability("version", "11");
			 cap.setCapability("platform", "Windows 7");

			 //cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		}
	
		
		cap.setCapability("scriptName", "LoginToeBayTest");
		
		RemoteWebDriver webdriver = new RemoteWebDriver(new URL(URL), cap);
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webdriver.manage().window().maximize();
		mySessionID = ((RemoteWebDriver)webdriver).getSessionId();	

		
		//RemoteWebDriver webdriver = new RemoteWebDriver(new URL("https://" + host + "+/nexperience/perfectomobile/wd/hub"), capabilities);
		
		return webdriver;
	
	}

}

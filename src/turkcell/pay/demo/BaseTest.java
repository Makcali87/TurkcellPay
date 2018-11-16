package turkcell.pay.demo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BaseTest {

	public static AndroidDriver<WebElement> driver;
	public String optVal = null;
	public static String mobileNumber = "532014285";// Enter your mobile number
	public static String userCode = "2597";// Enter your user code
	public static String device = "Android";
	public static String deviceName = "Mehmet";
	public static String platformVersion = "8.0";
	public static String platformName = "Android";
	public static String appPackage = "com.turkcell.paycell";
	public static String appActivity = "com.turkcell.paycell.ui.main.controller.MainActivity";
	public static String path = "C:/Users/makcali/Desktop/application/com-turkcell-paycell-9003-41767899-ea380ad4e21c1c14c26c37fff10aac01.apk";
	File classpathRoot = new File(System.getProperty("user.dir"));
	File appDir = new File(classpathRoot, "/apk");
	File app = new File(appDir, "com-turkcell-paycell-9003-41767899-ea380ad4e21c1c14c26c37fff10aac01");

	@BeforeTest
	public void setUp() throws MalformedURLException {

		DesiredCapabilities capabilites = new DesiredCapabilities();
		capabilites.setCapability("device", device);
		capabilites.setCapability("deviceName", deviceName);
		capabilites.setCapability("platformVersion", platformVersion);
		capabilites.setCapability("platformName", platformName);
		capabilites.setCapability("path", path);
		capabilites.setCapability("appPackage", appPackage);
		capabilites.setCapability("appActivity", appActivity);

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilites);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@AfterTest

	public void tearDown() throws Exception {
		driver.quit();
	}
}

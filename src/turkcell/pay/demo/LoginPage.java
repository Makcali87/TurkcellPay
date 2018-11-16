package turkcell.pay.demo;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class LoginPage extends BaseTest {

	public static String mobileNumber = "532014285";// Enter your mobile number
	public static String userCode = "2597";// Enter your user code


	@Test(priority = 1)

	public void login() throws MalformedURLException {

		driver.findElement(By.id("com.turkcell.paycell:id/fragment_welcome_launcher_video_start")).click();

	}

	@Test(priority = 2)
	public void setTelNo() throws InterruptedException {

		// Enter mobile number and click "Devam button"
		driver.findElement(By.id("com.turkcell.paycell:id/two_digit_frame")).sendKeys(mobileNumber);
		driver.hideKeyboard();
		Thread.sleep(2000);

		driver.findElement(By.id("com.turkcell.paycell:id/fragment_phone_number_go_btn")).click();

	}

	// sms code is taken from google messages app
	@Test(priority = 3)
	public void setSmsCode() throws InterruptedException {

		Thread.sleep(10000);
		System.out.println("Launch Message app");
		driver.startActivity("com.google.android.apps.messaging",
				"com.google.android.apps.messaging.ui.ConversationListActivity", null, null);
		Thread.sleep(5000);
		WebElement list = driver.findElement(By.id("android:id/list"));
		List<WebElement> msg = list.findElements(By.id("com.google.android.apps.messaging:id/conversation_snippet"));
		System.out.println("Turkcell code Message :-" + msg.get(0).getText());
		String opt[] = msg.get(0).getText().split("\\s+");
		optVal = opt[0];
		String testString = optVal;
		System.out.println("code :- " + optVal);

		driver.startActivity("com.turkcell.paycell", "com.turkcell.paycell.ui.main.controller.MainActivity", null,
				null);
		driver.findElement(By.id("com.turkcell.paycell:id/two_digit_frame")).sendKeys(mobileNumber);
		driver.hideKeyboard();
		Thread.sleep(2000);
		driver.findElement(By.id("com.turkcell.paycell:id/fragment_phone_number_go_btn")).click();

		char[] charArray = optVal.toCharArray();

		for (int i = 0; i < testString.length(); i++) {
			String s = Character.toString(charArray[i]);
			System.out.println(s);
			System.out.println(i + 1);
			WebElement code = driver.findElement(By.id("com.turkcell.paycell:id/otpView_tv" + i + 1));
			code.sendKeys(s);
		}

	}

	// After sms code, it should set user password

	@Test(priority = 4)
	public void setUserCode() throws InterruptedException {

		// (After sms code, it asks user code
		driver.findElement(By.id("com.turkcell.paycell:id/passwordView_et")).sendKeys(userCode);
		driver.hideKeyboard();
		Thread.sleep(2000);

	}

}

package cucumber.appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;


public class AppTest {
	
	WebDriver driver;

	@Given("^브라우저를 열고 앱을 실행 한다\\.$")
	public void 브라우저를_열고_앱을_실행_한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    return;
	}

	@When("^유효한 계정과 비밀번호를 가지고 로그인 한다\\.$")
	public void 유효한_계정과_비밀번호를_가지고_로그인_한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    return;
	}

	@Then("^로그인을 성공 한다\\.$")
	public void 로그인을_성공_한다() throws Throwable {
		// Created object of DesiredCapabilities class.
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set android deviceName desired capability. Set your device name.
        capabilities.setCapability("deviceName", "XT1562");

        // Set BROWSER_NAME desired capability. It's Android in our case here.
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

        // Set android VERSION desired capability. Set your mobile device's OS version.
        capabilities.setCapability(CapabilityType.VERSION, "6.0.1");

        // Set android platformName desired capability. It's Android in our case here.
        capabilities.setCapability("platformName", "Android");

        // Set android appPackage desired capability. It is
        // com.android.calculator2 for calculator application.
        // Set your application's appPackage if you are using any other app.
        capabilities.setCapability("appPackage", "com.android.calculator2");

        // Set android appActivity desired capability. It is
        // com.android.calculator2.Calculator for calculator application.
        // Set your application's appPackage if you are using any other app.
        capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

        // Created object of RemoteWebDriver will all set capabilities.
        // Set appium server address and port number in URL string.
        // It will launch calculator app in android device.
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 
		// Click on DELETE/CLR button to clear result text box before running test.
        driver.findElements(By.xpath("//android.widget.Button")).get(0).click();
        // Click on number 7 button.
        driver.findElement(By.name("7")).click();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

	}

}

package cucumber.appium;

import cucumber.appium.AppSetting;
import cucumber.appium.Property;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class ExampleAppTest {
	private StringBuffer verificationErrors;
	
	@When("^\"([^\"]*)\" 을 배포 한다\\.$")	
	public void 을_배포_한다(String serviceName) throws Throwable {
		System.out.println("Error StringBuffer init....");
		verificationErrors = new StringBuffer();
		
		Property.initProperty(serviceName);
		AppSetting.initSingleDriver();
	}

	@Given("^\"([^\"]*)\" 을 실행 시킨다\\.$")
	public void 을_실행_시킨다(String serviceName) throws Throwable {
	    // Not yet ...
	    return;
	}

}

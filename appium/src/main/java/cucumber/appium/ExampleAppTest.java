package cucumber.appium;

import cucumber.appium.AppSetting;
import cucumber.appium.Property;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.sikuli.script.*;

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
	    return;
	}
	
	// sikuli을 이용한 첫화면 검증을 시도 한다.
	@Then("^\"([^\"]*)\" 의 첫화면을 검증 한다\\.$")
	public void 의_첫화면을_검증_한다(String arg1) throws Throwable {
		Screen sn = new Screen();
        try{
        		sn.capture(sn.getBounds());
                sn.wait("src/img/exam_main.png");
                sn.click("src/img/exam_1.png", 0);
                sn.wait("src/img/exam_1_green.png");
                //sn.type(null, "hello world\n", 0);
        }
        catch(FindFailed e){
                e.printStackTrace();
        }
	}

}

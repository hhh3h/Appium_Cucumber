package cucumber.appium;

import cucumber.appium.ImageHandler;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ImageTest {
	@Then("^\"([^\"]*)\" 가 맞는지 확인 한다\\.$")
	public void 가_맞는지_확인_한다(String image) throws Throwable {
		image = Property.convertVariableToValue(image);
		System.out.println("Check your image that exists: " + image);
		ImageHandler imgHdr = new ImageHandler();
		imgHdr.validateImage(image);
	}
	
	@Then("^\"([^\"]*)\" 를 선택 한다\\.$")
	public void 를_선택_한다(String btn_image) throws Throwable {
		btn_image = Property.convertVariableToValue(btn_image);
		System.out.println("Select your button: " + btn_image);
		Property.initProperty(btn_image);
		ImageHandler imgHdr = new ImageHandler();
		imgHdr.selectImage(btn_image);
	}

	@When("^앱은 이미 배포가 되어 있다\\.$")
	public void 앱은_이미_배포가_되어_있다() throws Throwable {
	    
	}

	@Then("^만약 테스트가 진행 된다면 Exception을 발생 시킨다\\.$")
	public void 만약_테스트가_진행_된다면_Exception을_발생_시킨다() throws Throwable {
		throw new PendingException();
	}

}

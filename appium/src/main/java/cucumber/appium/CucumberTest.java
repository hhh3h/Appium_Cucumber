package cucumber.appium;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import cucumber.appium.SharedData;
import cucumber.appium.RestfulApiUtils;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import cucumber.appium.HttpHeader;
import cucumber.appium.Property;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class CucumberTest {
	@Given("^\\(먼저\\) 컴퓨터 전체 무게는 (\\d+)kg 일거야$")
	public void 먼저_컴퓨터_전체_무게는_kg_일거야(int arg1) throws Throwable {
		// 숫자는 무조건 변수로 사용할 수 있다. 내가 사용한 숫자를 그대로 argument로 받는다.
		System.out.printf("숫자 30을 이용한 결과값은 그대로 : %s\n", arg1);
	}

	@When("^\\(조건\\) 컴퓨터 전체를 저울위에 올려 놓고 측정했을때$")
	public void 조건_컴퓨터_전체를_저울위에_올려_놓고_측정했을때() throws Throwable {	    
	    return;
	}

	@Then("^\\(그러면\\) 결과는 컴퓨터 본체의 무게만 (\\d+)kg 이었어$")
	public void 그러면_결과는_컴퓨터_본체의_무게만_kg_이었어(int arg1) throws Throwable {	    
	    return;
	}

	@Then("^\\(그리고\\) 그리고 모니터 무게는 (\\d+)kg 이었지$")
	public void 그리고_그리고_모니터_무게는_kg_이었지(int arg1) throws Throwable {	    
	    return;
	}

	@Then("^\\(그리고\\) 그리고 키보드와 마우스 무게는 (\\d+)kg 이었어$")
	public void 그리고_그리고_키보드와_마우스_무게는_kg_이었어(int arg1) throws Throwable {	    
	    return;
	}

	@Then("^\\(그러나\\) 그러나 컴퓨터 전체 무게를 맞추지는 못했어$")
	public void 그러나_그러나_컴퓨터_전체_무게를_맞추지는_못했어() throws Throwable {	    
	    return;
	}

	@Then("^\\(그러면\\) 실제 측정해 보니 컴퓨터 전체 무게는 (\\d+)kg 이야$")
	public void 그러면_실제_측정해_보니_컴퓨터_전체_무게는_kg_이야(int arg1) throws Throwable {	    
	    return;
	}

	@Given("^너의 이름은 <이름> 이야$")
	public void 너의_이름은_이름_이야() throws Throwable {	    
	    return;
	}

	@Given("^우리는 \"([^\"]*)\" 을 사먹을려고 해$")
	public void 우리는_을_사먹을려고_해(String arg1, DataTable arg2) throws Throwable {
	    // DataTable을 지정했기에 DataTable 자체를 받는다. 이를 코딩으로 어떻게 처리할지 처리 한다.
		System.out.printf("데이타테이블 자체를 가져온다. 이를 코딩하여 처리로직을 만든다.: %s %s\n", arg1,arg2);
	}

	@Then("^computer는 \"([^\"]*)\" 글로벌 ObjectMaps에 정의해 놨어$")
	public void computer는_글로벌_ObjectMaps에_정의해_놨어(String arg1) throws Throwable {	    
	    // ObjectMaps에 정의해 놓은 변수를 사용하기 위해서 입력받은 arg1을 ObjectMaps에 찾게 한 다음 그 값을 return해 준다.
		System.out.printf("ObjectMaps에 값을 찾게 한다.: %s\n", arg1);
	}

	@Then("^로컬변수로 \"([^\"]*)\" 은 등록해 놓았단다\\.$")
	public void 로컬변수로_은_등록해_놓았단다(String arg1) throws Throwable {	    
	    // $앱이름 변수로 받아서 이를 로컬변수에서 찾는 로직을 구현하면 된다.
		System.out.printf("앱이름 변수는 로컬변수에서 찾을 수 있도록 구현해 놓자: %s\n", arg1);
	}

	@When("^API Header 입력$")
	public void api_Header_입력(DataTable arg1) throws Throwable {
	    return;
	}

	@When("^URL : \"([^\"]*)\", 방식 : \"([^\"]*)\", \"([^\"]*)\" API 요청보내기$")
	public void url_방식_API_요청보내기(String arg1, String arg2, String arg3, String arg4) throws Throwable {	    
	    // 어떻게 변수가 할당되는지 확인 한다. 역시 코딩으로 API을 처리하는 로직을 구현해서 사용 한다.
		System.out.printf("API 연결을 코딩으로 구현하여 처리 한다.: %s %s %s %s\n", arg1,arg2,arg3,arg4);
	}

	@When("^API 응답값 \"([^\"]*)\"을 \"([^\"]*)\"에 저장$")
	public void api_응답값_을_에_저장(String arg1, String arg2) throws Throwable {
		// 로직을 구현하여 값을 어떻게 처리할 것인지 판단한다.
	    return;
	}

	@Then("^\"([^\"]*)\" 값을 출력 하라$")
	public void 값을_출력_하라(String arg1) throws Throwable {	    
	    return;
	}
}
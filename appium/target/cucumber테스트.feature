@Cucumber
Feature: Cucumber의 여러가지 기능들을 테스트 한다. 
	Scenario: BDD 테스트 케이스 작성을 위해 사용자가 이해하기 쉬운 형태로 케이스 작성을 한 예이다.
	Given (먼저) 컴퓨터 전체 무게는 30kg 일거야
	When (조건) 컴퓨터 전체를 저울위에 올려 놓고 측정했을때
	Then (그러면) 결과는 컴퓨터 본체의 무게만 20kg 이었어
	And (그리고) 그리고 모니터 무게는 5kg 이었지
	And (그리고) 그리고 키보드와 마우스 무게는 2kg 이었어
	But (그러나) 그러나 컴퓨터 전체 무게를 맞추지는 못했어
	Then (그러면) 실제 측정해 보니 컴퓨터 전체 무게는 27kg 이야
	
	Scenario: Cucumber의 DataTable 기능과 변수등을 확인해 본다.
	Given 너의 이름은 <이름> 이야
	And 우리는 "아이스크림" 을 사먹을려고 해
	|이름  |과자|
	|송혜교| 새우깡 |
	|이영애| 감자깡 |
	Then computer는 "computer" 글로벌 ObjectMaps에 정의해 놨어
	But 로컬변수로 "$앱이름" 은 등록해 놓았단다.

	Scenario: Doc String을 통해 URI을 정의하여 API의 기능을 테스트 해본다.(POST로 API을 확인할 수 있는 환경에서 진행해 본다.)
	When API Header 입력
    |name|value|
    |Authorization|Bearer 2DFyVQaPnVTbHw9IJ39C1/On3oaHbia0Y0Pt3gCESD3VqTB6c4JZRwFy9sfWhoQW|
    When URL : "http://alpha-push.netmarble.com/email/check", 방식 : "POST", "createGlobalPush.json" API 요청보내기
    """
    ?mailAddress=hhh3h@netmarble.com&serviceCode=${netm}
    """
    And API 응답값 "@.resultData.authorizationCode"을 "$Code"에 저장
    Then "$Code" 값을 출력 하라
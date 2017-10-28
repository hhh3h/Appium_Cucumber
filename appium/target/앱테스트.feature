@test
Feature: Facebook 로그인 시나리오

	Scenario: 유효한 계정을 가지고 로그인에 성공 한다.
	Given 브라우저를 열고 앱을 실행 한다.
	When 유효한 계정과 비밀번호를 가지고 로그인 한다.
	Then 로그인을 성공 한다.
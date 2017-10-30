@example
Feature: 샘플앱을 테스트 한다.

	Scenario: 테스트앱 을 배포하여 실행 시킨다.
	When "$앱이름" 을 배포 한다.
	And "$앱이름" 을 실행 시킨다.
	Then "$앱이름" 의 첫화면을 검증 한다.
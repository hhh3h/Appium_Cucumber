package cucumber.appium;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;
import cucumber.appium.Property;
import cucumber.appium.Selendroid2Driver;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * Appium Setting 클래스
 * @author hhh3h
 * @time 오후 3:12:23
 */
public class AppSetting {
	private static int currentDriverIndex = 0;
	private static List<Selendroid2Driver> drivers = new ArrayList<Selendroid2Driver>();

	/**
	 * 드라이버에 앱을 등록한다.
	 * 
	 * @return void
	 * @author hhh3h
	 */
	public static void initSingleDriver() throws Exception {
		String appiumServerUrl = Property.convertVariableToValue("$자동화서버주소");
		String appName = Property.convertVariableToValue("$앱이름");
		String deviceName = Property.convertVariableToValue("$디바이스이름");
		String platformName = Property.convertVariableToValue("$플랫폼종류");
		String platformVersion = Property.convertVariableToValue("$플랫폼버전");
		
		startApp(appiumServerUrl, appName, deviceName, platformName, platformVersion, null);
	}
	
	/**
	 * 멀티 드라이버에 앱을 등록한다.
	 * @author hhh3h
	 */
	public static void initMultiDriver(String deviceIndex) throws Exception {
		String appiumServerUrl = Property.convertVariableToValue("$" + deviceIndex + "번 디바이스 자동화서버주소");
		String appName = Property.convertVariableToValue("$" + deviceIndex + "번 디바이스 앱이름");
		String deviceName = Property.convertVariableToValue("$" + deviceIndex + "번 디바이스 이름");
		String platformName = Property.convertVariableToValue("$" + deviceIndex + "번 디바이스 플랫폼종류");
		String platformVersion = Property.convertVariableToValue("$" + deviceIndex + "번 디바이스 플랫폼버전");
		String udid = Property.convertVariableToValue("$" + deviceIndex + "번 디바이스 udid");
		
		startApp(appiumServerUrl, appName, deviceName, platformName, platformVersion, udid);
	}

	/**
	 * 앱을 시작한다.
	 * @author hhh3h
	 */
	public static void startApp(String APPIUM_SERVER_URL, String APP_NAME, String DEVICE_NAME, String PLATFORM_NAME, String PLATFORM_VERSION, String UDID) throws IOException {
		ClassLoader classLoader = AppSetting.class.getClassLoader();
		System.out.println("현재 경로 : " + System.getProperty("user.dir"));
		File apkFile = new File("/Marco.Hong/workspace/java/appium/src/test/resources/app/" + APP_NAME);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.APP, apkFile.getAbsolutePath());
		System.out.println("path: " + apkFile.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
		capabilities.setCapability(MobileCapabilityType.APPLICATION_NAME, APP_NAME);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
		capabilities.setCapability("newCommandTimeout", 8000);
		capabilities.setCapability("unicodeKeyboard", true); 
		capabilities.setCapability("resetKeyboard", true);
		
		if (UDID != null)
			capabilities.setCapability("udid", UDID);
		
		System.out.println("Device Name: " + DEVICE_NAME + ", APP Name: " + APP_NAME + ", Platform Name: " + PLATFORM_NAME + ", Platform Version: " + PLATFORM_VERSION + ", Appium Server Url: " + APPIUM_SERVER_URL);
		Selendroid2Driver driver = new Selendroid2Driver(new URL(APPIUM_SERVER_URL), capabilities);
		drivers.add(driver);
		System.out.println("Test Start");
	}

	/**
	 * 쓴 앱을 드라이버에서 해제한다.
	 * 
	 * @return void
	 * @author hhh3h
	 */
	public static void destroy() {
		Property.clearProperties();
		//BasicUtils.clear();
	
		for (Selendroid2Driver selendroid2Driver : drivers)
			selendroid2Driver.quit();
		
		drivers.clear();
	}
	
	public static void setCurrentDriver(int cdi) {
		
		currentDriverIndex = cdi;
	}
	
	/**
	 * 현재 실행중인 앱을 재시작합니다.
	 * @return void
	 * @author hhh3h
	 */
	public static void restartApp() throws Exception {
		System.out.println("** restart");
		Selendroid2Driver driver = getDriver();
		
		if (drivers.size() == 1) {
			driver.quit();
			drivers.clear();
			initSingleDriver();
		}
		else {
			for (Selendroid2Driver selendroid2Driver : drivers) {
				if (selendroid2Driver.equals(driver)) {
					driver.quit();
					drivers.remove(selendroid2Driver);
					break;
				}
			}
			initMultiDriver(currentDriverIndex + "");
		}
		
	}
	
	/**
	 * 앱을 설치합니다.
	 * apk 파일 명을 넣어주어야 합니다.
	 * @return void
	 * @author hhh3h
	 */
	public static void installApp(String appName) throws IOException {
		ClassLoader classLoader = AppSetting.class.getClassLoader();
		String filePath = "/appium/app/" + appName;
		String command = "adb -d install -r " + filePath;
		System.out.println("installApp - command: " + command);
		
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(command);
	}
	
	/**
	 * 앱을 삭제합니다.
	 * 패키지 명을 넣어주어야 합니다.
	 * @return void
	 * @author hhh3h
	 */
	public static void uninstallApp(String packageName) throws IOException {
		String command = "adb uninstall " + packageName;
		System.out.println("uninstallApp - command: " + command);
		
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(command);
	}
	
	/**
	 * 앱을 재설치합니다.
	 * 재설치 할 apk 파일 명과 패키지 명을 넣어주어야 합니다.
	 * @return void
	 * @author hhh3h
	 */
	public static void reinstallApp(String appName, String packageName) throws Exception {
		Selendroid2Driver driver = getDriver();
		if (drivers.size() == 1) {
			driver.quit();
			drivers.clear();
		}
		else {
			for (Selendroid2Driver selendroid2Driver : drivers) {
				if (selendroid2Driver.equals(driver)) {
					driver.quit();
					drivers.remove(selendroid2Driver);
					break;
				}
			}
		}
		uninstallApp(packageName);
		installApp(appName);
	
		if (currentDriverIndex == 0)
			initSingleDriver();
		else
			initMultiDriver(currentDriverIndex + "");
	}
	
	public static Selendroid2Driver getDriver() {
		
		return drivers.get(currentDriverIndex);
	}
}

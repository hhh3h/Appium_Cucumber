package cucumber.appium;

import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.remote.RemoteTouchScreen;

import io.appium.java_client.android.AndroidDriver;

public class Selendroid2Driver extends AndroidDriver implements HasTouchScreen {
	public RemoteTouchScreen touch;
	
	public Selendroid2Driver(URL remoteAddress,
			Capabilities desiredCapabilities) {
		super(remoteAddress, desiredCapabilities);
		touch = new RemoteTouchScreen(getExecuteMethod());

	}

	public TouchScreen getTouch() {
		return touch;
	}
}

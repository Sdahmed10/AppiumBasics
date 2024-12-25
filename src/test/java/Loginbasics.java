import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Loginbasics {
    AndroidDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName", "Android");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:platformVersion", "14");
        caps.setCapability("appium:deviceName", "emulator-5554");
        caps.setCapability("appium:app", "/Users/takiacademy/Desktop/Proverbial/src/reso/proverbial_android.apk");
        System.out.println("Appium server started..");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        // Initialisation de WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Timeout de 10 secondes
    }

    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}

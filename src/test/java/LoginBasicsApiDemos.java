import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LoginBasicsApiDemos {
    // Déclaration d'une variable statique pour le driver Android.
    static AndroidDriver driver;
    // Déclaration d'une variable pour gérer les attentes explicites.
    WebDriverWait wait;

    @BeforeTest // Méthode annotée exécutée avant les tests.
    public void setUp() throws MalformedURLException {
        //Création d'un objet pour définir les capacités souhaitées.
        DesiredCapabilities caps = new DesiredCapabilities();

        // Définition des capacités nécessaires pour la session Appium.
        caps.setCapability("appium:platformName", "Android"); // Plateforme cible : Android.
        caps.setCapability("appium:automationName", "UiAutomator2"); // Framework d'automatisation utilisé : UiAutomator2.
        caps.setCapability("appium:platformVersion", "14"); // Version du système d'exploitation Android.
        caps.setCapability("appium:deviceName", "emulator-5554"); // Nom de l'appareil ou de l'émulateur
        caps.setCapability("appium:app", "/Users/takiacademy/Desktop/Proverbial/src/reso/ApiDemos-debug.apk");
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
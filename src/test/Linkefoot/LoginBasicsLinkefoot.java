import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LoginBasicsLinkefoot {
    // Déclaration d'un objet AndroidDriver pour piloter l'appareil
    //C'est le principal objet Appium utilisé pour interagir avec l'application Android.
    AndroidDriver driver;
    // Déclaration d'un objet WebDriverWait pour les délais d'attente explicites
    WebDriverWait wait;

    @BeforeTest
    //Cette annotation indique que la méthode setUp() doit être exécutée avant chaque test. Elle est utilisée pour configurer l'environnement de test.
    //This annotation indicates that the setUp() method should be executed before each test. It is used to configure the test environment.
    public void setUp() throws MalformedURLException {
        //Un objet qui définit toutes les propriétés nécessaires pour initialiser la session Appium.
        //An object that defines all the properties needed to initialize the Appium session.
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName", "Android");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:platformVersion", "14");
        caps.setCapability("appium:deviceName", "emulator-5554");
        caps.setCapability("appium:app", "/Users/takiacademy/Desktop/Proverbial/src/reso/linkefoot_staging 36.apk");
        caps.setCapability("appium:autoGrantPermissions", true);
        caps.setCapability("appium:ignoreHiddenApiPolicyError", true);
        System.out.println("Appium server started..");

        //AndroidDriver(new URL("http://127.0.0.1:4723"), caps) : Crée une nouvelle instance de AndroidDriver et se connecte au serveur
        // Appium local (127.0.0.1:4723), en passant les capacités définies précédemment.

        //AndroidDriver(new URL("http://127.0.0.1:4723"), caps) : Creates a new instance of AndroidDriver
        // and connects to the local Appium server (127.0.0.1:4723), passing the capabilities defined previously.
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        // Initialisation de WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Timeout de 10 secondes
    }

    //Cette annotation indique que la méthode tearDown() doit être exécutée après chaque test.
    //driver.quit() : Ferme la session du driver et termine l'exécution de l'application mobile.

    //This annotation indicates that the tearDown() method should be executed after each test.
    //driver.quit() : Closes the driver session and terminates the execution of the mobile application.

    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}

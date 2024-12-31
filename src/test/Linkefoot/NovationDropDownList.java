import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;


public class NovationDropDownList extends SetUpApiDemos {
    public static final By App_Button = AppiumBy.xpath("//android.widget.TextView[@content-desc=\"App\"]");
    public static final By Menu_Button = By.xpath("//android.widget.TextView[@content-desc=\"Menu\"]");
    public static final By FromXml_Button = By.xpath("//android.widget.TextView[@content-desc=\"Inflate from XML\"]");
    public static final By Title_Button = By.xpath("//android.widget.Spinner[@resource-id=\"io.appium.android.apis:id/spinner\"]");
    public static final By Order_Button = By.xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Order\"]");

    // Méthode générique pour vérifier la visibilité et cliquer
    private void verifyAndClick(By locator, String buttonName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Attente explicite pour que l'élément soit visible et cliquable
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        Assert.assertTrue(element.isDisplayed(), buttonName + " n'est pas affiché !");
        // Message de succès
        System.out.println(buttonName + " est bien affiché et prêt à être cliqué.");
        element.click();
    }

    @Test
    public void DropDownList() {
        System.out.println("APK started successfully");

        // Vérification et clic sur chaque bouton
        verifyAndClick(App_Button, "Le bouton App");
        verifyAndClick(Menu_Button, "Le bouton Menu");
        verifyAndClick(FromXml_Button, "Le bouton FromXml");
        verifyAndClick(Title_Button, "Le bouton Title");
        verifyAndClick(Order_Button, "Le bouton Order");

        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        String screenshotDirectory = "screenshots";
        String screenshotPath = screenshotDirectory + "/screenshot.png";

        try {
            // Vérifie si le dossier existe ou le crée
            Path directoryPath = Paths.get(screenshotDirectory);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath); // Crée le répertoire si inexistant
                System.out.println("Directory created: " + screenshotDirectory);
            }

            // Sauvegarde la capture d'écran
            Files.copy(srcFile.toPath(), Paths.get(screenshotPath));
            System.out.println("Screenshot saved at: " + screenshotPath);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }

    }
}

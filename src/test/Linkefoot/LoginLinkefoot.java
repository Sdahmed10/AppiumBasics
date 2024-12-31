import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoginLinkefoot extends LoginBasicsLinkefoot {
    private static final By SeConnecter_Button = AppiumBy.xpath("//android.view.View[@content-desc=\"Se connecter\"]");
    private static final By Email_Button = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]");
    private static final By Mdp_Button = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]");
    private static final By Login_Button = AppiumBy.xpath("(//android.view.View[@content-desc=\"Se connecter\"])[2]");
    private static final By Communauté_button = AppiumBy.xpath("//android.view.View[@content-desc=\"Communauté\"]");

    @Test
    public void LoginLinkefoot(){
        wait.until(ExpectedConditions.elementToBeClickable(SeConnecter_Button)).click();

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(Email_Button));
        Assert.assertTrue(emailField.isEnabled(), "Le champ email n'est pas activé.");
        emailField.click(); // Cliquez pour activer le champ avant d'utiliser sendKeys
        emailField.sendKeys("fifa");
        driver.hideKeyboard();

        WebElement mdpField = wait.until(ExpectedConditions.visibilityOfElementLocated(Mdp_Button));
        Assert.assertTrue(mdpField.isEnabled(), "Le champ email n'est pas activé.");
        mdpField.click(); // Cliquez pour activer le champ avant d'utiliser sendKeys
        mdpField.sendKeys("12345678@Aa");
        driver.hideKeyboard();

        WebElement Login = wait.until(ExpectedConditions.visibilityOfElementLocated(Login_Button));
        Login.click();


        wait.until(ExpectedConditions.elementToBeClickable(Communauté_button)).click();


        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        String screenshotDirectory = "screenshots";
        String screenshotPath = screenshotDirectory + "/screenshotLinkefoot.png";

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

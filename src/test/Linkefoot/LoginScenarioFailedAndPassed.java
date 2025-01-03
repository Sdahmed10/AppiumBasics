// Importation des bibliothèques nécessaires
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Classe principale de test pour la fonctionnalité de connexion
public class LoginScenarioFailedAndPassed extends LoginBasicsLinkefoot {

    private static final By SeConnecter_Button = AppiumBy.xpath("//android.view.View[@content-desc=\"Se connecter\"]");
    private static final By Email_Button = AppiumBy.xpath("//android.widget.EditText[1]");
    private static final By Mdp_Button = AppiumBy.xpath("//android.widget.EditText[2]");
    private static final By Login_Button = AppiumBy.xpath("(//android.view.View[@content-desc=\"Se connecter\"])[2]");
    private static final By Communauté_button = AppiumBy.xpath("//android.view.View[@content-desc=\"Communauté\"]");
    private static final By Profile_Button = AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"Profile\n" +
            "Onglet 5 sur 5\"]");
    private static final By Deconnexion_Button = AppiumBy.xpath("//android.view.View[@content-desc=\"Déconnexion\"]");


    //annotation utilisée dans TestNG pour fournir des données de test à une méthode de test.
    //Elle permet d'exécuter une même méthode de test plusieurs fois avec des ensembles de données différents
    @DataProvider(name = "loginScenarios")
    public Object[][] loginScenarios() {
        return new Object[][]{
                {"fifa", "12345678@Aa", true}, // Scénario valide
                {"fif", "2345678@Aa", false},  // Email et mot de passe incorrectes
                {"", "12345678@Aa", false},    // Champ email vide
                {"fifa", "", false},           // Champ mot de passe vide
                {"","", false}                 // champ email et mot de passes vides
        };
    }

    @Test(dataProvider = "loginScenarios")
    public void testLoginScenarios(String email, String password, boolean expectedSuccess) {
        try {
            // Cliquer sur le bouton "Se connecter"
            wait.until(ExpectedConditions.elementToBeClickable(SeConnecter_Button)).click();

            // Remplir le champ email
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(Email_Button));
            emailField.click();
            emailField.clear();
            emailField.sendKeys(email);
            driver.hideKeyboard();

            // Remplir le champ mot de passe
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(Mdp_Button));
            passwordField.click();
            passwordField.clear();
            passwordField.sendKeys(password);
            driver.hideKeyboard();

            // Cliquer sur le bouton de connexion
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(Login_Button));
            loginButton.click();


            if (expectedSuccess) {
                // Vérifier la présence de la page "Communauté"
                wait.until(ExpectedConditions.elementToBeClickable(Communauté_button));
                takeScreenshot("login_success_" + email);
                System.out.println("Connexion réussie avec l'email : " + email);

                // Cliquer sur profile
                wait.until(ExpectedConditions.visibilityOfElementLocated(Profile_Button)).click();

                // Scroll down to the Deconnexion_BUTTON
                scrollToElement(Deconnexion_Button);
                //click on Deconnexion button
                driver.findElement(Deconnexion_Button).click();

                // Effectuer la déconnexion
                WebElement deconnexionButton = wait.until(ExpectedConditions.elementToBeClickable(Deconnexion_Button));
                deconnexionButton.click();
                takeScreenshot("logout_success_" + email);
                System.out.println("Déconnexion réussie après connexion.");
            } else {
                // Si connexion échouée, vérifier qu'un message d'erreur apparaît
                takeScreenshot("login_failure_" + email);
                System.out.println("Connexion échouée avec l'email : " + email);
                Assert.fail("Connexion échouée comme attendu pour : " + email);
            }

        } catch (Exception e) {
            takeScreenshot("error_" + email);
            System.err.println("Erreur lors de la tentative de connexion : " + e.getMessage());
        }
    }

    private void takeScreenshot(String screenshotName) {
        String screenshotDirectory = "screenshots";
        String screenshotPath = screenshotDirectory + "/" + screenshotName + ".png";

        try {
            File srcFile = driver.getScreenshotAs(OutputType.FILE);
            Path directoryPath = Paths.get(screenshotDirectory);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            Files.copy(srcFile.toPath(), Paths.get(screenshotPath));
            System.out.println("Capture d'écran enregistrée à : " + screenshotPath);
        } catch (IOException e) {
            System.err.println("Échec de l'enregistrement de la capture d'écran : " + e.getMessage());
        }
    }
    // Method to scroll to the element
    public void scrollToElement (By elementLocator){
        AndroidDriver driver = (AndroidDriver) this.driver;
        // Get the content-desc value from the locator to construct UiSelector
        String contentDesc = elementLocator.toString().split("content-desc=\"")[1].split("\"")[0];
        // Create a UiScrollable object and use the content-desc value
        String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"" + contentDesc + "\"));";
        // Execute the scroll command
        driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));
        System.out.println("Scrolled down to the element.");
    }
}


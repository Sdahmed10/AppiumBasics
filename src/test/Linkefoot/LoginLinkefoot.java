// Importation des bibliothèques nécessaires
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


// Classe principale de test pour la fonctionnalité de connexion, étend une classe de base
public class LoginLinkefoot extends LoginBasicsLinkefoot {

    // Définition des localisateurs pour les éléments sur la page avec AppiumBy (utilisation de XPath pour localiser les éléments)
    private static final By SeConnecter_Button = AppiumBy.xpath("//android.view.View[@content-desc=\"Se connecter\"]");
    private static final By Email_Button = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]");
    private static final By Mdp_Button = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]");
    private static final By Login_Button = AppiumBy.xpath("(//android.view.View[@content-desc=\"Se connecter\"])[2]");
    private static final By Communauté_button = AppiumBy.xpath("//android.view.View[@content-desc=\"Communauté\"]");

    @Test // Annotation de test pour indiquer que la méthode suivante est un test

    // Méthode de test pour vérifier la fonctionnalité de connexion sur l'application Linkefoot
    public void LoginLinkefoot(){

        // Attendre que le bouton de connexion soit cliquable et le cliquer
        wait.until(ExpectedConditions.elementToBeClickable(SeConnecter_Button)).click();

        // Attendre que le champ email soit visible et s'assurer qu'il est activé
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(Email_Button));
        Assert.assertTrue(emailField.isEnabled(), "Le champ email n'est pas activé."); // Vérification si le champ est activé
        emailField.click(); // Cliquer sur le champ email pour le sélectionner avant d'y entrer du texte
        emailField.sendKeys("fifa"); // Entrer l'email "fifa" dans le champ
        driver.hideKeyboard(); // Masquer le clavier virtuel après avoir entré l'email

        // Attendre que le champ du mot de passe soit visible et s'assurer qu'il est activé
        WebElement mdpField = wait.until(ExpectedConditions.visibilityOfElementLocated(Mdp_Button));
        Assert.assertTrue(mdpField.isEnabled(), "Le champ email n'est pas activé."); // Vérification si le champ est activé
        mdpField.click(); // Cliquer sur le champ du mot de passe pour le sélectionner avant d'y entrer du texte
        mdpField.sendKeys("12345678@Aa"); // Entrer le mot de passe dans le champ
        driver.hideKeyboard(); // Masquer le clavier virtuel après avoir entré le mot de passe

        // Attendre que le bouton de connexion soit visible et cliquer dessus pour se connecter
        WebElement Login = wait.until(ExpectedConditions.visibilityOfElementLocated(Login_Button));
        Login.click();

        // Attendre que le bouton "Communauté" soit cliquable et cliquer dessus
        wait.until(ExpectedConditions.elementToBeClickable(Communauté_button)).click();

        // Prendre une capture d'écran après l'authentification et l'accès à la communauté
        File srcFile = driver.getScreenshotAs(OutputType.FILE); // Prendre la capture d'écran sous forme de fichier

        // Définir le répertoire où la capture d'écran sera sauvegardée
        String screenshotDirectory = "screenshots";
        String screenshotPath = screenshotDirectory + "/screenshotLinkefoot.png"; // Définir le chemin où la capture sera enregistrée

        try {
            // Vérifie si le dossier "screenshots" existe, sinon le crée
            Path directoryPath = Paths.get(screenshotDirectory);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath); // Créer le répertoire si inexistant
                System.out.println("Directory created: " + screenshotDirectory);
            }

            // Sauvegarder la capture d'écran dans le répertoire défini
            Files.copy(srcFile.toPath(), Paths.get(screenshotPath));
            System.out.println("Screenshot saved at: " + screenshotPath); // Affichage du message de succès

        } catch (IOException e) { // Gestion des exceptions si la sauvegarde échoue
            System.err.println("Failed to save screenshot: " + e.getMessage()); // Affichage de l'erreur
        }
    }
}

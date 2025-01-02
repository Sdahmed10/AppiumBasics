import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class LongPressAndMultiSelection extends SetUpApiDemos {

    private static final By Views_Button = AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Views\"]");
    private static final By Grid_Button = AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Grid\"]");
    private static final By SelectionMode_Button = AppiumBy.xpath("//android.widget.TextView[@content-desc=\"3. Selection Mode\"]");
    private static final By Folder_Button = AppiumBy.xpath("//android.widget.GridView[@resource-id=\"io.appium.android.apis:id/myGrid\"]/android.widget.FrameLayout[18]/android.widget.ImageView");
    private static final By Gmail_Button = AppiumBy.xpath("//android.widget.GridView[@resource-id=\"io.appium.android.apis:id/myGrid\"]/android.widget.FrameLayout[13]/android.widget.ImageView");
    private static final By Youtube_Button = AppiumBy.xpath("//android.widget.GridView[@resource-id=\"io.appium.android.apis:id/myGrid\"]/android.widget.FrameLayout[8]/android.widget.ImageView");
    private static final By Settings_Button = AppiumBy.xpath("//android.widget.GridView[@resource-id=\"io.appium.android.apis:id/myGrid\"]/android.widget.FrameLayout[3]/android.widget.ImageView");
    private static final By Drive_Button = AppiumBy.xpath("//android.widget.GridView[@resource-id=\"io.appium.android.apis:id/myGrid\"]/android.widget.FrameLayout[4]/android.widget.ImageView");

    // Méthode générique pour vérifier la visibilité et cliquer
    public void clickAndVerify(WebElement element, String elementName) {
        element.click();
        Assert.assertTrue(element.isDisplayed(), elementName + " not visible on the screen");
    }


    @Test
    public void longPressAndMultiSelection() throws InterruptedException {
        System.out.println("Test exécuté avec succès");
        // Wait for the element to be visible before interacting with it
        wait.until(ExpectedConditions.visibilityOfElementLocated(Views_Button)).click();

        // Wait for the element to be visible before interacting with it
        wait.until(ExpectedConditions.visibilityOfElementLocated(Grid_Button)).click();

        // Wait for the element to be visible before interacting with it
        wait.until(ExpectedConditions.visibilityOfElementLocated(SelectionMode_Button)).click();

        // Localisation du premier élément
        WebElement folderButton = driver.findElement(Folder_Button);

        // Configurer l'entrée tactile W3C
        //Description : On crée un objet de type PointerInput représentant un dispositif d'entrée tactile (par exemple, un doigt sur un écran tactile).
        //Paramètres :
        //PointerInput.Kind.TOUCH : Indique qu'il s'agit d'une interaction tactile.
        //"name" : Nom arbitraire donné à cet outil (utile pour le suivi dans une séquence).
        //Objectif : Simuler des gestes tactiles comme appuyer, glisser, etc.
        PointerInput name = new PointerInput(PointerInput.Kind.TOUCH, "name");

        // Séquence pour un appui long
        Sequence longPress = new Sequence(name, 1);
        //Description :
        //Cette action déplace le "doigt virtuel" sur le centre du bouton folderButton.
        //Paramètres :
        //Duration.ZERO : Déplacement instantané (pas d'animation ou de délai).
        //PointerInput.Origin.viewport() : L'origine des coordonnées est l'écran visible (viewport).
        //folderButton.getRect().getX() : Coordonnée X de l'élément.
        //folderButton.getRect().getY() : Coordonnée Y de l'élément.
        //Les calculs ajoutent la moitié de la largeur et de la hauteur pour cibler le centre de l'élément.
        //Objectif : Placer le "doigt" au centre du bouton.
        longPress.addAction(name.createPointerMove(
                Duration.ZERO,
                PointerInput.Origin.viewport(),
                folderButton.getRect().getX() + folderButton.getRect().getWidth() / 2,
                folderButton.getRect().getY() + folderButton.getRect().getHeight() / 2
        ));
        //Description : Cette action simule une pression sur l'écran à la position actuelle du "doigt".
        //Paramètre :
        //PointerInput.MouseButton.LEFT.asArg() : Bien que ce soit un geste tactile, Selenium utilise cet argument pour représenter un appui unique.
        //Objectif : Commencer l'appui long.
        longPress.addAction(name.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        //Ajouter une pause pour maintenir l'appui
        longPress.addAction(new Pause(name, Duration.ofSeconds(2)));
        //Description : Cette action simule le relâchement du "doigt" après l'appui long. (createPointerUp)
        //Paramètre :
        //PointerInput.MouseButton.LEFT.asArg() : Relâcher l'appui sur l'écran.
        //Objectif : Terminer l'appui long.
        longPress.addAction(name.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Exécuter l'action
        //Objectif : Simuler l'appui long sur le bouton folderButton.
        driver.perform(Arrays.asList(longPress));

        // Assert to verify the long press on the folder button
        Assert.assertTrue(folderButton.isDisplayed(), "Folder button long press failed");

        // Wait for the element to be visible before interacting with it
        WebElement GmailButton = wait.until(ExpectedConditions.visibilityOfElementLocated(Gmail_Button));
        //Simple click
        GmailButton.click();
        // Assert that the button is displayed
        Assert.assertTrue(GmailButton.isDisplayed(), "Gmail button not visible on the screen");


        clickAndVerify(wait.until(ExpectedConditions.visibilityOfElementLocated(Gmail_Button)), "Gmail button");
        clickAndVerify(wait.until(ExpectedConditions.visibilityOfElementLocated(Youtube_Button)), "Youtube button");
        clickAndVerify(wait.until(ExpectedConditions.visibilityOfElementLocated(Settings_Button)), "Settings button");
        clickAndVerify(wait.until(ExpectedConditions.visibilityOfElementLocated(Drive_Button)), "Drive button");

//        // Wait for the element to be visible before interacting with it
//        WebElement YoutubeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(Youtube_Button));
//        //Simple click
//        YoutubeButton.click();
//        // Assert that the button is displayed
//        Assert.assertTrue(YoutubeButton.isDisplayed(), "Youtube button not visible on the screen");
//
//        // Wait for the element to be visible before interacting with it
//        WebElement SettingsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(Settings_Button));
//        //Simple click
//        SettingsButton.click();
//        // Assert that the button is displayed
//        Assert.assertTrue(SettingsButton.isDisplayed(), "Settings button not visible on the screen");
//
//        // Wait for the element to be visible before interacting with it
//        WebElement DriveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(Drive_Button));
//        //Simple click
//        DriveButton.click();
//        // Assert that the button is displayed
//        Assert.assertTrue(DriveButton.isDisplayed(), "Drive button not visible on the screen");


        System.out.println("Long Press And Multi Selection executé avec succés");


    }

}

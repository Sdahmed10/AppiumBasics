import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;

public class LongPressAndMultiSelection extends SetUpApiDemos {
    private static final By Views_Button = AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Views\"]");
    private static final By Grid_Button = AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Grid\"]");
    private static final By SelectionMode_Button = AppiumBy.xpath("//android.widget.TextView[@content-desc=\"3. Selection Mode\"]");
    private static final By Folder_Button = AppiumBy.xpath("//android.widget.GridView[@resource-id=\"io.appium.android.apis:id/myGrid\"]/android.widget.FrameLayout[18]/android.widget.ImageView");
    private static final By Gmail_Button = AppiumBy.xpath("//android.widget.GridView[@resource-id=\"io.appium.android.apis:id/myGrid\"]/android.widget.FrameLayout[13]/android.widget.ImageView");
    private static final By Youtube_Button = AppiumBy.xpath("//android.widget.GridView[@resource-id=\"io.appium.android.apis:id/myGrid\"]/android.widget.FrameLayout[14]/android.widget.ImageView");
    private static final By Settings_Button = AppiumBy.xpath("//android.widget.GridView[@resource-id=\"io.appium.android.apis:id/myGrid\"]/android.widget.FrameLayout[3]/android.widget.ImageView");
    private static final By Drive_Button = AppiumBy.xpath("//android.widget.GridView[@resource-id=\"io.appium.android.apis:id/myGrid\"]/android.widget.FrameLayout[4]/android.widget.ImageView");

    public void ClickAndVerify(WebElement element, String elementName) {
        element.click();
        Assert.assertTrue(element.isDisplayed(), elementName + "not visible on the screen");
    }

    @Test
    public void LongPressAndMultiSelection() {
        System.out.println("Test exécuté avec succés");
//wait for the element to be visible before interacting with it
        wait.until(ExpectedConditions.visibilityOfElementLocated(Views_Button)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Grid_Button)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(SelectionMode_Button)).click();

        //localisation du premier element
        WebElement folderButton = driver.findElement(Folder_Button);
        PointerInput name = new PointerInput(PointerInput.Kind.TOUCH, "name");

        //sequence pour un appui long
        Sequence longPress = new Sequence(name, 1);
        longPress.addAction(name.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(),
                folderButton.getRect().getX() + folderButton.getRect().getWidth() / 2,
                folderButton.getRect().getY() + folderButton.getRect().getHeight() / 2));


        longPress.addAction(name.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        //Ajouter une pause pour maintenir l'appui
        longPress.addAction(new Pause(name, Duration.ofSeconds(2)));
        longPress.addAction(name.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //exexuter l'action
        driver.perform(Arrays.asList(longPress));

        //Assert to verify the long press on the folder button
        Assert.assertTrue(folderButton.isDisplayed(), "Folder button long press failed");

        ClickAndVerify(wait.until(ExpectedConditions.visibilityOfElementLocated(Gmail_Button)), "Gmail button");
        ClickAndVerify(wait.until(ExpectedConditions.visibilityOfElementLocated(Youtube_Button)), "Youtube button");
        ClickAndVerify(wait.until(ExpectedConditions.visibilityOfElementLocated(Settings_Button)), "Settings button");
        ClickAndVerify(wait.until(ExpectedConditions.visibilityOfElementLocated(Drive_Button)), "Drive button");


        System.out.println(" long press and multi Selection executé avec succés");


    }
}

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Text extends Loginbasics {
    private static final By TEXT_BUTTON = AppiumBy.xpath("//android.widget.Button[@resource-id=\"com.lambdatest.proverbial:id/Text\"]");
    private static final By TEXT_BOX = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.lambdatest.proverbial:id/Textbox\"]");

    @Test
    public void testText() {
        try {
            System.out.println("Test exécuté avec succès");
            driver.findElement(TEXT_BUTTON).click();
            boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(TEXT_BOX)).isDisplayed();
            Assert.assertTrue(isDisplayed, "'Proverbial' n'est pas affiché sur l'écran." );
            System.out.println("'Proverbial' est bien affiché sur l'écran.");
        } catch (Exception e) {
            Assert.fail("Le test a échoué en raison d'une exception : " + e.getMessage());
        }
    }
}

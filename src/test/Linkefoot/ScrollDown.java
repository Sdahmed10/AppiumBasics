import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScrollDown extends LoginBasicsApiDemos {
    private static final By VIEWS_BUTTON = AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Views\"]");
    private static final By VISIBILITY_BUTTON = AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Visibility\"]");
    private static final By VIS_BUTTON = AppiumBy.xpath("//android.widget.Button[@content-desc=\"Vis\"]");

    @Test
    public void testText() {
        try {
            System.out.println("Test exécuté avec succès");
            driver.findElement(VIEWS_BUTTON).click();
            // Scroll down to the Visibility_BUTTON
            scrollToElement(VISIBILITY_BUTTON);
            //click on visibility button
            driver.findElement(VISIBILITY_BUTTON).click();
            // Wait for the Vis_BUTTON to be visible
            boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(VIS_BUTTON)).isDisplayed();
            Assert.assertTrue(isDisplayed, "'Vis' n'est pas affiché sur l'écran.");
            System.out.println("'Vis' est bien affiché sur l'écran.");
        } catch (Exception e) {
            Assert.fail("Le test a échoué en raison d'une exception : " + e.getMessage());
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

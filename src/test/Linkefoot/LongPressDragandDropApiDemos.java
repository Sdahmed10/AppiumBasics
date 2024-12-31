import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class LongPressDragandDropApiDemos extends SetUpApiDemos {
    private static final By VIEWS_BUTTON = AppiumBy.accessibilityId("Views");
    private static final By DRAG_BUTTON = AppiumBy.accessibilityId("Drag and Drop");
    private static final By DRAG1_BUTTON = AppiumBy.xpath("//android.view.View[@resource-id=\"io.appium.android.apis:id/drag_dot_1\"]");
    private static final By DRAGHERE_LOCATION = AppiumBy.xpath("//android.view.View[@resource-id=\"io.appium.android.apis:id/drag_dot_3\"]");

    @Test
    public void click() throws InterruptedException {
        System.out.println("Apk éxécuté avec succés");
        driver.findElement(VIEWS_BUTTON).click();
        Thread.sleep(1000);
        driver.findElement(DRAG_BUTTON).click();
        // Locate draggable element and target location
        WebElement dragElement = driver.findElement(DRAG1_BUTTON);
        WebElement dropLocation = driver.findElement(DRAGHERE_LOCATION);

        // Perform long press and drag-and-drop using W3C Actions
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence longPressDragDrop = new Sequence(finger, 1);

        // Coordinates of drag element
        int startX = dragElement.getRect().getX() + dragElement.getRect().getWidth() / 2;
        int startY = dragElement.getRect().getY() + dragElement.getRect().getHeight() / 2;

        // Coordinates of drop location
        int endX = dropLocation.getRect().getX() + dropLocation.getRect().getWidth() / 2;
        int endY = dropLocation.getRect().getY() + dropLocation.getRect().getHeight() / 2;

        // Add actions to sequence: Long press, drag, and release
        longPressDragDrop.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        longPressDragDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPressDragDrop.addAction(new Pause(finger, Duration.ofSeconds(2))); // Long press duration
        longPressDragDrop.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY)); // Drag to target
        longPressDragDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the action
        driver.perform(List.of(longPressDragDrop));

        System.out.println("Drag and drop completed successfully");


    }
}


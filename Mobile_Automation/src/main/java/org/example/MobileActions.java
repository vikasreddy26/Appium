package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;

public class MobileActions {

    Waits waits;

    public MobileActions() {
        waits = new Waits();
    }


    public void click(By mobileElement) {
        waits.waitUntilTheElementLocated(mobileElement);
        Base.getDriver().findElement(mobileElement).click();
    }


    public void clear(By mobileElement) {
        waits.waitUntilTheElementLocated(mobileElement);
        Base.getDriver().findElement(mobileElement).clear();
    }


    public void sendKeys(By mobileElement, String value) {
        waits.waitUntilTheElementLocated(mobileElement);
        Base.getDriver().findElement(mobileElement).clear();
        Base.getDriver().findElement(mobileElement).sendKeys(value);
    }

    public void lockDevice(int seconds) {
        AndroidDriver<MobileElement> androidDriver = (AndroidDriver<MobileElement>) Base.getDriver();
        androidDriver.lockDevice(Duration.ofSeconds(seconds));
    }

    public void hideKeyBoard() {
        AndroidDriver<MobileElement> androidDriver = (AndroidDriver<MobileElement>) Base.getDriver();
        if (androidDriver.isKeyboardShown()) {
            androidDriver.hideKeyboard();
        }
    }

    public void UnlockDevice() {
        AndroidDriver<MobileElement> androidDriver = (AndroidDriver<MobileElement>) Base.getDriver();
        if (androidDriver.isDeviceLocked()) {
            androidDriver.unlockDevice();
        }
    }

    public void swipeByElementId(By by, String direction) {
        waits.waitUntilTheElementLocated(by);
        MobileElement element = Base.getDriver().findElement(by);
        Base.getDriver().executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(), "direction", direction, "percent", 0.75
        ));
    }

    public void scrollByElementId(By by, String direction) {
        waits.waitUntilTheElementLocated(by);
        MobileElement element = Base.getDriver().findElement(by);
        boolean canScrollMore = (Boolean) Base.getDriver().executeScript("mobile: scrollGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "percent", 3.0
        ));
    }

    public void swipeHorizontal(By element, double startPercentage, double endPercentage) {
        waits.waitUntilTheElementLocated(element);
        MobileElement mobileElement = Base.getDriver().findElement(element);
        // Get the size of the element
        Dimension size = mobileElement.getSize();
        // Calculate the start and end points for the swipe
        int startX = (int) (size.width * startPercentage);
        int endX = (int) (size.width * endPercentage);
        int y = size.height / 2;
        // Create a new instance of TouchAction
        TouchAction touchAction = new TouchAction(Base.getDriver());
        // Perform the horizontal swipe gesture
        touchAction.press(PointOption.point(startX, y))
                .waitAction()
                .moveTo(PointOption.point(endX, y))
                .release()
                .perform();
    }


    public String getText(By by) {
        waits.waitUntilTheElementLocated(by);
        return Base.getDriver().findElement(by).getText();
    }

    public boolean isDisplayed(By by) {
        waits.waitUntilTheElementLocated(by);
        return Base.getDriver().findElement(by).isDisplayed();
    }

    public boolean isEnabled(By by) {
        waits.waitUntilTheElementLocated(by);
        return Base.getDriver().findElement(by).isEnabled();
    }

    public boolean isSelected(By by) {
        waits.waitUntilTheElementLocated(by);
        return Base.getDriver().findElement(by).isSelected();
    }
}

package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;
import java.util.LinkedList;
import java.util.Set;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;


public class MobileActions {

    Waits waits;

    public MobileActions() {
        waits = new Waits();
    }


    public void click(By mobileElement) {
        waits.waitUntilTheElementLocated(mobileElement);
        Base.getDriver().findElement(mobileElement).click();
    }

    public void tap(MobileElement elementToTap){
        TouchAction touchAction = new TouchAction(Base.getDriver());
        touchAction.tap(PointOption.point(elementToTap.getCenter())).perform();
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

    public LinkedList<Integer> swipeHorizontalOnElement(By element, double startPercentage, double endPercentage) {
        waits.waitUntilTheElementLocated(element);
        MobileElement mobileElement = Base.getDriver().findElement(element);
        Dimension size = mobileElement.getSize();
        int startX = (int) (size.width * startPercentage);
        int endX = (int) (size.width * endPercentage);
        int y = size.height / 2;
        TouchAction touchAction = new TouchAction(Base.getDriver());
        touchAction.press(point(startX, y))
                .waitAction()
                .moveTo(point(endX, y))
                .release()
                .perform();
      LinkedList<Integer> l= new LinkedList<Integer>();
      l.add(startX);
      l.add(endX);
      l.add(y);
      return l;
    }


    public void swipeHorizontallyOnElementMultiple(By element, double startPercentage, double endPercentage,int counter){
        LinkedList<Integer> l =swipeHorizontalOnElement(element,startPercentage,endPercentage);
        int startX  = l.get(0);
        int endX = l.get(1);
        int y = l.get(2);
        for (int attempt = 1;attempt<=counter;attempt++){
            TouchAction touchAction = new TouchAction(Base.getDriver());
            touchAction.press(point(startX, y))
                    .waitAction()
                    .moveTo(point(endX, y))
                    .release()
                    .perform();
        }
    }


    public void scroll(double startPercentage, double endPercentage) {
        Dimension size = Base.getDriver().manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * startPercentage);//0.70
        int endY = (int) (size.height * endPercentage);//0.30
        TouchAction t = new TouchAction(Base.getDriver());
        t.press(point(startX, startY))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(startX, endY)).release()
                .perform();
    }


    public void scollTillElementFound(By element, double startPercentage, double endPercentage) {
        // Find the element you want to scroll to
        int maxScrollAttempts = 8;
        int scrollAttempts = 0;

        while (scrollAttempts < maxScrollAttempts) {
            try {
                if (isDisplayed(element)==true){
                    break;
                }
            } catch (Exception e) {
                scroll(startPercentage, endPercentage);
                System.out.println(scrollAttempts);
                scrollAttempts++;
            }
        }
    }

    public String getText(By by) {
        waits.waitUntilTheElementLocated(by);
        return Base.getDriver().findElement(by).getText();
    }

    public boolean isDisplayed(By by) {
//        waits.waitUntilTheElementLocated(by);
        return Base.getDriver().findElement(by).isDisplayed();
    }

    public boolean isEnabled(By by) {
        waits.waitUntilTheElementLocated(by);
        return Base.getDriver().findElement(by).isEnabled();
    }

    public void dragAndDrop(MobileElement a,MobileElement b){
        TouchAction action =new TouchAction(Base.getDriver());
        action.longPress(PointOption.point(a.getLocation().x, a.getLocation().y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(b.getLocation().x, b.getLocation().y))
                .release().perform();
    }

    public boolean isSelected(By by) {
        waits.waitUntilTheElementLocated(by);
        return Base.getDriver().findElement(by).isSelected();
    }

    public String getcontext(){
       return Base.getDriver().getContext();
    }

    public Set<String> getContextHandles(){
        return Base.getDriver().getContextHandles();
    }

    public void switchToWebView(){
        for (String contextHandle : getContextHandles()) {
            if (contextHandle.contains("WEBVIEW")) {
                Base.getDriver().context(contextHandle);
                break;
            }
        }
    }

}

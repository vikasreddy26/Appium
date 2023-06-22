package org.example;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {

    public void waitUntilTheElementLocated(By mobileElement) {
        WebDriverWait wait = new WebDriverWait(Base.getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mobileElement));
    }

    public void waitForElementClickabe(MobileElement mobileElement){
        WebDriverWait wait = new WebDriverWait(Base.getDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(mobileElement));
    }

    public boolean waitforInvisibilityOfElement(By mobileElement){
        WebDriverWait wait = new WebDriverWait(Base.getDriver(), 10);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(mobileElement));
    }

}

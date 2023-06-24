package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class PractiseTestClass  {

    Waits waits;
    MobileActions mobileActions;

    public PractiseTestClass() {
        waits = new Waits();
        mobileActions = new MobileActions();
    }

    By loginButton = By.xpath("//android.widget.Button[@content-desc=\"Log in\"]");
    By allowButton = By.id("com.android.permissioncontroller:id/permission_allow_button");
    By profileAvtar = By.id("com.instagram.android:id/tab_avatar");

    By photos = By.xpath("//android.widget.Button[@content-desc=\"3 photos by Mano vikas Reddy at row 2, column 2\"]");
    By enlargePhoto = By.xpath("//android.widget.FrameLayout[@content-desc=\"Photo 1 of 3 by Mano vikas Reddy, 68 likes\"]");

    By colaBeach = By.id("com.instagram.android:id/secondary_label");
    By loaction = By.xpath("//android.widget.TextView[@content-desc=\"Main beach Gokarna Karnataka\"]");
    By viewsButton = By.xpath("//android.widget.TextView[@content-desc=\"Views\"]");
    By WebView = By.xpath("//android.widget.TextView[@content-desc=\"WebView\"]");
    By lists = By.xpath("//android.widget.TextView[@content-desc=\"Lists\"]");
    By gallery = By.xpath("//android.widget.TextView[@content-desc=\"Gallery\"]");
    // By photos = By.xpath("//android.widget.TextView[@content-desc=\"1. Photos\"]");
    By galleryContainer = By.xpath("//android.widget.Gallery[@resource-id='io.appium.android.apis:id/gallery']/android.widget.ImageView[1]");

    @Test(priority = 1)
    public void test() {
        PractiseTestClass p = new PractiseTestClass();
        mobileActions.click(loginButton);
        //   mobileActions.click(allowButton);
        mobileActions.click(profileAvtar);
        mobileActions.click(photos);
        mobileActions.swipeHorizontallyOnElementMultiple(enlargePhoto, 0.8, 0.2, 1);

        //    mobileActions.swipeHorizontal(enlargePhoto,0.8,0.2);
//            mobileActions.click(viewsButton);
//            mobileActions.click(gallery);
//            mobileActions.click(photos);
//            mobileActions.scollTillElementFound(lists,0.70,0.30);
//            mobileActions.click(lists);
    }
}

package org.example;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class PractiseTestClass extends Base {

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

    @Test(priority = 1)
    public void test() {
        PractiseTestClass p = new PractiseTestClass();
        mobileActions.click(loginButton);
        mobileActions.click(allowButton);
        mobileActions.click(profileAvtar);
        mobileActions.click(photos);
        mobileActions.swipeHorizontal(enlargePhoto,0.8,0.2);
    }
}

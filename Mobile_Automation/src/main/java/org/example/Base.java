package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class Base
{
    public static AppiumDriver<MobileElement> driver = null;


    public URL startServer() {
        String nodePath = "C:\\Program Files\\nodejs\\node.exe";
        String appiumMainJSPath = "C:\\Users\\91809\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingAnyFreePort()
                .withAppiumJS(new File(appiumMainJSPath))
                .usingDriverExecutable(new File(nodePath))
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                .withArgument(GeneralServerFlag.RELAXED_SECURITY);
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
        service.start();

        if (service.isRunning()) {
            System.out.println("Appium server started successfully on " + service.getUrl());
        } else {
            System.out.println("Failed to start Appium server");
        }

        return service.getUrl();
    }

    @Test()
    public void setDriver() {
        startServer();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Oneplus Nord CE 5G");
        caps.setCapability("platformVersion", "13");
        caps.setCapability("appPackage", "com.instagram.android");
        caps.setCapability("appActivity", "com.instagram.mainactivity.LauncherActivity");
        caps.setCapability("udid", "ccb01f14");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("unlockType","pin");
        caps.setCapability("unlockKey","222696");
        driver = new AndroidDriver<MobileElement>(startServer(), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        lockDevice();
        UnlockDevice();
    }
    public static AppiumDriver<MobileElement> getDriver(){
        if(driver == null){
            try {
                System.out.println("Driver was not initialized");
            }catch (NullPointerException e){
                System.out.println(e);
            }
        }
        return driver;
    }

    @Test
    public void UnlockDevice(){
        AndroidDriver<MobileElement> androidDriver=(AndroidDriver<MobileElement>)getDriver();
        if (androidDriver.isDeviceLocked()){
            androidDriver.unlockDevice();
        }
    }

    @Test
    public void lockDevice(){
        AndroidDriver<MobileElement> androidDriver=(AndroidDriver<MobileElement>)getDriver();
        androidDriver.lockDevice(Duration.ofSeconds(4));
    }

    public void hideKeyBoard(){
        AndroidDriver<MobileElement> androidDriver=(AndroidDriver<MobileElement>)getDriver();
        if(androidDriver.isKeyboardShown()){
            androidDriver.hideKeyboard();
        }
    }

}

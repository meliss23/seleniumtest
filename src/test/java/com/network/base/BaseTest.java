package com.network.base;


import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.network.constant.Constants.WEB_URL;


public class BaseTest {


    static WebDriver webDriver = null;
    static JavascriptExecutor js = null;

    @Before
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("disable-notifications");
        chromeOptions.addArguments("disable-popup-blocking");
        setWebDriver(new ChromeDriver(chromeOptions));
        getWebDriver().navigate().to(WEB_URL);
        setJs((JavascriptExecutor) getWebDriver());
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public static void setWebDriver(WebDriver webDriver) {
        BaseTest.webDriver = webDriver;
    }

    public static JavascriptExecutor getJs() {
        return js;
    }

    public static void setJs(JavascriptExecutor js) {
        BaseTest.js = js;
    }

    @After
    public void tearDown() {
        getWebDriver().quit();
    }
}
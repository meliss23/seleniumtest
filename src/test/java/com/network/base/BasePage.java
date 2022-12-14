package com.network.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    WebDriver driver = null;
    WebDriverWait wait = null;
    JavascriptExecutor js = null;

    public BasePage(WebDriver driver, JavascriptExecutor js){
        this.driver=driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(60));
        this.js = js;
    }

    public WebElement findElement(By by){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return driver.findElement(by);
    }

    public void sendKeys(By by,String text){
        findElement(by).sendKeys(text);
    }

    public void click(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        findElement(by).click();

    }

    public String getText(By by){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return findElement(by).getText();
    }

    public void submit(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        findElement(by).submit();
    }

    public void executeScript(String script){
        js.executeScript(script);
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public void moveToElement(WebElement element) {
        new Actions(driver)
                .moveToElement(element)
                .perform();
    }
}
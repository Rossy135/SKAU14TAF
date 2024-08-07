package com.skillo.POM;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ISkillo {
    private final String BASE_URL = "http://training.skillo-bg.com:4200/";
    WebDriver driver;
    WebDriverWait wait;

    public ISkillo(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo(String pageURLsufix) {
        String currentURL = BASE_URL + pageURLsufix;
        driver.get(currentURL);
        waitPageTobeFullLoaded();
    }

    public void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        System.out.println("The user has clicked on element");
        waitPageTobeFullLoaded();
    }

    public void typeTextInField(WebElement element, String inputText) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(inputText);
        waitPageTobeFullLoaded();
    }

    public void waitPageTobeFullLoaded() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").equals("complete");
        System.out.println("DOM tree is fully loaded");
    }

    public boolean isUrlLoaded(String pageURL) {
        waitPageTobeFullLoaded();
        return wait.until(ExpectedConditions.urlContains(pageURL));
    }
}

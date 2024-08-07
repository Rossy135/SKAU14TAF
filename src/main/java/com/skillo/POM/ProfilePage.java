package com.skillo.POM;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.List;

public class ProfilePage extends ISkillo {
    @FindBy(id = "nav-link-profile")
    private WebElement navToProfileButton;
    @FindBy(xpath = "//div//h2")
    private WebElement userNameInMyProfile;
    @FindBy(xpath = "//app-post")
    private List<WebElement> postsInProfile;

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnProfileButton() {
        waitAndClick(navToProfileButton);
    }

    public String getUsername() {
        wait.until(ExpectedConditions.visibilityOf(userNameInMyProfile));
        return userNameInMyProfile.getText();
    }

    public int getPostCount() {
        return postsInProfile.size();
    }

    public void clickPost(int postIndex) {
        postsInProfile.get(postIndex).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").equals("complete");
    }

    public boolean isNavToProfileButtonShown() {
        boolean isButtonShown = false;
        try {
            wait.until(ExpectedConditions.visibilityOf(navToProfileButton));
            isButtonShown = true;
        } catch (TimeoutException e) {
            System.out.println("ERROR ! The navigation profile button was not presented to the user");
        }
        return isButtonShown;
    }
}




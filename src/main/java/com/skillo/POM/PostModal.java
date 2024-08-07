package com.skillo.POM;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PostModal extends ISkillo {
    @FindBy(css=".post-modal-img img")
    private WebElement image;
    @FindBy(xpath = "//a[@class=\"post-user\"]")
    private WebElement postCreator;
    @FindBy(xpath = "//label[@class =\"delete-ask\"]")
    private WebElement deletePostButton;
    @FindBy(xpath = "//button[contains(text(),\"Yes\")]")
    private WebElement confirmDeletingPost;
    @FindBy(xpath = "//button[contains(text(),\"No\")]")
    private WebElement cancelDeletingPost;

    public PostModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isImageVisible() {
        boolean isVisible = false;

        try {
            isVisible = wait.until(ExpectedConditions.visibilityOf(image)).isDisplayed();

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("ERROR : The file is not visible");
            isVisible = false;
        }

        return isVisible;
    }

    public String getPostUser() {
        wait.until(ExpectedConditions.visibilityOf(postCreator));
        return postCreator.getText();
    }

    public void clickOnBinIcon() {
        wait.until(ExpectedConditions.visibilityOf(deletePostButton));
        deletePostButton.click();
        waitPageTobeFullLoaded();
    }

    public void confirmDeletingPost() {
        wait.until(ExpectedConditions.visibilityOf(confirmDeletingPost));
        confirmDeletingPost.click();
        waitPageTobeFullLoaded();
    }
    public void cancelDeletingPost() {
        wait.until(ExpectedConditions.visibilityOf(cancelDeletingPost));
        cancelDeletingPost.click();
        waitPageTobeFullLoaded();
    }
}

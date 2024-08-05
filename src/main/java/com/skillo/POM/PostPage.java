package com.skillo.POM;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class PostPage extends ISkillo {

    private final String POST_PAGE_URL = "posts/create";
    @FindBy(css = "img.image-preview")
    private WebElement image;
    @FindBy(css = "input.input-lg")
    private WebElement imageTextElement;
    @FindBy(css = ".file[type='file']")
    private WebElement uploadField;
    @FindBy(name = "caption")
    private WebElement captionElement;
    @FindBy(id = "create-post")
    private WebElement createP0ostButton;
    @FindBy(id="toast-container")
    private WebElement PopUpDeletedPost;

    public PostPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    };

    public boolean isImageVisible() {
        boolean isVisible = false;

        try {
            isVisible = wait.until(ExpectedConditions.visibilityOf(image)).isDisplayed();

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("ERROR : The file is not visible");
            isVisible = false;
        };

        return isVisible;
    };

    public String getImageName() {
        String imageName = imageTextElement.getAttribute("placeholder");
        return imageName;
    };

    public void uploadPicture(File file) {
        uploadField.sendKeys(file.getAbsolutePath());
    };

    public void providePostCaption(String caption) {
        wait.until(ExpectedConditions.visibilityOf(captionElement));
        captionElement.sendKeys(caption);
    };

    public void clickCreatePostButton() {
        wait.until(ExpectedConditions.visibilityOf(createP0ostButton));
        createP0ostButton.click();
    };
    public boolean isImageDeleted() {
        boolean isDeleted = false;

        try {
            isDeleted = wait.until(ExpectedConditions.visibilityOf(PopUpDeletedPost)).isDisplayed();

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("ERROR : The image was not deleted");
            isDeleted = false;
        };

        return isDeleted;
    };
}

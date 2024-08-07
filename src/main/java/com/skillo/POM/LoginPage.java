package com.skillo.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends ISkillo {
    public static final String LOGIN_PAGE_SUFIX = "users/login";

    @FindBy(name = "usernameOrEmail")
    private WebElement usernameInputField;
    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordInputField;
    @FindBy(id = "sign-in-button")
    private WebElement loginFormSubmitButton;
    @FindBy(xpath = "//a[contains(.,'Register')]")
    private WebElement loginFormRegistrationLink;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void provideUserName(String userName) {
        typeTextInField(usernameInputField, userName);
    }

    public void providePassword(String userPassword) {
        typeTextInField(passwordInputField, userPassword);
    }

    public void clickSubmitButton() {
        waitAndClick(loginFormSubmitButton);
    }

    public void loginWithUserAndPassword(String userName, String password) {
        provideUserName(userName);
        providePassword(password);
        clickSubmitButton();
    }

    public void clickOnRegistrationLink(){
        waitAndClick(loginFormRegistrationLink);
    }
}
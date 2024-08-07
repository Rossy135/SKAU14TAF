package com.skillo.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends ISkillo   {

    @FindBy (xpath = "//input[@name=\"username\"]")
    private WebElement usernameInputField;
    @FindBy (xpath = "//input[@placeholder=\"email\"]")
    private WebElement emailInputField;
    @FindBy (xpath = "//*[@id=\"defaultRegisterFormPassword\"]")
    private WebElement passwordInputField;
    @FindBy (xpath = "//*[@id=\"defaultRegisterPhonePassword\"]")
    private WebElement confirmPasswordInputField;
    @FindBy (css = "#sign-in-button")
    private WebElement registrationSignInButton;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void provideUsername (String username) {
        typeTextInField(usernameInputField,username);
    }

    public void provideEmail (String email) {
        typeTextInField(emailInputField, email);
    }

    public void providePassword (String password) {
        typeTextInField(passwordInputField, password);
    }

    public void provideConfirmPassword (String password) {
        typeTextInField(confirmPasswordInputField, password);
    }

    public void clickOnSignInButton() {
        waitAndClick(registrationSignInButton);
    }

    public void fullRegistrationInputsAndActions (String username, String email, String password) {
        provideUsername(username);
        provideEmail(email);
        providePassword(password);
        provideConfirmPassword(password);
        clickOnSignInButton();
    }
}

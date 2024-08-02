package com.skilo.gui;

import com.skillo.POM.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.skillo.POM.LoginPage.LOGIN_PAGE_SUFIX;


public class LoginTest extends TestObject {

    @Test
    public void verifyAlreadyRegisteredUserCanSuccessfullyLogin() {

        final String USERNAME = "jussy_";
        final String PASSWORD = "Roska666.";
        final String HOME_PAGE_URL = "posts/all";
//        String URL  = getWebDriver().getCurrentUrl();


        HomePage homePage = new HomePage(super.getWebDriver());
        homePage.openHomePage();
        homePage.isUrlLoaded(HOME_PAGE_URL);
        homePage.clickOnNavigationLoginButton();
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.isUrlLoaded(LOGIN_PAGE_SUFIX);
        loginPage.provideUserName(USERNAME);
        loginPage.providePassword(PASSWORD);
        loginPage.clickSubmitButton();
        ProfilePage profilePage = new ProfilePage(super.getWebDriver());
        Assert.assertTrue(profilePage.isNavToProfileButtonShown());
        profilePage.clickOnProfileButton();
        Assert.assertEquals(USERNAME,profilePage.getUsername());
        Assert.assertTrue(homePage.isLogOutButtonShown());

    }


    @Test
    public void verifyAlreadyRegisteredUserCanNotSuccessfullyLoginWithWrongPassword() {

        final String USERNAME = "jussy_";
        final String PASSWORD = "WRONG_PASS";
        final String HOME_PAGE_URL = "posts/all";

        HomePage homePage = new HomePage(super.getWebDriver());
        homePage.openHomePage();
        homePage.waitPageTobeFullLoaded();
        homePage.isUrlLoaded(HOME_PAGE_URL);
        homePage.clickOnNavigationLoginButton();
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.waitPageTobeFullLoaded();
        loginPage.isUrlLoaded(LOGIN_PAGE_SUFIX);

        loginPage.provideUserName(USERNAME);
        loginPage.providePassword(PASSWORD);
        loginPage.clickSubmitButton();
        ProfilePage profilePage = new ProfilePage(super.getWebDriver());

        Assert.assertFalse(profilePage.isNavToProfileButtonShown(),"The user could log in successfully with wrong password");
        Assert.assertFalse(homePage.isLogOutButtonShown(),"The user could log in successfully with wrong password");

        }

    @Test
    public void verifyAlreadyRegisteredUserCanNotSuccessfullyLoginWithWrongUsername() {

        final String USERNAME = "WRONG";
        final String PASSWORD = "Roska666.";
        final String HOME_PAGE_URL = "posts/all";

        HomePage homePage = new HomePage(super.getWebDriver());
        homePage.openHomePage();
        homePage.waitPageTobeFullLoaded();
        homePage.isUrlLoaded(HOME_PAGE_URL);

        homePage.clickOnNavigationLoginButton();
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.waitPageTobeFullLoaded();
        loginPage.isUrlLoaded(LOGIN_PAGE_SUFIX);
        loginPage.provideUserName(USERNAME);
        loginPage.providePassword(PASSWORD);
        loginPage.clickSubmitButton();

        ProfilePage profilePage = new ProfilePage(super.getWebDriver());
        Assert.assertFalse(profilePage.isNavToProfileButtonShown(),"The user could log in successfully with wrong username");
        Assert.assertFalse(homePage.isLogOutButtonShown(),"The user could log in successfully with wrong username");

    }

    @Test
    public void verifyAlreadyRegisteredUserCanNotSuccessfullyLoginWithNotProvidedCredentials() {

        final String USERNAME = " ";
        final String PASSWORD = " ";
        final String HOME_PAGE_URL = "posts/all";

        HomePage homePage = new HomePage(super.getWebDriver());

        homePage.openHomePage();
        homePage.waitPageTobeFullLoaded();
        homePage.isUrlLoaded(HOME_PAGE_URL);

        homePage.clickOnNavigationLoginButton();
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.waitPageTobeFullLoaded();
        loginPage.isUrlLoaded(LOGIN_PAGE_SUFIX);

        loginPage.provideUserName(USERNAME);
        loginPage.providePassword(PASSWORD);
        loginPage.clickSubmitButton();
        ProfilePage profilePage = new ProfilePage(super.getWebDriver());
        Assert.assertFalse(profilePage.isNavToProfileButtonShown(),"The user could log in successfully without provided credentials");
        Assert.assertFalse(homePage.isLogOutButtonShown(),"The user could log in successfully without provided credentials");

    }
}
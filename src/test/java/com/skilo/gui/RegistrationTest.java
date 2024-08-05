package com.skilo.gui;

import com.github.javafaker.Faker;
import com.skillo.POM.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ContentGeneration;

public class RegistrationTest extends TestObject{

    @Test
    public void verifyUserCanRegisterWithValidData() {

        HomePage homePage = new HomePage(super.getWebDriver());

        final String USERNAME = ContentGeneration.createUser() ;
        final String EMAIL = ContentGeneration.createEmail();

        System.out.println("THE RANDOM GENERATED USERNAME IS: " + USERNAME);
        System.out.println("THE RANDOM GENERATED EMAIL IS: " + EMAIL);

        homePage.openHomePage();
        homePage.clickOnNavigationLoginButton();

        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.clickOnRegistrationLink();

        RegistrationPage registrationPage = new RegistrationPage(super.getWebDriver());
        registrationPage.fullRegistrationInputsAndActions(USERNAME, EMAIL, "123456");

        ProfilePage profilePage = new ProfilePage (super.getWebDriver());
        profilePage.clickOnProfileButton();
        Assert.assertEquals(USERNAME,profilePage.getUsername(),"Username doesn't match");
        Assert.assertTrue(homePage.isLogOutButtonShown(),"User couldn't register successfully");
    }

    @Test
    public void verifyUserCanNotRegisterWithINValidData(){
        HomePage homePage = new HomePage(super.getWebDriver());

        Faker faker = new Faker();
        final String USERNAME = faker.name().username();
        final String EMAIL = faker.name().name();

        System.out.println("THE RANDOM GENERATED USERNAME IS: " + USERNAME);
        System.out.println("THE RANDOM GENERATED EMAIL IS: " + EMAIL);

        homePage.openHomePage();
        homePage.clickOnNavigationLoginButton();

        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.clickOnRegistrationLink();

        RegistrationPage registrationPage = new RegistrationPage(super.getWebDriver());
        registrationPage.fullRegistrationInputsAndActions(USERNAME, EMAIL, "123456");

        ProfilePage profilePage = new ProfilePage(super.getWebDriver());
        Assert.assertFalse(profilePage.isNavToProfileButtonShown(),"User could successfully login with invalid data!");
    }
}

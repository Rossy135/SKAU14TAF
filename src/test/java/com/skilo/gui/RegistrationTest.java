package com.skilo.gui;
import com.github.javafaker.Faker;
import com.skillo.POM.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ContentGeneration;

//import java.util.Random;

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
        Assert.assertEquals(USERNAME,profilePage.getUsername());
        Assert.assertTrue(profilePage.isElementPresent(getWebDriver(), By.xpath("//*[@class=\"fas fa-sign-out-alt fa-lg\"]")));
    }

    @Test
    public void verifyUserCanNotRegisterWithINValidData(){
        HomePage homePage = new HomePage(super.getWebDriver());

        Faker faker = new Faker();
        final String USERNAME = faker.name().username();
        final String EMAIL = faker.name().name();

        System.out.println("THE RANDOM GENERATED USERNAME IS: " + USERNAME);
        System.out.println("THE RANDOM GENERATED EMAIL IS: " + EMAIL);

        System.out.println("STEP 1: Not logged in user has open the ISkilo HomePage.");
        homePage.openHomePage();

        System.out.println("STEP 2: The user has navigated to ISkilo LoginPage");
        homePage.clickOnNavigationLoginButton();

        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.clickOnRegistrationLink();

        RegistrationPage registrationPage = new RegistrationPage(super.getWebDriver());
        registrationPage.fullRegistrationInputsAndActions(USERNAME, EMAIL, "123456");

        ProfilePage profilePage = new ProfilePage(super.getWebDriver());
        Assert.assertFalse(profilePage.isElementPresent(getWebDriver(), By.id("nav-link-profile")), "ERROR: The user could register with invalid data!");
        profilePage.clickOnProfileButton();
        Assert.assertNotEquals(USERNAME,profilePage.getUsername());


    }

//    public static String generateRandomString() {
//        int length = 3;
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//        Random random = new Random();
//        StringBuilder randomString = new StringBuilder(length);
//
//        for (int i = 0; i < length; i++) {
//            int index = random.nextInt(characters.length());
//            randomString.append(characters.charAt(index));
//        }
//        return randomString.toString();
//    }

}

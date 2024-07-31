package com.skilo.gui;


import com.skillo.POM.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class PostTest extends TestObject{
    @DataProvider(name = "PostTestDataProvider")
    public Object[][] getUsers() {
        File postPicture = new File("src\\test\\resources\\uploads\\testUpload.jpg");
        String caption = "Testing create post caption";

        return new Object[][]{{
                "testingDemos", "testing",
                "testingDemos", postPicture, caption},
        };
    }

    @Test(dataProvider = "PostTestDataProvider")
    public void verifyUserCanCreateNewPost(String user, String password, String username, File file, String caption) {

        user = "jussy_";
        password = "Roska666.";
        final String HOME_PAGE_URL = "posts/all";
        final String LOGIN_PAGE_URL = "users/login";

        HomePage homePage = new HomePage(super.getWebDriver());

        homePage.openHomePage();
        homePage.waitPageTobeFullLoaded();
        homePage.isUrlLoaded(HOME_PAGE_URL);

        homePage.clickOnNavigationLoginButton();
        homePage.isUrlLoaded(LOGIN_PAGE_URL);

        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.loginWithUserAndPassword(user, password);

        homePage.isNewPostButtonToShown();
        homePage.clickOnNewPostButton();

        PostPage postPage = new PostPage(super.getWebDriver());
        postPage.uploadPicture(file);

        Assert.assertTrue(postPage.isImageVisible(), "The image is visible!");
        Assert.assertEquals(file.getName(), postPage.getImageName(), "The image name is correct");
        postPage.providePostCaption(caption);
        postPage.clickCreatePostButton();

        ProfilePage profilePage = new ProfilePage(super.getWebDriver());

        int expectedPostCount = 1;
        profilePage.isUrlLoaded("http://training.skillo-bg.com:4200/users/8464");
        int actualPostCount = profilePage.getPostCount();

        Assert.assertEquals(
                actualPostCount,
                expectedPostCount,
                "The number of Posts is incorrect!");

        profilePage.clickPost(0);

        PostModal postModal = new PostModal(super.getWebDriver());
        Assert.assertTrue(postModal.isImageVisible(), "The image is visible!");
        Assert.assertEquals(postModal.getPostUser(), user);
    }

    @Test(dataProvider = "PostTestDataProvider")
    public void verifyUserCanCreateNewPostAndDeleteIt(String user, String password, String username, File file, String caption) {

        user = "jussy_";
        password = "Roska666.";
        final String HOME_PAGE_URL = "posts/all";
        final String LOGIN_PAGE_URL = "users/login";

        HomePage homePage = new HomePage(super.getWebDriver());

        homePage.openHomePage();
        homePage.waitPageTobeFullLoaded();
        homePage.isUrlLoaded(HOME_PAGE_URL);

        homePage.clickOnNavigationLoginButton();
        homePage.isUrlLoaded(LOGIN_PAGE_URL);

        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.loginWithUserAndPassword(user, password);

        homePage.isNewPostButtonToShown();
        homePage.clickOnNewPostButton();

        PostPage postPage = new PostPage(super.getWebDriver());
        postPage.uploadPicture(file);

        Assert.assertTrue(postPage.isImageVisible(), "The image is visible!");
        Assert.assertEquals(file.getName(), postPage.getImageName(), "The image name is correct");
        postPage.providePostCaption(caption);
        postPage.clickCreatePostButton();

        ProfilePage profilePage = new ProfilePage(super.getWebDriver());

        int expectedPostCount = 1;
        int actualPostCount = profilePage.getPostCount();

        Assert.assertEquals(
                actualPostCount,
                expectedPostCount,
                "The number of Posts is incorrect!");

        profilePage.clickPost(0);

        PostModal postModal = new PostModal(super.getWebDriver());
        Assert.assertTrue(postModal.isImageVisible(), "The image is visible!");
        Assert.assertEquals(postModal.getPostUser(), user);

        postModal.clickOnBinIcon();
        postModal.confirmDeletingPost();
    }
}

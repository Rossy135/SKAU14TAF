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
                "jussy_", "Roska666.",
                "jussy_", postPicture, caption}
        };
    }

    @Test(dataProvider = "PostTestDataProvider")
    public void verifyUserCanCreateNewPostAndDeleteIt(String user, String password, String username, File file, String caption) {

        final String HOME_PAGE_URL = "posts/all";
        final String LOGIN_PAGE_URL = "users/login";

        HomePage homePage = new HomePage(super.getWebDriver());
        homePage.openHomePage();
        homePage.isUrlLoaded(HOME_PAGE_URL);
        homePage.clickOnNavigationLoginButton();
        homePage.isUrlLoaded(LOGIN_PAGE_URL);

        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.loginWithUserAndPassword(user, password);
        homePage.isNewPostButtonToShown();
        homePage.clickOnNewPostButton();

        PostPage postPage = new PostPage(super.getWebDriver());
        postPage.uploadPicture(file);
        Assert.assertTrue(postPage.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(file.getName(), postPage.getImageName(), "The image name isn't correct");
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
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postModal.getPostUser(), username);
        postModal.clickOnBinIcon();
        postModal.confirmDeletingPost();
        Assert.assertTrue(postPage.isImageDeleted(), "The image was not deleted!");
    }

    @Test (dataProvider = "PostTestDataProvider")
    public void verifyUserCouldCancelDeletingAPost(String user, String password, String username, File file, String caption) {

        final String HOME_PAGE_URL = "posts/all";
        final String LOGIN_PAGE_URL = "users/login";

        HomePage homePage = new HomePage(super.getWebDriver());
        homePage.openHomePage();
        homePage.isUrlLoaded(HOME_PAGE_URL);
        homePage.clickOnNavigationLoginButton();
        homePage.isUrlLoaded(LOGIN_PAGE_URL);

        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.loginWithUserAndPassword(user, password);
        homePage.isNewPostButtonToShown();
        homePage.clickOnNewPostButton();

        PostPage postPage = new PostPage(super.getWebDriver());
        postPage.uploadPicture(file);
        Assert.assertTrue(postPage.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(file.getName(), postPage.getImageName(), "The image name isn't correct");
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
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postModal.getPostUser(), username,"Username doesn't match!");
        postModal.clickOnBinIcon();
        postModal.cancelDeletingPost();
        Assert.assertFalse(postPage.isImageDeleted(), "The image was deleted, although it shouldn't be!");
    }
}
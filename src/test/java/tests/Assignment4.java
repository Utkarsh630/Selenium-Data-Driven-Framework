package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExcelDataProvider;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Assignment4 extends BaseTest {

    @Test
    public void testHomePage() throws IOException {
        Assert.assertTrue(homePage.validateContactNumber(), "Contact number on homepage is not matching with config file");
        captureScreenshotOfResult("HomePage");
    }

    @Test( dataProvider = "valid_login_data", dataProviderClass = ExcelDataProvider.class)
    public void testSuccessfulLogin(String Email, String Mobile, String Password) throws IOException {
        homePage.clickCommuterLogin();
        Assert.assertTrue(loginPage.checkLoginRadio(), "Login Form not opened");
        loginPage.enterEmail(Email);
        Assert.assertTrue(loginPage.validateCountryCode(), "Country code is not matching");
        loginPage.enterMobile(Mobile);
        loginPage.enterPassword(Password);
        loginPage.checkTerms();
        loginPage.clickLoginButton();
        homePage.openAccountDetails();
        captureScreenshotOfResult("SuccessfulLogin");
    }

    @Test(dataProvider = "invalid_login_data", dataProviderClass = ExcelDataProvider.class)
    public void testUnsuccessfulLogin(String Email, String Mobile, String Password, String Message) throws IOException {
        homePage.clickCommuterLogin();
        Assert.assertTrue(loginPage.checkLoginRadio(), "Login Form not opened");
        loginPage.enterEmail(Email);
        Assert.assertTrue(loginPage.validateCountryCode(), "Country code is not matching");
        loginPage.enterMobile(Mobile);
        loginPage.enterPassword(Password);
        loginPage.checkTerms();
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getAlertMessage().split("\n")[0], Message, "Error message is not matching");
        captureScreenshotOfResult("UnsuccessfulLogin");
    }

    @Test(dependsOnMethods = {"testHomePage"}, dataProvider = "forgot_password_data", dataProviderClass = ExcelDataProvider.class)
    public void testForgotPassword(String Email, String Mobile, String Message) throws IOException {
        homePage.clickCommuterLogin();
        Assert.assertTrue(loginPage.checkLoginRadio(), "Login Form not opened");
        loginPage.enterEmail(Email);
        Assert.assertTrue(loginPage.validateCountryCode(), "Country code is not matching");
        loginPage.enterMobile(Mobile);
        loginPage.checkTerms();
        loginPage.clickForgotPassword();
        Assert.assertEquals(loginPage.getAlertMessage().split("\n")[0], Message, "Error message is not matching");
        captureScreenshotOfResult("InvalidForgotPassword");
    }

    private void captureScreenshotOfResult(String testName) {
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);

            File directory = new File("src/screenshots/");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File destination = new File(directory, testName + LocalDate.now ()+ LocalTime.now ()+".png");
            FileUtils.copyFile(screenshot, destination);
            System.out.println("Screenshot saved to: " + destination.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to save screenshot for: " + testName);
            e.printStackTrace();
        }
    }
}

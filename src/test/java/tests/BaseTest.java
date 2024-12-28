package tests;

import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;

public class BaseTest {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver (  );
        driver.get ( ConfigLoader.getProperty ("baseURI") );
        driver.manage ().window ().maximize ();
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 5000 ) );
        homePage = new HomePage (driver);
        loginPage = new LoginPage ( driver );
    }

    @AfterMethod
    public void tearDown(){
            driver.quit ();
    }

}

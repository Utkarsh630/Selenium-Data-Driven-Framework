package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class HomePage {

    private final String COMMUTER_LOGIN_LOCATOR = "//a[@href='/login']";
    private final String MSG_PHONE_NUMBER_LOCATOR = "//div[@class='header-screen-reader-left']";
    private final String ACCOUNT_LINK_LOCATOR="//a[@href='/account']";

    @FindBy(xpath=COMMUTER_LOGIN_LOCATOR)
    WebElement commuterLogin;

    @FindBy(xpath=MSG_PHONE_NUMBER_LOCATOR)
    WebElement msgPhoneNumber;

//    @FindBy(xpath = COMMUTER_LOGIN_MSG_LOCATOR)
//    WebElement commuterLoginMsg;

    public By byAccountLink = By.xpath ( ACCOUNT_LINK_LOCATOR );

    WebDriver driver;
    WebDriverWait webDriverWait;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements ( driver, this );
        webDriverWait = new WebDriverWait ( driver, Duration.ofSeconds ( 10 ) );

    }

    public void clickCommuterLogin() {
        commuterLogin.click();
    }

    private String getContactNumber(){
        String [] message = msgPhoneNumber.getText ().split ( " " );
        return message[message.length-1];
    }

    public boolean validateContactNumber(){
        return getContactNumber ().equals ( ConfigLoader.getProperty ( "phone" ) );
    }

    public void openAccountDetails(){

        WebElement webElement = driver.findElement ( byAccountLink );
        webElement.click ();
    }




}

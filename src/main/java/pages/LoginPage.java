package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final String LOGIN_RADIO_LOCATOR = ".radiobtn:nth-child(1) .radio-btn div";
    private final String PASSWORD_LOCATOR = "//input[@type='password']";
    private final String MOBILE_LOCATOR = "//input[@type='tel']";
    private final String EMAIL_LOCATOR = "//label[contains(text(),'Email')]/preceding-sibling::input";
    private final String COUNTRY_CODE_LOCATOR = "//label[contains(text(),'Country')]/preceding-sibling::input";
    private final String TERMS_LOCATOR = ".checkbox div";
    private final String LOGIN_BUTTON_LOCATOR = "//*[@class='btn payee']";
    private final String ALERT_LOCATOR = ".alert .alert--content";
    private final String FORGOT_PASSWORD_LOCATOR = ".linkForgotPassword";


    @FindBy(css=LOGIN_RADIO_LOCATOR)
    WebElement loginRadio;
    @FindBy(xpath=PASSWORD_LOCATOR)
    WebElement password;
    @FindBy(xpath=MOBILE_LOCATOR)
    WebElement mobile;
    @FindBy(xpath = EMAIL_LOCATOR)
    WebElement email;
    @FindBy(xpath = COUNTRY_CODE_LOCATOR)
    WebElement countryCode;
    @FindBy(css = TERMS_LOCATOR)
    WebElement terms;
    @FindBy(xpath = LOGIN_BUTTON_LOCATOR)
    WebElement loginButton;
    WebDriver driver;
    WebDriverWait webDriverWait;

    @FindBy(css = FORGOT_PASSWORD_LOCATOR)
    WebElement forgotPassword;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements ( driver, this );
        webDriverWait = new WebDriverWait ( driver, Duration.ofSeconds ( 10 ) );
    }

    public boolean checkLoginRadio() {
        return loginRadio.getDomAttribute ( "class" ).contains ( "rdoActive" );
    }

    public void enterEmail(String mail) {
        email.clear ();
        email.sendKeys ( mail );
    }
    public void enterMobile(String mob) {
        mobile.clear ();
        mobile.sendKeys ( mob );
    }

    public boolean validateCountryCode(){
        return countryCode.getDomAttribute ( "value" ).equals ( "India +91" );
    }

    public void enterPassword(String pass) {
        password.clear ();
        password.sendKeys ( pass );
    }

    public void checkTerms() {
        terms.click ();
    }

    public void clickLoginButton() {
        loginButton.click ();
    }

    public String getAlertMessage(){
        webDriverWait.until ( ExpectedConditions.visibilityOfElementLocated (  By.cssSelector ( ALERT_LOCATOR ) ) );
        return driver.findElement ( By.cssSelector ( ALERT_LOCATOR ) ).getText ();
    }

    public void clickForgotPassword(){
        forgotPassword.click ();
    }

}

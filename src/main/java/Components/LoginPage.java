package Components;

import Ultilities.FrameworkConst;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[type='email']")
    private WebElement emailTxt;
    @FindBy(id = "identifierNext")
    private WebElement userNextBtn;
    @FindBy(css = "input[type='password']")
    private WebElement passwordTxt;
    @FindBy(id = "passwordNext")
    private WebElement passwordNextBtn;
    @FindBy(id = "logo")
    private WebElement logoTxt;

    public LoginPage navigateToLoginPage() {
        driver.get(FrameworkConst.BASE_URL);
        return this;
    }

    public LoginPage waitForPageLoad() {
        waitForElementVisible(logoTxt);
        return this;
    }

    public LandingPage loginWithValidCredentials() {
        emailTxt.sendKeys(FrameworkConst.USER_NAME);
        userNextBtn.click();
        waitForElementVisible(passwordTxt);
        passwordTxt.sendKeys(FrameworkConst.PASSWORD);
        passwordNextBtn.click();

        return new LandingPage(driver);
    }

}
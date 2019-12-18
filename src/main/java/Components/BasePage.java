package Components;

import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLOutput;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final int TIMEOUT = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT);
        PageFactory.initElements(driver,this);
    }

    protected void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void manualClearTextField(WebElement element) {
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
    }

    protected void clickAndwait(WebElement element) {
        try {
            element.click();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendTextSlow(WebElement ele, String text) {
        try {
            for (int i = 0; i < text.length(); i++) {
                ele.sendKeys(Character.toString(text.charAt(i)));
                wait.until(ExpectedConditions.attributeContains(ele, "value", text.substring(0, i)));
            }
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package WaiUtils;

import Ultilities.FrameworkConst;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    public static void waitForElementVisible(WebElement ele) {
        wait = new WebDriverWait(driver, FrameworkConst.ELEMENT_TIMEOUT);
        ExpectedCondition condition = ExpectedConditions.visibilityOf(ele);
        wait.until(condition);
    }
}

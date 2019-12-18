package Components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LandingPage extends BasePage {

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[aria-label='Calendar']")
    private WebElement logoImg;
    @FindBy(css = "button[aria-label='Create']")
    private WebElement createBtn;
    @FindBy(css = "div[data-active-view] div[role='button']")
    private WebElement displayTypeMenuBtn;
    @FindBy(css = "div[role='menu'] div[data-viewkey='week']")
    private WebElement viewAsWeekBtn;

    public LandingPage waitForCalendarPageLoad() {
        waitForElementVisible(logoImg);
        return this;
    }

    public CreatePopUp clickCreateButton() {
        waitForElementClickable(createBtn);
        clickAndwait(createBtn);
        return new CreatePopUp(driver);
    }

    public LandingPage selectViewAsWeek() {
        waitForElementClickable(displayTypeMenuBtn);
        displayTypeMenuBtn.click();
        waitForElementVisible(viewAsWeekBtn);
        clickAndwait(viewAsWeekBtn);
        return this;
    }

    public boolean isEventTitleDisplay(String monthDate, String eventTitle) {
        String eventTitleSelector = String.format("//h2[contains(text(),'%s')]/following-sibling::div//span[contains(text(),'%s')]",monthDate,eventTitle);
        WebElement titleElement = driver.findElement(By.xpath(eventTitleSelector));
        return wait.until(ExpectedConditions.visibilityOf(titleElement)).isDisplayed();
    }

    public boolean isEventTimeDisplay(String monthDate, String startTime, String endTime) {
        String eventTimeSelector = String.format("//h2[contains(text(),'%s')]/following-sibling::div//div[contains(text(),'%s – %s')]",monthDate,startTime,endTime);
        WebElement timeElement = driver.findElement(By.xpath(eventTimeSelector));
        return wait.until(ExpectedConditions.visibilityOf(timeElement)).isDisplayed();
    }

    public boolean isEventLocationDisplay(String monthDate, String eventLocation) {
        String eventLocationSelector = String.format("//h2[contains(text(),'%s')]/following-sibling::div//div[contains(text(),'%s')]", monthDate, eventLocation);
        WebElement locationElement = driver.findElement(By.xpath(eventLocationSelector));
        return wait.until(ExpectedConditions.visibilityOf(locationElement)).isDisplayed();
    }

}

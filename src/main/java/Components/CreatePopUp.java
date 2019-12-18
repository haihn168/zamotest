package Components;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class CreatePopUp extends BasePage {
    public CreatePopUp(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[aria-label='Add title']")
    private WebElement eventTitleTxt;
    @FindBy(css = "input[aria-label='Start date']")
    private WebElement eventStartDateCld;
    @FindBy(css = "input[aria-label='End date']")
    private WebElement eventEndDateCld;
    @FindBy(css = "input[aria-label='Start time']")
    private WebElement eventStartTimeCbx;
    @FindBy(css = "input[aria-label='End time']")
    private WebElement eventEndTimeCbx;
    @FindBy(xpath = "//span[@id='tabEvent']//span[contains(text(),'Add guests')]")
    private WebElement addGuestsBox;
    @FindBy(css = "input[aria-label='Guests']")
    private WebElement guestsTxt;
    @FindBy(xpath = "//span[@id='tabEvent']//span[contains(text(),'Add location or')]")
    private WebElement addLocationBox;
    @FindBy(xpath = "//div[contains(text(),'Add location')]/preceding-sibling::input")
    private WebElement locationTxt;
    @FindBy(xpath = "(//span[@id='tabEvent']//span[contains(text(),'Add description')])[1]")
    private WebElement descriptionBox;
    @FindBy(css = "div[aria-label~='Description']")
    private WebElement descriptionTxt;
    @FindBy(xpath = "//span[contains(text(),'Save')]")
    private WebElement saveBtn;
    @FindBy(xpath = "//span[contains(text(),'Send')]/ancestor::div[@role='button']")
    private WebElement invitationSendBtn;
    @FindBy(xpath = "//span[contains(text(),'t send')]/ancestor::div[@role='button']")
    private WebElement invitationDontSendBtn;
    @FindBy(xpath = "//span[contains(text(),'Dismiss')]/ancestor::div[@role='button']")
    private WebElement invitationDismissBtn;
    @FindBy(xpath = "//div[contains(text(),'Would you like')]")
    private WebElement invitationText;
    @FindBy(css = "div[role='listbox'][data-expanded=true]")
    private WebElement suggestedDropdown;

    public CreatePopUp createQuickEventNoTitle() {
        waitForElementVisible(saveBtn);
        saveBtn.click();
        return this;
    }

    public CreatePopUp setEventTitle(String title) {
        //TODO Handle null or blank title
        waitForElementVisible(eventTitleTxt);
        eventTitleTxt.click();
        sendTextSlow(eventTitleTxt, title);
        return this;
    }

    public CreatePopUp setEventDateTime(String startDate, String startTime, String endTime, String endDate) {
        waitForElementVisible(eventStartDateCld);
        manualClearTextField(eventStartDateCld);
        sendTextSlow(eventStartDateCld, startDate);

        waitForElementVisible(eventStartTimeCbx);
        manualClearTextField(eventStartTimeCbx);
        sendTextSlow(eventStartTimeCbx, startTime);

        waitForElementVisible(eventEndTimeCbx);
        manualClearTextField(eventEndTimeCbx);
        sendTextSlow(eventEndTimeCbx, endTime);

        waitForElementVisible(eventEndDateCld);
        manualClearTextField(eventEndDateCld);
        sendTextSlow(eventEndDateCld, endDate);

        return this;
    }

    public CreatePopUp setGuests(String guestEmail) {
        //TODO Handle multiple guests
        waitForElementVisible(addGuestsBox);
        addGuestsBox.click();
        waitForElementVisible(guestsTxt);
        sendTextSlow(guestsTxt, guestEmail);
        waitForElementVisible(suggestedDropdown);
        WebElement suggestDropdown = driver.findElement(By.cssSelector("div[data-hinttext~='"+guestEmail+"']"));
        suggestDropdown.click();

        WebElement guestContactCard = driver.findElement(By.cssSelector("div[data-id~='"+guestEmail+"']"));
        waitForElementVisible(guestContactCard);
        return this;
    }

    public CreatePopUp setLocation(String location)  {
        waitForElementVisible(addLocationBox);
        addLocationBox.click();
        waitForElementVisible(locationTxt);
        sendTextSlow(locationTxt,location);
        waitForElementVisible(suggestedDropdown);

        WebElement suggestDropdown = driver.findElement(By.xpath("//div[@data-placeholder='Add location']//div[contains(text(),'"+location+"')]"));
        waitForElementVisible(suggestDropdown);
        suggestDropdown.click();
        return this;
    }

    public CreatePopUp setDescription(String description) {
        waitForElementVisible(descriptionBox);
        descriptionBox.click();
        waitForElementVisible(descriptionTxt);
        descriptionTxt.sendKeys(description);
        return this;
    }

    public LandingPage saveTheEvent(Boolean sendInvitation) {
        waitForElementVisible(saveBtn);
        clickAndwait(saveBtn);

        if (!sendInvitation) {
            waitForElementClickable(invitationDontSendBtn);
            clickAndwait(invitationDontSendBtn);
        } else {
            waitForElementClickable(invitationSendBtn);
            clickAndwait(invitationSendBtn);
        }
        return new LandingPage(driver);
    }
}

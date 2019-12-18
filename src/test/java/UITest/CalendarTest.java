package UITest;

import Base.BaseTest;
import Components.LoginPage;
import Components.LandingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalendarTest extends BaseTest {

    @Test
    public void createEventTest() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        LandingPage landingPage = new LandingPage(driver);

        String title = "Test title";
        int startYearNumber = 2019,startDateNumber = 25;
        String startMonthNumber = "December";
        String monthDate = String.format("%s %d",startMonthNumber,startDateNumber);
        String startTime = "10:30am", endTime = "2:30pm";
        String guestEmail = "cookt@apple.com";
        String location = "ZAMO TECH";
        String description = "This is a test description";

        loginPage.navigateToLoginPage()
                .waitForPageLoad()
                .loginWithValidCredentials()
                .waitForCalendarPageLoad()
                .selectViewAsWeek()
                .clickCreateButton()
                .setEventTitle(title)
                .setEventDateTime(String.format("%d %s %d",startYearNumber,startMonthNumber,startDateNumber),
                        startTime,
                        endTime,
                        String.format("%d %s %d",startYearNumber,startMonthNumber,startDateNumber))
                .setGuests(guestEmail)
                .setLocation(location)
                .setDescription(description)
                .saveTheEvent(false);

        Assert.assertTrue(landingPage.isEventTitleDisplay(monthDate, title));
        Assert.assertTrue(landingPage.isEventTimeDisplay(monthDate, startTime, endTime));
        Assert.assertTrue(landingPage.isEventLocationDisplay(monthDate, location));
    }
}

package Base;

import DriverFactory.DriverFactory;
import DriverFactory.DriverManager;
import DriverFactory.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    private DriverManager driverManager;
    protected WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        System.out.println("============Starting UI Test===============");
        driverManager = DriverFactory.getManager(DriverType.CHROME);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = driverManager.getDriver();
    }

    @AfterMethod
    public void afterMethod() {
        driverManager.quitDriver();
    }

}

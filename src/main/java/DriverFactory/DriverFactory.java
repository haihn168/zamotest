package DriverFactory;

public class DriverFactory {
    public static DriverManager getManager(DriverType type) {
        DriverManager driverManager = null;
        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
            case SAFARI:
                break;
        }
        return driverManager;
    }
}

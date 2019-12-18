package DriverFactory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class ChromeDriverManager extends DriverManager {
    private ChromeDriverService chService;

    @Override
    public void startService() {
        try {
            chService = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(".\\Drivers\\chromedriver.exe"))
                    .usingAnyFreePort()
                    .build();
            chService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stopService() {
        if (null != chService && chService.isRunning()) {
            chService.stop();
        }
    }

    @Override
    public void createDriver() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("test-type");
        option.addArguments("--start-maximized");
        driver = new ChromeDriver(chService, option);
    }
}
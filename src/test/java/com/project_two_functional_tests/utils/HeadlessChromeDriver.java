package com.project_two_functional_tests.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessChromeDriver {

    private WebDriver driver;

    public HeadlessChromeDriver() {
        String headlessEnv = System.getProperty("headless", "false");
        boolean headless = Boolean.parseBoolean(headlessEnv);
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
        }
        driver = new ChromeDriver(options);
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}

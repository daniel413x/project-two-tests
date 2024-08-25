package com.project_two_functional_tests.utils;

import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

public class HeadlessFirefoxDriver {

    private WebDriver driver;

    public HeadlessFirefoxDriver() {
        // Determine if headless mode should be used
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        String headlessEnv = System.getProperty("headless", "false");
        boolean headless = Boolean.parseBoolean(headlessEnv);

        // Create FirefoxOptions instance
        FirefoxOptions options = new FirefoxOptions();

        options.setLogLevel(FirefoxDriverLogLevel.TRACE);
        options.setCapability("moz:firefoxOptions", Map.of("log", Map.of("level", "trace")));


        // Add headless-specific options if required
        if (headless) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
        }

        // Set up logging preferences
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        logs.enable(LogType.SERVER, Level.ALL);

        // Add logging preferences to FirefoxOptions
        options.setCapability("goog:loggingPrefs", logs);

        // Initialize the WebDriver with FirefoxOptions
        driver = new FirefoxDriver(options);
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}

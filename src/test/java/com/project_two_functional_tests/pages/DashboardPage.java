package com.project_two_functional_tests.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    
    private WebDriver driver;
    private static final String url = "http://localhost:5173";

    @FindBy(tagName="h1")
    private List<WebElement> figures;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean onPage() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return driver.getCurrentUrl().equals(url);
    }

    public void get() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.driver.get(url);
    }

    public boolean dashboardFiguresLoaded() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return figures.get(0).isDisplayed() && figures.get(1).isDisplayed() && figures.get(2).isDisplayed(); 
    }

    public boolean iShouldSeeTheTotalItemsInInventoryWithAValueOf300() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(figures.get(1).getText()) == 300; 
    }

    public boolean iShouldSeeTheTotalMaxCapacityWithAValueOf1000() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(figures.get(2).getText()) == 1000; 
    }
}

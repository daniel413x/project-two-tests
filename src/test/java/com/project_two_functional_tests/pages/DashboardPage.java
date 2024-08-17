package com.project_two_functional_tests.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    private WebDriver driver;
    private static final String url = "http://localhost:5173";

    @FindBy(tagName = "h1")
    private List<WebElement> figures;

    @FindBy(className = "ant-card")
    private List<WebElement> cards;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean onPage() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return figures.get(0).isDisplayed() && figures.get(1).isDisplayed() && figures.get(2).isDisplayed();
    }

    public boolean iShouldSeeCard(String title, String value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (WebElement card : cards) {
            String cardTitle = card.findElement(By.className("ant-card-head-title")).getText();

            if (cardTitle.equals(title)) {
                String cardValue = card.findElement(By.tagName("h1")).getText();

                return cardValue.equals((value));
            }
        }
        return false;
    }
}

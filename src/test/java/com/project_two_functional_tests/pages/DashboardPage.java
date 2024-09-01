package com.project_two_functional_tests.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String url = "http://localhost:5173";

    @FindBy(tagName = "h1")
    private List<WebElement> figures;

    @FindBy(className = "ant-card")
    private List<WebElement> cards;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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

    public boolean dashboardSectionLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ant-card")));
            return this.driver.findElements(By.className("ant-card")).isEmpty();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean iShouldSeeCard(String title, String value) {
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

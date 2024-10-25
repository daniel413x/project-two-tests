package com.project_two_functional_tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationTestsPage {

    private WebDriver driver;

    public NavigationTestsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Navigate to the specified page
    public void navigateToPage(String pageName) {
        driver.get("http://localhost:5173/" + pageName.toLowerCase());
    }

    // Click on specified page's nav link using its id
    public void clickOnNavLinkInSideNavigation(String id) {
        driver.findElement(By.id(id.toLowerCase())).click();
    }

    // Click on specified page's breadcrumb link using its id
    public void clickOnBreadcrumb(String id) {
        driver.findElement(By.id("breadcrumb-" + id.toLowerCase())).click();
    }

    // Verify specified page is displayed
    public boolean isOnPage(String pageName) {
        return driver.getCurrentUrl().contains(pageName.toLowerCase());
    }

      public void focusOnSkipNavigationLink() {
        WebElement body = driver.findElement(By.tagName("body"));
        body.sendKeys(Keys.TAB);
    }

    public boolean isSkipNavigationLinkDisplayed() {
        return driver.findElement(By.id("skip-nav")).isDisplayed();
    }

    public void clickOnSkipNavigationLink() {
        driver.findElement(By.id("skip-nav")).click();
    }

}

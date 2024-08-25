package com.project_two_functional_tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationTestsPage {

    private WebDriver driver;

    public NavigationTestsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToPage(String pageName) {
        driver.get("http://localhost:5173/" + pageName.toLowerCase());
    }

    public void clickOnNavLinkInSideNavigation(String id) {
        driver.findElement(By.id(id.toLowerCase())).click();
    }

    public void clickOnBreadcrumb(String id) {
        driver.findElement(By.id("breadcrumb-" + id.toLowerCase())).click();
    }

    public boolean isOnPage(String pageName) {
        return driver.getCurrentUrl().contains(pageName.toLowerCase());
    }
}

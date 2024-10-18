package com.project_two_functional_tests.steps.accessibilitySteps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;

import com.project_two_functional_tests.pages.AccessibilityTestsPage;
import com.project_two_functional_tests.utils.HeadlessChromeDriver;
import com.project_two_functional_tests.utils.ResetDatabase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccessibilitySteps {

    private WebDriver driver;
    private AccessibilityTestsPage accessibilityTestsPage;

    final private String DASHBOARD_PAGE = "Dashboard";
    final private String PRODUCTS_PAGE = "Products";
    final private String WAREHOUSES_PAGE = "Warehouses";
    final private String INVENTORY_PAGE = "Inventory";

    private String currentPage;

    @BeforeAll
    public static void resetDatabaseBeforeAll() {
        ResetDatabase.run();
    }

    @Before("@skip-navigation")
    public void before() {
        driver = new HeadlessChromeDriver().getDriver();
        this.accessibilityTestsPage = new AccessibilityTestsPage(driver);
        driver.manage().window().maximize();
    }

    @After("@skip-navigation")
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the {string} page")
    public void iAmOnTheCurrentPage(String currentPage) {
        this.currentPage = currentPage;
        String url;
        if (currentPage.equals(DASHBOARD_PAGE)) {
            url = "http://localhost:5173/dashboard";
        } else if (currentPage.startsWith(PRODUCTS_PAGE)) {
            url = "http://localhost:5173/products";
        } else if (currentPage.startsWith(WAREHOUSES_PAGE)) {
            url = "http://localhost:5173/warehouses";
        } else if (currentPage.startsWith(INVENTORY_PAGE)) {
            url = "http://localhost:5173/inventory?warehouse=all";
        } else {
            throw new IllegalArgumentException("Invalid page type");
        }
        driver.get(url);
    }

    @When("I tab the skip navigation link into focus")
    public void iTabSkipNavigationLinkIntoFocus() {
        accessibilityTestsPage.focusOnSkipNavigationLink();
    }

    @And("the skip navigation link is displayed")
    public void skipNavigationLinkDisplayed() {
        assertTrue(accessibilityTestsPage.isSkipNavigationLinkDisplayed());
    }

    @Then("I click the skip navigation link")
    public void iClickSkipNavigationLink() {
        accessibilityTestsPage.clickOnSkipNavigationLink();
    }

    @Then("I should be taken to the main content of the page")
    public void iShouldBeTakenToMainContent() {
        if (currentPage.equals(INVENTORY_PAGE)) {
            assertTrue(accessibilityTestsPage.isOnPage("inventory?warehouse=all#main-content"));
        } else {
            assertTrue(accessibilityTestsPage.isOnPage(currentPage + "#main-content"));
        }
    }
}

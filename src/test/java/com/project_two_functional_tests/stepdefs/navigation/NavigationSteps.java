package com.project_two_functional_tests.stepdefs.navigation;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;

import com.project_two_functional_tests.pages.NavigationTestsPage;
import com.project_two_functional_tests.utils.HeadlessChromeDriver;
import com.project_two_functional_tests.utils.ResetDatabase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NavigationSteps {

    private WebDriver driver;
    private NavigationTestsPage navigationTestsPage;

    final private String DASHBOARD_PAGE = "Dashboard";
    final private String PRODUCTS_PAGE = "Products";
    final private String WAREHOUSES_PAGE = "Warehouses";
    final private String INVENTORY_PAGE = "Inventory";
    final private String INVENTORY_CLIMBING_SHOES_PAGE = "Inventory - Climbing Shoes";
    final private String INVENTORY_CA1_PAGE = "Inventory - CA1";

    private String currentPage;

    @BeforeAll
    public static void resetDatabaseBeforeAll() {
        ResetDatabase.run();
    }

    @Before("@navigation or @keyboard-navigation or @keyboard-skip-navigation")
    public void before() {
        driver = new HeadlessChromeDriver().getDriver();
        this.navigationTestsPage = new NavigationTestsPage(driver);
        driver.manage().window().maximize();
    }

    @After("@navigation or @keyboard-navigation or @keyboard-skip-navigation")
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the {string} page")
    public void iAmOnTheCurrentPage(String arg) {
        currentPage = arg;
        String url;
        if (arg.equals(DASHBOARD_PAGE)) {
            url = "http://localhost:5173/dashboard";
        } else if (arg.startsWith(PRODUCTS_PAGE)) {
            url = "http://localhost:5173/products";
        } else if (arg.startsWith(WAREHOUSES_PAGE)) {
            url = "http://localhost:5173/warehouses";
        } else if (arg.startsWith(INVENTORY_CLIMBING_SHOES_PAGE)) {
            url = "http://localhost:5173/inventory?category=1";
        } else if (arg.startsWith(INVENTORY_CA1_PAGE)) {
            url = "http://localhost:5173/inventory?warehouse=1";
        } else if (arg.startsWith(INVENTORY_PAGE)) {
            url = "http://localhost:5173/inventory?warehouse=all";
        } else {
            throw new IllegalArgumentException("Invalid page type");
        }
        driver.get(url);
    }

    /**
     * 01-navigation.feature
     * Scenario Outline: Side Navigation
     */

    @When("I click on {string} in the side navigation")
    public void iClickOnANavLinkInTheSideNavigation(String targetPage) {
        String id = targetPage.toLowerCase();
        if (targetPage.equals(INVENTORY_PAGE)) {
            id = "all-inventory";
        }
        navigationTestsPage.clickOnNavLinkInSideNavigation(id);
    }

    @When("I focus and select {string} in the side navigation")
    public void iFocusAndSelectANavLinkInTheSideNavigation(String targetPage) {
        String id = targetPage.toLowerCase();
        if (targetPage.equals(INVENTORY_PAGE)) {
            id = "all-inventory";
        }
        navigationTestsPage.focusAndSelectNavLinkInSideNavigation(id);
    }

    /**
     * 01-navigation.feature
     * Scenario Outline: Breadcrumbs
     */

    @When("I click on {string} in the breadcrumbs")
    public void iClickANavLinkInTheBreadcrumbs(String page) {
        navigationTestsPage.clickOnBreadcrumb(page.toLowerCase());
    }

    @When("I focus and select {string} in the breadcrumbs")
    public void iFocusAndSelectANavLinkInTheBreadcrumbs(String page) {
        navigationTestsPage.focusAndSelectBreadcrumb(page.toLowerCase());
    }

    @Then("I should be navigated to {string}")
    public void iShouldBeNavigatedTo(String expectedPage) {
        assertTrue(navigationTestsPage.isOnPage(expectedPage));
    }


    @When("I focus on the skip navigation link")
    public void iFocusOnSkipNavigationLink() {
        navigationTestsPage.focusOnSkipNavigationLink();
    }

    @And("the skip navigation link is displayed")
    public void skipNavigationLinkDisplayed() {
        assertTrue(navigationTestsPage.isSkipNavigationLinkDisplayed());
    }

    @Then("I select the skip navigation link")
    public void iClickSkipNavigationLink() {
        navigationTestsPage.selectSkipNavigationLink();
    }

    @Then("I should be taken to the main content of the page")
    public void iShouldBeTakenToMainContent() {
        if (currentPage.equals(INVENTORY_PAGE)) {
            assertTrue(navigationTestsPage.isOnPage("inventory?warehouse=all#main-content"));
        } else {
            assertTrue(navigationTestsPage.isOnPage(currentPage + "#main-content"));
        }
    }
}

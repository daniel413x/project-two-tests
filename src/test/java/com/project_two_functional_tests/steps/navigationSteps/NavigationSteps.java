package com.project_two_functional_tests.steps.navigationSteps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;

import com.project_two_functional_tests.pages.NavigationTestsPage;
import com.project_two_functional_tests.utils.HeadlessChromeDriver;
import com.project_two_functional_tests.utils.ResetDatabase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
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

    @Before("@navigation")
    public void before() {
        driver = new HeadlessChromeDriver().getDriver();
        this.navigationTestsPage = new NavigationTestsPage(driver);
        driver.manage().window().maximize();
    }

    @After("@navigation")
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
        } else {
            throw new IllegalArgumentException("Invalid page type");
        }
        driver.get(url);
    }

    @When("I click on {string} in the side navigation")
    public void iClickOnANavLinkInTheSideNavigation(String targetPage) {
        String id = targetPage.toLowerCase();
        if (currentPage.equals(PRODUCTS_PAGE) && targetPage.equals(INVENTORY_PAGE)) {
            id = "inventory-products";
        }
        if (currentPage.equals(WAREHOUSES_PAGE) && targetPage.equals(INVENTORY_PAGE)) {
            id = "inventory-warehouses";
        }
        navigationTestsPage.clickOnNavLinkInSideNavigation(id);
    }

    @When("I click on {string} in the breadcrumbs")
    public void iClickOnInTheBreadcrumbs(String page) {
        navigationTestsPage.clickOnBreadcrumb(page);
    }

    @Then("I should be navigated to {string}")
    public void iShouldBeNavigatedTo(String expectedPage) {
        assertTrue(navigationTestsPage.isOnPage(expectedPage));
    }
}

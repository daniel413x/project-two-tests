package com.project_two_functional_tests.stepdefs.products;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;

import com.project_two_functional_tests.pages.ProductsPage;
import com.project_two_functional_tests.utils.HeadlessChromeDriver;
import com.project_two_functional_tests.utils.ResetDatabase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductsSteps {

    private WebDriver driver;
    private ProductsPage productsPage;

    private String testCategory = "Crampons";

    @BeforeAll
    public static void resetDatabaseBeforeAll() {
        ResetDatabase.run();
    }

    @Before("@product-categories or @product-categories-creation or @product-categories-update or @product-categories-delete or @keyboard-product-categories-creation or @keyboard-product-categories-update or @keyboard-product-categories-delete")
    public void before() {
        driver = new HeadlessChromeDriver().getDriver();
        this.productsPage = new ProductsPage(driver);
        driver.manage().window().maximize();
    }

    @After("@product-categories or @product-categories-creation or @product-categories-update or @product-categories-delete or @keyboard-product-categories-creation or @keyboard-product-categories-update or @keyboard-product-categories-delete")
    public void after() {
        if (this.driver != null) {
            this.driver.quit();
            ResetDatabase.run();
        }
    }

    @Given("I am on the product categories page")
    public void iAmOnTheProductsPage() {
        this.productsPage.get();
    }

    @When("the product categories load")
    public void theProductCategoryCardsLoaded() {
        this.productsPage.productCategoryCardsLoaded();
    }

    /**
     * 01-product-categories.feature
     * Scenario: Product categories are shown 
     */

    @Then("I should see product categories")
    public void iShouldSeeTheProductCategories() {
        assertTrue(this.productsPage.iShouldSeeTheProductCategories());
    }

    /**
     * 02-product-categories-creation.feature
     * Scenario: A new product category is created
     */


    @Then("I click the {string} button")
    public void iClickTheButton(String buttonText) {
        this.productsPage.clickButton(buttonText);
    }

    @Then("I focus and select the {string} button")
    public void iFocusAndSelectTheButton(String buttonText) {
        this.productsPage.focusAndSelectButton(buttonText);
    }

    @Then("I enter valid input for a new product category into the modal form")
    public void iEnterValidInput() {
        this.productsPage.enterInput(testCategory);
    }


    @Then("I focus and enter valid input for a new product category into the modal form")
    public void iFocusAndEnterInput() {
        this.productsPage.focusAndEnterInput(testCategory);
    }

    @And("the created product category should appear in the list of categories")
    public void theCreatedProductCategoryShouldAppearInTheListOfCategories() {
        assertTrue(this.productsPage.containsProductCategoryWithName(testCategory));
    }

    /**
     * 03-product-categories-update.feature, 04-product-categories-delete.feature
     * Features: Update product category, Delete product category
     */

    @And("I click the {string} icon on a category card")
    public void iClickTheIconOnACard(String iconType) {
        this.productsPage.clickIconOnCard(iconType);
    }

    @And("I focus and select the {string} icon on a category card")
    public void iFocusAndSelectTheIconOnACard(String iconType) {
        this.productsPage.focusAndSelectIconOnCard(iconType);
    }

    @Then("I should see a category name field pre-filled with the current name")
    public void iShouldSeeACategoryNameFieldPrefilled() {
        assertTrue(this.productsPage.updateFieldContainsProductCategoryName());
    }

    @And("update the category name field to a new value")
    public void updateTheCategoryNameToNewValue() {
        this.productsPage.updateNameField(testCategory);
    }

    @And("focus and update the category name field to a new value")
    public void focusAndUpdateTheCategoryNameToNewValue() {
        this.productsPage.focusAndUpdateNameField(testCategory);
    }

    /**
     * 03-product-categories-update.feature
     * Scenario: Save product category update 
     */

    @And("should see the category name updated to the new value")
    public void seeCategoryNameUpdated() {
        assertTrue(this.productsPage.savedCardIsUpdated(testCategory));
    }

    /**
     * 03-product-categories-update.feature
     * Scenario: Cancel product category update 
     */

    @And("should see the category name unchanged")
    public void seeTheCategoryNameUnchanged() {
        assertTrue(this.productsPage.canceledCardIsNotUpdated());
    }

    /**
     * 04-product-categories-delete.feature
     * Scenario: Delete product category 
     */

    @Then("I should not see the category card displayed")
    public void iShouldNotSeeCategoryCardDisplayed() {
        assertTrue(this.productsPage.cardIsDeleted());
    }

}

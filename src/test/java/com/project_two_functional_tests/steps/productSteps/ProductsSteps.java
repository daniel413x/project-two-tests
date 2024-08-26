package com.project_two_functional_tests.steps.productSteps;

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

    @Before("@product-categories or @product-categories-creation or @product-categories-update or @product-categories-delete")
    public void before() {
        driver = new HeadlessChromeDriver().getDriver();
        this.productsPage = new ProductsPage(driver);
        driver.manage().window().maximize();
    }

    @After("@product-categories or @product-categories-creation or @product-categories-update or @product-categories-delete")
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

    @Then("I should see product categories")
    public void iShouldSeeTheProductCategories() {
        assertTrue(this.productsPage.iShouldSeeTheProductCategories());
    }

    @And("I have opened the create product category form modal")
    public void iHaveOpenedTheCreateProductCategoryFormModal() {
        this.productsPage.clickOnCreateProductCategoryButton();
    }

    @When("I enter valid input for a new product category")
    public void iEnterValidInput() {
        this.productsPage.enterInput(testCategory);
    }

    @And("I press the product category form submit button")
    public void iPressTheSubmitButton() {
        this.productsPage.clickOnModalSubmitButton();
    }

    @And("the created product category should appear in the list of categories")
    public void theCreatedProductCategoryShouldAppearInTheListOfCategories() {
        assertTrue(this.productsPage.containsProductCategoryWithName(testCategory));
    }

    @And("I select the {string} icon on a category card")
    public void iSelectTheIconOnACard(String iconType) {
        this.productsPage.selectIconOnCard(iconType);
    }

    @Then("I should see a category name field pre-filled with the current name")
    public void iShouldSeeACategoryNameFieldPrefilled() {
        assertTrue(this.productsPage.updateFieldContainsProductCategoryName());
    }

    @And("edit the category name field to a new value")
    public void editTheCategoryNameToNewValue() {
        this.productsPage.editUpdateField(testCategory);
    }

    @Then("I should click the {string} button")
    public void iShouldClickTheButton(String buttonText) {
        this.productsPage.clickButtonInModal(buttonText);
    }

    @And("see the category name updated to the new value")
    public void seeCategoryNameUpdated() {
        assertTrue(this.productsPage.savedCardIsUpdated(testCategory));
    }

    @And("see the category name unchanged")
    public void seeTheCategoryNameUnchanged() {
        assertTrue(this.productsPage.canceledCardIsNotUpdated());
    }

    @And("select Delete from the dropdown")
    public void selectDeleteFromDropdown() {
        this.productsPage.selectDeleteDropdownOption();
    }

    @Then("I should not see the category card displayed")
    public void iShouldNotSeeCategoryCardDisplayed() {
        assertTrue(this.productsPage.cardIsDeleted());

    }

}

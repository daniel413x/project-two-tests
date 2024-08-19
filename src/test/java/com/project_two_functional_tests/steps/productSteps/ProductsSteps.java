package com.project_two_functional_tests.steps.productSteps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.project_two_functional_tests.pages.ProductsPage;
import com.project_two_functional_tests.utils.ResetDatabase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductsSteps {

    private WebDriver driver;
    private ProductsPage productsPage;

    private String testCategory = "Crampons";

    @Before("@product-categories or @product-categories-creation or @product-categories-update or @product-categories-delete")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        this.productsPage = new ProductsPage(driver);
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

    @Then("the created product category should appear in the list of categories")
    public void theCreatedProductCategoryShouldAppearInTheListOfCategories() {
        assertTrue(this.productsPage.containsProductCategoryWithName(testCategory));
    }

    @And("there is one or more existing category cards")
    public void thereIsOneOrMoreExistingCategoryCards() {}

    @When("I select the {string} icon on a category card") 
    public void iSelectTheIconOnACard(String iconType) {}

    @Then("I should see a category name field pre-filled with the current name")
    public void iShouldSeeACategoryNameFieldPrefilled() {}

    @And("be able to edit the category name field to a new value")
    public void beAbleToEditTheCategoryNameToNewValue() {}

    @Then("I should click the {string} button")
    public void iShouldClickTheButton(String buttonText) {}

    @And("see the category name updated to the new value")
    public void seeTheCategoryNameUpdated() {}

    @And("see the category name unchanged")
    public void seeTheCategoryNameUnchanged() {}

    @And("select Delete from the dropdown")
    public void selectDeleteFromDropdown() {}

    @Then("I should not see the category card displayed")
    public void iShouldNotSeeCategoryCardDisplayed() {}

}

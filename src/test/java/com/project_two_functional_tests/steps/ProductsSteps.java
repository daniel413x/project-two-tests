package com.project_two_functional_tests.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.project_two_functional_tests.pages.ProductsPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@Disabled("Temporarily disabled")
public class ProductsSteps {

    private WebDriver driver;
    private ProductsPage productsPage;

    private String createdTestProductCategoryName = "Stylish";

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
        }
    }

    @Given("I am on the products page")
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
        this.productsPage.enterInput(createdTestProductCategoryName);
    }

    @And("I press the product category form submit button")
    public void iPressTheSubmitButton() {
        this.productsPage.clickOnModalSubmitButton();
    }

    @Then("the product category form submission should be successful")
    public void theFormSubmissionShouldBeSuccessful() {
        assertTrue(this.productsPage.containsProductCategoryWithName(createdTestProductCategoryName));
    }

    @And("the created product category should appear in the list of categories")
    public void theCreatedProductCategoryShouldAppearInTheListOfCategories() {
        assertTrue(this.productsPage.containsProductCategoryWithName(createdTestProductCategoryName));
    }
}

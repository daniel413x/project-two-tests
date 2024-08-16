package com.project_two_tests.pages;

import org.mockito.internal.matchers.Find;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class ProductsPage {

    private WebDriver driver;
    private static final String url = "http://localhost:5173/products";

    @FindBy(className="cards")
    private WebElement categoryCards;

    @FindBy(tagName = "button")
    private List<WebElement> addCategoryButton;

    @FindBy(className = "ant-modal-content")
    private WebElement modal;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean onPage() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
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

    public boolean productCategoryCardsLoaded() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return !categoryCards.findElements(By.tagName("div")).isEmpty();
    }

    public boolean iShouldSeeTheProductCategories() {
        return categoryCards.getText().contains("Climbing Shoes");
    }

    public void clickOnCreateProductCategoryButton() {
        // need to optimize
        WebElement button = addCategoryButton.get(0);
        button.click();
        modal.isDisplayed();
    }

    public void enterInput(String input) {
        WebElement field = modal.findElement(By.tagName("input"));
        field.sendKeys(input);
    }

    public void clickOnModalSubmitButton() {
        WebElement button = modal.findElement(By.className("ant-modal-footer")).findElements(By.tagName("button")).get(1);
        button.click();
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean containsProductCategoryWithName(String name) {
        return categoryCards.getText().contains(name);
    }

    public boolean doesNotContainProductCategoryWithName(String name) {
        return !categoryCards.getText().contains(name);
    }
}

package com.project_two_functional_tests.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {

    private WebDriver driver;
    private static final String url = "http://localhost:5173/inventory?category=all";

    @FindBy(id="inventory")
    private WebElement inventorySection;

    @FindBy(tagName = "button")
    private List<WebElement> addInventoryButton;

    @FindBy(className = "ant-modal-content")
    private WebElement modal;

    public InventoryPage(WebDriver driver) {
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

    public boolean inventorySectionLoaded() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return !inventorySection.findElements(By.tagName("div")).isEmpty();
    }

    // TODO: define helper code on the frontend for more substantial "inventory list" tests
    public boolean iShouldSeeTheInventoryForAnyCategory() {
        return inventorySection.getText().contains("Titanium Ascend Rope 60m") && inventorySection.getText().contains("Guardian Belay Device");
    }

    public boolean iShouldSeeTheInventoryForASingleCategory() {
        return inventorySection.getText().contains("Eagle Eye Climbing Helmet");
    }

    public boolean iShouldSeeTheInventoryForASingleWarehouse() {
        return inventorySection.getText().contains("Titanium Ascend Rope 60m") && inventorySection.getText().contains("Guardian Belay Device");
    }

    public void clickOnCreateInventoryButton() {
        WebElement button = addInventoryButton.get(0);
        button.click();
        modal.isDisplayed();
    }

    public void enterCreationFormInputs(String brand, String name, String description, String productType, String warehouse, String price, String size, String quantity) {
        WebElement brandField = modal.findElement(By.id("form_in_modal_brand"));
        brandField.sendKeys(brand);
        WebElement nameField = modal.findElement(By.id("form_in_modal_name"));
        nameField.sendKeys(name);
        WebElement descriptionField = modal.findElement(By.id("form_in_modal_description"));
        descriptionField.sendKeys(description);
        WebElement productTypeField = modal.findElement(By.id("form_in_modal_categoryName"));
        if (productTypeField.isEnabled()) {
            productTypeField.sendKeys(productType);
            productTypeField.sendKeys(Keys.ENTER);
        }
        WebElement warehouseField = modal.findElement(By.id("form_in_modal_warehouseName"));
        if (warehouseField.isEnabled()) {
            warehouseField.sendKeys(warehouse);
            warehouseField.sendKeys(Keys.ENTER);
        }
        WebElement priceField = modal.findElement(By.id("form_in_modal_price"));
        priceField.sendKeys(price);
        WebElement sizeField = modal.findElement(By.id("form_in_modal_size"));
        sizeField.sendKeys(size);
        WebElement quantityField = modal.findElement(By.id("form_in_modal_quantity"));
        quantityField.sendKeys(quantity);
    }

    public void clickOnModalSubmitButton() {
        WebElement button = modal.findElement(By.className("ant-modal-footer")).findElements(By.tagName("button")).get(1);
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        button.click();
    }

    public boolean containsInventoryWithName(String name) {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return inventorySection.getText().contains(name);
    }

    public boolean doesNotContainInventoryWithName(String name) {
        return !inventorySection.getText().contains(name);
    }
}

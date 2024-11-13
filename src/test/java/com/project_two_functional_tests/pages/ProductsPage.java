package com.project_two_functional_tests.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {

    private WebDriver driver;
    private JavascriptExecutor js;

    private static final String url = "http://localhost:5173/products";
    private String updatedCardCategoryName;
    private String deletedCardCategoryName;

    @FindBy(className = "ant-card")
    private List<WebElement> cards;

    @FindBy(className = "ant-btn-primary")
    private WebElement addCategoryButton;

    @FindBy(className = "ant-modal-content")
    private WebElement modal;

    @FindBy(className = "ant-dropdown")
    private WebElement dropdown;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Verify if the current page URL matches the expected URL
    public boolean onPage() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.getCurrentUrl().equals(url);
    }

    // Navigate to the specified URL
    public void get() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.driver.get(url);
    }

    // Check if the product category cards are loaded
    public boolean productCategoryCardsLoaded() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cards.size() > 0;
    }

    // Verify if all categories are displayed by checking related cards
    public boolean iShouldSeeTheProductCategories() {
        for (WebElement card : cards) {
            String title = card.findElement(By.className("ant-card-meta-title")).getText();
            if (title.equals("Climbing Shoes"))
                return true;
        }
        return false;
    }

    public void clickButton(String buttonText) {
        WebElement button = driver.findElement(By.xpath("//button[span[text()='" + buttonText + "']]"));
        button.click();
    }

    public void focusAndSelectButton(String buttonText) {
        WebElement button = driver.findElement(By.xpath("//button[span[text()='" + buttonText + "']]"));
        js.executeScript("arguments[0].focus();", button);
        button.sendKeys(Keys.ENTER);    
    }

    // Fill out form input in the modal
    public void enterInput(String input) {
        WebElement field = modal.findElement(By.tagName("input"));
        field.sendKeys(input);
    }

    // Fill out form input in the modal
    public void focusAndEnterInput(String input) {
        WebElement field = modal.findElement(By.tagName("input"));
        js.executeScript("arguments[0].focus();", field);
        field.sendKeys(Keys.ENTER);    
        field.sendKeys(input);
    }

    // Check if any cards contain matching name (returns true if found)
    public boolean containsProductCategoryWithName(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (WebElement card : cards) {
            String title = card.findElement(By.className("ant-card-meta-title")).getText();
            if (title.equals(name))
                return true;
        }
        return false;
    }

    // Check if any cards contain matching name (returns false if found)
    public boolean doesNotContainProductCategoryWithName(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (WebElement card : cards) {
            String title = card.findElement(By.className("ant-card-meta-title")).getText();
            if (title.equals(name))
                return false;
        }
        return true;
    }

    // Click on "Edit" or "..." button on card
    public void clickIconOnCard(String iconType) {
        switch (iconType) {
            case "edit":
                updatedCardCategoryName = cards.get(0).findElement(By.className("ant-card-meta-title")).getText();
                cards.get(0).findElement(By.className("anticon-edit")).click();
                break;
            case "delete":
                deletedCardCategoryName = cards.get(0).findElement(By.className("ant-card-meta-title")).getText();
                cards.get(0).findElement(By.className("anticon-delete")).click();
                break;
        }
    }

    // Select "Edit" or "..." button on card via keyboard
    public void focusAndSelectIconOnCard(String iconType) {
        switch (iconType) {
            case "edit":
                updatedCardCategoryName = cards.get(0).findElement(By.className("ant-card-meta-title")).getText();
                WebElement editIcon = cards.get(0).findElement(By.className("anticon-edit"));
                js.executeScript("arguments[0].focus();", editIcon);
                editIcon.sendKeys(Keys.ENTER); 
                break;
            case "delete":
                deletedCardCategoryName = cards.get(0).findElement(By.className("ant-card-meta-title")).getText();
                WebElement deleteIcon = cards.get(0).findElement(By.className("anticon-delete"));
                js.executeScript("arguments[0].focus();", deleteIcon);
                deleteIcon.sendKeys(Keys.ENTER); 
                break;
        }
    }

    // Checks if the form field contains the current category name based on its
    // initial value
    public boolean updateFieldContainsProductCategoryName() {
        WebElement updateField = modal.findElement(By.id("form_in_modal_name"));
        return updateField.getAttribute("value").equals(updatedCardCategoryName);
    }

    // Edits the input in the form field with the specified value, replacing the
    // existing input
    public void updateNameField(String input) {
        WebElement nameField = modal.findElement(By.id("form_in_modal_name"));

        // Clear method won't work due to the way the initial value of the field is
        // being set
        for (int i = 0; i < updatedCardCategoryName.length(); i++) {
            nameField.sendKeys(Keys.BACK_SPACE);
        }

        nameField.sendKeys(input);
    }

    // Edits the input in the form field with the specified value, replacing the
    // existing input via keyboard
    public void focusAndUpdateNameField(String input) {
        WebElement nameField = modal.findElement(By.id("form_in_modal_name"));
        js.executeScript("arguments[0].focus();", nameField);
        nameField.sendKeys(Keys.ENTER); 

        // Clear method won't work due to the way the initial value of the field is
        // being set
        for (int i = 0; i < updatedCardCategoryName.length(); i++) {
            nameField.sendKeys(Keys.BACK_SPACE);
        }

        nameField.sendKeys(input);
    }

    // Checks that card is updated after save by verifying it contains the new name
    public boolean savedCardIsUpdated(String newTitle) {
        return this.containsProductCategoryWithName(newTitle);
    }

    // Checks that card is not updated after cancel by verifying it contains the
    // initial name
    public boolean canceledCardIsNotUpdated() {
        return this.containsProductCategoryWithName(updatedCardCategoryName);
    }

    // Checks that card is deleted by verifying no cards it contains the name
    public boolean cardIsDeleted() {
        return this.doesNotContainProductCategoryWithName(deletedCardCategoryName);
    }

}

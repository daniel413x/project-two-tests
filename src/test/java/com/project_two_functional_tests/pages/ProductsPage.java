package com.project_two_functional_tests.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {

    private WebDriver driver;
    private static final String url = "http://crag-supply-co-client.s3-website-us-east-1.amazonaws.com/products";
    private String editedCardCategoryName;
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean onPage() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cards.size() > 0;
    }

    public boolean iShouldSeeTheProductCategories() {
        for (WebElement card : cards) {
            String title = card.findElement(By.className("ant-card-meta-title")).getText();
            if (title.equals("Climbing Shoes"))
                return true;
        }
        return false;
    }

    public void clickOnCreateProductCategoryButton() {
        addCategoryButton.click();
        modal.isDisplayed();
    }

    public void enterInput(String input) {
        WebElement field = modal.findElement(By.tagName("input"));
        field.sendKeys(input);
    }

    public void clickOnModalSubmitButton() {
        WebElement button = modal.findElement(By.xpath("//button[@type='submit']"));
        button.click();
    }

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

    public void selectIconOnCard(String iconType) {
        switch (iconType) {
            case "edit":
                editedCardCategoryName = cards.get(0).findElement(By.className("ant-card-meta-title")).getText();
                cards.get(0).findElement(By.className("anticon-edit")).click();
                break;
            case "...":
                deletedCardCategoryName = cards.get(0).findElement(By.className("ant-card-meta-title")).getText();
                cards.get(0).findElement(By.className("anticon-ellipsis")).click();
                break;
        }
    }

    public boolean updateFieldContainsProductCategoryName() {
        WebElement updateField = modal.findElement(By.id("form_in_modal_name"));
        return updateField.getAttribute("value").equals(editedCardCategoryName);
    }

    public void editUpdateField(String input) {
        WebElement updateField = modal.findElement(By.id("form_in_modal_name"));

        // Clear method won't work due to the way the initial value of the field is being set
        for (int i = 0; i < editedCardCategoryName.length(); i++) {
            updateField.sendKeys(Keys.BACK_SPACE);
        }

        updateField.sendKeys(input);
    }

    public void clickButtonInModal(String buttonText) {
        switch (buttonText) {
            case "Save":
                modal.findElement(By.xpath("//button[@type='submit']")).click();
                break;
            case "Cancel":
                // Update frontend to be more specific selector
                modal.findElement(By.className("ant-btn-default")).click();
                break;
        }
    }

    public boolean savedCardIsUpdated(String newTitle) {
        return this.containsProductCategoryWithName(newTitle);
    }

    public boolean canceledCardIsNotUpdated() {
        return this.containsProductCategoryWithName(editedCardCategoryName);
    }

    public void selectDeleteDropdownOption() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        WebElement deleteMenuItem = wait.until(ExpectedConditions.elementToBeClickable(By.className("ant-dropdown-menu-item-danger")));
        deleteMenuItem.click();
    }

    public boolean cardIsDeleted() {
        return this.doesNotContainProductCategoryWithName(deletedCardCategoryName);
    }

}

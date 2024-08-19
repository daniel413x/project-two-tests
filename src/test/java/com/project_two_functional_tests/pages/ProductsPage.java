package com.project_two_functional_tests.pages;

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

    @FindBy(className="ant-card")
    private List<WebElement> cards;

    @FindBy(tagName = "button")
    private WebElement addCategoryButton;

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
        return cards.size() > 0;
    }

    public boolean iShouldSeeTheProductCategories() {
        for (WebElement card : cards) {
            String title = card.findElement(By.className("ant-card-meta-title")).getText();
            if (title.equals("Climbing Shoes")) return true;
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
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        WebElement button = modal.findElement(By.xpath("//button[@type='submit']"));
        button.click();
    }

    public boolean containsProductCategoryWithName(String name) {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        for (WebElement card : cards) {
            String title = card.findElement(By.className("ant-card-meta-title")).getText();
            System.out.println(title);
            if (title.equals(name)) return true;
        }
        return false;
    }

    public boolean doesNotContainProductCategoryWithName(String name) {
        for (WebElement card : cards) {
            String title = card.findElement(By.className("ant-card-meta-title")).getText();
            if (title.equals(name)) return false;
        }
        return true;
    }
}

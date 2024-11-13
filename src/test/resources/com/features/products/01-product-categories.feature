@product-categories
Feature: Product categories shown 

  Scenario:
    Given I am on the product categories page
    When the product categories load
    Then I should see product categories

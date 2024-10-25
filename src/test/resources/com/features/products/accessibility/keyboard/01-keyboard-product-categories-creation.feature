@keyboard-product-categories-creation
Feature: Product categories creation via keyboard

  Scenario: A new product category is created via keyboard
    Given I am on the product categories page
    When I tab and select the "Add Category" button
    Then I enter valid input for a new product category
    And I submit the form
    Then the created product category should appear in the list of categories

@keyboard-product-categories-creation
Feature: Product categories creation via keyboard

  Scenario: A new product category is created via keyboard
    Given I am on the product categories page
    When the product categories load
    When I focus and select the "Add Category" button
    Then I focus and enter valid input for a new product category into the modal form
    And I focus and select the "Create" button
    Then the created product category should appear in the list of categories
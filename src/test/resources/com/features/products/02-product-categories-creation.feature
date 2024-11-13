@product-categories-creation
Feature: Product categories creation

  Scenario: A new product category is created
    Given I am on the product categories page
    When the product categories load
    Then I click the "Add Category" button
    Then I enter valid input for a new product category into the modal form
    And I click the "Create" button
    Then the created product category should appear in the list of categories

@products-categories-creation
Feature: Product categories creation

  Background:
    Given I am on the products page
    And I have opened the create product category form modal

  Scenario: A new product category is created
    When I enter valid input for a new product category
    And I press the product category form submit button
    Then the product category form submission should be successful
    And the created product category should appear in the list of categories

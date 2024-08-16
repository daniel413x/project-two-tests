@products-categories-creation
Feature: Product categories creation

  Background:
    Given I am on the products page
    And I have opened the create form modal

  Scenario: A new product category is created
    When I enter valid input
    And I press the submit button
    Then the form submission should be successful
    And the created product category should appear in the list of categories

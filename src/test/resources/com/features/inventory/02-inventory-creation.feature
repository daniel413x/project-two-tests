@inventory-creation
Feature: Inventory creation

  Background:
    Given I am on the all inventory page
    And I have opened the create inventory form modal

  Scenario: A new inventory is created
    When I enter valid input for new inventory
    And I press the create inventory form submit button
    Then the created inventory should appear in the list of inventory

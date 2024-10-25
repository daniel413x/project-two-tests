@keyboard-warehouse-creation
Feature: Warehouse creation via keyboard

  Scenario: A new warehouse is created via keyboard
    Given I am on the warehouses page
    When I tab and select the "Add Warehouse" button
    Then I enter valid input for a new warehouse
    And I submit the form
    Then the created warehouse should appear in the list of warehouses

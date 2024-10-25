@keyboard-warehouse-creation
Feature: Warehouse creation via keyboard

  Scenario: A new warehouse is created via keyboard
    Given I am on the warehouses page
    When I tab and select the "Add Warehouse" button
    Then I tab and enter valid input for a new warehouse
    And I tab and select the submit button for the "warehouse" modal
    Then the created warehouse should appear in the list of warehouses

@warehouse-creation
Feature: Warehouse creation

  Scenario:
    Given I am on the warehouses page
    When the warehouses load
    Then I click the "Add Warehouse" button
    Then I enter valid input for a new warehouse into the modal form
    And I click the "Create" button
    Then the created warehouse should appear in the list of warehouses

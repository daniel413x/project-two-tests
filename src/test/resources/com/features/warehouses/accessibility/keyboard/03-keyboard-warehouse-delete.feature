@keyboard-warehouse-delete
Feature: Delete warehouse via keyboard

  Scenario: Delete warehouse via keyboard
    Given I am on the warehouses page
    When the warehouses load
    And I tab and select the "..." icon on a warehouse card
    And tab and select Delete from the dropdown
    Then I should not see the warehouse card displayed

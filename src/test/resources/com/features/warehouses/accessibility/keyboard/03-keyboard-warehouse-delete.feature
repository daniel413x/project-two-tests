@keyboard-warehouse-delete
Feature: Delete warehouse via keyboard

  Scenario: Delete warehouse via keyboard
    Given I am on the warehouses page
    When the warehouses load
    And I focus and select the "delete" icon on a warehouse card
    Then I should not see the warehouse card displayed

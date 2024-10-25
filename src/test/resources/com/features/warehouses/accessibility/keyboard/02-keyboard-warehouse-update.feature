@keyboard-warehouse-update
Feature: Update warehouse via keyboard

  Background:
    Given I am on the warehouses page
    When the warehouses load
    And I tab and select the "edit" icon on a warehouse card
    Then I should see a form with pre-filled fields of current warehouse information
    And tab and edit the Warehouse Name, Max Capacity, Street Address, City, State, and Zip Code fields

  Scenario: Save warehouse update via keyboard
    Then I should tab and select the "Save" button
    And see the warehouse name, city, and state updated
    Then I should tab and select the updated warehouse card
    And see the max capacity updated

  Scenario: Cancel warehouse update va keyboard
    Then I should tab and select the "Cancel" button
    And see the warehouse name, city, and state unchanged
    Then I should tab and select the unchanged warehouse card
    And see the max capacity unchanged

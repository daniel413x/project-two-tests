@warehouse-update
Feature: Update warehouse

  Background:
    Given I am on the warehouses page
    When the warehouses load
    And I select the "edit" icon on a warehouse card
    Then I should see a form with pre-filled fields of current warehouse information
    And edit the Warehouse Name, Max Capacity, Street Address, City, State, and Zip Code fields

  Scenario: Save warehouse update
    Then I should click the "Save" button
    And see the warehouse name, city, and state updated
    Then I should select the updated warehouse card
    And see the max capacity updated

  Scenario: Cancel warehouse update
    Then I should click the "Cancel" button
    And see the warehouse name, city, and state unchanged
    Then I should select the unchanged warehouse card
    And see the max capacity unchanged

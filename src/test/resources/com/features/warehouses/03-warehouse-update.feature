@warehouse-update
Feature: Update warehouse

  Background:
    Given I am on the warehouses page
    When the warehouses load
    And I click the "edit" icon on a warehouse card
    Then I should see a form with pre-filled fields of current warehouse information
    And update the Warehouse Name, Max Capacity, Street Address, City, State, and Zip Code fields

  Scenario: Save warehouse update
    Then I click the "Save" button
    And should see the warehouse name, city, and state updated
    Then I click the updated warehouse card
    And should see the max capacity updated

  Scenario: Cancel warehouse update
    Then I click the "Cancel" button
    And should see the warehouse name, city, and state unchanged
    Then I click the unchanged warehouse card
    And should see the max capacity unchanged

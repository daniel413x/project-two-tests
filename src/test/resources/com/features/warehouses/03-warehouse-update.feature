@warehouse-update
Feature: Update warehouse

  Background:
    Given I am on the warehouses page
    And there is one or more existing warehouse cards
    When I select the edit icon on a card
    Then I should see a form with pre-filled fields of current warehouse information
    And be able to edit the "Warehouse Name", "Max Capacity", "Street Address", "City", "State", and "Zip Code" fields

  Scenario: Save warehouse update
    Then I should click the Save button
    And see the warehouse name, city, and state updated
    Then I should select that card
    And see the max capacity updated

  Scenario: Cancel warehouse update
    Then I should click the Cancel button
    And see the warehouse name, city, and state unchanged
    Then I should select that card
    And see the max capacity unchanged

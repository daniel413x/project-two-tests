@warehouse-delete
Feature: Delete warehouse

  Scenario: Delete warehouse
    Given I am on the warehouses page
    When the warehouses load
    And I select the "..." icon on a warehouse card
    And select Delete from the dropdown
    Then I should not see the warehouse card displayed

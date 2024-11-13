@warehouse-delete
Feature: Delete warehouse

  Scenario: Delete warehouse
    Given I am on the warehouses page
    When the warehouses load
    And I click the "delete" icon on a warehouse card
    Then I should not see the warehouse card displayed

@warehouse-delete
Feature: Delete warehouse

  Scenario: Delete product category
    Given I am on the warehouses page
    And there is one or more existing warehouse cards
    When I select the "..." icon on a warehouse card
    And select "Delete" from the dropdown
    Then I should not see the warehouse card displayed
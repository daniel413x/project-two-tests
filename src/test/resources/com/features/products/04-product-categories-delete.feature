@product-categories-delete
Feature: Delete product category

  Scenario: Delete product category
    Given I am on the product categories page
    And there is one or more existing category cards
    When I select the "..." icon on a category card
    And select "Delete" from the dropdown
    Then I should not see the category card displayed

@product-categories-update
Feature: Update product category

  Background:
    Given I am on the product categories page
    And there is one or more existing category cards
    When I select the edit icon on a card
    Then I should see a category name field pre-filled with the current name
    And be able to edit the category name field to a new value

  Scenario: Save product category update
    Then I should click the Save button
    And see the category name updated to the new value

  Scenario: Cancel product category update
    Then I should click the Cancel button
    And see the category name unchanged

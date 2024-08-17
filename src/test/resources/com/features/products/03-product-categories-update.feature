@products-categories-update
Feature: Update product category

  Background:
    Given I am on the product categories page
    When I select the edit icon on the "Climbing Shoes" card
    Then I should see a category name field pre-filled with "Climbing Shoes"
    And be able to edit the category name field to "Climbing Shoes & Crampons"

  Scenario: Save product category update
    Then I should click the Save button
    And see the category name updated to "Climbing Shoes & Crampons"

  Scenario: Cancel product category update
    Then I should click the Cancel button
    And see the category name remain "Climbing Shoes"

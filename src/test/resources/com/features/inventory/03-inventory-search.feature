# @inventory-search
# Feature: Search inventory items
#   Scenario Outline: Search inventory items on all inventory page
#     Given I am on the all inventory page
#     When I select the search icon on the "<column>" column
#     Then I should see a search modal displayed
#     Then I should enter "<value>" in the search field
#     And click the Search button
#     Then I should see rows displayed where the "<column>" column has a value that contains "<value>"
#     Examples:
#       | column      | value         |
#       | Brand       | Black Diamond |
#       | Name        | Miura         |
#       | Description | advanced      |
#   Scenario Outline: Search inventory items on a warehouse inventory page
#     Given I am on the inventory page for a specific warehouse
#     When I select the search icon on the "<column>" column
#     Then I should see a search modal displayed
#     Then I should enter "<value>" in the search field
#     And click the Search button
#     Then I should see rows displayed where the "<column>" column has a value that contains "<value>"
#     Examples:
#       | column      | value         |
#       | Brand       | Black Diamond |
#       | Name        | Miura         |
#       | Description | advanced      |
#   Scenario Outline: Search inventory items on a product category page
#     Given I am on the inventory page for a specific product category
#     When I select the search icon on the "<column>" column
#     Then I should see a search modal displayed
#     Then I should enter "<value>" in the search field
#     And click the Search button
#     Then I should see rows displayed where the "<column>" column has a value that contains "<value>"
#     Examples:
#       | column      | value         |
#       | Brand       | Black Diamond |
#       | Name        | Miura         |
#       | Description | advanced      |
#   Scenario Outline: Reset search of inventory items on all inventory page
#     Given I am on the all inventory page and I have searched "<column>" for "Black Diamond"
#     When I select the search icon on the "<column>" column
#     Then I should see a search modal displayed
#     And click the Reset button
#     Then I should see my search cleared
#     And all rows displayed where the "<column>" column contains any value
#     Examples:
#       | column      |
#       | Brand       |
#       | Name        |
#       | Description |
#   Scenario Outline: Reset search of inventory items on a warehouse inventory page
#     Given I am on the inventory page for a specific warehouse and I have searched "<column>" for "Black Diamond"
#     When I select the search icon on the "<column>" column
#     Then I should see a search modal displayed
#     And click the Reset button
#     Then I should see my search cleared
#     And all rows displayed where the "<column>" column contains any value
#     Examples:
#       | column      |
#       | Brand       |
#       | Name        |
#       | Description |
#   Scenario Outline: Search inventory items on a product category page
#     Given I am on the inventory page for a specific product category and I have searched "<column>" for "Black Diamond"
#     When I select the search icon on the "<column>" column
#     Then I should see a search modal displayed
#     And click the Reset button
#     Then I should see my search cleared
#     And all rows displayed where the "<column>" column contains any value
#     Examples:
#       | column      |
#       | Brand       |
#       | Name        |
#       | Description |

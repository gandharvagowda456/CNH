Feature: Placing An Order

  @test
  Scenario: Placing an Order
    Given user launch the url
      | attachImage |
      | true        |
    And user should be on "Login" page
    And wait for "ebayLogo" to be appeared
    And below details are entered
      | ebaySearch | book|
    And wait for "ebaySearchBtn" to be appeared
    And "ebaySearchBtn" is clicked
    And wait for "firstBook" to be appeared
    And "firstBook" is clicked
    And switch to child tab
    And wait for "addToCartBtn" to be appeared
    And "addToCartBtn" is clicked
    And wait for "popUpAddedCart" to be appeared
    And "closePopUP" is clicked
    And wait for "cartValues" to be appeared
    And verify "cartValues" with "1" item

  Scenario: API
    And get response



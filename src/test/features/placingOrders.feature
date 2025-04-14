Feature: Placing An Order

  Scenario: Placing an Order
    Given user launch the url
      | attachImage |
      | true        |
    And user should be on "Login" page
    And "logInButton" is clicked
    And below details are entered
      | username | test_interview@cnh.com|
    And wait for "continueButton" to be appeared
    And "continueButton" should be enabled
    And "continueButton" is clicked
    And below details are entered
      | password | 11223344|
    And wait for "labTestsServices" to be appeared
    And "labTestsServices" is clicked
    And wait for "kidneyFunctionTestsButton" to be appeared
    And "kidneyFunctionTestsButton" is clicked
    And wait for "bookNowButton" to be appeared
    And "bookNowButton" is clicked
    And wait for "memberNameXpath" to be appeared
    And "memberNameXpath" is clicked
    And wait for "continueButton" to be appeared
    And "continueButton" is clicked
    And wait for "nextButton" to be appeared
    And "nextButton" is clicked
    And wait for "nextButton" to be appeared
    And "nextButton" is clicked
    And wait for "nextButton" to be appeared
    And "nextButton" is clicked
    And wait for "selectLabButton" to be appeared
    And "selectLabButton" is clicked
    And wait for "pickTime" to be appeared
    And "pickTime" is clicked
    And wait for "placeAndPalButton" to be appeared
    And "placeAndPalButton" is clicked
    And wait for "netBankingOption" to be appeared
    And "netBankingOption" is clicked
    And wait for "axisBankOption" to be appeared
    And "axisBankOption" is clicked
    And wait for "dropDownSelID" to be appeared
    And "dropDownSelID" is clicked
    And wait for "chargedOption" to be appeared
    And hover over and click on "chargedOption"
    And wait for "submitButton" to be appeared
    And "submitButton" is clicked
    And wait for "continueButton" to be appeared
    And "continueButton" is clicked
    And wait for "orderProduct" to be appeared
    And "orderProduct" should be displayed
    And wait for "cancelOrderButton" to be appeared
    And "cancelOrderButton" is clicked
    And wait for "yesButton" to be appeared
    And "yesButton" is clicked
    And wait for "otherOption" to be appeared
    And "otherOption" is clicked
    And wait for "continueButton" to be appeared
    And "continueButton" is clicked
    And wait for "homePageButton" to be appeared
    And "homePageButton" is clicked
    And wait for "profileDropdown" to be appeared
    And "profileDropdown" is clicked
    And wait for "logOutButton" to be appeared
    And "logOutButton" is clicked
    And wait for "logInButton" to be appeared
    And "logInButton" should be displayed





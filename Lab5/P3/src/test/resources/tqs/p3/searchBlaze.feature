Feature: SearchBlaze

  Scenario: Purchase the 3rd Flight from Paris to Rome
    When I navigate to "https://www.blazedemo.com"
    And I choose "Paris" on the input "fromPort"
    And I choose "Rome" on the input "toPort"
    And I click on "Find Flights"
    And I choose the flight 3
    And I click on "Purchase Flight"
    Then I should be shown results including "Thank you for your purchase today!"
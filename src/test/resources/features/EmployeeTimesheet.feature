Feature: Timesheet managemenet

  @TimeSheetDryRun
  Scenario Outline: I am able to see the correct total of my booked time sheet for a week
    Given Navigate to Time Page after Log in and naviagte to MyTimeSheetPage
    When I capture the booked time from all days and do the sum of it
    And I capture  the total displyed under Total column
    Then I verify that the Displayed total is correct

    Examples: 
      | MyTimtSheetPeriod         |
      | CurrentWoorkalreadyBooked |

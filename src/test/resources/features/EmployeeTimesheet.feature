Feature: Timesheet managemenet

  Scenario Outline: I am able to see the correct total of my booked time sheet for a week
    Given Navigate to Time Page after Log in and naviagte to MyTimeSheetPage
    When I capture the booked time from all days and do the sum of it
    And I capture  the total displyed under Total column
    Then I verify that the Displayed total is correct

    Examples: 
      | MyTimtSheetPeriod         |
      | CurrentWoorkalreadyBooked |

  @AddProjectDryRun
  Scenario Outline: I am able to add new project to book Timesheet
    Given Navigate to Time Page after Log in and naviagte to Projects Page
    When I add new project with name "<projectname>" existing customer with Customer name as "<CustomerName>"
    Then I search newly added project by "<projectname>"
    And I Delete the searched Project

    Examples: 
      | CustomerName | projectname |
      | custromer1   | myproject1  |

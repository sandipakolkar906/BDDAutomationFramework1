Feature: Dashboard Widgets tests

  @DryRunDashboard
  Scenario: I am able to view correct information at Time at Work widget
    Given I log in with Admin user and I am at Dashboard Page
    When I view the Time at work widget at Dashboard Page
    Then I check below values from the widget showing correct values
      | WidgetinfoTitle    | ExpectedValue   |
      | lastpunchedinTime  | Not Punched In  |
      | currentTime        | 0h 0m Today     |
      | CurrentWeekspan    | Mar 24 - Mar 30 |
      | TotalWeekHoursmins | 0h 0m           |

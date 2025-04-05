Feature: leave CRUD Operations

  @DryRunLleave
  Scenario: I am able to add, edit and Delete  LeaveType
    Given Navigate to LEave after log in with Admin user
    When I navigate config and then Leave Types
    Then Add the Leave with below field and values and I verify leave is displayed in leave list
      | LeaveTypeTitle | SickLeave4 |
    Then I Edit the Leave type and change below values and I verify verify the leave name is changed to new name
      | LeaveTypeTitle | PersonalLeave1 |
    And Dlete the Newly added Leave Type

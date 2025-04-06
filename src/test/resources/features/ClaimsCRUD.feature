Feature: Claims CRUD operations

  @DryRunClaims
  Scenario: I am able to add, edit and Delete  Expenses and verify the total
    Given Navigate to Claims after log in with Admin user
    And I navigate to submit claims Page
    When I crate claim Request with Event and Currency
      | EventName  | currencyName        |
      | HotelBills | Afghanistan Afghani |
    When I add Expenses for the new claim Request with below fields and values
      | Field         | values      |
      | ExpenseType   | FuelExpense |
      | Date          | 2025-04-06  |
      | ExpenseAmount |          50 |
    When I add Expenses for the new claim Request with below fields and values
      | Field         | values      |
      | ExpenseType   | FuelExpense |
      | Date          | 2025-04-06  |
      | ExpenseAmount |          50 |
    Then I verify the total amount of all expenses is caculated correctly
    When I edit the Expense
    When Delete one Expense

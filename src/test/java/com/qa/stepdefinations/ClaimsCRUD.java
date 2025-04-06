package com.qa.stepdefinations;

import com.qa.base.Base;
import com.qa.pages.AddEmployeePage;
import com.qa.pages.ClaimsPage;
import com.qa.pages.LoginPage;
import com.qa.util.CaptureScreenshot;
import com.qa.util.ReadProperties;
import com.qa.util.WaitMethods;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class ClaimsCRUD extends Base {

	Scenario scenario;
	LoginPage objloginPage;
	ClaimsPage objClaimsPage;

	@Before
	public void logintoApplication(Scenario scenario) {

		this.scenario = scenario;
	}

	@Given("^Navigate to Claims after log in with Admin user$")
	public void navigate_to_Claims_after_log_in_with_Admin_user() throws Throwable {

		scenario.write("Starting orange HRM log in page ");
		driver = initializeWebDriver();
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		objloginPage = new LoginPage(driver);
		scenario.write("Logging in to the OrangeHRM Applciation ");
		objloginPage.logintoApplication(ReadProperties.getAppUserName(), ReadProperties.getAppPassword(), scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

		scenario.write("Navigate to Claim page ");
		objClaimsPage = new ClaimsPage(driver);
		objClaimsPage.navigatetoClaimsPage(scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@Given("^I navigate to submit claims Page$")
	public void i_navigate_to_submit_claims_Page() throws Throwable {
		scenario.write("Navigate to submit Claim page ");
		objClaimsPage.navigatetoSubmitClaims(scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@When("^I crate claim Request with Event and Currency$")
	public void i_crate_claim_Request_with_Event_and_Currency(DataTable eventandCurrencyTable) throws Throwable {

		scenario.write("Selecting  Event as : " + eventandCurrencyTable.raw().get(1));
		scenario.write("Adding Currency as  as : " + eventandCurrencyTable.raw().get(1).get(1));
		objClaimsPage.addEventandCurrency(scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@When("^I add Expenses for the new claim Request with below fields and values$")
	public void i_add_Expenses_for_the_new_claim_Request_with_below_fields_and_values(DataTable expenseInfoTable)
			throws Throwable {
		scenario.write("Adding Expenses");
		objClaimsPage.addExpenses(scenario, expenseInfoTable.raw().get(2).get(1), expenseInfoTable.raw().get(3).get(1));
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I verify the total amount of all expenses is caculated correctly$")
	public void i_verify_the_total_amount_of_all_expenses_is_caculated_correctly() throws Throwable {
		scenario.write("Verifying the total is calculated correctly ");
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		Assert.assertEquals("100.00", objClaimsPage.getTotalExpenseAmount(scenario));

	}

	@When("^I edit the Expense$")
	public void i_edit_the_Expense() throws Throwable {
		scenario.write("Editing the last Expense ");
		objClaimsPage.editlastExpense(scenario, "10");
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		WaitMethods.staticWait(10000);
		Assert.assertEquals("60.00", objClaimsPage.getTotalExpenseAmount(scenario));
	}

	@When("^Delete one Expense$")
	public void delete_one_Expense() throws Throwable {
		scenario.write("Deleting the last Expense ");
		objClaimsPage.deletelastExpense(scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		Assert.assertEquals("50.00", objClaimsPage.getTotalExpenseAmount(scenario));
	}

	@After
	public void closeApplication(Scenario scenario) {

		scenario.write("Closing the application");
		closeBrowser();
	}
}

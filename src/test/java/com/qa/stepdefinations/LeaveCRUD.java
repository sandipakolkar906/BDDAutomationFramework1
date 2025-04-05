package com.qa.stepdefinations;

import com.qa.base.Base;
import com.qa.pages.LeavePage;
import com.qa.pages.LoginPage;
import com.qa.util.CaptureScreenshot;
import com.qa.util.ReadProperties;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class LeaveCRUD extends Base {
	Scenario scenario;
	LoginPage objloginPage;
	LeavePage objLeavePage;

	@Before
	public void logintoApplication(Scenario scenario) {

		this.scenario = scenario;
	}

	@Given("^Navigate to LEave after log in with Admin user$")
	public void navigate_to_LEave_after_log_in_with_Admin_user() throws Throwable {
		scenario.write("Starting orange HRM log in page ");
		driver = initializeWebDriver();
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		objloginPage = new LoginPage(driver);
		scenario.write("Logging in to the OrangeHRM Applciation ");
		objloginPage.logintoApplication(ReadProperties.getAppUserName(), ReadProperties.getAppPassword(), scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

		scenario.write("Navigate to Leave page ");
		objLeavePage = new LeavePage(driver);
		objLeavePage.navigateToLeavePage(scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@When("^I navigate config and then Leave Types$")
	public void i_navigate_config_and_then_Leave_Types() throws Throwable {
		scenario.write("navigate config and then Leave Types ");
		objLeavePage.navigateToConfigMenu(scenario);
		objLeavePage.navigateToLEaveType(scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@Then("^Add the Leave with below field and values and I verify leave is displayed in leave list$")
	public void add_the_Leave_with_below_field_and_values_and_I_verify_leave_is_displayed_in_leave_list(
			DataTable leaveTypeTable) throws Throwable {

		Assert.assertEquals(leaveTypeTable.raw().get(0).get(1),
				objLeavePage.addLeaveType(scenario, leaveTypeTable.raw().get(0).get(1)));
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@Then("^I Edit the Leave type and change below values and I verify verify the leave name is changed to new name$")
	public void i_Edit_the_Leave_type_and_change_below_values_and_I_verify_verify_the_leave_name_is_changed_to_new_name(
			DataTable leaveTypeTable) throws Throwable {
		Assert.assertEquals(leaveTypeTable.raw().get(0).get(1),
				objLeavePage.editLeaveType(scenario, leaveTypeTable.raw().get(0).get(1)));
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^Dlete the Newly added Leave Type$")
	public void dlete_the_Newly_added_Leave_Type() throws Throwable {
		objLeavePage.deleteNewlyAddedLeaveType(scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@After
	public void closeApplication(Scenario scenario) {

		scenario.write("Closing the application");
		closeBrowser();
	}

}

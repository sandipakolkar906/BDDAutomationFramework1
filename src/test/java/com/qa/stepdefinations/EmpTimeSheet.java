package com.qa.stepdefinations;

import com.qa.base.Base;
import com.qa.pages.LoginPage;
import com.qa.pages.MyTimehseetPage;
import com.qa.util.CaptureScreenshot;
import com.qa.util.ReadProperties;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

/**
 * @author Sandip
 * 
 *         This is a class to add ....
 *
 */
public class EmpTimeSheet extends Base {

	Scenario scenario;
	LoginPage objloginPage;
	MyTimehseetPage objMyTimehseetPage;
	int expectedBookedTimeForalldays;
	int actualSumofallBookedDays;

	@Before
	public void logintoApplication(Scenario scenario) {

		this.scenario = scenario;
	}

	@Given("^Navigate to Time Page after Log in and naviagte to MyTimeSheetPage$")
	public void navigate_to_Time_Page_after_Log_in_and_naviagte_to_MyTimeSheetPage() throws Throwable {

		scenario.write("Starting orange HRM log in page ");
		driver = initializeWebDriver();
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		objloginPage = new LoginPage(driver);
		scenario.write("Logging in to the OrangeHRM Applciation ");
		objloginPage.logintoApplication(ReadProperties.getAppUserName(), ReadProperties.getAppPassword(), scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		objMyTimehseetPage = new MyTimehseetPage(driver);
		scenario.write("Navigating to MyTimeSheet Page ");
		objMyTimehseetPage.navigateToTimePage(scenario);
		objMyTimehseetPage.navigateToMyTimeSheetsPage(scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@When("^I capture the booked time from all days and do the sum of it$")
	public void i_capture_the_booked_time_from_all_days_and_do_the_sum_of_it() throws Throwable {
		scenario.write("Capturing the all days booked time ");
		expectedBookedTimeForalldays = objMyTimehseetPage.getSumofBookedDaysTime(scenario);
		scenario.write("Expected Sum of all booked days is : ==========> " + expectedBookedTimeForalldays);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@When("^I capture  the total displyed under Total column$")
	public void i_capture_the_total_displyed_under_Total_column() throws Throwable {
		scenario.write("Capturing actual sum of booked days calcualted by application!");
		actualSumofallBookedDays = objMyTimehseetPage.getsumofallBookedDays(scenario);
		scenario.write("Sum of all booked days is : ==========> " + actualSumofallBookedDays);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I verify that the Displayed total is correct$")
	public void i_verify_that_the_Displayed_total_is_correct() throws Throwable {
		Assert.assertEquals(expectedBookedTimeForalldays, actualSumofallBookedDays);
	}

	// Add Project Step Definations

	@Given("^Navigate to Time Page after Log in and naviagte to Projects Page$")
	public void navigate_to_Time_Page_after_Log_in_and_naviagte_to_Projects_Page() throws Throwable {
		scenario.write("Starting orange HRM log in page ");
		driver = initializeWebDriver();
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		objloginPage = new LoginPage(driver);
		scenario.write("Logging in to the OrangeHRM Applciation ");
		objloginPage.logintoApplication(ReadProperties.getAppUserName(), ReadProperties.getAppPassword(), scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		objMyTimehseetPage = new MyTimehseetPage(driver);
		objMyTimehseetPage.navigateToTimePage(scenario);
		scenario.write("Navigate to Add Project Screen");
		objMyTimehseetPage.navigateToProjectsPage(scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@When("^I add new project with name \"([^\"]*)\" existing customer with Customer name as \"([^\"]*)\"$")
	public void i_add_new_project_with_name_existing_customer_with_Customer_name_as(String projectName,
			String customerName) throws Throwable {

		scenario.write("Adding new Project");
		objMyTimehseetPage.addNewProject(customerName, projectName, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I search newly added project by \"([^\"]*)\"$")
	public void i_search_newly_added_project_by(String projectName) throws Throwable {
		scenario.write("Searching new Project");
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		Assert.assertEquals(projectName, objMyTimehseetPage.searchProjectbyProjectName(projectName, scenario));
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@Then("^I Delete the searched Project$")
	public void i_Delete_the_searched_Project() throws Throwable {
		scenario.write("Deleting new Project");
		objMyTimehseetPage.deleteProject(scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@After
	public void closeApplication(Scenario scenario) {

		scenario.write("Closing the application");
		closeBrowser();
	}

}

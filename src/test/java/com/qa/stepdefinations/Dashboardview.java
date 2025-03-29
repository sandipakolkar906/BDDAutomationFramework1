package com.qa.stepdefinations;

import java.util.HashMap;

import com.qa.base.Base;
import com.qa.pages.DashboardPage;
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

public class Dashboardview extends Base {

	Scenario scenario;
	LoginPage objloginPage;
	DashboardPage objDashboardPage;
	HashMap<String, String> timeatWorkWidgetDataMap;

	@Before
	public void logintoApplication(Scenario scenario) {

		this.scenario = scenario;
	}

	@Given("^I log in with Admin user and I am at Dashboard Page$")
	public void i_log_in_with_Admin_user_and_I_am_at_Dashboard_Page() throws Throwable {

		scenario.write("Starting orange HRM log in page ");
		driver = initializeWebDriver();
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		objloginPage = new LoginPage(driver);
		scenario.write("Logging in to the OrangeHRM Applciation ");
		objloginPage.logintoApplication(ReadProperties.getAppUserName(), ReadProperties.getAppPassword(), scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@When("^I view the Time at work widget at Dashboard Page$")
	public void i_view_the_Time_at_work_widget_at_Dashboard_Page() throws Throwable {
		scenario.write("Navigate to Dashboard page and verify the Time at work widget ");
		objDashboardPage = new DashboardPage(driver);
		timeatWorkWidgetDataMap = objDashboardPage.getDataFromTimeatworkwidget(scenario);
		Assert.assertEquals("Time at Work", timeatWorkWidgetDataMap.get("timeatWorkWidgettitle"));
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I check below values from the widget showing correct values$")
	public void i_check_below_values_from_the_widget_showing_correct_values(DataTable WigteExpectedDatatable)
			throws Throwable {
		scenario.write("Verifying the Expected Values from Time at work widget as per the table in feature file");
		Assert.assertEquals(WigteExpectedDatatable.raw().get(1).get(1), timeatWorkWidgetDataMap.get("punchedinStatus"));
		Assert.assertEquals(WigteExpectedDatatable.raw().get(2).get(1),
				timeatWorkWidgetDataMap.get("currentSystemTime"));
		Assert.assertEquals(WigteExpectedDatatable.raw().get(3).get(1),
				timeatWorkWidgetDataMap.get("currentWeekRange"));
		Assert.assertEquals(WigteExpectedDatatable.raw().get(4).get(1),
				timeatWorkWidgetDataMap.get("totalhoursinWeek"));
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@After
	public void closeApplication(Scenario scenario) {

		scenario.write("Closing the application");
		closeBrowser();
	}

}

package com.qa.stepdefinations;

import java.util.HashMap;

import org.junit.Assert;

import com.qa.base.Base;
import com.qa.pages.LoginPage;
import com.qa.pages.RecruitmentPage;
import com.qa.util.CaptureScreenshot;
import com.qa.util.ReadProperties;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RecruitmentCRUD extends Base {

	Scenario scenario;
	LoginPage objloginPage;
	RecruitmentPage objRecruitmentPage;
	HashMap<String, String> fieldMap;

	@Before
	public void logintoApplication(Scenario scenario) {

		this.scenario = scenario;
	}

	@Given("^Navigate to Recruitment page after log in with Admin$")
	public void navigate_to_Recruitment_page_after_log_in_with_Admin() throws Throwable {

		scenario.write("Starting orange HRM log in page ");
		driver = initializeWebDriver();
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		objloginPage = new LoginPage(driver);
		scenario.write("Logging in to the OrangeHRM Applciation ");
		objloginPage.logintoApplication(ReadProperties.getAppUserName(), ReadProperties.getAppPassword(), scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		objRecruitmentPage = new RecruitmentPage(driver);
		scenario.write("Naviagte to Recruitment page");
		objRecruitmentPage.naviagetToRecruitmentPage(scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@When("^Add new Candidate with below field and values$")
	public void add_new_Candidate_with_below_field_and_values(DataTable candidateInputDataTable) throws Throwable {
		scenario.write("Adding new Candidate ");
		fieldMap = new HashMap<String, String>();

		fieldMap.put("firstname", candidateInputDataTable.raw().get(1).get(1));
		fieldMap.put("middlename", candidateInputDataTable.raw().get(2).get(1));
		fieldMap.put("lastname", candidateInputDataTable.raw().get(3).get(1));
		fieldMap.put("email", candidateInputDataTable.raw().get(4).get(1));

		objRecruitmentPage.addnewCandidate(fieldMap, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@Then("^I verify that candidate is added with currentDate$")
	public void i_verify_that_candidate_is_added_with_currentDate() throws Throwable {
		scenario.write("Searching and verifying if candidate is added with todays date and  Candidate full name ");
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

		HashMap<String, String> actualValuesMap = objRecruitmentPage.getCandiatenameandApplicationDate(scenario,
				fieldMap.get("firstname"));

		Assert.assertEquals(
				fieldMap.get("firstname") + " " + fieldMap.get("middlename") + " " + fieldMap.get("lastname"),
				actualValuesMap.get("candidatefullName"));

		Assert.assertEquals(java.time.LocalDateTime.now().toLocalDate().toString(),
				actualValuesMap.get("applicationDate"));

	}

	@Then("^I delete the searched Candidate$")
	public void i_delete_the_searched_Candidate() throws Throwable {
		scenario.write("Deleting the searched candidate! ");
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		Assert.assertEquals("No Records Found", objRecruitmentPage.deletesearchedcandidate(scenario));
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@After
	public void closeApplication(Scenario scenario) {

		scenario.write("Closing the application");
		closeBrowser();
	}

}

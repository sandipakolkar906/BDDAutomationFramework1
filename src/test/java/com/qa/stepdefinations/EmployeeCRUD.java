package com.qa.stepdefinations;

import java.util.HashMap;

import com.qa.base.Base;
import com.qa.pages.AddEmployeePage;
import com.qa.pages.LoginPage;
import com.qa.pages.EmployeeReports;
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

public class EmployeeCRUD extends Base {

	Scenario scenario;
	LoginPage objloginPage;
	AddEmployeePage objAddEmployeePage;

	EmployeeReports empReports;

	@Before
	public void logintoApplication(Scenario scenario) {

		this.scenario = scenario;
	}

	@Given("^Navigate to PIM after log in with Admin user$")
	public void navigate_to_PIM_after_log_in_with_Admin_user() throws Throwable {

		scenario.write("Starting orange HRM log in page ");
		driver = initializeWebDriver();
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		objloginPage = new LoginPage(driver);
		scenario.write("Logging in to the OrangeHRM Applciation ");
		objloginPage.logintoApplication(ReadProperties.getAppUserName(), ReadProperties.getAppPassword(), scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		objAddEmployeePage = new AddEmployeePage(driver);
		scenario.write("Navigating to PIM Page ");
		objAddEmployeePage.navigateToPIMPage(scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@When("^I Add employee with  first name as \"([^\"]*)\" and mname as \"([^\"]*)\" and lName as \"([^\"]*)\"$")
	public void i_Add_employee_with_first_name_as_and_mname_as_and_lName_as(String empFirstName, String empMiddleName,
			String empLastName) throws Throwable {
		scenario.write("Adding new employee ");
		objAddEmployeePage.addNewEmp(empFirstName, empMiddleName, empLastName, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@Then("^I  verify employeeAdded in list with  first name as \"([^\"]*)\" and mname as \"([^\"]*)\" and lName as \"([^\"]*)\"$")
	public void i_verify_employeeAdded_in_list_with_first_name_as_and_mname_as_and_lName_as(String empFirstName,
			String empMiddleName, String empLastName) throws Throwable {
		scenario.write("Navigating to Employee List Page");
		objAddEmployeePage.navigateToEmployeeListPage(scenario);
		scenario.write("Searcning  newly added employee and veriyfying ");
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		Assert.assertEquals(empFirstName + " " + empMiddleName, objAddEmployeePage
				.searchnewlyaddedEmpbyKey(empFirstName + " " + empMiddleName + " " + empLastName, scenario));
	}

	@When("^I click on Edit button and update below values and save the Data$")
	public void i_click_on_Edit_button_and_update_below_values_and_save_the_Data(DataTable tablewithUpdateValues)
			throws Throwable {

		scenario.write("Editing the values of the employee");
		scenario.write("Datatable Values to update" + tablewithUpdateValues);
		HashMap<String, String> mapofvaluestoUdpate = new HashMap();

		mapofvaluestoUdpate.put("firstName", tablewithUpdateValues.raw().get(0).get(1));
		mapofvaluestoUdpate.put("middleName", tablewithUpdateValues.raw().get(1).get(1));
		mapofvaluestoUdpate.put("lastName", tablewithUpdateValues.raw().get(2).get(1));
		scenario.write("Map Values to update" + mapofvaluestoUdpate);

		objAddEmployeePage.editEmpinfo(mapofvaluestoUdpate, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I search the employee and ensure that it is searched using below values$")
	public void i_search_the_employee_and_ensure_that_it_is_searched_using_below_values(
			DataTable tablewithUpdatedValues) throws Throwable {
		scenario.write("Navigate to Employee Search Page");
		objAddEmployeePage.navigateToEmployeeListPage(scenario);
		scenario.write("search the employee and validate updated values ");

		Assert.assertEquals(
				tablewithUpdatedValues.raw().get(0).get(1) + " " + tablewithUpdatedValues.raw().get(1).get(1),
				objAddEmployeePage.searchnewlyaddedEmpbyKey(
						tablewithUpdatedValues.raw().get(0).get(1) + " " + tablewithUpdatedValues.raw().get(1).get(1),
						scenario));
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@Then("^I select and Delete the Updated Employee and verify employee is not  in search result$")
	public void i_select_and_Delete_the_Updated_Employee_and_verify_employee_is_not_in_search_result()
			throws Throwable {
		Assert.assertEquals("No Records Found", objAddEmployeePage.deletesearchedEmp(scenario));
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	// Report Operations

	@When("^I add Custom Report with below ReportName as \"([^\"]*)\" and below Display field group and field names$")
	public void i_add_Custom_Report_with_below_ReportName_as_and_below_Display_field_group_and_field_names(
			String reportName, DataTable reportFieldTable) throws Throwable {

		empReports = new EmployeeReports(driver);

		scenario.write("Navigate to Report page");

		empReports.navigateToReportsPage(scenario);
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		scenario.write("Adding the custom Report with fileds ");
		empReports.addCustomReport(reportName, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@Then("^I  verify Report is searched in the Report with ReportName as \"([^\"]*)\"$")
	public void i_verify_Report_is_searched_in_the_Report_with_ReportName_as(String reportName) throws Throwable {
		scenario.write("Searching the crated Report ");
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		WaitMethods.staticWait(5000);
		empReports.navigateToReportsPage(scenario);
		empReports.searchReport(reportName, scenario);
		
	}

	@Then("^I verify the Report is generated with below fields$")
	public void i_verify_the_Report_is_generated_with_below_fields(DataTable generatedReportTable) throws Throwable {
		scenario.write("Verifying generatred Report Fields ");
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		Assert.assertEquals(generatedReportTable.raw().get(0).get(1),
				empReports.getGeneratedReportFields(scenario).get("Employee Id"));
		Assert.assertEquals(generatedReportTable.raw().get(2).get(1),
				empReports.getGeneratedReportFields(scenario).get("Employee Last Name"));
		Assert.assertEquals(generatedReportTable.raw().get(1).get(1),
				empReports.getGeneratedReportFields(scenario).get("Employee First Name"));
	}

	@After
	public void closeApplication(Scenario scenario) {

		scenario.write("Closing the application");
		closeBrowser();
	}

}

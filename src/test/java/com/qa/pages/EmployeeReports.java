package com.qa.pages;

import java.util.HashMap;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.ElementActions;

import cucumber.api.Scenario;

public class EmployeeReports {

	WebDriver driver;

	@FindBy(xpath = "//span[text()='PIM']")
	WebElement pimPageLink;

	@FindBy(xpath = "//h6[@name='PIM']")
	WebElement pimPageTitle;

	@FindBy(xpath = "//a[text()='Reports']")
	WebElement ReportPageLink;

	@FindBy(xpath = "//button[text()=' Add ']")
	WebElement addReportsButton;

	@FindBy(xpath = "//label[text()='Report Name']/following::input[@class='oxd-input oxd-input--active']")
	WebElement reportsNamefield;

	@FindBy(xpath = "//label[text()='Select Display Field Group']/following::div[5]/child::i")
	WebElement displayFieldGroupdropdownArrow;

	@FindBy(xpath = "//label[text()='Select Display Field']/following::div[5]/child::i")
	WebElement displayFielddropdownArrow;

	@FindBy(xpath = "//label[text()='Select Display Field Group']/following::button[@class='oxd-icon-button orangehrm-report-icon']")
	WebElement addempFieldButton;

	@FindBy(xpath = "//button[text()=' Save ']")
	WebElement saveReportsButton;

	@FindBy(xpath = "//label[text()='Report Name']/following::input[1]")
	WebElement SearchReportNameField;

	@FindBy(xpath = "//button[text()=' Search ']")
	WebElement SearchReportButton;

	@FindBy(xpath = "//div[@class='oxd-table-body']/descendant::div[7]")
	WebElement searchedReportName;

	@FindBy(xpath = "//i[@class='oxd-icon bi-file-text-fill']")
	WebElement generateReportButton;

	@FindBy(xpath = "//div[@class='header-rgRow actual-rgRow']/descendant::div[2]")
	WebElement empidFieldinReport;

	@FindBy(xpath = "//div[@class='header-rgRow actual-rgRow']/descendant::div[4]")
	WebElement emplastNameFieldinReport;

	@FindBy(xpath = "//div[@class='header-rgRow actual-rgRow']/descendant::div[8]")
	WebElement empfirsNameFieldinReport;

	public EmployeeReports(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String navigateToReportsPage(Scenario scenario) {

		ElementActions.clickElement(driver, ReportPageLink, scenario);

		return ElementActions.getText(driver, ReportPageLink, scenario);
	}

	public void addCustomReport(String reportName, Scenario scenario) {

		ElementActions.clickElement(driver, addReportsButton, scenario);
		ElementActions.sendKeys(driver, reportsNamefield, scenario, reportName);
		ElementActions.clickElement(driver, displayFieldGroupdropdownArrow, scenario);
		Actions objactions = new Actions(driver);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		objactions.sendKeys(Keys.ENTER).build().perform();
		ElementActions.clickElement(driver, displayFielddropdownArrow, scenario);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		objactions.sendKeys(Keys.ENTER).build().perform();
		ElementActions.clickElement(driver, addempFieldButton, scenario);
		ElementActions.clickElement(driver, displayFielddropdownArrow, scenario);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		objactions.sendKeys(Keys.ENTER).build().perform();
		ElementActions.clickElement(driver, addempFieldButton, scenario);
		ElementActions.clickElement(driver, displayFielddropdownArrow, scenario);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		objactions.sendKeys(Keys.ENTER).build().perform();
		ElementActions.clickElement(driver, addempFieldButton, scenario);
		ElementActions.clickElement(driver, saveReportsButton, scenario);

	}

	public String searchReport(String reportName, Scenario scenario) {

		ElementActions.sendKeys(driver, reportsNamefield, scenario, reportName);
		Actions objactions = new Actions(driver);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		objactions.sendKeys(Keys.ENTER).build().perform();
		ElementActions.clickElement(driver, SearchReportButton, scenario);
		return ElementActions.getText(driver, searchedReportName, scenario);
	}

	public HashMap<String, String> getGeneratedReportFields(Scenario scenario) {
		HashMap<String, String> generatedReportFieldMap = new HashMap();
		ElementActions.clickElement(driver, generateReportButton, scenario);
		generatedReportFieldMap.put("Employee Id", empidFieldinReport.getText());
		generatedReportFieldMap.put("Employee First Name", empfirsNameFieldinReport.getText());
		generatedReportFieldMap.put("Employee Last Name", emplastNameFieldinReport.getText());

		return generatedReportFieldMap;
	}

}

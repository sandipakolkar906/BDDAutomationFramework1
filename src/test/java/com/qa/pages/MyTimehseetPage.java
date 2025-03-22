package com.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.ElementActions;
import com.qa.util.WaitMethods;

import cucumber.api.Scenario;

public class MyTimehseetPage {

	WebDriver driver;
	// Page object Repository

	@FindBy(xpath = "//span[text()='Time']")
	WebElement timePageLink;

	@FindBy(xpath = "//span[text()='Timesheets ']")
	WebElement TimeSheetsMenu;

	@FindBy(xpath = "//a[text()='My Timesheets']")
	WebElement mytimeSheetPageLink;

	@FindBy(xpath = "//table[@class='orangehrm-timesheet-table']/tbody/tr[2]/td[3]")
	WebElement monTotal;

	@FindBy(xpath = "//table[@class='orangehrm-timesheet-table']/tbody/tr[2]/td[4]")
	WebElement TueTotal;

	@FindBy(xpath = "//table[@class='orangehrm-timesheet-table']/tbody/tr[2]/td[5]")
	WebElement wedTotal;

	@FindBy(xpath = "//table[@class='orangehrm-timesheet-table']/tbody/tr[2]/td[6]")
	WebElement thusTotal;

	@FindBy(xpath = "//table[@class='orangehrm-timesheet-table']/tbody/tr[2]/td[7]")
	WebElement fridayTotal;

	@FindBy(xpath = "//table[@class='orangehrm-timesheet-table']/tbody/tr[2]/td[10]")

	WebElement allDayTotal;

	// Page class repository for Project management

	@FindBy(xpath = "//span[text()='Project Info ']")

	WebElement projectInfoMenu;

	@FindBy(xpath = "//a[text()='Projects']")

	WebElement projectsoption;

	@FindBy(xpath = "//button[text()=' Add ']")

	WebElement addProjectButton;

	@FindBy(xpath = "//label[text()='Name']/following::input[1]")

	WebElement projectNameField;

	@FindBy(xpath = "//label[text()='Customer Name']/following::input[1]")

	WebElement customerNameField;

	@FindBy(xpath = "//label[text()='Project']/following::input[1]")

	WebElement searchbyprojectNameField;

	@FindBy(xpath = "//button[text()=' Search ']")

	WebElement searchProjectbutton;

	@FindBy(xpath = "//div[@class='oxd-table-body']/div[1]/div/child::div[3]")

	WebElement searchedProjectName;

	@FindBy(xpath = "//button/i[@class='oxd-icon bi-trash']")
	WebElement deleteprojectButton;

	@FindBy(xpath = "//button[text()=' Yes, Delete ']")
	WebElement deleteprojectConfirmButton;

	@FindBy(xpath = "//span[text()='No Records Found']")
	WebElement noRecordsMessageafterSearch;

	@FindBy(xpath = "//button/i[@class='oxd-icon bi-pencil-fill']")

	WebElement editProjectButton;

	@FindBy(xpath = "//label[text()='Description']/following::textarea[1]")

	WebElement descriptionField;

	@FindBy(xpath = "//button[text()=' Save ']")

	WebElement saveprojectButton;

	// Page class constructer
	public MyTimehseetPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Add project Page operation methods
	public void navigateToProjectsPage(Scenario scenario) {
		ElementActions.clickElement(driver, projectInfoMenu, scenario);
		ElementActions.clickElement(driver, projectsoption, scenario);
	}

	public void addNewProject(String customerName, String projectName, Scenario scenario) {

		ElementActions.clickElement(driver, addProjectButton, scenario);

		ElementActions.sendKeys(driver, projectNameField, scenario, projectName);

		ElementActions.sendKeys(driver, customerNameField, scenario, customerName);
		Actions objactions = new Actions(driver);
		WaitMethods.staticWait(5000);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		WaitMethods.staticWait(5000);
		objactions.sendKeys(Keys.ENTER).build().perform();
		WaitMethods.staticWait(5000);
		ElementActions.clickElement(driver, saveprojectButton, scenario);
		WaitMethods.staticWait(15000);

	}

	public String searchProjectbyProjectName(String projectName, Scenario scenario) {
		navigateToProjectsPage(scenario);
		WaitMethods.staticWait(5000);
		ElementActions.sendKeys(driver, searchbyprojectNameField, scenario, projectName);
		Actions objactions = new Actions(driver);
		WaitMethods.staticWait(5000);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		WaitMethods.staticWait(5000);
		objactions.sendKeys(Keys.ENTER).build().perform();
		WaitMethods.staticWait(5000);
		return ElementActions.getText(driver, searchedProjectName, scenario);

	}

	public void deleteProject(Scenario scenario) {
		ElementActions.clickElement(driver, deleteprojectButton, scenario);
		ElementActions.clickElement(driver, deleteprojectConfirmButton, scenario);
	}

	// Page operation methods - Time Sheet

	public void navigateToTimePage(Scenario scenario) {
		ElementActions.clickElement(driver, timePageLink, scenario);
	}

	public void navigateToMyTimeSheetsPage(Scenario scenario) {

		ElementActions.clickElement(driver, TimeSheetsMenu, scenario);
		WaitMethods.staticWait(5000);
		ElementActions.clickElement(driver, mytimeSheetPageLink, scenario);

	}

	public int getSumofBookedDaysTime(Scenario scenario) {

		return Integer.parseInt(ElementActions.getText(driver, monTotal, scenario).split(":")[0])
				+ Integer.parseInt(ElementActions.getText(driver, TueTotal, scenario).split(":")[0])
				+ Integer.parseInt(ElementActions.getText(driver, wedTotal, scenario).split(":")[0])
				+ Integer.parseInt(ElementActions.getText(driver, thusTotal, scenario).split(":")[0])
				+ Integer.parseInt(ElementActions.getText(driver, fridayTotal, scenario).split(":")[0]);

	}

	public int getsumofallBookedDays(Scenario scenario) {

		return Integer.parseInt(ElementActions.getText(driver, allDayTotal, scenario).split(":")[0]);
	}

}

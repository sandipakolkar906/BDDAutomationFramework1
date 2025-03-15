package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

	// Page class constructer
	public MyTimehseetPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page operation methods

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

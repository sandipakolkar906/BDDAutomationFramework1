package com.qa.pages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.ElementActions;

import cucumber.api.Scenario;

public class DashboardPage {

	// page object repo

	WebDriver driver;

	@FindBy(xpath = "//p[text()='Time at Work']")
	WebElement timeatWorkWidgettitle;

	@FindBy(xpath = "//p[text()='Time at Work']/following::p[@class='oxd-text oxd-text--p orangehrm-attendance-card-state']")
	WebElement punchedinStatus;

	@FindBy(xpath = "//p[text()='Time at Work']/following::span[@class='oxd-text oxd-text--span orangehrm-attendance-card-fulltime']")
	WebElement currentSystemTime;

	@FindBy(xpath = "//p[text()='Time at Work']/following::p[text()='This Week']/following::p[1]")
	WebElement currentWeekRange;

	@FindBy(xpath = "//p[text()='Time at Work']/following::p[@class='oxd-text oxd-text--p orangehrm-attendance-card-fulltime']")
	WebElement totalhoursinWeek;

	// page class constructer

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// page operation methods

	public HashMap<String, String> getDataFromTimeatworkwidget(Scenario scenario) {

		HashMap<String, String> mapofTimeatworkwidgetdata = new HashMap<String, String>();
		mapofTimeatworkwidgetdata.put("timeatWorkWidgettitle",
				ElementActions.getText(driver, timeatWorkWidgettitle, scenario));
		mapofTimeatworkwidgetdata.put("punchedinStatus", ElementActions.getText(driver, punchedinStatus, scenario));
		mapofTimeatworkwidgetdata.put("currentSystemTime", ElementActions.getText(driver, currentSystemTime, scenario));
		mapofTimeatworkwidgetdata.put("currentWeekRange", ElementActions.getText(driver, currentWeekRange, scenario));
		mapofTimeatworkwidgetdata.put("totalhoursinWeek", ElementActions.getText(driver, totalhoursinWeek, scenario));
		return mapofTimeatworkwidgetdata;

	}

}

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

public class LeavePage {

	// Page object Repo

	WebDriver driver;

	@FindBy(xpath = "//span[text()='Leave']")
	WebElement leavePagelink;

	@FindBy(xpath = "//span[text()='Configure ']")
	WebElement configureLeavemenu;

	@FindBy(xpath = "//a[text()='Leave Types']")
	WebElement leaveTypeOption;

	@FindBy(xpath = "//button[text()=' Add ']")
	WebElement addleaveTypeButton;

	@FindBy(xpath = "//label[text()='Name']/following::input[1]")
	WebElement leaveTypeNameField;

	@FindBy(xpath = "//button[text()=' Save ']")
	WebElement saveLeaveTypeButton;

	@FindBy(xpath = "//div[@class='oxd-table-card']/div[1]/child::div[2]/div[1]")
	WebElement addedleaveTypeName;

	@FindBy(xpath = "//button/child::i[@class='oxd-icon bi-pencil-fill']")
	WebElement editLEaveTypeButton;

	@FindBy(xpath = "//button/child::i[@class='oxd-icon bi-trash']")
	WebElement deleteLeaveTypeButton;

	@FindBy(xpath = "//button[text()=' Yes, Delete ']")
	WebElement deleteConfirmationButton;

	// Page class constructer

	public LeavePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// page operation methods

	public void navigateToLeavePage(Scenario scenario) {

		ElementActions.clickElement(driver, leavePagelink, scenario);
	}

	public void navigateToConfigMenu(Scenario scenario) {
		ElementActions.clickElement(driver, configureLeavemenu, scenario);
	}

	public void navigateToLEaveType(Scenario scenario) {
		ElementActions.clickElement(driver, leaveTypeOption, scenario);
	}

	public String addLeaveType(Scenario scenario, String leaveTypeName) {
		ElementActions.clickElement(driver, addleaveTypeButton, scenario);
		ElementActions.sendKeys(driver, leaveTypeNameField, scenario, leaveTypeName);
		ElementActions.clickElement(driver, saveLeaveTypeButton, scenario);
		return ElementActions.getText(driver, addedleaveTypeName, scenario);
	}

	public String editLeaveType(Scenario scenario, String updatedLeaveTypeName) {
		ElementActions.clickElement(driver, editLEaveTypeButton, scenario);
		ElementActions.clickElement(driver, leaveTypeNameField, scenario);
		WaitMethods.staticWait(5000);
		Actions objaction = new Actions(driver);
		for (int i = 0; i <= 9; i++) {
			objaction.sendKeys(Keys.BACK_SPACE).build().perform();
			WaitMethods.staticWait(2000);
		}

		WaitMethods.staticWait(5000);
		ElementActions.sendKeys(driver, leaveTypeNameField, scenario, updatedLeaveTypeName);
		ElementActions.clickElement(driver, saveLeaveTypeButton, scenario);

		return ElementActions.getText(driver, addedleaveTypeName, scenario);

	}

	public void deleteNewlyAddedLeaveType(Scenario scenario) {
		ElementActions.clickElement(driver, deleteLeaveTypeButton, scenario);
		ElementActions.clickElement(driver, deleteConfirmationButton, scenario);
	}

}

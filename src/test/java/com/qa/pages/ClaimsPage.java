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

public class ClaimsPage {

	// page object repo

	WebDriver driver;

	@FindBy(xpath = "//span[text()='Claim']")
	WebElement claimPageLink;

	@FindBy(xpath = "//a[text()='Submit Claim']")
	WebElement submitclaimlink;

	@FindBy(xpath = "//label[text()='Event']/following::i[1]")
	WebElement eventDropdownarrow;

	@FindBy(xpath = "//div[text()='HotelBills']")
	WebElement hotelBillsExpense;

	@FindBy(xpath = "//label[text()='Currency']/following::i[1]")
	WebElement currencyDropdownarrow;

	@FindBy(xpath = "//div[text()='Afghanistan Afghani']")
	WebElement AfghaniCurrency;

	@FindBy(xpath = "//button[text()=' Create ']")
	WebElement createButton;

	@FindBy(xpath = "//h6[text()='Expenses']/following::button[1]")
	WebElement addExpenseButton;

	@FindBy(xpath = "//div[@class='oxd-select-text--after']")
	WebElement expenceTypeDowpdownarrow;

	@FindBy(xpath = "//div[text()='fuelExpence']")
	WebElement fuelexpenceType;

	@FindBy(xpath = "//input[@placeholder='yyyy-mm-dd']")
	WebElement expenseDatefield;

	@FindBy(xpath = "//label[text()='Amount']/following::input[1]")
	WebElement amountField;

	@FindBy(xpath = "//button[text()=' Save ']")
	WebElement saveExpenseButton;

	@FindBy(xpath = "//div[@class='orangehrm-bottom-container']/p[1]")
	WebElement totalAmountText;

	@FindBy(xpath = "//div[@class='oxd-table']/div[@class='oxd-table-body']/div[1]/child::div/child::div[6]/following::i[@class='oxd-icon bi-pencil-fill']")
	WebElement lastExpenseEditButton;

	@FindBy(xpath = "//div[@class='oxd-table']/div[@class='oxd-table-body']/div[1]/child::div/child::div[6]/following::i[@class='oxd-icon bi-trash']")
	WebElement lastExpenseDeleteButton;

	@FindBy(xpath = "//button[text()=' Yes, Delete ']")
	WebElement deleteexpeseConfirmButton;

	public ClaimsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// page operation methods

	public void navigatetoClaimsPage(Scenario scenario) {
		ElementActions.clickElement(driver, claimPageLink, scenario);
	}

	public void navigatetoSubmitClaims(Scenario scenario) {
		WaitMethods.staticWait(5000);
		ElementActions.clickElement(driver, submitclaimlink, scenario);
	}

	public void addEventandCurrency(Scenario scenario) {
		Actions objactions;
		ElementActions.clickElement(driver, eventDropdownarrow, scenario);
		WaitMethods.staticWait(2000);
		objactions = new Actions(driver);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		WaitMethods.staticWait(5000);
		objactions.sendKeys(Keys.ENTER).build().perform();
		WaitMethods.staticWait(2000);
		ElementActions.clickElement(driver, currencyDropdownarrow, scenario);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		WaitMethods.staticWait(2000);
		objactions.sendKeys(Keys.ENTER).build().perform();
		WaitMethods.staticWait(2000);
		ElementActions.clickElement(driver, AfghaniCurrency, scenario);
		WaitMethods.staticWait(2000);
		ElementActions.clickElement(driver, createButton, scenario);
		WaitMethods.staticWait(5000);

	}

	public void addExpenses(Scenario scenario, String date, String amount) {

		WaitMethods.staticWait(5000);
		ElementActions.clickElement(driver, addExpenseButton, scenario);
		WaitMethods.staticWait(2000);
		ElementActions.clickElement(driver, expenceTypeDowpdownarrow, scenario);
		Actions objactions = new Actions(driver);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		WaitMethods.staticWait(2000);
		objactions.sendKeys(Keys.ENTER).build().perform();
		WaitMethods.staticWait(2000);
		ElementActions.sendKeys(driver, expenseDatefield, scenario, date);
		WaitMethods.staticWait(2000);
		ElementActions.sendKeys(driver, amountField, scenario, amount);
		WaitMethods.staticWait(2000);
		ElementActions.clickElement(driver, saveExpenseButton, scenario);
		WaitMethods.staticWait(5000);

	}

	public String getTotalExpenseAmount(Scenario scenario) {
		WaitMethods.staticWait(5000);
		return ElementActions.getText(driver, totalAmountText, scenario).split(":")[1].trim();
	}

	public void editlastExpense(Scenario scenario, String amount) {
		WaitMethods.staticWait(2000);
		ElementActions.clickElement(driver, lastExpenseEditButton, scenario);
		WaitMethods.staticWait(2000);
		ElementActions.clickElement(driver, amountField, scenario);

		Actions objactions = new Actions(driver);

		for (int i = 0; i <= 4; i++) {
			WaitMethods.staticWait(2000);
			objactions.sendKeys(Keys.BACK_SPACE).build().perform();

		}
		WaitMethods.staticWait(2000);
		ElementActions.sendKeys(driver, amountField, scenario, amount);
		WaitMethods.staticWait(2000);
		ElementActions.clickElement(driver, saveExpenseButton, scenario);
		WaitMethods.staticWait(5000);
	}

	public void deletelastExpense(Scenario scenario) {
		ElementActions.clickElement(driver, lastExpenseDeleteButton, scenario);
		ElementActions.clickElement(driver, deleteexpeseConfirmButton, scenario);

	}

}

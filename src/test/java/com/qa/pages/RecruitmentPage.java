package com.qa.pages;

import java.util.HashMap;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.ElementActions;
import com.qa.util.WaitMethods;

import cucumber.api.Scenario;

public class RecruitmentPage {

	WebDriver driver;
	// Page object Repository

	@FindBy(xpath = "//span[text()='Recruitment']")
	WebElement recruitnemtPagelinkButton;

	@FindBy(xpath = "//a[text()='Candidates']")
	WebElement candidatelinkButton;

	@FindBy(xpath = "//button[text()=' Add ']")
	WebElement addcandidatelButton;

	@FindBy(xpath = "//input[@name='firstName']")
	WebElement candidateFirstName;

	@FindBy(xpath = "//input[@name='middleName']")
	WebElement candidatemiddleName;

	@FindBy(xpath = "//input[@name='lastName']")
	WebElement candidatelastName;

	@FindBy(xpath = "//label[text()='Email']/following::input[1]")
	WebElement candidateemail;

	@FindBy(xpath = "//button[text()=' Save ']")
	WebElement saveCandidateBUtton;

	@FindBy(xpath = "//label[text()='Candidate Name']/following::input[1]")
	WebElement searchByCandidateName;

	@FindBy(xpath = "//div[@class='oxd-table-body']/div[1]/div/child::div[3]")
	WebElement searchedCandidateName;

	@FindBy(xpath = "//div[@class='oxd-table-body']/div[1]/div/child::div[5]")
	WebElement candidateapplicationDate;

	@FindBy(xpath = "//button[text()=' Search ']")
	WebElement searchEmpButton;

	@FindBy(xpath = "//button/i[@class='oxd-icon bi-trash']")
	WebElement deleteEmployeeButton;

	@FindBy(xpath = "//button[text()=' Yes, Delete ']")
	WebElement deleteEmployeeConfirmButton;

	@FindBy(xpath = "//span[text()='No Records Found']")
	WebElement noRecordsMessageafterSearch;

	// page class constructer

	public RecruitmentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// page operation methods

	public void naviagetToRecruitmentPage(Scenario scenario) {

		ElementActions.clickElement(driver, recruitnemtPagelinkButton, scenario);
	}

	public void addnewCandidate(HashMap<String, String> fieldMap, Scenario scenario) {

		ElementActions.clickElement(driver, candidatelinkButton, scenario);
		ElementActions.clickElement(driver, addcandidatelButton, scenario);

		ElementActions.sendKeys(driver, candidateFirstName, scenario, fieldMap.get("firstname"));
		ElementActions.sendKeys(driver, candidatemiddleName, scenario, fieldMap.get("middlename"));
		ElementActions.sendKeys(driver, candidatelastName, scenario, fieldMap.get("lastname"));
		ElementActions.sendKeys(driver, candidateemail, scenario, fieldMap.get("email"));
		ElementActions.clickElement(driver, saveCandidateBUtton, scenario);

	}

	public HashMap<String, String> getCandiatenameandApplicationDate(Scenario scenario, String firstName) {
		WaitMethods.staticWait(5000);
		ElementActions.clickElement(driver, recruitnemtPagelinkButton, scenario);
		ElementActions.sendKeys(driver, searchByCandidateName, scenario, firstName);
		Actions objactions = new Actions(driver);
		WaitMethods.staticWait(15000);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		WaitMethods.staticWait(15000);
		objactions.sendKeys(Keys.ENTER).build().perform();
		ElementActions.clickElement(driver, searchEmpButton, scenario);

		HashMap<String, String> outputfieldMap = new HashMap<String, String>();

		outputfieldMap.put("candidatefullName", ElementActions.getText(driver, searchedCandidateName, scenario));
		outputfieldMap.put("applicationDate", ElementActions.getText(driver, candidateapplicationDate, scenario));

		return outputfieldMap;

	}

	public String deletesearchedcandidate(Scenario scenario) {
		WaitMethods.staticWait(5000);
		ElementActions.clickElement(driver, deleteEmployeeButton, scenario);
		WaitMethods.staticWait(5000);
		ElementActions.clickElement(driver, deleteEmployeeConfirmButton, scenario);
		WaitMethods.staticWait(5000);
		return ElementActions.getText(driver, noRecordsMessageafterSearch, scenario);
	}
}

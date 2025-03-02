package com.qa.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.ElementActions;
import com.qa.util.WaitMethods;

import cucumber.api.Scenario;

public class AddEmployeePage {

	WebDriver driver;

	@FindBy(xpath = "//span[text()='PIM']")
	WebElement pimPageLink;

	@FindBy(xpath = "//h6[@name='PIM']")
	WebElement pimPageTitle;

	@FindBy(xpath = "//button[text()=' Add ']")
	WebElement addEmpButton;

	@FindBy(xpath = "//input[@name='firstName']")
	WebElement empFirstNameField;

	@FindBy(xpath = "//input[@name='middleName']")
	WebElement empMiddleameField;

	@FindBy(xpath = "//input[@name='lastName']")
	WebElement empLastNameField;

	@FindBy(xpath = "//button[text()=' Save ']")
	WebElement saveEmp1Button;

	@FindBy(xpath = "//a[text()='Employee List']")
	WebElement empListPage;

	@FindBy(xpath = "//label[text()='Employee Name']/following::input[1]")
	WebElement searchempnameField;

	@FindBy(xpath = "//button[text()=' Search ']")
	WebElement searchEmpButton;

	@FindBy(xpath = "//button[text()=' Add ']/following::span[1]")
	WebElement msgEmpsearch;

	@FindBy(xpath = "//div[@class='oxd-table-body']/div[1]/div/child::div")
	List<WebElement> emprecordList;

	// Editing the employee Details

	@FindBy(xpath = "//button/i[@class='oxd-icon bi-pencil-fill']")
	WebElement editEmployeeButton;

	@FindBy(xpath = "//button/i[@class='oxd-icon bi-trash']")
	WebElement deleteEmployeeButton;

	@FindBy(xpath = "//button[text()=' Yes, Delete ']")
	WebElement deleteEmployeeConfirmButton;

	@FindBy(xpath = "//span[text()='No Records Found']")
	WebElement noRecordsMessageafterSearch;

	public AddEmployeePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page Opearion Methods

	public String navigateToPIMPage(Scenario scenario) {

		ElementActions.clickElement(driver, pimPageLink, scenario);

		return ElementActions.getText(driver, pimPageTitle, scenario);
	}

	public void addNewEmp(String empFirstName, String empMiddleName, String empLastName, Scenario scenario) {

		ElementActions.clickElement(driver, addEmpButton, scenario);
		ElementActions.sendKeys(driver, empFirstNameField, scenario, empFirstName);
		ElementActions.sendKeys(driver, empMiddleameField, scenario, empMiddleName);
		ElementActions.sendKeys(driver, empLastNameField, scenario, empLastName);
		ElementActions.clickElement(driver, saveEmp1Button, scenario);

	}

	public void navigateToEmployeeListPage(Scenario scenario) {
		ElementActions.clickElement(driver, empListPage, scenario);
	}

	public String searchnewlyaddedEmpbyKey(String key, Scenario scenario) {

		ElementActions.sendKeys(driver, searchempnameField, scenario, key);
		ElementActions.clickElement(driver, searchEmpButton, scenario);

		for (WebElement element : emprecordList) {

			System.out.println(ElementActions.getText(driver, element, scenario));
		}

		return ElementActions.getText(driver, emprecordList.get(2), scenario);
	}

	public void editEmpinfo(Map<String, String> mapofvaluestoUdpate, Scenario scenario) {

		ElementActions.clickElement(driver, editEmployeeButton, scenario);
		WaitMethods.staticWait(5000);
		// Entering values
		ElementActions.sendKeys(driver, empFirstNameField, scenario, mapofvaluestoUdpate.get("firstName"));
		ElementActions.sendKeys(driver, empMiddleameField, scenario, mapofvaluestoUdpate.get("middleName"));
		ElementActions.sendKeys(driver, empLastNameField, scenario, mapofvaluestoUdpate.get("lastName"));
		WaitMethods.staticWait(5000);
		ElementActions.clickElement(driver, saveEmp1Button, scenario);

	}

	public String deletesearchedEmp(Scenario scenario) {
		WaitMethods.staticWait(5000);
		ElementActions.clickElement(driver, deleteEmployeeButton, scenario);
		WaitMethods.staticWait(5000);
		ElementActions.clickElement(driver, deleteEmployeeConfirmButton, scenario);
		WaitMethods.staticWait(5000);
		return ElementActions.getText(driver, noRecordsMessageafterSearch, scenario);
	}

}

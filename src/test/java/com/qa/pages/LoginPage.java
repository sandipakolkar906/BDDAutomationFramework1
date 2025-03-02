package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.ElementActions;

import cucumber.api.Scenario;

public class LoginPage {

	WebDriver driver;

	// Page object Repository

	@FindBy(xpath = "//input[@name='username']")
	WebElement userNameField;

	@FindBy(xpath = "//input[@name='password']")
	WebElement passwordField;

	@FindBy(xpath = "//button[text()=' Login ']")
	WebElement loginButton;

	@FindBy(xpath = "//h6[text()='Dashboard']")
	WebElement homepageTitle;

	// Page object Repository

	// Page class constructer

	/**
	 * @param driver
	 *            This is page class constructer of page factory design pattern
	 */
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @param email
	 * @param password
	 * @param scenario
	 * @return
	 */

	public String logintoApplication(String userName, String password, Scenario scenario) {

		ElementActions.sendKeys(driver, userNameField, scenario, userName);

		ElementActions.sendKeys(driver, passwordField, scenario, password);

		ElementActions.clickElement(driver, loginButton, scenario);

		return ElementActions.getText(driver, homepageTitle, scenario);
	}

}

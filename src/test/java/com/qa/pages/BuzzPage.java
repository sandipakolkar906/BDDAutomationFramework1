package com.qa.pages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.ElementActions;
import com.qa.util.WaitMethods;

import cucumber.api.Scenario;

public class BuzzPage {

	// page object repo

	WebDriver driver;

	@FindBy(xpath = "//span[text()='Buzz']")
	WebElement buzzPageLinkbtn;

	@FindBy(xpath = "//textarea[@class='oxd-buzz-post-input']")
	WebElement posttextfield;

	@FindBy(xpath = "//button[text()=' Post ']")
	WebElement postButton;

	@FindBy(xpath = "//p[text()='Sandip Akolkar']")
	WebElement userProfileTitle;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-buzz-post-emp-name']")
	WebElement buzzInputName;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-buzz-post-time']")
	WebElement buzzInputTime;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-buzz-post-body-text']")
	WebElement postedCommentText;

	@FindBy(xpath = "//div[@class='orangehrm-buzz-post-actions']/div[1]")
	WebElement heartIconforLike;

	@FindBy(xpath = "//i[@class='oxd-icon bi-heart-fill orangehrm-buzz-stats-like-icon']/following::p[1]")
	WebElement countofLikes;

	@FindBy(xpath = "//i[@class='oxd-icon bi-three-dots']")
	WebElement threeDotsButton;

	@FindBy(xpath = "//i[@class='oxd-icon bi-pencil']")
	WebElement editpostButton;

	@FindBy(xpath = "//p[text()='Edit Post']/following::textarea[@class='oxd-buzz-post-input']")
	WebElement editPosttextField;

	@FindBy(xpath = "//p[text()='Edit Post']/following::button[@class='oxd-button oxd-button--medium oxd-button--main']")
	WebElement editPostButton;

	@FindBy(xpath = "//i[@class='oxd-icon bi-trash']")
	WebElement deletePostButton;

	@FindBy(xpath = "//button[text()=' Yes, Delete ']")
	WebElement deleteconfirmationButton;

	// page class constructer

	public BuzzPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// page operation methods

	public void navigateToBuzzPage(Scenario scenario) {
		ElementActions.clickElement(driver, buzzPageLinkbtn, scenario);

	}

	public void postComment(String textTopost, Scenario scenario) {
		ElementActions.sendKeys(driver, posttextfield, scenario, textTopost);
		ElementActions.clickElement(driver, postButton, scenario);
		WaitMethods.staticWait(5000);
		navigateToBuzzPage(scenario);
	}

	public HashMap<String, String> getPostcommentDetails(Scenario scenario) {
		WaitMethods.staticWait(5000);
		HashMap<String, String> commentinfoMap = new HashMap<String, String>();

		commentinfoMap.put("username", ElementActions.getText(driver, buzzInputName, scenario));

		commentinfoMap.put("commenttimestamp", ElementActions.getText(driver, buzzInputTime, scenario));
		commentinfoMap.put("commenttext", ElementActions.getText(driver, postedCommentText, scenario));

		return commentinfoMap;
	}

	public String captureLoggedinUserName(Scenario scenario) {
		WaitMethods.staticWait(5000);
		return ElementActions.getText(driver, userProfileTitle, scenario);
	}

	public void likeComment(Scenario scenario) {
		WaitMethods.staticWait(15000);
		ElementActions.clickElement(driver, heartIconforLike, scenario);

	}

	public int likeCount(Scenario scenario) {
		WaitMethods.staticWait(5000);
		String likecouunt = ElementActions.getText(driver, countofLikes, scenario);
		String[] splittedString = likecouunt.split(" ");
		return Integer.parseInt(splittedString[0]);
	}

	public void editComment(Scenario scenario, String texttoUpdate) {
		WaitMethods.staticWait(5000);
		ElementActions.clickElement(driver, threeDotsButton, scenario);
		ElementActions.clickElement(driver, editpostButton, scenario);
		WaitMethods.staticWait(5000);

		ElementActions.sendKeys(driver, editPosttextField, scenario, texttoUpdate);
		WaitMethods.staticWait(5000);
		ElementActions.clickElement(driver, editPostButton, scenario);
	}

	public String expectedUpdatedComment(Scenario scenario) {
		WaitMethods.staticWait(5000);
		return ElementActions.getText(driver, postedCommentText, scenario);
	}

	public void deletePost(Scenario scenario) {
		WaitMethods.staticWait(5000);
		ElementActions.clickElement(driver, threeDotsButton, scenario);
		ElementActions.clickElement(driver, deletePostButton, scenario);
		ElementActions.clickElement(driver, deleteconfirmationButton, scenario);

	}
}

package com.qa.stepdefinations;

import java.util.HashMap;

import com.qa.base.Base;
import com.qa.pages.BuzzPage;
import com.qa.pages.LoginPage;
import com.qa.util.CaptureScreenshot;
import com.qa.util.ReadProperties;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class BuzzCRUD extends Base {

	Scenario scenario;
	LoginPage objloginPage;
	BuzzPage objBuzzPage;

	@Before
	public void logintoApplication(Scenario scenario) {

		this.scenario = scenario;
	}

	@Given("^Navigate to BUzz after log in with Admin user$")
	public void navigate_to_BUzz_after_log_in_with_Admin_user() throws Throwable {

		scenario.write("Starting orange HRM log in page ");
		driver = initializeWebDriver();
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		objloginPage = new LoginPage(driver);
		scenario.write("Logging in to the OrangeHRM Applciation ");
		objloginPage.logintoApplication(ReadProperties.getAppUserName(), ReadProperties.getAppPassword(), scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		objBuzzPage = new BuzzPage(driver);
		scenario.write("Navigate to buzzPage ");
		objBuzzPage.navigateToBuzzPage(scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@When("^Post the comment as \"([^\"]*)\"$")
	public void post_the_comment_as(String commentTextToPost) throws Throwable {

		scenario.write("Posting new Comment ");
		objBuzzPage.postComment(commentTextToPost, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^verify that comment time user and comment text is posted correctly as \"([^\"]*)\"$")
	public void verify_that_comment_time_user_and_comment_text_is_posted_correctly_as(String expectedCommentText)
			throws Throwable {
		scenario.write("verifyiong posted comment text , user , Time ");
		HashMap<String, String> commentMap = objBuzzPage.getPostcommentDetails(scenario);
		System.out.println("======" + commentMap.get("commenttimestamp"));
		Assert.assertEquals(java.time.LocalDateTime.now().toLocalDate().toString(),
				commentMap.get("commenttimestamp").split(" ")[0]);
		Assert.assertEquals(expectedCommentText, commentMap.get("commenttext"));
		Assert.assertEquals(objBuzzPage.captureLoggedinUserName(scenario), commentMap.get("username"));
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@When("^I click on like$")
	public void i_click_on_like() throws Throwable {
		scenario.write("clicking on like button");
		objBuzzPage.likeComment(scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I verify the like count is updated as Expected count \"([^\"]*)\"$")
	public void i_verify_the_like_count_is_updated_as_Expected_count(String expectedCount) throws Throwable {
		scenario.write("verifying Like Count");
		Assert.assertEquals(Integer.parseInt(expectedCount), objBuzzPage.likeCount(scenario));
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@When("^I Edit the post with updated comment\"([^\"]*)\"$")
	public void i_Edit_the_post_with_updated_comment(String texttoUpdate) throws Throwable {
		scenario.write("Editing the post ");
		objBuzzPage.editComment(scenario, " " + texttoUpdate);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I verify that updated comment\"([^\"]*)\"$")
	public void i_verify_that_updated_comment(String expectedUpdatedComment) throws Throwable {
		scenario.write("verifying updated  post comment ");
		System.out.println("====== " + objBuzzPage.expectedUpdatedComment(scenario));
		Assert.assertTrue(objBuzzPage.expectedUpdatedComment(scenario).contains(expectedUpdatedComment));

		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I delete the Post$")
	public void i_delete_the_Post() throws Throwable {
		scenario.write("Deleting the post");
		objBuzzPage.deletePost(scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@After
	public void closeApplication(Scenario scenario) {

		scenario.write("Closing the application");
		closeBrowser();
	}

}

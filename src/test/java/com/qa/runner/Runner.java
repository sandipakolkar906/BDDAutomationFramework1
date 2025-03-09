package com.qa.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = { "com.qa.stepdefinations" }, tags = {
		"@DryRun" }, plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json" }, monochrome = true)

public class Runner {

	// "html:target/cucumber-reports" }

}

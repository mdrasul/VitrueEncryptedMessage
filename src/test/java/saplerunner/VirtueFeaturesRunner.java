package saplerunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = { "src/test/resources/testfeatures/ReadEncrypctedEmail.feature" },
		glue = { "samplesteps" },
		tags = { "@smoke"},
		plugin = {"pretty"})

public class VirtueFeaturesRunner {}

package ru.ifellow.Kalinina;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "ru.ifellow.Kalinina.jiraSteps")
@ConfigurationParameter(key = Constants.EXECUTION_DRY_RUN_PROPERTY_NAME, value = "false")

public class CucumberRunnerTest {
}

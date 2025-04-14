package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty", "json:build/test-results/json-report.json", "html:build/test-results/html-report.html", "junit:build/test-results/json-xml.xml","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "summary"},
        glue = {"stepdefs"},
        features = {"src/test/features"},
        tags ="@gg",
        monochrome = true)
public class CucumberTests extends AbstractTestNGCucumberTests {
}

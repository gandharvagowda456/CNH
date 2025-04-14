package stepdefs;

import driverfactory.WebBaseDriver;
import helpers.readFile.ParseJsonData;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import utils.ObjectData;

public class WebDriverSetup extends WebBaseDriver {

    @Before
    public void startWebDriver() throws Exception {
        setupBrowser("No_Headless");
        ParseJsonData parseJsonData = new ParseJsonData();
        if(System.getProperty("env") != null){
            parseJsonData.getSiteConfigData(System.getProperty("env"));
        }else{
            parseJsonData.getSiteConfigData("stg");
        }
    }
    @After
    public void closePageDriver(Scenario scenario){
        if(scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot)pageDriver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png",pageDriver.getCurrentUrl());
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
            scenario.log("Error to access web element = "+ ObjectData.WEB_ELEMENT_STR);

        }
        closeDriverNotNull();
    }
}

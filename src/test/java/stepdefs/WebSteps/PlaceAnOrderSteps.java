package stepdefs.WebSteps;

import driverfactory.WebBaseDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageobjects.PlaceAnOrderPage;
import tasks.CommonTask;
import testdata.TestDatas;
import utils.pageObjects.PageObjectManager;

import java.util.List;
import java.util.Map;

public class PlaceAnOrderSteps {
    public WebDriver pageDriver;
    public PlaceAnOrderSteps() {pageDriver = WebBaseDriver.pageDriver; }
    PlaceAnOrderPage placeAnOrderPage;
    CommonTask commonTasks;
    Object pageObject;

    private void createInstance(){
        if(placeAnOrderPage == null){
            placeAnOrderPage = new PlaceAnOrderPage(pageDriver);
        } if(commonTasks == null){
            commonTasks = new CommonTask(pageDriver);
        }

    }

    @Given("user launch the url")
    public void user_login_to_the_application(DataTable dt) throws InterruptedException {
        createInstance();
        placeAnOrderPage.launchWebSite(TestDatas.URL);
        // Set Attach Image status
        List<Map<String, String>> list = dt.asMaps(String.class, String.class);
        TestDatas.ATTACH_IMAGE_ON_STEP_ALWAYS = list.get(0).get("attachImage");

    }

    @Given("click on {string} button")
    public void clickOnButton(String type) throws InterruptedException {
        createInstance();
        placeAnOrderPage.clickOnButton(type);
    }

    @Then("wait for element {string} to be displayed")
    public void verifyUsernameFieldDisplayed(String ele) {
        Assert.assertTrue(placeAnOrderPage.isElementDisplayed(ele));
    }

    @And("enter username details")
    public void enterBelowDetails() throws InterruptedException {
        placeAnOrderPage.enterUserName(TestDatas.LOGIN_USERNAME);
        Thread.sleep(20000);
    }

    @Given("^user should be on \"([^\"]*)\" page$")
    public void user_should_be_on_page(String pageName) {
        pageObject = PageObjectManager.getPageObject(pageName);
    }

    @When("^\"([^\"]*)\" is clicked$")
    public void element_is_clicked(String locator) throws InterruptedException {
        commonTasks.clickBtn(pageObject, locator);
    }

    @When("^below details are entered$")
    public void below_details_are_entered(DataTable dataTable) throws InterruptedException {
        Map<String, String> fieldValues = dataTable.asMap(String.class, String.class);
        commonTasks.enterText(pageObject, fieldValues);
    }
    @When("^\"([^\"]*)\" should be enabled$")
    public void element_should_be_enabled(String elementName) throws InterruptedException {
        commonTasks.verifyElementIsEnabled(pageObject, elementName);
    }

    @When("^wait for \"([^\"]*)\" to be appeared$")
    public void wait_for_element_to_appear(String elementName) {
        commonTasks.waitForElementToBeVisible(pageObject, elementName);
    }

    @When("^hover over and click on \"([^\"]*)\"$")
    public void hoverAndClick(String elementName) {
        commonTasks.simulateMouseHoverAndClick(pageObject, elementName);
    }

    @When("^\"([^\"]*)\" should be displayed$")
    public void element_should_be_displayed(String elementName) {
        commonTasks.verifyElementIsDisplayed(pageObject, elementName);
    }

}

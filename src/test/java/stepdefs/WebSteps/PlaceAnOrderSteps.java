package stepdefs.WebSteps;

import driverfactory.WebBaseDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageobjects.PlaceAnOrderPage;
import tasks.CommonTask;
import testdata.TestDatas;
import utils.pageObjectManager.PageObjectManager;

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
    public void wait_for_element_to_appear(String elementName) throws InterruptedException {
        commonTasks.waitForElementToBeVisible(pageObject, elementName);
        Thread.sleep(5000);
    }

    @When("^hover over and click on \"([^\"]*)\"$")
    public void hoverAndClick(String elementName) throws InterruptedException {
        commonTasks.simulateMouseHoverAndClick(pageObject, elementName);
    }

    @When("^\"([^\"]*)\" should be displayed$")
    public void element_should_be_displayed(String elementName) {
        commonTasks.verifyElementIsDisplayed(pageObject, elementName);
    }
    @When("switch to child tab")
    public void switchToChildTab() throws InterruptedException {
        commonTasks.switchToNewTab();
    }
    @When("verify {string} with {string} item")
    public void verifyCartItem(String elementName, String expectedValue) {
        commonTasks.verifyCartElementHasText(pageObject, elementName, expectedValue);
    }

    @When("get response")
    public void getResponse() {
        Response response = RestAssured
                .given()
                .when()
                .get("https://api.coingecko.com/api/v3/coins/bitcoin")
                .then()
                .statusCode(200)
                .extract()
                .response();

        //2.a  BPI contains USD, GBP, EUR
        Map<String, Object> currentPrice = response.jsonPath().getMap("market_data.current_price");
        Assert.assertEquals(currentPrice.size() > 0, true, "Current price map is empty");

        Assert.assertTrue(currentPrice.containsKey("usd"), "USD not found in current_price");
        Assert.assertTrue(currentPrice.containsKey("gbp"), "GBP not found in current_price");
        Assert.assertTrue(currentPrice.containsKey("eur"), "EUR not found in current_price");

        // 2.b currency has market_cap and total_volume
        Map<String, Object> marketCap = response.jsonPath().getMap("market_data.market_cap");
        Map<String, Object> totalVolume = response.jsonPath().getMap("market_data.total_volume");

        Assert.assertTrue(marketCap.containsKey("usd"), "market_cap missing USD");
        Assert.assertTrue(marketCap.containsKey("gbp"), "market_cap missing GBP");
        Assert.assertTrue(marketCap.containsKey("eur"), "market_cap missing EUR");

        Assert.assertTrue(totalVolume.containsKey("usd"), "total_volume missing USD");
        Assert.assertTrue(totalVolume.containsKey("gbp"), "total_volume missing GBP");
        Assert.assertTrue(totalVolume.containsKey("eur"), "total_volume missing EUR");

        // 2.c  price_change_percentage_24h exists
        Float priceChange24h = response.jsonPath().getFloat("market_data.price_change_percentage_24h");
        Assert.assertNotNull(priceChange24h, "price_change_percentage_24h is missing");
        System.out.println("Price change (24h): " + priceChange24h);
        List<String> homepageList = response.jsonPath().getList("links.homepage");
        Assert.assertNotNull(homepageList, "Homepage list is null");
        Assert.assertFalse(homepageList.get(0).isEmpty(), "Homepage URL is empty");
        System.out.println("Homepage URL: " + homepageList.get(0));

    }

}

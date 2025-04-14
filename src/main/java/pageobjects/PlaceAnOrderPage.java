package pageobjects;



import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.pageactions.PageActions;

@Slf4j
public class PlaceAnOrderPage extends WebBaseClass {
    public PlaceAnOrderPage(WebDriver pageDriver) {
        super(pageDriver);
        PageFactory.initElements(pageDriver, this);
    }
    PageActions pageActions = new PageActions();

    @FindBy( xpath = "//button[text()='I Agree']")
    public WebElement logInButton;

    @FindBy( name = "username")
    public WebElement username;

    public void launchWebSite(String url){
        log.info("-------------------------------------------------------------");
        pageDriver.manage().deleteAllCookies();
        log.info("--------------    Launch URL          ------------------------");
        pageDriver.get(url);
        pageDriver.navigate().refresh();
    }

    public void clickOnButton(String type) throws InterruptedException {
        pageActions.waitForWebElementToDisplay("//*[text()='"+type+"']");
        pageDriver.findElement(By.xpath("//*[text()='"+type+"']")).click();

    }

    public boolean isElementDisplayed(String element) {
        pageActions.waitForWebElementToDisplay("//*[@name='" + element + "']");
        return pageActions.waitForWebElementToDisplay("//*[@name='" + element + "']");
    }

    public void enterUserName(String value) {
        username.clear();
        username.sendKeys(value);
    }


}

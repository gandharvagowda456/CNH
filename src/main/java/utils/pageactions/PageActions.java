package utils.pageactions;

import driverfactory.WebBaseDriver;
import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ObjectData;

import java.util.concurrent.TimeUnit;

public class PageActions {
    WebDriver pageDriver;
    public PageActions() {
        pageDriver = WebBaseDriver.pageDriver;
    }

    public boolean waitForPageElementToDisplay(WebElement webEle) throws InterruptedException {
        Awaitility.await().atMost(30, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).until(() ->{
            try {
                webEle.isDisplayed();
                return webEle.isDisplayed();
            } catch ( Exception e) {
                System.out.println("WebElement is not found trying to read : " + webEle);
                ObjectData.WEB_ELEMENT_STR = webEle.toString();
                return false;
            }
        });
        return webEle.isDisplayed();
    }
    public boolean waitForWebElementToDisplay(String eleStr){
        Awaitility.await().atMost(30, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).until(() ->{
            try {
                return pageDriver.findElement(By.xpath(eleStr)).isDisplayed();
            } catch ( Exception e) {
                System.out.println("WebElement is not found trying to read : " + eleStr);
                ObjectData.WEB_ELEMENT_STR = eleStr.toString();
                return false;
            }
        });
        return pageDriver.findElement(By.xpath(eleStr)).isDisplayed();
    }
    public boolean waitForCSSWebElementToDisplay(String eleStr){
        Awaitility.await().atMost(30, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).until(() ->{
            try {
                return pageDriver.findElement(By.cssSelector(eleStr)).isDisplayed();
            } catch ( Exception e) {
                System.out.println("WebElement is not found trying to read : " + eleStr);
                ObjectData.WEB_ELEMENT_STR = eleStr.toString();
                return false;
            }
        });
        return pageDriver.findElement(By.xpath(eleStr)).isDisplayed();
    }

}

package tasks;

import io.cucumber.java.en.When;
import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.WebBaseClass;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.TimeUnit;



public class CommonTask extends WebBaseClass {
    public CommonTask(WebDriver pageDriver) {
        super(pageDriver);
        PageFactory.initElements(pageDriver, this);
    }

    public void clickBtn(Object pageObject, String elementName) {
        try {
            Field field = pageObject.getClass().getDeclaredField(elementName);
            field.setAccessible(true);
            By locator = (By) field.get(pageObject);

            Awaitility.await()
                    .atMost(30, TimeUnit.SECONDS)
                    .pollInterval(1, TimeUnit.SECONDS)
                    .until(() -> {
                        WebElement element = pageDriver.findElement(locator);
                        return element.isDisplayed() && element.isEnabled();
                    });

            pageDriver.findElement(locator).click();

        }  catch (Exception e) {
            throw new RuntimeException("Failed to click on element: " + elementName, e);
        }
    }

//    public void clickBtn(Object pageObject, String elementName) {
//        try {
//            Field field = pageObject.getClass().getDeclaredField(elementName);
//            field.setAccessible(true);
//            By locator = (By) field.get(pageObject);
//
//            WebDriverWait wait = new WebDriverWait(pageDriver, Duration.FIVE_MINUTES);
//
//            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
//
//            // Scroll into view if needed
//            ((JavascriptExecutor) pageDriver).executeScript("arguments[0].scrollIntoView(true);", element);
//
//            // Small sleep to allow for any animations to finish
//            Thread.sleep(500);
//
//            element.click();
//
//            System.out.println("Clicked on element: " + elementName);
//
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to click on element: " + elementName, e);
//        }
//    }


    public void enterText(Object pageObject, Map<String, String> fieldValues) {
        for (Map.Entry<String, String> entry : fieldValues.entrySet()) {
            String fieldName = entry.getKey();
            String value = entry.getValue();

            try {
                Field field = pageObject.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                By locator = (By) field.get(pageObject);

                WebElement element = new WebDriverWait(pageDriver, 10)
                        .until(ExpectedConditions.visibilityOfElementLocated(locator));
                element.clear();
                element.sendKeys(value);

            } catch (Exception e) {
                throw new RuntimeException("Failed to enter value into field: " + fieldName, e);
            }
        }
    }

    public void waitForElementToBeVisible(Object pageObject, String elementName) {
        try {
            Field field = pageObject.getClass().getDeclaredField(elementName);
            field.setAccessible(true);
            By locator = (By) field.get(pageObject);

            Awaitility.await()
                    .atMost(30, TimeUnit.SECONDS)
                    .pollInterval(1, TimeUnit.SECONDS)
                    .until(() -> {
                        try {
                            WebElement element = pageDriver.findElement(locator);
                            return element.isDisplayed();
                        } catch (Exception e) {
                            return false;
                        }
                    });

            System.out.println("Element '" + elementName + "' is visible on the page.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to wait for element: " + elementName, e);
        }
    }


    public void verifyElementIsEnabled(Object pageObject, String elementName) {
        try {
            // Access locator using reflection
            Field field = pageObject.getClass().getDeclaredField(elementName);
            field.setAccessible(true);
            By locator = (By) field.get(pageObject);

            // Wait for element to be present
            WebDriverWait wait = new WebDriverWait(pageDriver, 20);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

            // Verify if it's enabled
            if (!element.isEnabled()) {
                throw new AssertionError("Element '" + elementName + "' is disabled.");
            }

            System.out.println("Element '" + elementName + "' is enabled.");

        } catch (Exception e) {
            throw new RuntimeException("Failed to verify element enabled state: " + elementName, e);
        }
    }

    public void simulateMouseHoverAndClick(Object pageObject, String elementName) {
        try {
            Field field = pageObject.getClass().getDeclaredField(elementName);
            field.setAccessible(true);
            By locator = (By) field.get(pageObject);

            WebDriverWait wait = new WebDriverWait(pageDriver, 20);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            Actions actions = new Actions(pageDriver);
            actions.moveToElement(element).click().build().perform();

            System.out.println("Hovered over and clicked on: " + elementName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to hover and click on element: " + elementName, e);
        }
    }


    public void verifyElementIsDisplayed(Object pageObject, String elementName) {
        try {
            Field field = pageObject.getClass().getDeclaredField(elementName);
            field.setAccessible(true);
            By locator = (By) field.get(pageObject);

            Awaitility.await()
                    .atMost(30, TimeUnit.SECONDS)
                    .pollInterval(1, TimeUnit.SECONDS)
                    .until(() -> {
                        try {
                            WebElement element = pageDriver.findElement(locator);
                            return element.isDisplayed();
                        } catch (Exception e) {
                            return false;
                        }
                    });

            System.out.println("Element '" + elementName + "' is displayed on the page.");

        } catch (Exception e) {
            throw new RuntimeException("Failed to verify element visibility: " + elementName, e);
        }
    }

    public void switchToNewTab() {
        String currentHandle = pageDriver.getWindowHandle();
        for (String handle : pageDriver.getWindowHandles()) {
            if (!handle.equals(currentHandle)) {
                pageDriver.switchTo().window(handle);
                break;
            }
        }
        System.out.println("Switched to new tab/window: " + pageDriver.getTitle());
    }
    public void closeCurrentTabAndSwitchBackToParent(String parentHandle) {
        pageDriver.close(); // Closes the current tab
        pageDriver.switchTo().window(parentHandle); // Switches back to parent
        System.out.println("Closed current tab and switched back to parent: " + pageDriver.getTitle());
    }

    public void verifyCartElementHasText(Object pageObject, String elementName, String expectedText) {
        try {
            Field field = pageObject.getClass().getDeclaredField(elementName);
            field.setAccessible(true);
            By locator = (By) field.get(pageObject);

            Awaitility.await()
                    .atMost(30, TimeUnit.SECONDS)
                    .pollInterval(1, TimeUnit.SECONDS)
                    .until(() -> {
                        try {
                            WebElement element = pageDriver.findElement(locator);
                            return element.isDisplayed() && element.getText().trim().equals(expectedText);
                        } catch (Exception e) {
                            return false;
                        }
                    });

            WebElement element = pageDriver.findElement(locator);
            String actualText = element.getText().trim();

            if (!actualText.equals(expectedText)) {
                throw new AssertionError("Expected text: '" + expectedText + "', but found: '" + actualText + "'");
            }

            System.out.println("Element '" + elementName + "' is displayed with expected text: '" + expectedText + "'");

        } catch (Exception e) {
            throw new RuntimeException("Failed to verify text for element: " + elementName, e);
        }
    }



}

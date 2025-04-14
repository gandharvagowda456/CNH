package pageobjects;

import org.openqa.selenium.WebDriver;

public class WebBaseClass {
    public static WebDriver pageDriver;
    public static boolean bResult;

    public WebBaseClass(WebDriver pageDriver){
        WebBaseClass.pageDriver = pageDriver;
        WebBaseClass.bResult = true;
    }
}

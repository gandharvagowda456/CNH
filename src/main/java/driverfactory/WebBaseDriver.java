package driverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebBaseDriver {
    public static WebDriver pageDriver;

    public void setupBrowser(String browserMode) throws MalformedURLException, InterruptedException {
        Capabilities browserCap = null;
        String testBrowser = System.getProperty("browserType");
        System.out.println("Test run from - "+testBrowser);
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("test-type");
        chromeOptions.addArguments("allow-running-insecure-content");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--lang=en");
        chromeOptions.addArguments("disable-notifications");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--log-level=3");
        chromeOptions.addArguments("--silent");



        if(testBrowser == null){
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("--window-size=1980,1080");
            chromeOptions.addArguments("--ignore-ssl-errors=yes");
            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.addArguments("--disable-cookies");
            chromeOptions.addArguments("--allow-insecure-localhost");
            pageDriver =  new ChromeDriver( chromeOptions );


        }
        else if(testBrowser.equalsIgnoreCase("remote")){
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("start-maximized");
            chromeOptions.addArguments("--ignore-ssl-errors=yes");
            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.addArguments("('--allow-insecure-localhost')");
        }

    }
    public void tearDownBrowser(){
        pageDriver.quit();
    }
    public void closeDriverNotNull(){
        if(pageDriver != null)
            pageDriver.quit();
    }

}

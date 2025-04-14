package pageobjects;

import org.openqa.selenium.By;

public class LogInPage {
    public By logInButton = By.xpath("//button[text()='Login']");
    public By username = By.name("username");
    public By continueButton = By.xpath("//button[text()='Continue']");
    public By password = By.name("loginPIN");
    public By logIn = By.xpath("//button[text()='Login']");
    public By labTestsServices = By.xpath("//p[text()='Lab Tests']");
    public By kidneyFunctionTestsButton = By.xpath("//p[text()='Kidney Function Test with Electrolytes and Phosphorus']");
    public By bookNowButton = By.xpath("//p[text()='Book Now']");
    public By memberNameRadio = By.name("popupRadioBtnGroup");
    public By memberNameXpath = By.xpath("(//p[text()='John Doe'])[2]");
    public By nextButton = By.xpath("//button[text()='Next']");
    public By selectLabButton = By.xpath("(//button[text()='Select Lab'])[1]");
    public By pickTime = By.xpath("//div[text()='11:30 - 12:00']");
    public By placeAndPalButton = By.xpath("//button[text()='Place Order & Pay']");
    public By netBankingOption = By.xpath("(//*[text()='NetBanking'])[2]");
    public By axisBankOption = By.xpath("//*[text()='Axis']");
    public By dropDownSelector = By.xpath("//button[text()='Select Options']");
    public By dropDownSelID = By.id("txnStateDropdownToggle");
    public By chargedOption = By.xpath("//*[text()='CHARGED']");
    public By submitButtonX = By.xpath("//button[text()='submitButton']");
    public By submitButton = By.id("submitButton");
    public By successMessage = By.xpath("//button[text()='Status Captured successfully']");
    public By cancelOrderButton = By.xpath("//button[text()='Cancel Order']");
    public By yesButton = By.xpath("//button[text()='Yes']");
    public By otherOption = By.xpath("//*[text()='Diagnostic Other']");
    public By homePageButton = By.xpath("//button[text()='Go to Home Page']");
    public By profileDropdown = By.xpath("(//p[text()='John Doe'])[1]");
    public By logOutButton = By.xpath("//*[text()='Logout']");



}

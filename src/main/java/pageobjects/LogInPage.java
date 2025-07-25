package pageobjects;

import org.openqa.selenium.By;

public class LogInPage {
    public By ebayLogo = By.xpath("//*[@id='gh-logo']");
    public By ebaySearch = By.xpath("//*[@id='gh-ac']");
    public By ebaySearchBtn = By.xpath("//*[@id='gh-search-btn']");
    public By firstBook = By.xpath("(//*[@id='srp-river-results']//ul[@class='srp-results srp-list clearfix']//li/div/div//a)[1]");
    public By closePopUP = By.xpath("//*[@aria-label='Close overlay']");
    public By cartValues = By.xpath("//*[@class='badge gh-badge']");
    public By cartItem = By.xpath("//*[@class='gh-cart__icon' and @aria-label='Your shopping cart contains 1 items']");
    public By addToCartBtn = By.xpath("//*[@id='atcBtn_btn_1']");
    public By popUpAddedCart = By.xpath("//*[text()='Added to cart']");
  
}

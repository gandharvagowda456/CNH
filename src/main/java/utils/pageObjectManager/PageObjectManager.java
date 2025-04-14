package utils.pageObjectManager;


import pageobjects.LogInPage;

public class PageObjectManager {
    public static Object getPageObject(String pageName) {
        if (pageName.equalsIgnoreCase("Login")) {
            return new LogInPage();
        }
        throw new IllegalArgumentException("No such page: " + pageName);
    }
}

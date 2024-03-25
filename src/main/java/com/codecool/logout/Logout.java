package com.codecool.logout;

import com.codecool.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Logout extends Page {
    @FindBy(id = "user-options")
    private WebElement userOptions;
    @FindBy(id = "system")
    private WebElement logoutButton;
    @FindBy(xpath = "//a[@href='/login.jsp']")
    private WebElement loginButton;

    public Logout(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    private void clickUserOptions() {
        userOptions.click();
    }

    private void clickLogoutButton() {
        logoutButton.click();
    }
    
    public void logout() {
        clickUserOptions();
        clickLogoutButton();
    }

    public boolean verifyLogout() {
        return loginButton.isDisplayed();
    }
}

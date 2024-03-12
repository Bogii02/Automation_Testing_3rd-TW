package com.codecool;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Login extends Page {

    public final static String LOGIN_URL = "https://jira-auto.codecool.metastage.net/login.jsp";
    @FindBy(id = "login-form-username")
    private WebElement usernameField;

    @FindBy(id = "login-form-password")
    private WebElement passwordField;

    @FindBy(id = "login-form-submit")
    WebElement loginButton;

    @FindBy(id = "header-details-user-fullname")
    WebElement profileMenuID;


    public Login(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private void fillUsername(String username) {
        usernameField.sendKeys(username);
    }

    private void fillPassword(String password) {
        passwordField.sendKeys(password);
    }

    private void clickLoginButton() {
        loginButton.click();
    }

    public String verifyLogin() {
        return profileMenuID.getAttribute("data-username");
    }

    public void login(String username, String password) {
        fillUsername(username);
        fillPassword(password);
        clickLoginButton();
        wait.until(ExpectedConditions.visibilityOf(profileMenuID));
    }

}

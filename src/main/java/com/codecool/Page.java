package com.codecool;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(id = "browse_link")
    private WebElement projectsMenuPoint;
    @FindBy(xpath = "//div[@id='project_view_all']")
    private WebElement viewAllOption;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToProjectSearch() {
        projectsMenuPoint.click();
        wait.until(ExpectedConditions.visibilityOf(viewAllOption));
        viewAllOption.click();
    }

}
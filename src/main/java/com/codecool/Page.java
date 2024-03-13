package com.codecool;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class Page {
  
    private static final int TIMEOUT_IN_SECONDS = 10;
  
    protected WebDriver driver;
  
    protected WebDriverWait wait;
  
    @FindBy(id = "browse_link")
    private WebElement projectsMenuPoint;
  
    @FindBy(id = "project_view_all")
    private WebElement viewAllOption;
  
    @FindBy(id = "find_link")
    private WebElement issuesMenuPoint;
  
    @FindBy(id = "issues_new_search_link_lnk")
    private WebElement searchForIssuesOption;

    @FindBy(id = "create_link")
    private WebElement createButton;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_IN_SECONDS));
    }

    public void navigateToProjectSearch() {
        projectsMenuPoint.click();
        wait.until(ExpectedConditions.visibilityOf(viewAllOption));
        viewAllOption.click();
    }

    public void clickOnCreateButton(){
        wait.until(ExpectedConditions.visibilityOf(createButton));
        createButton.click();
    }

    public void navigateToIssueSearch() {
        issuesMenuPoint.click();
        wait.until(ExpectedConditions.visibilityOf(searchForIssuesOption));
        searchForIssuesOption.click();
    }
}
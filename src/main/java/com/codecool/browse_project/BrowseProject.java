package com.codecool.browse_project;

import com.codecool.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BrowseProject extends Page {
    @FindBy(id = "project-filter-text")
    private WebElement projectSearchField;

    public BrowseProject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void searchProject(String name) {
        projectSearchField.sendKeys(name);

    }

    public boolean isProjectPresent(String name) {
        String xpathOfProjectField = "//a[@title='" + name + "']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathOfProjectField)));
        WebElement projectField = driver.findElement(By.xpath(xpathOfProjectField));
        return projectField.isDisplayed();
    }
}


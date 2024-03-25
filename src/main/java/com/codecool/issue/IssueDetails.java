package com.codecool.issue;

import com.codecool.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IssueDetails extends Page {

    @FindBy(xpath = "//*[@id=\"opsbar-operations_more\"]/span")
    private WebElement dropdownMenu;
    @FindBy(id = "delete-issue")
    private WebElement deleteIssueButton;
    @FindBy(id = "delete-issue-submit")
    private WebElement deleteButton;

    public IssueDetails(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private void getDetailedDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(dropdownMenu));
        dropdownMenu.click();
    }

    public void deleteIssue() {
        getDetailedDropdown();

        wait.until(ExpectedConditions.visibilityOf(deleteIssueButton));
        deleteIssueButton.click();

        wait.until(ExpectedConditions.visibilityOf(deleteButton));
        deleteButton.click();
    }

}

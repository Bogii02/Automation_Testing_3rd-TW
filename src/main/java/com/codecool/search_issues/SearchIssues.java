package com.codecool.search_issues;

import com.codecool.Page;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchIssues extends Page {
    @FindBy(xpath = "//li[@data-id='assignee']")
    private WebElement assigneeSearchCriteria;
    @FindBy(id = "assignee-input")
    private WebElement assigneeInput;
    @FindBy(xpath = "//span[starts-with(@id, 'issue_summary_assignee')]")
    private WebElement displayedIssueAssigneeSpan;

    public SearchIssues(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private void clickAssigneeSearchCriteria() {
        assigneeSearchCriteria.click();
    }
    public void searchIssueByAssignee(String assigneeName) {
        clickAssigneeSearchCriteria();
        wait.until(ExpectedConditions.visibilityOf(assigneeInput));
        assigneeInput.sendKeys(assigneeName);
        assigneeInput.sendKeys(Keys.ENTER);
    }
    public String getDisplayedIssueAssigneeUsername() {
        wait.until(ExpectedConditions.visibilityOf(displayedIssueAssigneeSpan));
        return displayedIssueAssigneeSpan.getAttribute("rel");
    }
}

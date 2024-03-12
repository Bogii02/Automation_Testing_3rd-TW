package com.codecool;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateIssue extends Page {
    private Project projectType;
    private String summary;
    @FindBy(id = "project-single-select")
    private WebElement projectDropdown;

    @FindBy(id = "summary")
    private WebElement summaryField;

    @FindBy(id = "create-issue-submit")
    private WebElement createIssueSubmitButton;

    @FindBy(xpath = "//*[@id=\"project-field\"]")
    private WebElement projectInputField;

    @FindBy(xpath = "//*[@id=\"aui-flag-container\"]/div/div/a")
    private WebElement issueName;

    @FindBy(id = "key-val")
    private WebElement testID;

    public CreateIssue(WebDriver driver, Project projectType, String summary) {
        super(driver);
        this.projectType = projectType;
        this.summary = summary;
        PageFactory.initElements(driver, this);
    }

    private void chooseProjectFromProjectDropdownMenu() {
        wait.until(ExpectedConditions.visibilityOf(projectDropdown));
        projectDropdown.click();
        projectInputField.sendKeys(projectType.toString());
        projectInputField.sendKeys(Keys.ENTER);
    }

    private void fillInSummaryField() {
        wait.until(ExpectedConditions.visibilityOf(summaryField));
        summaryField.sendKeys(summary);
    }

    private void clickOnCreateIssueSubmitButton() {
        wait.until(ExpectedConditions.visibilityOf(createIssueSubmitButton));
        createIssueSubmitButton.click();
    }

    public void createIssue() throws InterruptedException {
        fillInSummaryField();

        chooseProjectFromProjectDropdownMenu();

        Thread.sleep(1000);
        clickOnCreateIssueSubmitButton();
    }

    public String getIssueNameAndGetToIssueDetailedPage() {
        wait.until(ExpectedConditions.visibilityOf(issueName));
        String createdIssueName = issueName.getAccessibleName();
        issueName.click();
        return createdIssueName;
    }

    public String verifyIssueName() {
        wait.until(ExpectedConditions.visibilityOf(testID));
        String detailedIssueName = testID.getAccessibleName();
        return detailedIssueName;
    }

}

package com.codecool.issue;

import com.codecool.IssueDetails;
import com.codecool.login.Login;
import com.codecool.Project;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class CreateIssueTest {
    private static final Dotenv dotenv = Dotenv.load();
    private static String username;
    private static String password;
    private WebDriver driver;

    @BeforeAll
    public static void initializeData() {
        username = dotenv.get("JIRA_USERNAME");
        password = dotenv.get("JIRA_PASSWORD");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Login login = new Login(driver);
        driver.get(Login.LOGIN_URL);
        login.login(username, password);
    }

    @ParameterizedTest
    @EnumSource(value = Project.class)
    public void createIssueTest(Project projectType) throws InterruptedException {
        CreateIssue issue = new CreateIssue(driver, projectType, "test");
        issue.clickOnCreateButton();
        issue.createIssue();
        Assertions.assertEquals(issue.getIssueNameAndGetToIssueDetailedPage(), issue.verifyIssueName());
    }

    @AfterEach
    void tearDown() {
        IssueDetails issueDetails = new IssueDetails(driver);
        issueDetails.deleteIssue();
        driver.quit();
    }
}
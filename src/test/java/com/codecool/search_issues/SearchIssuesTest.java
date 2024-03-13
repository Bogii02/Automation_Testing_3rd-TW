package com.codecool.search_issues;

import com.codecool.Login;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class SearchIssuesTest {
    private static final Dotenv dotenv = Dotenv.load();
    private static String username;
    private static String password;
    private static String jiraFullName;
    private WebDriver driver;
    private SearchIssues searchIssues;

    @BeforeAll
    public static void initializeData() {
        username = dotenv.get("JIRA_USERNAME");
        password = dotenv.get("JIRA_PASSWORD");
        jiraFullName = dotenv.get("JIRA_FULL_NAME");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Login login = new Login(driver);
        searchIssues = new SearchIssues(driver);
        driver.get(Login.LOGIN_URL);
        login.login(username, password);
        searchIssues.navigateToIssueSearch();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    public void searchIssuesByAssigneeTest() {
        searchIssues.searchIssueByAssignee(jiraFullName);
        Assertions.assertEquals(username, searchIssues.getDisplayedIssueAssigneeUsername());
    }
}
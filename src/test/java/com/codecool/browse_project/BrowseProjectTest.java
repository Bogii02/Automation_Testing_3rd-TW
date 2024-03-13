package com.codecool.browse_project;

import com.codecool.login.Login;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class BrowseProjectTest {
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
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Login login = new Login(driver);
        driver.get(Login.LOGIN_URL);
        login.login(username, password);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/browse_project_test_data.csv", numLinesToSkip = 1)
    public void browseProjectTest(String projectName) {
        BrowseProject browseProject = new BrowseProject(driver);
        browseProject.navigateToProjectSearch();

        browseProject.searchProject(projectName);

        Assertions.assertTrue(browseProject.isProjectPresent(projectName));
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/browse_not_existing_project_test_data.csv", numLinesToSkip = 1)
    public void browseNotExistingProjectTest(String projectName) {
        BrowseProject browseProject = new BrowseProject(driver);
        browseProject.navigateToProjectSearch();

        browseProject.searchProject(projectName);

        Assertions.assertThrows(TimeoutException.class, () ->
        browseProject.isProjectPresent(projectName));
    }
}
package com.codecool.login;

import com.codecool.Login;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class LoginTest {
    private WebDriver driver;
    private static final Dotenv dotenv = Dotenv.load();
    private static String username;
    private static String password;

    @BeforeAll
    public static void initializeData() {
        username = dotenv.get("JIRA_USERNAME");
        password = dotenv.get("JIRA_PASSWORD");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Login.LOGIN_URL);
    }

    @Test
    public void verifyLogin() {
        Login login = new Login(driver);
        login.login(username, password);
        Assertions.assertEquals(username, login.verifyLogin());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/unsuccessful_login_data.csv", numLinesToSkip = 1)
    public void unsuccessfulLoginTest(String username, String password) {
        Login login = new Login(driver);
        login.login(username, password);
        Assertions.assertTrue(login.isUnsuccessfulLoginErrorMessageDisplayed());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
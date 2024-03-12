package com.codecool;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class LogoutTest {
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

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    public void logoutTest() {
        Logout logout = new Logout(driver);

        logout.logout();
        boolean actual = logout.verifyLogout();

        Assertions.assertTrue(actual);
    }
}
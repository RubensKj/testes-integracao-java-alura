package br.com.alura.leilao.selenium.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSeleniumTest {

    private static final String LOGIN_PAGE = "http://localhost:8080/login";

    private WebDriver browser;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "C:\\workspace\\01.SeleniumWebDriver\\chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        browser = new ChromeDriver();
        browser.navigate().to(LOGIN_PAGE);
    }

    @AfterEach
    void tearDown() {
        browser.quit();
    }

    @Test
    void shouldLoginWithValidData() {
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        assertNotEquals(LOGIN_PAGE, browser.getCurrentUrl());
        assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    void shouldNotLoginWithInvalidData() {
        browser.findElement(By.id("username")).sendKeys("invalido");
        browser.findElement(By.id("password")).sendKeys("123123123");
        browser.findElement(By.id("login-form")).submit();

        assertEquals(LOGIN_PAGE.concat("?error"), browser.getCurrentUrl());
        assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")).getText());
    }
}

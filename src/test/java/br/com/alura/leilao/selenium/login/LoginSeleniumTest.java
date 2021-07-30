package br.com.alura.leilao.selenium.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSeleniumTest {

    private LoginPage loginPage;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage();
    }

    @AfterEach
    void tearDown() {
        this.loginPage.quitBrowser();
    }

    @Test
    void shouldLoginWithValidData() {
        this.loginPage.fillLoginFields("fulano", "pass");
        this.loginPage.submit();

        assertFalse(this.loginPage.isLoginPageUrl());
        assertEquals("fulano", this.loginPage.getNameUserLogged());
    }

    @Test
    void shouldNotLoginWithInvalidData() {
        this.loginPage.fillLoginFields("invalido", "123123123");
        this.loginPage.submit();

        assertTrue(this.loginPage.isLoginPageUrlWithError());
        assertTrue(this.loginPage.contains("Usuário e senha inválidos."));
        assertNull(this.loginPage.getNameUserLogged());
    }

    @Test
    void shouldntAccessRestrictedAreaWithoutAuthenticated() {
        this.loginPage.navigateToLanchesPage();

        assertTrue(this.loginPage.isLoginPageUrl());
        assertFalse(this.loginPage.contains("Dados do Leilão"));
    }
}

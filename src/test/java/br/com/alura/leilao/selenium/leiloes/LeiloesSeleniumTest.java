package br.com.alura.leilao.selenium.leiloes;

import br.com.alura.leilao.selenium.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeiloesSeleniumTest {

    private LeiloesPage leiloesPage;
    private CadastroLeilaoPage cadastroLeilaoPage;

    @BeforeEach
    void setUp() {
        LoginPage loginPage = new LoginPage();
        loginPage.fillLoginFields("fulano", "pass");
        this.leiloesPage = loginPage.submit();
        this.cadastroLeilaoPage = this.leiloesPage.navigateToForm();
    }

    @AfterEach
    void tearDown() {
        this.leiloesPage.quitBrowser();
    }

    @Test
    void shouldSaveLeilao() {
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilão do dia " + hoje;
        String valor = "500.00";

        this.leiloesPage = this.cadastroLeilaoPage.saveLeilao(nome, valor, hoje);

        assertTrue(this.leiloesPage.isLeilaoCadastrado(nome, valor, hoje));
    }

    @Test
    void shouldntSaveLeilao() {
        this.leiloesPage = this.cadastroLeilaoPage.saveLeilao("", "", "");

        assertFalse(this.cadastroLeilaoPage.isActualPage());
        assertTrue(this.leiloesPage.isActualPage());
        assertTrue(this.cadastroLeilaoPage.isContainsMessagesErrors());
    }
}

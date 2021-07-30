package br.com.alura.leilao.selenium.leiloes;

import br.com.alura.leilao.selenium.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeiloesSeleniumTest {

    private LoginPage loginPage;
    private LeiloesPage leiloesPage;

    @BeforeEach
    void setUp() {
        this.loginPage = new LoginPage();
    }

    @AfterEach
    void tearDown() {
        this.leiloesPage.quitBrowser();
    }

    @Test
    void shouldSaveLeilao() {
        this.loginPage.fillLoginFields("fulano", "pass");
        this.leiloesPage = this.loginPage.submit();

        CadastroLeilaoPage cadastroLeilaoPage = this.leiloesPage.navigateToForm();

        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilão do dia " + hoje;
        String valor = "500.00";

        this.leiloesPage = cadastroLeilaoPage.saveLeilao(nome, valor, hoje);

        assertTrue(this.leiloesPage.isLeilaoCadastrado(nome, valor, hoje));
    }
}

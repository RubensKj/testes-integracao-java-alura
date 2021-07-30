package br.com.alura.leilao.selenium.leiloes;

import br.com.alura.leilao.selenium.page.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeiloesPage extends PageObject {

    private static final String LEILOES_URL = "http://localhost:8080/leiloes";

    public LeiloesPage(WebDriver webDriver) {
        super(webDriver);
        getBrowser().navigate().to(LEILOES_URL);
    }

    public void quitBrowser() {
        getBrowser().quit();
    }

    public CadastroLeilaoPage navigateToForm() {
        WebDriver browser = getBrowser();

        browser.navigate().to(LEILOES_URL.concat("/new"));

        return new CadastroLeilaoPage(browser);
    }

    public boolean isLeilaoCadastrado(String nome, String valor, String data) {
        WebDriver browser = this.getBrowser();

        WebElement element = browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));

        WebElement colunaNome = element.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaDataAbertura = element.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValorInicial = element.findElement(By.cssSelector("td:nth-child(3)"));

        return colunaNome.getText().equals(nome)
                && colunaDataAbertura.getText().equals(data)
                && colunaValorInicial.getText().equals(valor);
    }
}

package br.com.alura.leilao.selenium.leiloes;

import br.com.alura.leilao.selenium.page.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroLeilaoPage extends PageObject {

    public CadastroLeilaoPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void quitBrowser() {
        this.getBrowser().quit();
    }

    public LeiloesPage saveLeilao(String nome, String valorInicial, String dataAbertura) {
        WebDriver browser = getBrowser();

        browser.findElement(By.id("nome")).sendKeys(nome);
        browser.findElement(By.id("valorInicial")).sendKeys(valorInicial);
        browser.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
        browser.findElement(By.id("button-submit")).submit();

        return new LeiloesPage(browser);
    }
}

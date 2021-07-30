package br.com.alura.leilao.selenium.leiloes;

import br.com.alura.leilao.selenium.page.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroLeilaoPage extends PageObject {

    private static final String LEILAO_CADASTRO_URL = "http://localhost:8080/leiloes/new";

    public CadastroLeilaoPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LeiloesPage saveLeilao(String nome, String valorInicial, String dataAbertura) {
        WebDriver browser = getBrowser();

        browser.findElement(By.id("nome")).sendKeys(nome);
        browser.findElement(By.id("valorInicial")).sendKeys(valorInicial);
        browser.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
        browser.findElement(By.id("button-submit")).submit();

        return new LeiloesPage(browser);
    }

    public boolean isActualPage() {
        return LEILAO_CADASTRO_URL.equals(getBrowser().getCurrentUrl());
    }

    public boolean isContainsMessagesErrors() {
        String pageSource = getBrowser().getPageSource();

        return pageSource.contains("minimo 3 caracteres")
                && pageSource.contains("não deve estar em branco")
                && pageSource.contains("deve ser um valor maior de 0.1")
                && pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
    }
}

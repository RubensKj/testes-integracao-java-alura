package br.com.alura.leilao.selenium.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class PageObject extends ChromePage {

    private final WebDriver webDriver;

    public PageObject() {
        this.webDriver = new ChromeDriver();
    }

    public PageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void quitBrowser() {
        this.webDriver.quit();
    }

    public WebDriver getBrowser() {
        return webDriver;
    }
}

package br.com.alura.leilao.selenium.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public abstract class PageObject extends ChromePage {

    private final WebDriver webDriver;

    public PageObject() {
        this.webDriver = new ChromeDriver();

        addTimeouts();
    }

    public PageObject(WebDriver webDriver) {
        this.webDriver = webDriver;

        addTimeouts();
    }

    private void addTimeouts() {
        this.webDriver.manage().timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS)
                .pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public void quitBrowser() {
        this.webDriver.quit();
    }

    public WebDriver getBrowser() {
        return webDriver;
    }
}

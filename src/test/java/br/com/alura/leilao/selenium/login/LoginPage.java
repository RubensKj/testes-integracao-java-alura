package br.com.alura.leilao.selenium.login;

import br.com.alura.leilao.selenium.leiloes.LeiloesPage;
import br.com.alura.leilao.selenium.page.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageObject {

    private static final String LOGIN_PAGE = "http://localhost:8080/login";

    public void quitBrowser() {
        getBrowser().quit();
    }

    public void fillLoginFields(String username, String password) {
        WebDriver browser = getBrowser();

        browser.navigate().to(LOGIN_PAGE);
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public LeiloesPage submit() {
        WebDriver browser = getBrowser();

        browser.findElement(By.id("login-form")).submit();
        return new LeiloesPage(browser);
    }

    public boolean isLoginPageUrl() {
        return LOGIN_PAGE.equals(getBrowser().getCurrentUrl());
    }

    public boolean isLoginPageUrlWithError() {
        return LOGIN_PAGE.concat("?error").equals(getBrowser().getCurrentUrl());
    }

    public String getNameUserLogged() {
        try {
            return getBrowser().findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void navigateToLanchesPage() {
        this.getBrowser().navigate().to("http://localhost:8080/leiloes/2");
    }

    public boolean contains(String text) {
        return this.getBrowser().getPageSource().contains(text);
    }
}

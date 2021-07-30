package br.com.alura.leilao.selenium.login;

import br.com.alura.leilao.selenium.page.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage extends PageObject {

    private static final String LOGIN_PAGE = "http://localhost:8080/login";
    private final WebDriver browser;

    public LoginPage() {
        this.browser = new ChromeDriver();
        this.browser.navigate().to(LOGIN_PAGE);
    }

    public void quitBrowser() {
        this.browser.quit();
    }

    public void fillLoginFields(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public void submit() {
        browser.findElement(By.id("login-form")).submit();
    }

    public boolean isLoginPageUrl() {
        return LOGIN_PAGE.equals(browser.getCurrentUrl());
    }

    public boolean isLoginPageUrlWithError() {
        return LOGIN_PAGE.concat("?error").equals(browser.getCurrentUrl());
    }

    public String getNameUserLogged() {
        try {
            return browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void navigateToLanchesPage() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");
    }

    public boolean contains(String text) {
        return browser.getPageSource().contains(text);
    }
}

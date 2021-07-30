package br.com.alura.leilao.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWorldSelenium {

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\workspace\\01.SeleniumWebDriver\\chromedriver.exe");
    }

    @Test
    void hello() {
        WebDriver browser = new ChromeDriver();

        browser.navigate().to("http://localhost:8080/leiloes");

        browser.quit();
    }
}

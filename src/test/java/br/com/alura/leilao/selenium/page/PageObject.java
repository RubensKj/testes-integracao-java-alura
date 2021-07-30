package br.com.alura.leilao.selenium.page;

import static br.com.alura.leilao.selenium.util.PropertyWebDriver.setDriverPathInSystem;

public abstract class PageObject {

    public PageObject() {
        setDriverPathInSystem();
    }
}

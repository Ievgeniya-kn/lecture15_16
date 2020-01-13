package com.petclinic.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Configuration {
    private String browser = System.getProperty("browser", "chrome");

    public WebDriver initDriver() {


        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                Browser chrome = Browser.CHROME;
                driver = chrome.getDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                Browser firefox = Browser.FIREFOX;
                driver = firefox.getDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                chrome = Browser.CHROME;
                driver = chrome.getDriver();

        }
        return driver;
    }
}

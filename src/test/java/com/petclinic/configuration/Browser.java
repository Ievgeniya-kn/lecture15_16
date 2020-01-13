package com.petclinic.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.Driver;

public enum Browser {
    CHROME ( new ChromeDriver()),
    FIREFOX (new FirefoxDriver());


    public WebDriver driver;

    Browser(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }



    //    switch (browser.toLowerCase()) {
//        case "chrome":
//            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//            break;
//        case "firefox":
//            WebDriverManager.firefoxdriver().setup();
//            driver =  new FirefoxDriver();
//            break;
//        default:
//            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//    }
}

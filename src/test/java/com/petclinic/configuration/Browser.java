package com.petclinic.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.Driver;

public enum Browser {
    CHROME ("ChromeDriver()","chromedriver()"),
    FIREFOX ("FirefoxDriver()", "firefoxdriver()");

    private String driverName;
    private String driver;

    Browser(String driverName, String driver) {
        this.driverName = driverName;
        this.driver = driver;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
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

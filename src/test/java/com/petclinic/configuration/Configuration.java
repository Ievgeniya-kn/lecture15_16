package com.petclinic.configuration;

import org.openqa.selenium.WebDriver;

public class Configuration {
    private String browser = System.getProperty("browser", "chrome");

    WebDriver driver;
}

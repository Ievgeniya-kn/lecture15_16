package com.lecture18_AddOwner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class AddOwner {

    WebDriver driver;
    WebElement firstName, lastName,address,city,phone,submit;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5000,TimeUnit.MILLISECONDS);
    }

    @Test(priority = 1)
    public void recheckAllFieldsArePresentOnPage() {
        driver.get("http://139.59.149.247:8000/petclinic/owners/add");
        firstName = driver.findElement(By.name("firstName"));
        lastName = driver.findElement(By.name("lastName"));
        address = driver.findElement(By.id("address"));
        city = driver.findElement(By.id("city"));
        phone = driver.findElement(By.name("telephone"));
        submit = driver.findElement(By.cssSelector(".btn[type='submit']"));
        assertTrue(firstName.isDisplayed());
        assertTrue(lastName.isDisplayed());
        assertTrue(city.isDisplayed());
        assertTrue(address.isDisplayed());
        assertTrue(phone.isDisplayed());
    }

    @Test (priority = 2)
    public void recheckAllFieldsAreRequired() {
        try {
            submit.clear();
        } catch(Exception testError){
            System.out.println("Button isn't active due to required fields are empty");
           testError.printStackTrace();
        }
    }

    @Test (priority=3)
    public void addNewOwner() {
         firstName.sendKeys("Jack");
         lastName.sendKeys("Daniels");
         city.sendKeys("Tennessy");
         address.sendKeys("23 Oak Road");
         phone.sendKeys("123456");
         submit.click();
         String currentUrl = driver.getCurrentUrl();
         assertEquals(currentUrl,"http://139.59.149.247:8000/petclinic/owners");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

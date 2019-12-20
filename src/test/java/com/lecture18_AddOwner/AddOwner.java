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

    WebDriver petClinic;
    WebElement firstName, lastName,address,city,phone,submit;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        petClinic = new ChromeDriver();
        petClinic.manage().timeouts().implicitlyWait(5000,TimeUnit.MILLISECONDS);
    }

    @Test(priority = 1)
    public void recheckAllFieldsArePresentOnPage() {
        petClinic.get("http://139.59.149.247:8000/petclinic/owners/add");
        firstName = petClinic.findElement(By.name("firstName"));
        lastName = petClinic.findElement(By.name("lastName"));
        address = petClinic.findElement(By.id("address"));
        city = petClinic.findElement(By.id("city"));
        phone = petClinic.findElement(By.name("telephone"));
        submit = petClinic.findElement(By.cssSelector(".btn[type='submit']"));
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
         String currentUrl =petClinic.getCurrentUrl();
         assertEquals(currentUrl,"http://139.59.149.247:8000/petclinic/owners");
    }


    @AfterClass
    public void afterClass() {
        petClinic.quit();
    }
}

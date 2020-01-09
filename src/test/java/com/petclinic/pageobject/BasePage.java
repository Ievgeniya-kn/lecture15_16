package com.petclinic.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {


    private static final String BASE_URL = "http://139.59.149.247:8000/petclinic";

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }


    protected WebDriverWait waitFor() {
        return new WebDriverWait(driver, 4);
    }

    protected void goToUrl(String url, String title) {
        driver.get(BASE_URL + url);
        waitFor().withMessage(title+ " page is not open!")
                .until(ExpectedConditions.textToBe(By.xpath("//h2"), title));
    }

    public void clearField(String fieldId){

        WebElement field = driver.findElement(By.id(fieldId));
        field.sendKeys("234");
        while (field.getAttribute("value").length()>0) {
            field.sendKeys("\u0008");
        }
    }

//    waitFor().withMessage("Field '" + fieldName + "' not found!").until(ExpectedConditions.and(
//            ExpectedConditions.textToBe(By.xpath("//*[@for='" + fieldId + "']"), fieldName),
//            ExpectedConditions.presenceOfElementLocated(By.id(fieldId))
}

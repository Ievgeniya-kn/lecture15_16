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
        return new WebDriverWait(driver, 2);
    }

    protected void goToUrl(String url, String title) {
        driver.get(BASE_URL + url);
        waitFor().withMessage(title + " page is not open!")
                .until(ExpectedConditions.textToBe(By.xpath("//h2"), title));
    }

    public void clearField(String fieldId) {

        WebElement field = driver.findElement(By.id(fieldId));
        field.sendKeys("234");
        while (field.getAttribute("value").length() > 0) {
            field.sendKeys("\u0008");
        }
    }

    protected boolean verifyBtnIsDisabled(By button) {
        boolean saveIsEnabled = true;
        waitFor().withMessage("button is still enabled")
                .until(ExpectedConditions.invisibilityOf(driver.findElement(button)));

        try {
            WebElement savePet = driver.findElement(button);

        } catch (Exception ex) {
            saveIsEnabled = false;
        }
        return saveIsEnabled;
    }

    protected WebElement waitUntilElementVisible(String elementName, By elementPath) {
        waitFor().withMessage(elementName + " isn't enabled")
                .until(ExpectedConditions.visibilityOf(driver.findElement(elementPath)));

        return driver.findElement(elementPath);
    }

    protected void waitUntilTableVisible(By tablePath) {
        waitFor().until(ExpectedConditions.presenceOfAllElementsLocatedBy(tablePath));
    }

    protected WebElement waitUntilClickable(String elementName, By elementPath) {
        waitFor().withMessage(elementName + " not clickable")
                .until(ExpectedConditions.elementToBeClickable(elementPath));
        return driver.findElement(elementPath);
    }
}

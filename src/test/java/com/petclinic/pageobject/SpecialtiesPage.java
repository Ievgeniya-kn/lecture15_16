package com.petclinic.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SpecialtiesPage extends BasePage {

    private WebElement name;
    private WebDriverWait wait;
    private By nameId = By.id("name");
    private By saveBtn = By.xpath("//*[text()='Save']");
    private By addBtn = By.xpath("//*[text()=' Add ']");

    public SpecialtiesPage(WebDriver driver) {
        super(driver);
    }

    public SpecialtiesPage openPage() {
        goToUrl("/specialties", "Specialties");
        waitUntilElementVisible("Add button",addBtn);
        return this;
    }

    public void addSpeciality(String name) {
        WebElement nameField = driver.findElement(nameId);
        nameField.clear();
        nameField.sendKeys(name);
        clickSaveSpecialitiestButton();
    }

    public void clickAddSpecialitiestButton() {
        WebElement addSpecialitiestBtn = driver.findElement(addBtn);
        waitUntilClickable("Add Button",addBtn);
        addSpecialitiestBtn.click();
    }

    public void clickSaveSpecialitiestButton() {
        WebElement saveSpecialitiest = driver.findElement(saveBtn);
        waitUntilClickable("Save button", saveBtn);
        saveSpecialitiest.click();
    }


    public boolean verifySaveBtnIsDisabled() {
        return verifyBtnIsDisabled(saveBtn);
    }
}

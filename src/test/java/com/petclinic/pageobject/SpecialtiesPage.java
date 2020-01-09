package com.petclinic.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SpecialtiesPage extends BasePage {

    private WebElement name;
    private By nameId = By.id("name");
    private By saveBtn = By.xpath("//*[text()='Save']");
    private By addBtn = By.xpath("//*[text()=' Add ']");

    public SpecialtiesPage(WebDriver driver) {
        super(driver);
    }

    public SpecialtiesPage openPage() {
        goToUrl("/specialties", "Specialties");
        return this;
    }

    public void setName(String name) {
        WebElement nameField = driver.findElement(nameId);
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void clickAddSpecialitiestButton() {
        WebElement addSpecialitiestBtn = driver.findElement(addBtn);
        addSpecialitiestBtn.click();
    }

    public void clickSaveSpecialitiestButton() {
        WebElement saveSpecialitiest = driver.findElement(saveBtn);
        saveSpecialitiest.click();
    }

    public boolean verifySaveBtnIsDisabled() {
        boolean saveIsEnabled = true;
        try {
            WebElement savePet = driver.findElement(saveBtn);

        } catch (Exception ex) {
            saveIsEnabled = false;
        }
        return saveIsEnabled;
    }
}

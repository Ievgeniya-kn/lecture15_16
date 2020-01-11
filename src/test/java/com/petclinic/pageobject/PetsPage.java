package com.petclinic.pageobject;

import com.petclinic.Pet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PetsPage extends BasePage {
    private WebElement name;
    private By nameId = By.id("name");
    private By saveBtn = By.xpath("//*[text()='Save']");
    private By addBtn = By.xpath("//*[text()=' Add ']");


    public PetsPage(WebDriver driver) {
        super(driver);
    }

    public PetsPage openPage() {
        goToUrl("/pettypes", "Pet Types");
        return this;
    }

    public void addPet(String name) {
        WebElement nameField = driver.findElement(nameId);
        nameField.clear();
        nameField.sendKeys(name);
        clickSavePetButton();
    }

    public void clickAddPetButton() {
        WebElement addPetBtn = driver.findElement(addBtn);
        waitUntilClickable("Add Button",addBtn);
        addPetBtn.click();
    }

    public void clickSavePetButton() {
        WebElement savePet = driver.findElement(saveBtn);
        waitUntilClickable("Save Button",saveBtn);
        savePet.click();
    }


    public boolean verifySaveBtnIsDisabled() {
        return verifyBtnIsDisabled(saveBtn);
    }

}

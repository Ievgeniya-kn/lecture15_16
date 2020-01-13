package com.petclinic.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SpecialtiesPage extends BasePage {

    private WebElement name;
    private WebDriverWait wait;
    private By nameId = By.id("name");
    private By saveBtn = By.xpath("//*[text()='Save']");
    private By addBtn = By.xpath("//*[text()=' Add ']");
    private By tableId = By.id("specialties");
    private By specRowId = By.xpath("//input[@name='spec_name']");
    private String buttonEdit = "//following-sibling::td//*[text()='Delete']";

    public SpecialtiesPage(WebDriver driver) {
        super(driver);
    }

    public SpecialtiesPage openPage() {
        goToUrl("/specialties", "Specialties");
        WebElement addButton = waitUntilElementVisible("Add button", addBtn);
        return this;
    }

    public void addSpeciality(String name) {
        WebElement nameField = driver.findElement(nameId);
        nameField.clear();
        nameField.sendKeys(name);
        clickSaveSpecialitiestButton();
    }

    public void clickAddSpecialitiestButton() {
        WebElement addSpecialitiestBtn = waitUntilClickable("Add Button", addBtn);
        addSpecialitiestBtn.click();
    }

    public void clickSaveSpecialitiestButton() {
        WebElement saveSpecialitiest = waitUntilClickable("Save button", saveBtn);
        saveSpecialitiest.click();
    }


    public boolean verifySaveBtnIsDisabled() {
        return verifyBtnIsDisabled(saveBtn);
    }

    public List<String> getSpecTypeList() {

        List<String> specialities = new ArrayList<>();

        List<WebElement> specElements = driver.findElements(specRowId);
        for (WebElement singleSpecialist : specElements
        ) {
            specialities.add(singleSpecialist.getAttribute("value"));

        }

        return specialities;
    }

    public void pressDeleteSpecialialities(String oldName) {
        int i = 0;
        List<WebElement> specTypesElements = driver.findElements(specRowId);
        for (WebElement namePet : specTypesElements
        ) {
            i++;
            if (oldName.equals(namePet.getAttribute("value"))) {
                System.out.println(namePet.getLocation());
                WebElement btnDelete = driver.findElement(By.xpath("//table//tr[" + i + "]/td[1]" + buttonEdit));
                btnDelete.click();
                waitUntilTableVisible(tableId);
                i--;
            }
        }
    }
}

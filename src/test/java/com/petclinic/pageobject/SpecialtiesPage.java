package com.petclinic.pageobject;

import io.qameta.allure.Step;
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

    @Step("Open page /specialties")
    public SpecialtiesPage openPage() {
        goToUrl("/specialties", "Specialties");
        WebElement addButton = waitUntilElementVisible("Add button", addBtn);
        return this;
    }
    @Step("Add specialist with name = {name} ")
    public void addSpeciality(String name) {
        WebElement nameField = driver.findElement(nameId);
        nameField.clear();
        nameField.sendKeys(name);
        clickSaveSpecialitiestButton();
    }
    @Step("Press button 'Add' ")
    public void clickAddSpecialitiestButton() {
        WebElement addSpecialitiestBtn = waitUntilClickable("Add Button", addBtn);
        addSpecialitiestBtn.click();
    }
    @Step("Press button 'Save' ")
    public void clickSaveSpecialitiestButton() {
        WebElement saveSpecialitiest = waitUntilClickable("Save button", saveBtn);
        saveSpecialitiest.click();
    }

    @Step("Recheck if button Add is disabled")
    public void ifButtonDisabledPressAdd() {
        try {
            driver.findElement(nameId);
        } catch(Exception ex) {
            clickAddSpecialitiestButton();
        }
    }


    public boolean verifySaveBtnIsDisabled() {
        return verifyBtnIsDisabled(saveBtn);
    }

    @Step("Get all Specialist List")
    public List<String> getSpecTypeList() {

        List<String> specialities = new ArrayList<>();

        List<WebElement> specElements = driver.findElements(specRowId);
        for (WebElement singleSpecialist : specElements
        ) {
            specialities.add(singleSpecialist.getAttribute("value"));

        }

        return specialities;
    }
    @Step("Delete Specialist name = {oldName}")
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

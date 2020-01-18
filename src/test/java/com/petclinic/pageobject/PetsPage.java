package com.petclinic.pageobject;

import com.petclinic.Pet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PetsPage extends BasePage {
    private WebElement name;
    private By nameId = By.id("name");
    private By saveBtn = By.xpath("//*[text()='Save']");
    private By addBtn = By.xpath("//*[text()=' Add ']");
    private By tableId = By.id("pettypes");
    private By petRowId = By.xpath("//input[@name='pettype_name']");
    private String buttonDelete = "//following-sibling::td//*[text()='Delete']";


    public PetsPage(WebDriver driver) {
        super(driver);
    }

    public PetsPage openPage() {
        goToUrl("/pettypes", "Pet Types");

        WebElement addButton = waitUntilElementVisible("Add button", addBtn);
        return this;
    }

    public void addPet(String name) {
        WebElement nameField = driver.findElement(nameId);
        nameField.clear();
        nameField.sendKeys(name);
        clickSavePetButton();
    }

    public void clickAddPetButton() {
        WebElement addPetBtn = waitUntilClickable("Add Button", addBtn);
//addPetBtn.clear();
//        waitUntilClickable("Add Button", addBtn);
        addPetBtn.click();
    }

    public void ifButtonDisabledPressAdd() {
        try {
            driver.findElement(nameId);
        } catch(Exception ex) {
            clickAddPetButton();
        }
    }

    public void clickSavePetButton() {
        WebElement savePet = driver.findElement(saveBtn);
        waitUntilClickable("Save Button", saveBtn);
        savePet.click();
    }


    public boolean verifySaveBtnIsDisabled() {
        return verifyBtnIsDisabled(saveBtn);
    }


    public List<String> getPetTypeList() {

        waitUntilTableVisible(tableId);

        List<String> petTypes = new ArrayList<>();

        List<WebElement> petTypesElements = driver.findElements(petRowId);
        for (WebElement namePet : petTypesElements
        ) {
            petTypes.add(namePet.getAttribute("value"));
        }

        return petTypes;
    }


    public void pressDeletePet(String petName) {
        int i = 0;
        List<WebElement> petTypesElements = driver.findElements(petRowId);
        for (WebElement namePet : petTypesElements
        ) {
            i++;
            if (petName.equals(namePet.getAttribute("value"))) {
                System.out.println(namePet.getLocation());
                WebElement btnDelete = driver.findElement(By.xpath("//table//tr[" + i + "]/td[1]" + buttonDelete));
                btnDelete.click();
                waitUntilTableVisible(tableId);
                i--;
            }
        }
    }

}

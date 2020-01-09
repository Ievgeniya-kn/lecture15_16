package com.petclinic.pageobject;

import com.petclinic.Owner;
import com.petclinic.Veterinarian;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NewVeterinarianPage {

    private WebDriver driver;
    private WebElement lastNameField;
    private By firstNameId = By.id("firstName");
    private By lastNameId = By.id("lastName");
    private By typeId = By.id("specialties");


    public NewVeterinarianPage(WebDriver driver) {
        this.driver = driver;
    }

    public By returnByForField(String fieldName) {
        switch (fieldName) {
            case "Last name":
                return lastNameId;
            case "Type":
                return typeId;
            default:
                return firstNameId;
        }
    }

    public void fillVeterinarian(Veterinarian veterinarian) {
        setFirstName(veterinarian.getFirstName());
        setLastName(veterinarian.getLastName());
        setType();
    }


    public void setFirstName(String firstName) {
        WebElement name = driver.findElement(firstNameId);
        name.clear();
        name.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        WebElement lastNameField = driver.findElement(lastNameId);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void clearField(String fieldName) {
        WebElement nameField = driver.findElement(returnByForField(fieldName));
        nameField.sendKeys("234");
        while (nameField.getAttribute("value").length() > 0) {
            nameField.sendKeys("\u0008");
        }
    }


    public void setType() {
        Select type = new Select (driver.findElement(typeId));
        type.selectByIndex(0);
    }


    public VeterinariansPage clickAddVetButton() {
        WebElement addVetBtn = driver.findElement(By.xpath("//*[text()='Save Vet']"));
        addVetBtn.click();
        return new VeterinariansPage(driver);
    }

    public WebElement errorMessage(String errorMessage) {
        By xpathErrorMessage = By.xpath("//*[@class='help-block'][text()='" + errorMessage + "']");

        WebElement errorMessageElement = driver.findElement(xpathErrorMessage);
        return errorMessageElement;
    }
}

package com.petclinic.pageobject;

import com.petclinic.Owner;
import com.petclinic.Veterinarian;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NewVeterinarianPage extends BasePage {

    //    private WebDriver driver;
    private WebElement lastNameField;
    private By firstNameId = By.id("firstName");
    private By lastNameId = By.id("lastName");
    private By typeId = By.id("specialties");
    private By saveVetButton = By.xpath("//*[text()='Save Vet']");


    public NewVeterinarianPage(WebDriver driver) {
        super(driver);
    }

    @Step("Return Locator for field")
    public By returnByForField(String fieldName) {
        switch (fieldName) {
            case "lastName":
                return lastNameId;
            case "Type":
                return typeId;
            default:
                return firstNameId;
        }
    }

    @Step("Fill Veterinarian")
    public void fillVeterinarian(Veterinarian veterinarian) {
        setFirstName(veterinarian.getFirstName());
        setLastName(veterinarian.getLastName());
        setType();
    }

    @Step("Set field='First Name' with value={firstName}")
    public void setFirstName(String firstName) {
        WebElement name = driver.findElement(firstNameId);
        name.clear();
        name.sendKeys(firstName);
    }

    @Step("Set field='Last Name' with {lastName}")
    public void setLastName(String lastName) {
        WebElement lastNameField = driver.findElement(lastNameId);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    @Step("Clear Field")
    public void clearField(String fieldName) {
        WebElement nameField = driver.findElement(returnByForField(fieldName));
        nameField.sendKeys("234");
        while (nameField.getAttribute("value").length() > 0) {
            nameField.sendKeys("\u0008");
        }
    }

    @Step("Set field='Type' from dropdown by Index=0")
    public void setType() {
        Select type = new Select(driver.findElement(typeId));
        type.selectByIndex(0);
    }

    @Step("Press Button 'Save Veterinarian'")
    public VeterinariansPage clickSaveVetButton() {
        WebElement addVetBtn =  waitUntilClickable("Save button", saveVetButton);
        addVetBtn.click();
        return new VeterinariansPage(driver);
    }

    @Step("Verify expected message is displayed {errorMessage}")
    public WebElement errorMessage(String errorMessage) {
        By xpathErrorMessage = By.xpath("//*[@class='help-block'][text()='" + errorMessage + "']");

        WebElement errorMessageElement = driver.findElement(xpathErrorMessage);
        return errorMessageElement;
    }
}

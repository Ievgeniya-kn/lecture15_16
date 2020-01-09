package com.petclinic.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.petclinic.Owner;

import java.security.Key;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

public class NewOwnerPage {

    private WebDriver driver;
    private WebElement lastNameField;
    private By firstNameId = By.id("firstName");
    private By lastNameId = By.id("lastName");
    private By addressId = By.id("address");
    private By telephoneId = By.id("telephone");
    private By cityId = By.id("city");

    public NewOwnerPage(WebDriver driver) {
        this.driver = driver;
    }

    public By returnByForField(String fieldName) {
        switch (fieldName) {
            case "Last name":
                return lastNameId;
            case "Address":
                return addressId;
            case "City":
                return cityId;
            case "Phone":
                return telephoneId;
            default:
                return firstNameId;
        }
    }

    public void fillOwner(Owner owner) {
        setFirstName(owner.getFirstName());
        setLastName(owner.getLastName());
        setAddress(owner.getAddress());
        setCity(owner.getCity());
        setTelephone(owner.getTelephone());
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


    public void setAddress(String address) {
        WebElement addressField = driver.findElement(addressId);
        addressField.clear();
        addressField.sendKeys(address);
    }

    public void setCity(String city) {
        WebElement cityField = driver.findElement(cityId);
        cityField.clear();
        cityField.sendKeys(city);
    }

    public void setTelephone(String telephone) {
        WebElement telephoneField = driver.findElement(telephoneId);
        telephoneField.clear();
        telephoneField.sendKeys(telephone);
    }

    public OwnersPage clickAddOwnerButton() {
        WebElement addOwnerBtn = driver.findElement(By.xpath("//*[text()='Add Owner']"));
        addOwnerBtn.click();
        return new OwnersPage(driver);
    }

    public WebElement errorMessage(String errorMessage) {
        By xpathErrorMessage = By.xpath("//*[@class='help-block'][text()='" + errorMessage + "']");

        WebElement errorMessageElement = driver.findElement(xpathErrorMessage);
        return errorMessageElement;
    }

}

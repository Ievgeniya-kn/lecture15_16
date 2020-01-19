package com.petclinic.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.petclinic.Owner;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

public class NewOwnerPage extends BasePage{

//    private WebDriver driver;
    private WebDriverWait waitFor;
    private WebElement lastNameField;
    private By firstNameId = By.id("firstName");
    private By lastNameId = By.id("lastName");
    private By addressId = By.id("address");
    private By telephoneId = By.id("telephone");
    private By cityId = By.id("city");
    private By addOwnerButton = By.xpath("//*[text()='Add Owner']");

    public NewOwnerPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get Locator for field")
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

    @Step("Fill Owner")
    public void fillOwner(Owner owner) {
        setFirstName(owner.getFirstName());
        setLastName(owner.getLastName());
        setAddress(owner.getAddress());
        setCity(owner.getCity());
        setTelephone(owner.getTelephone());
    }


    @Step("Set field='First Name'")
    public void setFirstName(String firstName) {
        WebElement name = driver.findElement(firstNameId);
        name.clear();
        name.sendKeys(firstName);
    }
    @Step("Set field='Last Name'")
    public void setLastName(String lastName) {
        WebElement lastNameField = driver.findElement(lastNameId);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    @Step("Clear field")
    public void clearField(String fieldName) {
        WebElement nameField = driver.findElement(returnByForField(fieldName));
        nameField.sendKeys("234");
        while (nameField.getAttribute("value").length() > 0) {
            nameField.sendKeys("\u0008");
        }
    }

    @Step("Set field='Address'")
    public void setAddress(String address) {
        WebElement addressField = driver.findElement(addressId);
        addressField.clear();
        addressField.sendKeys(address);
    }

    @Step("Set field='City'")
    public void setCity(String city) {
        WebElement cityField = driver.findElement(cityId);
        cityField.clear();
        cityField.sendKeys(city);
    }

    @Step("Set field='First Name'")
    public void setTelephone(String telephone) {
        WebElement telephoneField = driver.findElement(telephoneId);
        telephoneField.clear();
        telephoneField.sendKeys(telephone);
    }

    @Step("Press button 'Add Owner'")
    public OwnersPage clickAddOwnerButton() {
        WebElement addOwnerBtn = waitUntilClickable("Button Add",addOwnerButton);

        addOwnerBtn.click();
        return new OwnersPage(driver);
    }

    @Step("Verify expected message is displayed {errorMessage}")
    public WebElement errorMessage(String errorMessage) {
        By xpathErrorMessage = By.xpath("//*[@class='help-block'][text()='" + errorMessage + "']");

        waitFor = new WebDriverWait(driver,1);
        waitFor.until(ExpectedConditions.textToBePresentInElementLocated(xpathErrorMessage, errorMessage));
        WebElement errorMessageElement = driver.findElement(xpathErrorMessage);
        return errorMessageElement;
    }

}

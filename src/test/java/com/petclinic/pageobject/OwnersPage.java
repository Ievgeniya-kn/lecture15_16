package com.petclinic.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.petclinic.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnersPage extends BasePage {
    private By addOwnerBtnPath = By.xpath("//*[text()='Add Owner']");
    private By ownersTablePath = By.cssSelector(".ownerFullName>a");

    public OwnersPage(WebDriver driver) {
        super(driver);
    }

    public OwnersPage openPage() {
        goToUrl("/owners", "Owners");
        return this;
    }

    public List<String> getOwnersNames() {
        List<String> owners = new ArrayList<>();
        waitUntilTableVisible(ownersTablePath);
        List<WebElement> elements = driver.findElements(ownersTablePath);
        for (WebElement fullName : elements) {
            owners.add(fullName.getText());
        }

        return owners;
    }

    public List<Owner> getOwnersList() {
        List<Owner> owners = new ArrayList<>();
        WebElement ownersTable = driver.findElement(By.xpath("//*[@class='table-responsive']"));
        waitUntilTableVisible(ownersTablePath);
        List<WebElement> ownersList = ownersTable.findElements(By.xpath(".//tbody/tr"));
        for (WebElement userRow : ownersList) {
            owners.add(createOwner(userRow));
        }

        return owners;
    }

    public NewOwnerPage clickAddOwnerBtn() {
        WebElement addOwnerBtn = driver.findElement(addOwnerBtnPath);
        waitUntilClickable("Add Owner", addOwnerBtnPath);
        addOwnerBtn.click();
        return new NewOwnerPage(driver);
    }

    private Owner createOwner(WebElement userRow) {
        Owner owner = new Owner();
        String fullName = userRow.findElement(By.xpath("./td/a")).getText();
        String[] fullNameArray = fullName.split(" ");
        if (fullNameArray.length > 1) {
            owner.setFirstName(fullNameArray[0]);
            owner.setLastName(fullNameArray[1]);
        } else {
            owner.setFirstName(fullNameArray[0]);
        }
        owner.setAddress(userRow.findElement(By.xpath("./td[2]")).getText());
        owner.setCity(userRow.findElement(By.xpath("./td[3]")).getText());
        owner.setTelephone(userRow.findElement(By.xpath("./td[4]")).getText());

        String pets = userRow.findElement(By.xpath("./td[5]")).getText();
        if (!pets.isEmpty()) {
            owner.setPets(pets);
        }

        return owner;
    }

}

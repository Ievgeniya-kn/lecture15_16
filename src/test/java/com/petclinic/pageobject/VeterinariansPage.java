package com.petclinic.pageobject;

import com.petclinic.Owner;
import com.petclinic.Veterinarian;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class VeterinariansPage extends BasePage{
    public VeterinariansPage(WebDriver driver) {
        super(driver);
    }

    public VeterinariansPage openPage() {
        goToUrl("/vets", "Veterinarians");
        return this;
    }

    public List<String> getVeterinariansNames() {
        List<String> veterinarians = new ArrayList<>();

        List<WebElement> elements = driver.findElements(By.id("vets>a"));
        for (WebElement fullName : elements) {
            veterinarians.add(fullName.getText());
        }

        return veterinarians;
    }

    public List<Veterinarian> getveterinariansList() {
        List<Veterinarian> veterinarians = new ArrayList<>();
        WebElement veterinariansTable = driver.findElement(By.id("vets"));

        List<WebElement> veterinariansList = veterinariansTable.findElements(By.xpath(".//tbody/tr"));
        for (WebElement userRow : veterinariansList) {
            veterinarians.add(createVeterinarian(userRow));
        }

        return veterinarians;
    }

    public NewVeterinarianPage clickAddVetButton() {
        WebElement addVetButton = driver.findElement(By.xpath("//*[text()='Add Vet']"));
        addVetButton.click();
        return new NewVeterinarianPage(driver);
    }

    private Veterinarian createVeterinarian(WebElement userRow) {
        Veterinarian veterinarian = new Veterinarian();
        String fullName = userRow.findElement(By.xpath("./td")).getText();
        String[] fullNameArray = fullName.split(" ");
        if (fullNameArray.length > 1) {
            veterinarian.setFirstName(fullNameArray[0]);
            veterinarian.setLastName(fullNameArray[1]);
        } else {
            veterinarian.setFirstName(fullNameArray[0]);
        }


        return veterinarian;
    }
}

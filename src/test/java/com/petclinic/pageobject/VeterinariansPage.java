package com.petclinic.pageobject;

import com.petclinic.Owner;
import com.petclinic.Veterinarian;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class VeterinariansPage extends BasePage{

    private By addButton=By.xpath("//*[text()='Add Vet']");

    public VeterinariansPage(WebDriver driver) {
        super(driver);
    }


    @Step("Open page /vets")
    public VeterinariansPage openPage() {
        goToUrl("/vets", "Veterinarians");
        WebElement addBtn = waitUntilElementVisible("Add Button",addButton);
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


    @Step("Get Veterinarian List")
    public List<Veterinarian> getveterinariansList() {
        List<Veterinarian> veterinarians = new ArrayList<>();
        WebElement veterinariansTable = driver.findElement(By.cssSelector("table[id='vets']"));

        List<WebElement> veterinariansList = veterinariansTable.findElements(By.xpath(".//tbody/tr"));
        for (WebElement userRow : veterinariansList) {
            veterinarians.add(createVeterinarian(userRow));
        }

        return veterinarians;
    }

    @Step("Press button 'Add'")
    public NewVeterinarianPage clickAddVetButton() {
        WebElement addVetButton = waitUntilClickable("Add button", addButton);
        addVetButton.click();
        return new NewVeterinarianPage(driver);
    }

    @Step("Get Veterinarian from List")
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
    @Step("Verify Veterinarians List contains Newely added")
    public void verifyVetNewelyAddedPresentinList(Veterinarian veterinarian){
        List<Veterinarian> veterinariansNames = getveterinariansList();
        assertThat(veterinariansNames).contains(veterinarian);
    }
}

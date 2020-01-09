package com.petclinic.pageobject;

import org.openqa.selenium.WebDriver;

public class SpecialtiesPage extends BasePage {

    public SpecialtiesPage(WebDriver driver) {
        super(driver);
    }

    public SpecialtiesPage openPage() {
        goToUrl("/specialties", "Specialties");
        return this;
    }
}

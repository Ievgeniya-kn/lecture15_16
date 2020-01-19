package com.petclinic;

import com.petclinic.pageobject.SpecialtiesPage;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Speciality")
public class NewSpecialityTest extends TestBase {

    private String specialityInput = "Dentist";
    Specialties specialties;
    SpecialtiesPage specialtiesPage;


    @BeforeClass
    @Step("Open Speciality page")
    public void beforeClass() {
        specialties = new Specialties();
        specialtiesPage = new SpecialtiesPage(driver);
        specialtiesPage.openPage();
        specialtiesPage.clickAddSpecialitiestButton();
    }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Step("Add Speciality = {specialityInput}")
    public void verifyAddNewSpecialist() {
        specialtiesPage.addSpeciality(specialityInput);

        assertFalse(specialtiesPage.verifySaveBtnIsDisabled());
        assertThat(specialtiesPage.getSpecTypeList()).contains(specialityInput);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validation fields upon Creation")
    public void verifySpecialisttNameIsRequired() {
        specialtiesPage.ifButtonDisabledPressAdd();
        specialtiesPage.addSpeciality("");
        assertFalse(specialtiesPage.verifySaveBtnIsDisabled());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Step ("Verify that Speciality {specialityInput} is deleted")
    public void specialityDeletePetByName() {
        specialtiesPage.pressDeleteSpecialialities(specialityInput);
        assertFalse(specialtiesPage.getSpecTypeList().contains(specialityInput));
    }
}

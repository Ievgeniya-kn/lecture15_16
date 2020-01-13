package com.petclinic;

import com.petclinic.pageobject.SpecialtiesPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import static org.assertj.core.api.Assertions.assertThat;

public class NewSpecialityTest extends TestBase{

    private String specialityInput = "Dentist";
    Specialties specialties;
    SpecialtiesPage specialtiesPage;


    @BeforeClass
    public void beforeClass() {
        specialties = new Specialties();
        specialtiesPage = new SpecialtiesPage(driver);
        specialtiesPage.openPage();
        specialtiesPage.clickAddSpecialitiestButton();
    }


    @Test
    public void verifyAddNewSpecialist()    {
        specialtiesPage.addSpeciality(specialityInput);

        assertFalse(specialtiesPage.verifySaveBtnIsDisabled());
        assertThat(specialtiesPage.getSpecTypeList()).contains(specialityInput);
    }

    @Test
    public void verifySpecialisttNameIsRequired()    {
        specialtiesPage.addSpeciality("");
        assertTrue(specialtiesPage.verifySaveBtnIsDisabled());
    }

    @Test
    public void petNameDeletePetByName() {
        specialtiesPage.pressDeleteSpecialialities(specialityInput);
        assertFalse(specialtiesPage.getSpecTypeList().contains(specialityInput));
    }
}

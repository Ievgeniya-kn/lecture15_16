package com.petclinic;

import com.petclinic.pageobject.SpecialtiesPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class NewSpecialityTest extends TestBase{

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
        specialtiesPage.addSpeciality("Dentist");

        assertFalse(specialtiesPage.verifySaveBtnIsDisabled());
    }

    @Test
    public void verifySpecialisttNameIsRequired()    {
        specialtiesPage.addSpeciality("");
        assertTrue(specialtiesPage.verifySaveBtnIsDisabled());
    }
}

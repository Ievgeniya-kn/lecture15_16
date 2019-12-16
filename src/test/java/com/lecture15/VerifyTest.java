package com.lecture15;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class VerifyTest {

    private int a=2;
    private int b=4;

    @Test
    public void verifyMupliplication() {
        assertEquals(a*b,8);
    }

    @Test
    public void verifyDivision() {
        assertEquals(b/a,2);
    }
}

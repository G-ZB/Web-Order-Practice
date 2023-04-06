package com.academy.techcenture;

import com.academy.techcenture.driver.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected WebDriver driver;


    @BeforeTest
    public void beforeTest() {
        Driver.getReport();
    }

    @BeforeMethod
    public void setUpBefore() {
        driver = Driver.getDriver();
    }


    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
    }


    @AfterTest
    public void afterTest() {Driver.closeReport();}

}

package com.academy.techcenture;

import com.academy.techcenture.pages.Login;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{



    @Test(priority = 1)
    public void loginPositiveTest() {

        Login loginPage = new Login(driver);
        loginPage.navigateToLoginPage();
        loginPage.login();


    }
    @Test(priority = 2)
    public void loginNegativeTest() {

        Login loginPage = new Login(driver);
        loginPage.navigateToLoginPage();
        loginPage.loginNegative();
    }

}

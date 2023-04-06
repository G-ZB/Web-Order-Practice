package com.academy.techcenture;

import com.academy.techcenture.pages.Login;
import com.academy.techcenture.pages.Product;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @Test
    public void verifyAllProductsTest() throws InterruptedException {

        Login loginPage = new Login(driver);
        loginPage.navigateToLoginPage();
        loginPage.login();

        Product productPage = new Product(driver);
        productPage.clickOnViewAllProductsLink();
        productPage.verifyProductTable();

        Thread.sleep(3000);

        productPage.logout();








    }

}

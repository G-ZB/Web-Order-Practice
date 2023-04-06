package com.academy.techcenture.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertTrue;

public class Base {

//This class will include all common elements to all pages

    protected WebDriver driver; //instead private let make it protected so that it can be inhiretanted

    protected Base(WebDriver driver) {  //instead private let make it protected so that it can be inhiretanted
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "Logo_Info")
    protected WebElement logoInfo;


    @FindBy(linkText = "Logout")
    protected WebElement logout;


    @FindBy(tagName = "h1")
    protected WebElement logo;

    @FindBy(id = "ctl00_menu")
    protected WebElement menuItems;

    public void logout() {
        assertTrue(logout.isDisplayed(), "Logout Links is not displayed");
        logout.click();
    }

    public void clickOnViewAllOrdersLink() {
        WebElement viewAllOrders = menuItems.findElement(By.linkText("View all orders"));
//        assertTrue(viewAllOrders.isDisplayed(), "view All Orders is not displayed");
        viewAllOrders.click();
    }
    public void clickOnViewAllProductsLink() {
        WebElement viewAllProducts = menuItems.findElement(By.linkText("View all products"));
        assertTrue(viewAllProducts.isDisplayed(), "view All Products is not displayed");
        viewAllProducts.click();
    }

    public void clickOnOrderLink() {
        WebElement Order = menuItems.findElement(By.linkText("Order"));
        assertTrue(Order.isDisplayed(), "Order is not displayed");
        Order.click();
    }

}

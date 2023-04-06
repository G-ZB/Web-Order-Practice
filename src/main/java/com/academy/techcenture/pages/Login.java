package com.academy.techcenture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.academy.techcenture.config.ConfigReader.getProperty;

public class Login {


    private WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // this line will initialize all elements
    }


    //    find web elements
    @FindBy(id = "ctl00_MainContent_username")
    private WebElement userNameInput;

    @FindBy(id = "ctl00_MainContent_password")
    private WebElement passWordInput;

    @FindBy(id = "ctl00_MainContent_login_button")
    private WebElement loginBtn;

    @FindBy(id = "ctl00_MainContent_status")
    private WebElement warningMessage;

    //this Method will navigate the driver to Login page
    public void navigateToLoginPage(){
        driver.get(getProperty("url"));
//        assertEquals(driver.getTitle(), "Web Orders Login", "Web Order Page title is wrong");

    }

    //this method is used to login to the application
// it gets the username and password from the config file
    public void login() {
        userNameInput.sendKeys(getProperty("username"));
        passWordInput.sendKeys(getProperty("password"));
        loginBtn.click();
//        assertTrue(loginBtn.isEnabled(), "Login Btn not enabled");

    }

    public void loginNegative(){
        userNameInput.sendKeys("Tester");
        passWordInput.sendKeys("dd");
        loginBtn.click();
//        assertTrue(warningMessage.isEnabled(), "warning message is not displayed");


    }

}

package com.academy.techcenture.pages;

import com.academy.techcenture.config.ConfigReader;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.academy.techcenture.utils.CommonUtils.generateRandomNumber;
import static com.academy.techcenture.utils.CommonUtils.generateRandomStringNumber;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class PlaceOrder extends Base {


    private Faker faker;
    private Random random;


    HashMap map = new HashMap<>();
    public PlaceOrder(WebDriver driver)  {
        super(driver);
        faker = new Faker(new Locale("en-us"));
//        PageFactory.initElements(driver, this); // this line will initialize all elements

    }
    @FindBy(xpath = "//h2[contains(text(),'Order')]")
    private WebElement orderPageHeader;


    @FindBy(xpath = "//h3[text()='Product Information']")
    private WebElement productInfoHeader;


    @FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
    private WebElement productSelect;


    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    private WebElement randomQuantity;

    @FindBy(xpath = "//input[@value='Calculate']")
    private WebElement calculateBtn;

    @FindBy(xpath = "//h3[text()='Address Information']")
    private WebElement addressInfoHeader;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtName")
    private WebElement customerNameInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox2")
    private WebElement customerStreetInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox3")
    private WebElement customerCityInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox4")
    private WebElement customerStateAbbInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox5")
    private WebElement customerZipByStateInput;

    @FindBy(xpath = "//h3[text()='Payment Information']")
    private WebElement paymentInformation;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
    private WebElement cardNumberInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
    private WebElement expirationDateInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    private WebElement processBtn;


    @FindBy(xpath = "//input[@value='Reset']")
    private WebElement resetBtn;

    @FindBy(tagName = "strong")
    private WebElement confirmationMessage;

    @FindBy(xpath = "//[Text()='New order has been successfully added.']")
    private WebElement confirmationNewOrderMessage;



    // Methods
    public void headersDisplay() {
        assertTrue(orderPageHeader.isDisplayed(), "Order Page header is not displayed");
        assertTrue(productInfoHeader.isDisplayed(), "product InfoHeader not displayed");
        assertTrue(addressInfoHeader.isDisplayed(), "Address Information not displayed");
        assertTrue(paymentInformation.isDisplayed(), "Payment Information not displayed");



    }

    public void fillOutProductInformation() {

        Select select = new Select(productSelect);
        select.selectByIndex(generateRandomNumber(0, 2));
        String productSelected = select.getFirstSelectedOption().getText();
        ConfigReader.setProperty("Product", productSelected);


        String quantity = Integer.toString(generateRandomNumber(5, 10));
        ConfigReader.setProperty("#", quantity);
        randomQuantity.sendKeys(Integer.toString(generateRandomNumber(5, 10)));


        assertTrue(calculateBtn.isEnabled(), "calculateBtn not enabled");
        calculateBtn.click();
    }

    public void fillOutAdressInformation()  {

        String fullName = faker.name().fullName();
        ConfigReader.setProperty("Name", fullName);
        customerNameInput.sendKeys(faker.name().fullName());

        String streetAddress = faker.address().streetAddress();
        ConfigReader.setProperty("Street", streetAddress);
        customerStreetInput.sendKeys(faker.address().streetAddress());

        String cityName = faker.address().cityName();
        ConfigReader.setProperty("City", cityName);
        customerCityInput.sendKeys(faker.address().cityName());

        String stateAbbr = faker.address().stateAbbr();
        ConfigReader.setProperty("state", stateAbbr);
        customerStateAbbInput.sendKeys(faker.address().stateAbbr());

        String zipCodeByState = faker.address().stateAbbr();
        ConfigReader.setProperty("Zip", zipCodeByState);
        customerZipByStateInput.sendKeys(faker.address().zipCodeByState(faker.address().stateAbbr()));

    }

    public void fillOutPaymentInformation() {

        int randomCardIndex = generateRandomNumber(0, 2);
        WebElement selectedCard = driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_" + randomCardIndex));
        selectedCard.click();
        String cardName = selectedCard.getAttribute("value");
        ConfigReader.setProperty("Card", cardName);





        String randomCardNumber = (randomCardIndex == 2 ? generateRandomStringNumber(15) : generateRandomStringNumber(16));
        cardNumberInput.sendKeys(randomCardNumber);
        ConfigReader.setProperty("Card Number", randomCardNumber);


        int randomMonth = generateRandomNumber(1, 12);
        String randomDate = (randomMonth <= 9 ? "0" + randomMonth : Integer.toString(randomMonth)) + "/" + generateRandomNumber(23,26);
        expirationDateInput.sendKeys(randomDate);
        ConfigReader.setProperty("Exp", randomDate);



    }

    public void recordRealTimeOfOrder()  {

        SimpleDateFormat gmtDateFormat = new SimpleDateFormat("MM/dd/MMMM");
        gmtDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateOfOrder = gmtDateFormat.format(new Date());
        ConfigReader.setProperty("date", dateOfOrder);


    }
    public void placeNewOrder() {

        fillOutProductInformation();
        fillOutAdressInformation();
        fillOutPaymentInformation();
        processBtn.click();
        assertTrue(confirmationMessage.isDisplayed(), "Text message is missing");
        assertEquals(confirmationMessage.getText().trim(), "New order has been successfully added.");

//        assertTrue(resetBtn.isEnabled(), "resetButton not enabled");
//        resetBtn.click();
    }

    public void validateOrderPlaced()  {

        List<WebElement> columns = driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[1]/th"));
        List<WebElement> firstRow = driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]"));

        for(int i=1; i<firstRow.size() - 1;i++){
            String headers = columns.get(i).getText().trim().toLowerCase();

            String expectedColumnData = ConfigReader.getProperty(headers);
            String actualColumnData = firstRow.get(i).getText().trim();

            assertEquals(actualColumnData,expectedColumnData, "Not matching data" );


        }

    }
}

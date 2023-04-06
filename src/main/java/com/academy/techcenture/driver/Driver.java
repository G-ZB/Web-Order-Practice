package com.academy.techcenture.driver;

import com.academy.techcenture.config.ConfigReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {


//Created Class Driver. In this class I will have all elements (proprieties and methods and constructor)


    //Declare a propriety(Variable) called driver of type WebDriver. since this variable is private static it can not be used in a constructor
    private static WebDriver driver;
    protected static ExtentReports reports;
    protected ExtentTest extentTest;

    //I will also create an empty (because the declared propriety is static) private constructor, so that the class Driver can not be instantiated (Driver driver = new Driver())
    private Driver() {

    }

//Will create Methods to get the Browsers (Chrome, Edge, Safari,...): Since we will be returning a browser, the Method return will be of type WebDriver

    public static WebDriver getDriver(){


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");


        String browser = ConfigReader.getProperty("browser");
        String implicitWait = ConfigReader.getProperty("implicitWait");
        String loadPage = ConfigReader.getProperty("pageLoadTime");


        switch(browser){

            case "chrome":
                WebDriverManager.chromedriver().setup();
                WebDriver driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(implicitWait)));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(loadPage)));
                driver.manage().window().maximize();
                return driver;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
                driver.manage().window().maximize();
                return driver;

            case "firefox":

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
                driver.manage().window().maximize();
                return driver;

            default:
                throw new RuntimeException("No driver found");
        }

    }


    public static void getReport(){

        reports = new ExtentReports(System.getProperty("user.dir")+"test-output/ExtentReports.html");
        reports.addSystemInfo("OS NAME",System.getProperty("os.name"));
        reports.addSystemInfo("ENGINEER",System.getProperty("user.name"));
        reports.addSystemInfo("ENVIRONMENT", "QA");
        reports.addSystemInfo("JAVA VERSION",System.getProperty("java.version"));

    }

    public static void closeReport(){

        reports.flush();
        reports.close();
    }
    public static void quitDriver() {

        if (driver != null) {
            driver.quit();

        }
    }
}

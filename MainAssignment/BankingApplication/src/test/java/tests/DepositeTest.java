package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.CustomerLogin;
import Needs.ExtentReporter;
import pages.HomePage;

@Test(priority = 3)
public class DepositeTest  {

    WebDriver driver;
    WebDriverWait wait;
    String url = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";
    CustomerLogin custLogin;
    HomePage homePage;

    ExtentReports extent = ExtentReporter.extentReporterGenerator();
    ExtentTest test;

    Logger log = LogManager.getLogger(AddCustomerTest.class.getName());

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\omcv\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        log.info("Deposit test started");
    }

    // deposit some amount - scenario
    public void deposit() throws Exception {

       // test = extent.createTest("deposite");

        homePage = new HomePage(driver);
        homePage.clickCustomerLogin();

        custLogin = new CustomerLogin(driver);
        custLogin.login();
        custLogin.deposit();

        //test.log(Status.PASS, "Successful");
        //extent.flush();
        log.info("deposit test passed");
    }

    @AfterClass
    public  void  closeDriver(){
        driver.quit();
    }

}

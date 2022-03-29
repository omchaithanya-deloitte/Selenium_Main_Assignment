package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.BankManagerLogin;
import Needs.ExtentReporter;
import pages.HomePage;

@Test(priority = 1)
public class AddCustomerTest{

    WebDriver driver;
    WebDriverWait wait;
    String url = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";
    BankManagerLogin bankmngLogin;
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
        log.info("Add Customer test started");
    }

    @Test  // adding a new customer - scenario
    public void addingCustomer() throws Exception {

        //test = extent.createTest("Adding customer");

        homePage = new HomePage(driver);
        homePage.clickBankManagerLogin();

        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@ng-click=\"addCust()\"]")));

        bankmngLogin = new BankManagerLogin(driver);
        bankmngLogin.addCustomer();

        //test.log(Status.PASS, "Successful");
        //extent.flush();
        log.info("Add Customer test passed");
    }

    @AfterClass
    public  void  closeDriver(){
        driver.quit();
    }

}

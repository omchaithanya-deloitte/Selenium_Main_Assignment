package pages;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;

import static Needs.ScreenShots.takeSnapShot;

public class BankManagerLogin {

    WebDriver driver;
    WebDriverWait wait;

    public BankManagerLogin(WebDriver driver) {
        this.driver = driver;
    }

    // add a customer
    public void addCustomer() throws Exception {
        WebElement element1 = driver.findElement(By.xpath("//button[@ng-click=\"addCust()\"]"));
        element1.click();    // click on add customer button

        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@ng-model=\"fName\"]")));

        WebElement Fname = driver.findElement(By.xpath("//input[@ng-model=\"fName\"]"));    // x-path for first name
        WebElement Lname = driver.findElement(By.xpath("//input[@ng-model=\"lName\"]"));    // x-path for last name
        WebElement Pcode = driver.findElement(By.xpath("//input[@ng-model=\"postCd\"]"));   // x-path for postal code

        // reading data from excel file
        String excelPath = "C:\\Users\\omcv\\IdeaProjects\\BankingApplication\\data.xlsx";
        FileInputStream file = new FileInputStream(excelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        XSSFRow row = null;
        XSSFCell cell = null;
        String fName = null;
        String lName = null;
        String pCOde = null;

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            for (int j = 0; j <= row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (j == 0) {
                    fName = cell.getStringCellValue();
                }
                if (j == 1)
                    lName = cell.getStringCellValue();

                if (j == 2)
                    pCOde = String.valueOf(cell.getNumericCellValue());
            }
            Fname.sendKeys(fName);  // send first name to input
            Lname.sendKeys(lName);  // send last name to input
            Pcode.sendKeys(pCOde);  // send postal code to input

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Add Customer\"]")));

            WebElement AddButton = driver.findElement(By.xpath("//button[text()=\"Add Customer\"]"));
            AddButton.click();  // click on add customer after filling data
            Thread.sleep(1500);

            driver.switchTo().alert().accept();   // click ok at alert
            takeSnapShot(driver, "C://Users//omcv//Downloads//My Assignments//Selenium//Assignments//Sucess ScreenShots//addCustomer.png");
        }

    }

    // open an account for a customer
    public void openAccount() throws Exception {

        WebElement element2 = driver.findElement(By.xpath("//button[@ng-click=\"openAccount()\"]"));
        element2.click();   // click on open account button

        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id=\"userSelect\"]")));

        WebElement element3 = driver.findElement(By.xpath("//select[@id=\"userSelect\"]"));
        Select dropdown = new Select(element3);     // select a user from drop down
        dropdown.selectByVisibleText("Harry Potter");

        WebElement element4 = driver.findElement(By.xpath("//select[@id=\"currency\"]"));
        Select dropdown1 = new Select(element4);
        dropdown1.selectByVisibleText("Rupee");     // select "Rupee" from drop down

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Process\"]")));

        WebElement element5 = driver.findElement(By.xpath("//button[text()=\"Process\"]"));
        element5.click();    // click on Process button

        driver.switchTo().alert().accept();   // click ok at alert
        takeSnapShot(driver, "C://Users//omcv//Downloads//My Assignments//Selenium//Assignments//Sucess ScreenShots//openAccount.png");
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void clickBankManagerLogin(){
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Bank Manager Login\"]"))); // waiting until "BankManagerLogin" is visible

        WebElement element1 = driver.findElement(By.xpath("//button[text()=\"Bank Manager Login\"]"));
        element1.click();    // click on BankManagerLogin
    }

    public void clickCustomerLogin(){
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Customer Login\"]")));  // waiting until "CustomerLogin" is visible

        WebElement element2 = driver.findElement(By.xpath("//button[text()=\"Customer Login\"]"));
        element2.click();    // click on CustomerLogin
    }
}

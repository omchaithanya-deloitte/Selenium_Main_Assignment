package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static Needs.ScreenShots.takeSnapShot;

public class CustomerLogin {
    WebDriver driver;
    WebDriverWait wait;

    public CustomerLogin(WebDriver driver){
        this.driver = driver;
    }

    // login function for customer login page
    public void login(){
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name=\"userSelect\"]")));

        WebElement element1 = driver.findElement(By.xpath("//select[@name=\"userSelect\"]"));
        Select dropdown = new Select(element1);
        dropdown.selectByVisibleText("Harry Potter");   // select a user from drop down

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Login\"]")));

        WebElement element2 = driver.findElement(By.xpath("//button[text()=\"Login\"]"));
        element2.click();   // click on Login button

    }

    // function to deposit amount
    public void deposit() throws Exception {
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@ng-click=\"deposit()\"]")));

        WebElement element1 = driver.findElement(By.xpath("//button[@ng-click=\"deposit()\"]"));
        element1.click();  // select deposit

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@ng-model=\"amount\"]")));

        WebElement element2 = driver.findElement(By.xpath("//input[@ng-model=\"amount\"]"));
        element2.sendKeys("1000");  // send 1000 as amount into input

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]")));

        WebElement element3 = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]"));
        String amountBefore = element3.getText();   // balance before deposit

        WebElement element4 = driver.findElement(By.xpath("//button[text()=\"Deposit\"]"));
        element4.click();    // click on deposit

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]")));

        String amountAfter = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]")).getText(); // balance after deposit

        Assert.assertNotEquals(amountBefore,amountAfter);    // verify if balance is updated
        System.out.println("Amount has been updated after deposite.");
        takeSnapShot(driver, "C://Users//omcv//Downloads//My Assignments//Selenium//Assignments//Sucess ScreenShots//deposite.png");
    }

    // function for withdraw
    public void withdrawl() throws Exception {
        // first deposit then withdraw
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@ng-click=\"deposit()\"]")));

        WebElement element1 = driver.findElement(By.xpath("//button[@ng-click=\"deposit()\"]"));
        element1.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@ng-model=\"amount\"]")));

        WebElement amount_deposite = driver.findElement(By.xpath("//input[@ng-model=\"amount\"]"));
        amount_deposite.sendKeys("1000");

        WebElement element3 = driver.findElement(By.xpath("//button[text()=\"Deposit\"]"));
        element3.click();

        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@ng-click=\"withdrawl()\"]")));

        WebElement element4 = driver.findElement(By.xpath("//button[@ng-click=\"withdrawl()\"]"));
        element4.click(); // select withdrawl

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Withdraw\"]")));

        WebElement new_element = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]"));
        String amount_before = new_element.getText(); // balance before withdrawing

        WebElement amount_withdraw = driver.findElement(By.xpath("//input[@ng-model=\"amount\"]"));
        amount_withdraw.sendKeys("500");  // send 500 as amount into input

        WebElement element7 = driver.findElement(By.xpath("//button[text()=\"Withdraw\"]"));
        element7.click();   // click on withdraw

        Thread.sleep(1000);
        WebElement new_element1 = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]"));
        String amount_after = new_element1.getText();   // balance after withdrawal

        Assert.assertNotEquals(amount_before, amount_after);   // verify if balance is updated after withdrawal
        System.out.println("Withdraw successful and balance updated.");

        takeSnapShot(driver, "C://Users//omcv//Downloads//My Assignments//Selenium//Assignments//Sucess ScreenShots//withdrawl.png");

    }

    // function for verifying error message when withdraw more amount than in balance
    public void withdrawMoreThanBalance() throws Exception {

        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@ng-click=\"withdrawl()\"]")));

        WebElement element1 = driver.findElement(By.xpath("//button[@ng-click=\"withdrawl()\"]"));
        element1.click();  // select withdrawl

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@ng-model=\"amount\"]")));

        WebElement element2 = driver.findElement(By.xpath("//input[@ng-model=\"amount\"]"));
        element2.sendKeys("10000");  // send 10000 as amount into input

        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/button")).click();  // click withdraw button

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"Transaction Failed. You can not withdraw amount more than the balance.\"]")));

        String ExpectedErrorMsg = "Transaction Failed. You can not withdraw amount more than the balance.";   // expected error message
        WebElement element3 = driver.findElement(By.xpath("//span[text()=\"Transaction Failed. You can not withdraw amount more than the balance.\"]"));
        String ActualErrorMsg = element3.getText();   // actual error message

        Assert.assertEquals(ExpectedErrorMsg,ActualErrorMsg);    // verify error message
        System.out.println("Error message verified.");
        takeSnapShot(driver, "C://Users//omcv//Downloads//My Assignments//Selenium//Assignments//Sucess ScreenShots//WithdrawMoreThanBalance.png");
    }

    // function to verify transactions
    public void verifyTransaction() throws Exception {
        // first deposit and withdraw some amount to verify transactions

        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@ng-click=\"deposit()\"]")));

        WebElement element1 = driver.findElement(By.xpath("//button[@ng-click=\"deposit()\"]"));
        element1.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@ng-model=\"amount\"]")));

        WebElement amount_deposite = driver.findElement(By.xpath("//input[@ng-model=\"amount\"]"));
        amount_deposite.sendKeys("1000");

        WebElement element3 = driver.findElement(By.xpath("//button[text()=\"Deposit\"]"));
        element3.click();

        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@ng-click=\"withdrawl()\"]")));

        WebElement element4 = driver.findElement(By.xpath("//button[@ng-click=\"withdrawl()\"]"));
        element4.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Withdraw\"]")));

        WebElement amount_withdraw = driver.findElement(By.xpath("//input[@ng-model=\"amount\"]"));
        amount_withdraw.sendKeys("500");

        WebElement element7 = driver.findElement(By.xpath("//button[text()=\"Withdraw\"]"));
        element7.click();

        Thread.sleep(3500);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/span")));

        WebElement element8 = driver.findElement(By.xpath("//button[@ng-click=\"transactions()\"]"));
        element8.click();   // select transactions

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"anchor0\"]/td[2]")));

        // expected deposit and withdrawal amounts
        String ExpectedDeposite = "1000";
        String ExpectedWithdrawl = "500";

        // actual withdrawal and deposit amounts
        String ActualDeposite = driver.findElement(By.xpath("//*[@id=\"anchor0\"]/td[2]")).getText();
        String ActualWithdrawl = driver.findElement(By.xpath("//*[@id=\"anchor1\"]/td[2]")).getText();

        // verify transactions
        Assert.assertEquals(ActualDeposite, ExpectedDeposite);
        Assert.assertEquals(ActualWithdrawl, ExpectedWithdrawl);

        System.out.println("Amount verified successfully");
    }
}

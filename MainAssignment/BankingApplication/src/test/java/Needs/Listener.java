package Needs;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;

public class Listener implements ITestListener {
    WebDriver driver;
    String path = "C://Users//omcv//Downloads//My Assignments//Selenium//Assignments//Failure ScreenShots//failure.jpg";
    public void onTestFailure(ITestResult result){

        test.log(Status.FAIL, "Failed");
        extent.flush();

        ITestListener.super.onTestFailure(result);
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File des = new File((path));
        try {
            FileUtils.copyFile(src,des);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    ExtentReports extent = ExtentReporter.extentReporterGenerator();
    ExtentTest test;
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result){
        test.log(Status.PASS, "Successful");
        extent.flush();
    }
}

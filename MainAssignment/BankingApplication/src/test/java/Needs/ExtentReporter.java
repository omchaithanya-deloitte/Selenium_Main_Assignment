package Needs;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReporter {

    static ExtentReports extent;
    public static ExtentReports extentReporterGenerator(){
        String path = "C://Users//omcv//IdeaProjects//BankingApplication//ExtentReports//report.html";
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(path);
        extent = new ExtentReports();
        extent.attachReporter(extentHtmlReporter);
        return extent;
    }
}

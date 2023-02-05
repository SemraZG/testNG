package techproed.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listeners implements ITestListener {

    /*
    This class is used to implement the Listeners
    ITestListener is an interface
    We use this interface to customize testNG framework
    onStart,onFinish,onTestStart,onTestFinish,onTestSuccess,onTestFailure are special method names are used to LISTEN the test methods
    We especially use listeners for adding a special condition such as test pass, fail, or skipped
     */
    protected ExtentReports extentReports;
    protected ExtentHtmlReporter extentHtmlReporter;
    protected ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {
        System.out.println("onStart - Execute ONCE before ALL tests : "+context.getName());
        //      REPORT  PATH
        String currentTime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String path = System.getProperty("user.dir")+"/test-output/reports/"+currentTime+"html_report.html";

//        creating HTML report in the path
        extentHtmlReporter = new ExtentHtmlReporter(path);
//        creating extent reports object for generating the Entire reports with configuration
        extentReports = new ExtentReports();

//        ***************
//        adding custom System Info
        extentReports.setSystemInfo("Test Environment","Regression");
        extentReports.setSystemInfo("Application","TechProEd");
        extentReports.setSystemInfo("Browser","Chrome");
        extentReports.setSystemInfo("Team","Eagles");
        extentReports.setSystemInfo("SQA","John Fox");
        extentReports.setSystemInfo("Sprint Number","SP205");

//        adding more custom info
        extentHtmlReporter.config().setReportName("TechProEd home page");
        extentHtmlReporter.config().setDocumentTitle("TechProEd extent reports");
//        ********************
        //        DONE WITH CONFIGURATION
        extentReports.attachReporter(extentHtmlReporter);
//        SUMMARY: Extent Reports and Extent HTML Reporter is used to add custom information on the report and create the report in a PATH

//        REPORT IS DONE. NOW CREATING EXTENT TEST TO LOG INFO IN THE TEST CASE
//        Creating extent test
        extentTest= extentReports.createTest("My Extent Reporter","Regression Test Report");

    }
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("onFinish - Execute ONCE after ALL tests : "+context.getName());
        //        generating the report
        extentReports.flush();
    }
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("onTestStart - Execute ONCE before EACH @Test : "+result.getName());

    }
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("onTestSuccess - Execute AFTER EACH PASSED @Test : "+result.getName());
    }
    @Override
    public void onTestFailure(ITestResult result) {
//        System.out.println("onTestFailure - Execute AFTER EACH FAILED @Test : "+result.getName());
        try {
            ReusableMethods.getScreenshot("TEST CASE FAILED : " + result.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("onTestSkipped - Execute AFTER EACH SKIPPED @Test : "+result.getName());
    }
}

package tasks;

import java.util.Arrays;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentListener implements ITestListener{

	
	static Date d = new Date();
    static String fileName = "ExtentReport_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
 
    static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\reports\\"+fileName);
     
    public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
     
 
    public void onTestStart(ITestResult result) {
     
        ExtentTest test = extent.createTest(result.getTestClass().getName()+"     @TestCase : "+result.getMethod().getMethodName());
        testReport.set(test);
    }
 
    public void onTestSuccess(ITestResult result) {
         
        String methodName=result.getMethod().getMethodName();
        String logText="<b>"+"TEST CASE:- "+ methodName+ " - PASSED"+"</b>";      
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        testReport.get().pass(markup);
         
    }
 
    public void onTestFailure(ITestResult result) {
        String failureLogg="<b>"+"TEST CASE FAILED :- " +  result.getMethod().getMethodName()+"<b>";
        Markup markup = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
        testReport.get().log(Status.FAIL, markup);
        testReport.get().log(Status.FAIL, result.getThrowable().getMessage());
 
    }
    public void onFinish(ITestContext context) {
    	 
        if (extent != null) {
            extent.flush();
        }
    }
    
    public void logMessage(String message) {
    	testReport.get().log(Status.INFO,message);
    }
}

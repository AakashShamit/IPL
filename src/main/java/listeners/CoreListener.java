package listeners;

import org.testng.*;
import org.testng.xml.*;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CoreListener implements ISuiteListener, ITestListener, IInvokedMethodListener, IAlterSuiteListener {

    @Override
    public void onStart(ISuite suite) {
        log.info("EXECUTION STARTED FOR SUITE - " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        log.info("EXECUTION FINISHED FOR SUITE - " + suite.getName());
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {
        log.info(result.getInstanceName() + " - " + result.getMethod().getMethodName() + "");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        log.info(result.getInstanceName() + " - " + result.getMethod().getMethodName() + "");
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info(result.getInstanceName() + " - " + result.getMethod().getMethodName() + " Started --->");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(result.getInstanceName() + " - " + result.getMethod().getMethodName() + " - Test Success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info(result.getInstanceName() + " - " + result.getMethod().getMethodName() + " - Test Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info(result.getInstanceName() + " - " + result.getMethod().getMethodName() + " - Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
        log.info(context.getName() + " - " + " - Execution Started");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info(context.getName() + " - " + " - Execution finished");
    }

    @Override
    public void alter(List<XmlSuite> list) {
    }

}
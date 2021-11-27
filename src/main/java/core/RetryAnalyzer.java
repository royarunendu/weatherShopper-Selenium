package core;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    int counter = 0;
    int retryLimit = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(counter < retryLimit)
        {
            System.out.println("Starting retry..."+counter);
            System.out.println("Reloading the page...");
            counter++;
            return true;
        }
        return false;
    }
}

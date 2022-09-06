package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {


    public void onTestFailure(ITestResult result) {
        System.out.println("--- FAILED --- FAILED --- FAILED --- : " + result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("--- SUCCESS --- SUCCESS --- SUCCESS --- : " + result.getName());
    }


}

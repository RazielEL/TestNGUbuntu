package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TheInternetLinks extends CommonMethods {

    public static void main (String[] args) throws AWTException, IOException {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
        driver.get("http://localhost:7080/");

        List<WebElement> contentLinksNames = driver.findElements(By.xpath("//*[@id=\"content\"]/ul/li"));
        List<WebElement> contentLinks = driver.findElements(By.xpath("//*[@id=\"content\"]/ul/li/a"));
        List<String> endpoints = new ArrayList<>();
        System.out.println(contentLinks.size());


        for(int i = 0; i< contentLinks.size(); i++){
            endpoints.add(contentLinks.get(i).getAttribute("href").substring(21));

        }

        ExcelSaverTheInternetLinks(contentLinksNames, contentLinks, endpoints);




    }
}

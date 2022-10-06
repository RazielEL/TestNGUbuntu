package featureA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CommonMethods;

import java.awt.*;
import java.util.List;

public class Scenario2 extends CommonMethods {


    @Test
    public void EntryAdd() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver,30);
        SoftAssert sa = new SoftAssert();

        WebElement EApage = driver.findElement(By.linkText("Entry Ad"));
        EApage.click();

        WebElement closeButton = driver.findElement(By.xpath("//*[@id=\"modal\"]/div[2]/div[3]/p"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"modal\"]/div[2]/div[1]/h3")));

        WebElement modalTitle = driver.findElement(By.xpath("//*[@id=\"modal\"]/div[2]/div[1]/h3"));
        System.out.println("Is modal Title displayed? : " + modalTitle.isDisplayed());
        System.out.println(modalTitle.getText());
        String modalTitletext = "THIS IS A MODAL WINDOW";

        WebElement modalBody = driver.findElement(By.xpath("//*[@id=\"modal\"]/div[2]/div[2]/p"));
        System.out.println("Is modal Body displayed? : " + modalBody.isDisplayed());
        System.out.println(modalBody.getText());
        String modalBodytext = "It's commonly used to encourage a user to take an action " +
                "(e.g., give their e-mail address to sign up for something or disable their ad blocker).";

        sa.assertEquals(modalBodytext,modalBody.getText());
        sa.assertEquals(modalTitletext,modalTitle.getText());

        sa.assertAll();

    }

    @Test
    public void ExitIntent() throws InterruptedException, AWTException {

        WebDriverWait wait = new WebDriverWait(driver,30);
        SoftAssert sa = new SoftAssert();

        WebElement EIPage = driver.findElement(By.linkText("Exit Intent"));
        EIPage.click();
        Thread.sleep(2000);

        Robot robot = new Robot();
        robot.mouseMove(600,0);
        Thread.sleep(2000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ouibounce-modal\"]/div[2]")));

        List<WebElement> modal = driver.findElements(By.xpath("//*[@id=\"ouibounce-modal\"]/div[2]/div"));
        System.out.println(modal.size());

        for(int i = 0; i<modal.size() ; i++){
            System.out.println(modal.get(i).getText());
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ouibounce-modal\"]/div[2]/div[3]")));
        WebElement closeButton = driver.findElement(By.xpath("//*[@id=\"ouibounce-modal\"]/div[2]/div[3]"));
        closeButton.click();

        System.out.println("Is Close Button displayed? : " + closeButton.isDisplayed());
        sa.assertEquals(true,closeButton.isDisplayed());
        sa.assertAll();

    }

    @Test
    public void FileDownload() throws InterruptedException {
        WebElement fdpage = driver.findElement(By.linkText("File Download"));
        fdpage.click();
        WebElement download = driver.findElement(By.linkText("some-file.txt"));
        Thread.sleep(2000);
        download.click();
        Thread.sleep(2000);

    }






}

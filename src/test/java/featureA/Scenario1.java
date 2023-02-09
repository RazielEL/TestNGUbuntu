package featureA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;


public class Scenario1 extends CommonMethods {

    @Test (groups = "smoke")
    public void DynamicControlsCheckbox() throws InterruptedException {
        SoftAssert sa = new SoftAssert();
        WebElement dcpage = driver.findElement(By.linkText("Dynamic Controls"));
        dcpage.click();
        Thread.sleep(3000);
        WebElement checkbox = driver.findElement(By.xpath("//*[@id=\"checkbox\"]/input"));
        checkbox.click();
        System.out.println(checkbox.isSelected());
        Assert.assertTrue(checkbox.isSelected());

        Thread.sleep(2000);

        WebElement removeButton = driver.findElement(By.xpath("//*[@id=\"checkbox-example\"]/button"));
        removeButton.click();

        Thread.sleep(2000);

        WebElement message = driver.findElement(By.id("message"));
        System.out.println(message.getText());
        String expectedMessage = "It's gone!";

        Assert.assertEquals(message.getText(),expectedMessage);
        Assert.assertTrue(message.isDisplayed());

        WebElement addButton = driver.findElement(By.xpath("//*[@id=\"checkbox-example\"]/button"));
        addButton.click();

        WebElement secondCheckBoxButton = driver.findElement(By.id("checkbox"));

        Thread.sleep(2000);

        System.out.println("Checkbox is displayed again? : " + secondCheckBoxButton.isDisplayed());
        Assert.assertTrue(secondCheckBoxButton.isDisplayed());

        sa.assertAll();

    }
    @Test(groups = "smoke")
    public void DynamicControlsEnableTextbox(){

        WebElement dcpage = driver.findElement(By.linkText("Dynamic Controls"));
        dcpage.click();

        WebElement enable = driver.findElement(By.xpath("//*[@id=\"input-example\"]/button"));
        enable.click();

        String expectedMessage = "It's enabled!";
        WebElement message = driver.findElement(By.id("message"));

        System.out.println("Is expected message shown? : " +message.isDisplayed() + ", " + message.getText() );
        Assert.assertEquals(expectedMessage,message.getText());

        WebElement textbox = driver.findElement(By.xpath("//*[@id=\"input-example\"]/input"));
        textbox.sendKeys("Jestem sobie maly mis");

        String text = "Jestem sobie maly mis";

        System.out.println("Text in textbox: " + textbox.getText());

        SoftAssert sa = new SoftAssert();
        sa.assertAll();

    }

    @Test(groups = "smoke")
    public void DynamicLoading() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver,30);
        SoftAssert sa = new SoftAssert();

        WebElement dynamicLoading = driver.findElement(By.linkText("Dynamic Loading"));
        dynamicLoading.click();

        List<WebElement> lista = new ArrayList<>();
        lista = driver.findElements(By.xpath("//*[@id=\"content\"]/div/a"));

        lista.forEach((punkt) -> System.out.println(punkt.getText()));

        String link1text = "Example 1: Element on page that is hidden";
        String link2text = "Example 2: Element rendered after the fact";

        sa.assertEquals(link1text,lista.get(0).getText());
        sa.assertEquals(link2text,lista.get(1).getText());

        WebElement link1 = lista.get(0);
        link1.click();

        WebElement link2 = lista.get(1);

        WebElement startButton = driver.findElement(By.xpath("//*[@id=\"start\"]/button"));
        startButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        WebElement finish = driver.findElement(By.id("finish"));
        System.out.println(finish.getText());
        String finishText = "Hello World!";

        sa.assertEquals(finish.getText(),finishText);

        driver.navigate().back();
        link2.click();

        WebElement startButton2 = driver.findElement(By.xpath("//*[@id=\"start\"]/button"));
        startButton2.click();

        WebElement finish2 = driver.findElement(By.id("finish"));
        System.out.println(finish2.getText());
        String finish2Text = "Hello World!";
        sa.assertEquals(finish2Text,finish2.getText());
        sa.assertAll();



    }


}

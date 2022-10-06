package featureA;

import utils.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Scenario4 extends CommonMethods {

    @Test (groups = "retest")
    public void LoginPage(){

        WebElement lppage = driver.findElement(By.linkText("Form Authentication"));
        lppage.click();

        WebElement usernameBox = driver.findElement(By.id("username"));
        WebElement passwordBox = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]/button/i"));

        String username = "tomsmith";
        String password = "SuperSecretPassword!";

        usernameBox.sendKeys(username);
        passwordBox.sendKeys(password);
        loginButton.click();

        WebElement greenStripe = driver.findElement(By.id("flash"));
        String greenStripeText = "You logged into a secure area!]";

        SoftAssert sa = new SoftAssert();

        sa.assertEquals(greenStripeText,greenStripe.getText());

        WebElement logoutButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/a/i"));
        logoutButton.click();

        String logoutGreenStripeText = "You logged out of the secure area!";

        WebElement logoutGreenStripe = driver.findElement(By.id("flash"));

        sa.assertEquals(logoutGreenStripeText,logoutGreenStripe.getText());

        sa.assertAll("Asserted!");

    }

}

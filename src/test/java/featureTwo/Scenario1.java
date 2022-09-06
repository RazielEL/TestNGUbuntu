package featureTwo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;

public class Scenario1 extends CommonMethods {


    @Test(groups = "regression")
    public void FileUpload() throws InterruptedException {

        SoftAssert sa = new SoftAssert();

        WebElement fupage = driver.findElement(By.linkText("File Upload"));
        fupage.click();

        WebElement submitButton = driver.findElement(By.id("file-submit"));
        WebElement choseFile = driver.findElement(By.id("file-upload"));
        choseFile.sendKeys("C:\\Users\\pawlo\\Downloads\\some-file.txt");
        Thread.sleep(1000);
        submitButton.click();

        WebElement uploadedFile = driver.findElement(By.id("uploaded-files"));
        String uploadedFileText = "some-file.txt";
        System.out.println("Name of the file: some-file.txt, uploaded file: " + uploadedFile.getText());

        sa.assertTrue(uploadedFile.isDisplayed());
        sa.assertAll();

    }
    @Test
    public void FloatingMenu(){

        SoftAssert sa = new SoftAssert();

        WebElement fmpage = driver.findElement(By.linkText("Floating Menu"));
        fmpage.click();
        boolean flag = false;

        List<WebElement> floatingMenu = driver.findElements(By.xpath("//*[@id=\"menu\"]/ul/li"));
        System.out.println(floatingMenu.size());

        List<String> floatingMenuText = new ArrayList<>();
        floatingMenuText.add("Home");
        floatingMenuText.add("News");
        floatingMenuText.add("Contact");
        floatingMenuText.add("About");

        for(int i = 0; i<floatingMenu.size(); i++) {
            System.out.println("Jedno z drugim: " + floatingMenu.get(i).getText() + " = " + floatingMenuText.get(i));
            if (floatingMenu.get(i).getText().equals(floatingMenuText.get(i))) {
                flag = true;
            } else {
                flag = false;
            }
            sa.assertEquals(floatingMenu.get(i).getText(), floatingMenuText.get(i));
        }

        if (flag) {
            System.out.println("All good");
        } else {
            System.out.println("No good");
        }

        sa.assertAll();


    }

    @Test (groups = "smoke")
    public void ForgotPassword(){
        WebElement fppage = driver.findElement(By.linkText("Forgot Password"));
        fppage.click();
        WebElement textBox = driver.findElement(By.id("email"));
        String keysToSend = "\"januszmaj@gmail.com\"";
        textBox.sendKeys("januszmaj@gmail.com");
        WebElement retrievePasswordButton = driver.findElement(By.id("form_submit"));
        retrievePasswordButton.click();

        WebElement emailVerification = driver.findElement(By.xpath("//*[@id=\"post\"]/table/tbody/tr[2]/td[2]/div"));
        System.out.println(emailVerification.getText());

        if(keysToSend.equals(emailVerification.getText())){
            System.out.println("Jest jo");
        } else {
            System.out.println("Jest nie jo");
        }

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(keysToSend, emailVerification.getText());
        sa.assertAll();

    }


}

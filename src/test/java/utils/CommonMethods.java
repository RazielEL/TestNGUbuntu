package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

// co do before / after class method etc to trzeba zrobic, zeby bylo wygodnie
// moze byc osobna klasa np HOOKS, ktora bedzie odpowiadac dla poszczegolnych packages.
// w tym przypadku mamy 2 x Features, 4x class, gdzie jest TA SAMA STRONA STARTOWA
// mysle, ze najlepiej jest robic osobna class HOOKS do kazdej package i po prostu
// sortowac te scenariusze wg stron startowych.

public class CommonMethods extends PageInitializers {

    public static WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public static void OpenBrowserAndStartApplication() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
        driver.get("http://localhost:7080/"); //tutaj trzeba zmienic hardcoded na properties. To jest baseURL na dockera.
        // driver.get("https://the-internet.herokuapp.com/"); // tutaj jest internetowa wersja strony, jakby ktos nie mial dockera. Tylko // zmienic.

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterMethod(alwaysRun = true)
    public void GoToMainPage() {
        driver.navigate().to("http://localhost:7080/");
        // driver.navigate().to("https://the-internet.herokuapp.com/"); // jak wyzej
    }

    @AfterClass(alwaysRun = true)
    public static void TearDown() {
        driver.quit();
    }

    public static void jseClick(String arg, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(arg, element);
    }

    public static void ExcelSaverTheInternetLinks(List<WebElement> tytuly, List<WebElement> linki, List<String> endpointy) throws IOException {

        String path = "C:\\Users\\pawlo\\IdeaProjects\\NewTestNG\\resources\\ListOfLinks.xlsx";

        FileInputStream fileInputStream = new FileInputStream(path);
        XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = wb.getSheet("Sheet1");

        for (int i = 1; i < tytuly.size(); i++) {

            Row row = sheet.createRow(i);
            row.createCell(0).setCellValue(tytuly.get(i).getText());
            row.createCell(1).setCellValue(linki.get(i).getAttribute("href"));
            row.createCell(2).setCellValue(endpointy.get(i));

            System.out.println(tytuly.get(i).getText());
            System.out.println(linki.get(i).getAttribute("href"));
            System.out.println(endpointy.get(i));
        }

        FileOutputStream outputStream = new FileOutputStream(path);
        wb.write(outputStream);
        outputStream.close();

    }


}


package utils;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

// co do before / after class method etc to trzeba zrobic, zeby bylo wygodnie
// moze byc osobna klasa np HOOKS, ktora bedzie odpowiadac dla poszczegolnych packages.
// w tym przypadku mamy 2 x Features, 4x class, gdzie jest TA SAMA STRONA STARTOWA
// mysle, ze najlepiej jest robic osobna class HOOKS do kazdej package i po prostu
// sortowac te scenariusze wg stron startowych.

public class CommonMethods {

    public static WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public static void OpenBrowserAndStartApplication() {

        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
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

    @AfterTest(alwaysRun = true)
    public static void TearDown() {
        driver.quit();
    }

    public static void jseClick(String arg, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(arg, element);
    }




}


package testy;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.DriverProvider;
import utils.FileUtils;
import utils.PropertiesReader;
import utils.ScreenshotUtil;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class BaseTest {
    final static Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    public Properties properties;
    public WebDriver driver;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setUp(String browser) {
        LOGGER.info("Uruchamiam metodę Setup dla klasy");

        driver = new DriverProvider().getDriver(browser);

        properties = new PropertiesReader().getConfigurationProperties(
                FileUtils.getResourceFilePath("config.properties")
        );
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        LOGGER.info("Uruchamiam metodę TearDown dla klasy");
        driver.quit();
    }

    @BeforeMethod
    public void methodSetUp(ITestResult result) {
        LOGGER.info("Uruchamiam metodę testową " + result.getMethod().getMethodName());
//        Wyświetlam informację o uruchomieniu metody testowej wraz z jej nazwą pobraną z ITestResult dostarczanych
//        przez TestNG
    }

    @AfterMethod(alwaysRun = true)
    public void methodTearDown(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        // Zapisuję nazwę metody na potrzeby logowania i zapisu screenshota

        // Dorzuciłem dwie linijki związane z formatowaniem czasu z milliseconds na bardziej czytelny format
        SimpleDateFormat sdf = new SimpleDateFormat(properties.getProperty("dateTimeFormat"));
        long timestampMillis = result.getEndMillis();
        String timestamp = sdf.format(timestampMillis);
        String fileName = methodName + "_" + timestamp;

        // Uruchamiam odpowiedni kod na podstawie wyniku testu pobranego z ITestResults
        if (result.getStatus() != ITestResult.SUCCESS) {
            ScreenshotUtil.takeScreenshot(driver, fileName);
        }

        LOGGER.info("Kończę metodę testową " + methodName + " - " + timestamp);
    }
}

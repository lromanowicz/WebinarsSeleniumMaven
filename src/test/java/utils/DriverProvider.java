package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testy.BaseTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverProvider {

    final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    static WebDriver driver;

    public WebDriver getDriver(String browser) {

        LOGGER.info("Wybrany driver to " + browser.toUpperCase());

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            // Logowanie informacji na temat wybranej do testów (przekazanej przez Maven) wersji drivera
            WebDriverManager.firefoxdriver().setup();
            // Konfiguracja drivera z wykorzystaniem WebDriverManagera

            // FirefoxOptions options = setFirefoxOptions();
            // driver = new FirefoxDriver(options);
            // Powyższe linijki realizują dokładnie to samo zadanie co poniższa
            driver = new FirefoxDriver(setFirefoxOptions());
            // WebDriverManager.edgedriver().setup();
            // driver = new EdgeDriver();
            // W równie prosty sposób możemy skorzystać z drivera dla innej przeglądarki
        } else if (browser.equalsIgnoreCase("remote-chrome")) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", "chrome");

            try {
                driver = new RemoteWebDriver(new URL("http://192.168.1.23:58978"), caps);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        } else if (browser.equalsIgnoreCase("remote-firefox")) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", "firefox");

            try {
                driver = new RemoteWebDriver(new URL("http://192.168.1.23:58978"), caps);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }

    private FirefoxOptions setFirefoxOptions() {
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-private");
        return options;
    }

}

package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {
    final static Logger LOGGER = LoggerFactory.getLogger(ProductPage.class);
    // Logger możemy stosować w dowolnej klasie w ramach naszego kodu

    WebDriver driver;

    BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        // Wykorzystujemy PageFactory, dlatego musimy zainicjalizować WebElementy zadeklarowane z wykorzystaniem
        // @FindBy, @FindBys oraz @FindAll
    }
    // Przypisujemy instancję WebDrivera do pola w klasie bazowej strony. Sama instancja tworzona jest w teście i
    // przekazywana dalej. W kolejnych miejscach nie możemy tworzyć nowych instancji ponieważ nie byłyby one ze sobą
    // zsynchronizowane

    //  W klasie bazowej tworzymy metody, które będą wspólne dla wielu stron. Mogą to być metody odpowiedzialne za
    // oczekiwanie na dany element, wypełnianie pól tekstem, czy wszelkie inne, które musielibyśmy dublować w
    // poszczególnych klasach PageObject'ów

    public void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForPresenceOfElement(By elementLocator) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        return wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
    }

    public void clickElement(WebElement element) {
        // Klikanie lementu
        element.click();
    }

    public boolean isElementDisplayed(WebElement element) {
        // Sprawdzanie czy element jest widoczny
        return element.isDisplayed();
    }

    public String getTextFromElement(WebElement element) {
        // Pobieranie tekstu z elementu
        return element.getText();
    }

    public void fillElement(WebElement element, String text) {
        // Czyszczenie elementu
        element.clear();
        // Wypełnianie elementu
        element.sendKeys(text);
    }
}

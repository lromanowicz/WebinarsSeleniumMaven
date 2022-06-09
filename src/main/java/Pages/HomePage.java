package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
        // Instancja WebDrivera jest przekazywana z testu, w tym miejscu przekazujemy ją dalej do konstruktora klasy bazowej
    }

    // Inicjalizowanie elementów z PageFactory
    @FindBy(css = "article")
    WebElement firstProductBy;

    @FindBy(css = "article .price")
    WebElement firstProductPriceBy;

    // Stary sposób inicjowania selektorów ( a potem elementów)
    // By firstProductBy = By.cssSelector("article");
    // By firstProductPriceBy = By.cssSelector("article .price");
    // Zebranie lokalizatorów w jednym miejscu

    public void openHomePage() {
        driver.get("http://sampleshop.inqa.pl/");
        // Przejście do odpowiedniej strony. Docelowo nie będziemy wykorzystywać sztywno zapisanych (zahardokodowanych)
        // adresów URL, a raczej pobierali je z jakiegoś pliku konfiguracyjnego
    }

    public void openFirstProduct() {
        // Wywołanie metody klikajacej element
        clickElement(firstProductBy);
    }

    public boolean checkFirstProductIsDisplayed() {
        // Wywołanie metody sprawdzajacej czy element jest widoczny
        return isElementDisplayed(firstProductBy);
    }

    public String getPriceOfFirstElement() {
        // Wywołanie metody pobierającej tekst z elementu
        return getTextFromElement(firstProductPriceBy);
    }
}

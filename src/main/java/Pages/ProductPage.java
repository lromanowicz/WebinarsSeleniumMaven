package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@title=\"Biały\"]")
    WebElement colorWhiteBox;
    @FindBy(xpath = "//input[@title=\"czarny\"]")
    WebElement colorBlackBox;
    @FindBy(id = "group_1")
    WebElement selectSizeElement;
    @FindBy(xpath = "//input[@name=\"qty\"]")
    WebElement numberOfPiecesInput;
    @FindBy(css = ".add button")
    WebElement addToBasketButton;
    // Deklaracja lokalizatorów WebElementów - stworzone przed wykorzytaniem PageFactory

    @FindBy(css = ".add button")
    WebElement addToBasketButtonElement;
    //    WebElement deklarowany w oparciu o PageFactory
    @FindBy(css = "#blockcart-modal")
    WebElement cartSummaryElement;
    By cartSummaryBy = By.cssSelector("#blockcart-modal");

    public void openProductPage() {
        LOGGER.info("Otwieram stronę produktu");
        driver.get("http://sampleshop.inqa.pl/men/1-1-hummingbird-printed-t-shirt.html#/1-rozmiar-s/8-kolor-bialy");
        // Otwieramy oczekwianą stronę. W docelowym środowisku ten zapis nie będzie oczywiście poprawny ponieważ
        // otwieramy za każdym razem tę samą stronę. Tu należałoby stworzyć mechanizm, który otwiera stronę produktu
        // przekazanego jako parametr, lub pozostawić otwieranie strony produktu logice testowej.
        // Ta forma została stworzona jedynie dla zobrazowania metody otwierania strony.
    }

    public void selectColor(String color) {
        LOGGER.info("Wybieram kolor: " + color);
        if (color.equals("white")) {
            colorWhiteBox.click();
        } else if (color.equals("black")) {
            colorBlackBox.click();
        }
    }

    public void selectSize(String size) {
        Select selectSize = new Select(selectSizeElement);
        selectSize.selectByVisibleText(size);
    }

    public void setNumberOfPieces(String number) {
        clickElement(numberOfPiecesInput);
        fillElement(numberOfPiecesInput, number);
    }

    public void clickAddToBasketButton() {
        // WebElement addToBasketButton = driver.findElement(addToBasketBy);
        // addToBasketButton.click();
        // driver.findElement(addToBasketBy).click();
        // Pierwsze dwie linijki odpowiadają trzeciej, jednak są zapisane w bardziej czytelny sposób.

        addToBasketButtonElement.click();
        // Ten zapis, bazujący na elemencie stworzonym w oparciu o PageFactory, odpowiada powyższemu. Mamy tu jednak
        // bardziej czytelny zapis i nie musimy za każdym razem odwoływać się do drivera oraz findElement. Dodatkowo
        // możemy skorzystać z @CacheLookUp i cache'ować wynik wyszukiwania co wpłynie pozytywnie na wydajność testów.
        // Należy jednak pamiętać, że ten zabieg zadziała dobrze jedynie w przypadku elementów w pełni statycznych.
    }

    public void checkAddToBasketSummaryDisplayed() {
        //  waitForElementVisibility(.....);
        WebElement cartSummaryElement = waitForPresenceOfElement(cartSummaryBy);
        waitForElementVisibility(cartSummaryElement);
        // Przykład wykorzystania metody służącej do oczekiwania na określony element, zadeklarowanej w klasie bazowej.
        // Jest to jedynie rozpoczęty przykład, który wymaga dalszej implementacji.
    }

    public String getColorFromBasketSummary() {
        return cartSummaryElement.findElement(By.cssSelector(".kolor strong")).getText();
    }

    public String getSizeFromBasketSummary() {
        return getTextFromElement(cartSummaryElement.findElement(By.cssSelector(".rozmiar strong")));
    }

    public String getQuantityFromBasketSummary() {
        return cartSummaryElement.findElement(By.cssSelector(".product-quantity strong")).getText();
    }
}

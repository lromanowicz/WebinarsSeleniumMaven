package testy;

import Pages.HomePage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;


@Owner("Piotr Krzosa")
@Epic("Nazwa epica")
@Story("Nazwa story")
public class HomePageTests extends BaseTest {

    @Test(groups = {"SmokeTests"})
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("http://google.com")
    public void checkHomePageTitle(){
        driver.get("http://sampleshop.inqa.pl/");
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "Automation Sample Shop");
    }

    @Test(groups = {"SmokeTests"})
    @Severity(SeverityLevel.MINOR)
    @TmsLink("http://google.com")
    public void checkProductPrice(){
        HomePage homePage = new HomePage(driver);
//        tworzymy instancję obiektu strony
        homePage.openHomePage();
//        otwieramy stronę (przechodzimy do odpowiedniego URL)
        String actualPrice = homePage.getPriceOfFirstElement();
//        Odwołując się do metody tej strony pobieramy cenę
        Assert.assertEquals( actualPrice, "23,52 zł");
    }

//    @Test
//    public void checkProductsList(){
//        HomePage.getListOfProducts();
//        promoPage.getListOfProducts();
//        categoryPage.getListOfProducts();
//
//        Assert.compareListOfProducts();
//    }
//    Przykład kodu do pytania, które padło w trakcie zajęć. Metody nie są w praktyce zaimplementowane
}

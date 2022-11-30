package com.network;

import com.network.base.BasePage;
import com.opencsv.exceptions.CsvValidationException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.*;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import static com.network.constant.Constants.*;

public class NetworkPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(NetworkPage.class);

    public NetworkPage(WebDriver driver, JavascriptExecutor js) {
        super(driver, js);
    }

    public NetworkPage closeCookieAlert() throws InterruptedException {
        logger.info("Açılış sayfasından Cookie popup'ı kabul ediliyor.");
        WebElement cookieAccept = findElement(COOKIE_WARNING);
        cookieAccept.click();
        Thread.sleep(1000);
        return this;
    }

    public NetworkPage searchJacket() throws InterruptedException {
        logger.info("Ceket araması yapılıyor...");
        sendKeys(SEARCH_INPUT,"ceket");
        Thread.sleep(1000);
        submit(SEARCH_INPUT);
        Thread.sleep(4000);
        return this;
    }

    public NetworkPage gotoBottomOfPage() throws InterruptedException {
        logger.info("İkini sayfaya geçmek için sayfa sonuna gidiliyor...");
        executeScript("window.scrollTo(0, document.body.scrollHeight - 1500)");
        Thread.sleep(1000);
        return this;
    }

    public NetworkPage gotoPage2() throws InterruptedException {
        executeScript("document.getElementsByClassName('-sm')[0].click();");
        Thread.sleep(1000);
        Assert.assertThat(getCurrentUrl(), CoreMatchers.containsString("page=2"));
        logger.info("Sayfa 2'ye geçildi.");
        Thread.sleep(1000);
        return this;
    }

    public NetworkPage findFirstDiscountedProduct() throws InterruptedException {

        // İlk indirimli ürünü bul (Kırmızı kutucuk ile gösterilen)
        WebElement element = findElement(PRODUCT_DISCOUNT_PERCENT);

        Thread.sleep(1000);

        Point discountlLocation = element.getLocation();

        // İndirimli ürüne scrool et
        executeScript("window.scrollTo(" + discountlLocation.x + ", " + (discountlLocation.y - 400) + ")");

        // indirimli ürüne hover et
        moveToElement(element);
        logger.info("Sepette olan uygun beden seçliyor.");
        executeScript("var elements = [...document.getElementsByClassName('product__discountPercent')[0].offsetParent.offsetParent.getElementsByClassName('radio-box__label')];\n" +
                "\n" +
                "elements.forEach(\n" +
                "    (element, index, array) => {\n" +
                "        if (!element.classList.contains('-disabled')) {\n" +
                "\t\t\telements[index].click();\n" +
                "\t\t}\n" +
                "    }\n" +
                ");");

        Thread.sleep(2000);
        executeScript("window.scrollTo(" + (discountlLocation.x - 50) + ", " + (discountlLocation.y - 400) + ")");
        Thread.sleep(1000);

        WebElement priceContainer = findElement(PRODUCT_PRICE_XPATH);
        String productPrice = priceContainer.getText().split("\n")[2];

        logger.info("Seçilen ürün fiyatı = " + productPrice);

        return this;
    }

    public NetworkPage gotoBasketAndComparePrices() throws InterruptedException, ParseException {
        findElement(By.xpath("//a[contains(text(),'Sepete Git')]")).click();

        Thread.sleep(3000);

        String salePrice = findElement(By.xpath("//span[@class='cartItem__price -sale']")).getText();
        String oldPrice = findElement(By.xpath("//span[@class='cartItem__price -old -labelPrice']")).getText();

        NumberFormat format = NumberFormat.getInstance(Locale.ENGLISH);

        double cleanSalePrice = format.parse(salePrice.replace(" TL", "").trim()).doubleValue();
        double cleanOldPrice = format.parse(oldPrice.replace(" TL", "").trim()).doubleValue();

        Assert.assertTrue(Double.compare(cleanSalePrice, cleanOldPrice) < 0);
        logger.info("Ürün fiyatları karşılaştırıldı");

        Thread.sleep(1000);
        return this;
    }

    public NetworkPage gotoLoginPageAndFillCredentials() throws CsvValidationException, IOException, InterruptedException {
        findElement(CONTINUE_XPATH).click();

        String username = new CSVReader().getUserInfo(0);
        String password = new CSVReader().getUserInfo(1);

        Thread.sleep(4000);

        findElement(EMAIL_INPUT).sendKeys(username);
        findElement(PASSWORD_INPUT).sendKeys(password);

        logger.info("CSV'den bilgiler okundu ve ekrana yazıldı");

        String loginButton = findElement(LOGIN_BUTTON_XPATH).getText();
        Assert.assertTrue(loginButton.equals("GİRİŞ YAP"));

        Thread.sleep(4000);
        return this;
    }

    public NetworkPage gotoHomeAndCheckBasket () throws InterruptedException {
        findElement(HOME_LOGO).click();
        Thread.sleep(3000);
        findElement(BASKET_ICON).click();
        Thread.sleep(2000);
        findElement(REMOVE_ITEM_FROM_BASKET_BUTTON).click();
        Thread.sleep(2000);
        findElement(ACCEPT_REMOVE_BUTTON).click();
        Thread.sleep(2000);
        findElement(BASKET_ICON).click();
        Thread.sleep(5000);
        String emtyMessage = findElement(EMPTY_MESSAGE).getText();
        Assert.assertTrue(emtyMessage.contains("Sepetiniz Henüz Boş"));
        return this;
    }

}
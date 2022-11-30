package com.network;

import static org.junit.Assert.assertTrue;

import com.network.base.BaseTest;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseTest {

    NetworkPage networkPage;

    private static final Logger logger = LogManager.getLogger(NetworkPage.class);

    @Before
    public void init() {
        networkPage = new NetworkPage(getWebDriver(), getJs());
    }

    @Test
    public void testNetworkComTr() {
        try {

            networkPage
                    .closeCookieAlert()
                    .searchJacket()
                    .gotoBottomOfPage()
                    .gotoPage2()
                    .findFirstDiscountedProduct()
                    .gotoBasketAndComparePrices()
                    .gotoLoginPageAndFillCredentials()
                    .gotoHomeAndCheckBasket();

        } catch (InterruptedException e) {
            logger.error("Bekleme süresinde bir hata oluştu");
            logger.error(e.getStackTrace());
        } catch (CsvValidationException e) {
            logger.error("CSV dosyasından veriler okunamadı");
            logger.error(e.getStackTrace());
        } catch (IOException e) {
            logger.error("Data read edilemedi");
            logger.error(e.getStackTrace());
        } catch (ParseException e) {
            logger.error("Veri dönüştürme hatası");
            logger.error(e.getStackTrace());
        }
    }
}

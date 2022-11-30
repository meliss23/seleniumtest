package com.network.constant;

import org.openqa.selenium.By;

public class Constants {

    public static final String WEB_URL = "https://www.network.com.tr/";

    public static final By COOKIE_WARNING = By.id("onetrust-accept-btn-handler");
    public static final By SEARCH_INPUT = By.id("search");
    public static final By PRODUCT_DISCOUNT_PERCENT = By.className("product__discountPercent");
    public static final By PRODUCT_PRICE_XPATH = By.xpath("//div[contains(@class, 'product__discountPercent')]//ancestor::div[contains(@class, 'product__prices')]");
    public static final By CONTINUE_XPATH = By.xpath("//button[contains(.,'DEVAM ET')]");
    public static final By EMAIL_INPUT = By.id("n-input-email");
    public static final By PASSWORD_INPUT = By.id("n-input-password");
    public static final By LOGIN_BUTTON_XPATH = By.xpath("//button[contains(.,'GİRİŞ YAP')]");
    public static final By HOME_LOGO = By.className("headerCheckout__logo");
    public static final By BASKET_ICON = By.className("header__basketTrigger");
    public static final By REMOVE_ITEM_FROM_BASKET_BUTTON = By.className("header__basketProductBtn");
    public static final By ACCEPT_REMOVE_BUTTON = By.xpath("//button[contains(.,'Çıkart')]");
    public static final By EMPTY_MESSAGE = By.className("header__emptyBasketText");

}
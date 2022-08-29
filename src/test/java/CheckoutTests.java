import Pages.*;
import Utilities.DriverManager;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.Optional;

public class CheckoutTests extends BaseTest {

    @Test
    public void verifyEmptyLastNameError(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();
        HomePage homepage= new HomePage(DriverManager.getDriver().driver);
        homepage.clickOnAddSauceLabsBackPackToCartButton();
        homepage.clickOnCart();
        CartPage cart = new CartPage(DriverManager.getDriver().driver);
        cart.clickOnCheckoutButton();
        CheckoutInfoPage checkoutInfo = new CheckoutInfoPage(DriverManager.getDriver().driver);
        checkoutInfo.setFirstNameField("Juan");
        checkoutInfo.setPostalCode("1234");
        checkoutInfo.clickOnContinue();
        Assert.assertEquals("Error: Last Name is required", checkoutInfo.getInfoError());
    }

    @Test
    public void verifyEmptyPostalCode(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();
        HomePage homepage= new HomePage(DriverManager.getDriver().driver);
        homepage.clickOnAddSauceLabsBackPackToCartButton();
        homepage.clickOnCart();
        CartPage cart = new CartPage(DriverManager.getDriver().driver);
        cart.clickOnCheckoutButton();
        CheckoutInfoPage checkoutInfo = new CheckoutInfoPage(DriverManager.getDriver().driver);
        checkoutInfo.setFirstNameField("Juan");
        checkoutInfo.setLastNameField("Quispe");
        checkoutInfo.clickOnContinue();
        Assert.assertEquals("Error: Postal Code is required", checkoutInfo.getInfoError());
    }

    @Test
    public void verifyEmptyCart(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();
        HomePage homepage= new HomePage(DriverManager.getDriver().driver);
        homepage.clickOnCart();
        CartPage cart = new CartPage(DriverManager.getDriver().driver);
        cart.clickOnCheckoutButton();
        String curr_url = DriverManager.getDriver().driver.getCurrentUrl();
        Assert.assertEquals("https://www.saucedemo.com/cart.html",curr_url);
    }

    @Test
    public void verifySum(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();
        HomePage homepage= new HomePage(DriverManager.getDriver().driver);
        homepage.clickOnAddSauceLabsBackPackToCartButton();
        homepage.clickOnAddBikelight();
        homepage.clickOnCart();
        CartPage cart = new CartPage(DriverManager.getDriver().driver);
        cart.clickOnCheckoutButton();
        CheckoutInfoPage checkoutInfo = new CheckoutInfoPage(DriverManager.getDriver().driver);
        checkoutInfo.setFirstNameField("Juan");
        checkoutInfo.setLastNameField("Quispe");
        checkoutInfo.setPostalCode("1234");
        checkoutInfo.clickOnContinue();
        CheckoutBriefPage brief = new CheckoutBriefPage(DriverManager.getDriver().driver);
        Double price = Double.parseDouble(brief.getFirstItemPrice().replaceAll("\\$","")) + Double.parseDouble(brief.getSecondItemPrice().replaceAll("\\$",""));
        Double total = Double.parseDouble(brief.getTotalPrice().replaceAll("[^\\d.]",""));
        Assert.assertEquals(price, total);
    }
}

package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    WebDriver driver;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(xpath = "//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[1]")
    WebElement backpackCuantity;

    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnCheckoutButton(){ checkoutButton.click(); }
    public boolean backpackCuantityIsTB(){
        boolean res = backpackCuantity.isEnabled();
        return res;
    }
}

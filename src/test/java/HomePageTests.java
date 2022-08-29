import Pages.HomePage;
import Pages.LoginPage;
import Utilities.DriverManager;
import org.junit.Assert;
import org.junit.Test;

public class HomePageTests extends BaseTest{
    @Test
    public void verifyTwitter(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();
        HomePage homepage= new HomePage(DriverManager.getDriver().driver);
        homepage.clickOnTwitterButton();
        String[] tabHandles = DriverManager.getWindowHandles();
        DriverManager.getDriver().driver.switchTo().window(tabHandles[1]);
        String curr_url = DriverManager.getDriver().driver.getCurrentUrl();
        Assert.assertEquals("https://twitter.com/saucelabs", curr_url);
    }

    @Test
    public void verifyAbout(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();
        HomePage homepage= new HomePage(DriverManager.getDriver().driver);
        homepage.clickOnMenuButton();
        homepage.clickOnAboutButton();
        String curr_url = DriverManager.getDriver().driver.getCurrentUrl();
        Assert.assertEquals("https://saucelabs.com/", curr_url);
    }


}

import core.Browser;
import core.ConfigReader;
import locators.CheckoutPage;
import locators.ConfirmationPage;
import locators.MainPage;
import locators.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/*
    After the application is launched, the selection of product done is based on temperature displayed and based on that "toBuy" value is set
    Now we need to select two products based on some substring value and price, in this case the price should be least for both the products
    and add to cart. Then verify that cart value is correct and make payment. A retry mechanism is implemented at this stage which is based on
    String matching "PAYMENT SUCCESS", if the string does not matches, the test case is explicitly failed but before doing that user is navigated
    back to previous page to cart details page and re-enter card details.
* */

public class TestScenario1 {

    WebDriver driver;
    HashMap<String, Integer> cartMap = new HashMap<>();
    Browser browser = new Browser(driver);
    ConfigReader configReader = new ConfigReader();
    MainPage mainPage;
    ProductPage productPage;
    CheckoutPage checkoutPage;
    ConfirmationPage confirmationPage;
    String toBuy = null;

    @BeforeClass
    @Parameters({"browserName","testEnv"})
    public void launchApplication(String browserName, String testEnv) {
        try {
            driver = browser.launchBrowser(browserName);
            driver.navigate().to(configReader.readProperties(testEnv.toUpperCase()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public void initialize(){
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
        confirmationPage = new ConfirmationPage(driver);
    }


    @Test(priority=1)
    public void test1_selectProductBasedOnTemperature(){

        String currentTempVal= mainPage.getCurrentTemperature().getText().split(" ")[0];
        int temperatureValue = Integer.parseInt(currentTempVal);
        if(temperatureValue<19) {
            System.out.println("Temperature is: "+temperatureValue+" | User to buy Moisturizers");
            toBuy = "Moisturizers";
            mainPage.getMoisturizersButton().click();
        }
        else if(temperatureValue>34) {
            System.out.println("Temperature is: "+temperatureValue+" | User to buy Sunscreens");
            toBuy = "Sunscreens";
            mainPage.getSunscreensButton().click();
        }
        else{
            System.out.println("Temperature is: "+temperatureValue+" | Nothing to buy");
        }
    }

   @Test(priority=2)
    public void test2_addProductsToCart() {

        int productIndex;
        String[] regex = null;
        List<WebElement> productNames = productPage.getProductNames();
        List<WebElement> productPrices = productPage.getproductPrices();
        List<WebElement> addButton = productPage.getAddButton();

        if (toBuy == "Moisturizers") {
            Assert.assertEquals(productPage.getParentProduct().getText(), "Moisturizers");
            regex = new String[]{"Aloe", "almond"};
        }
        else if (toBuy == "Sunscreens") {
            Assert.assertEquals(productPage.getParentProduct().getText(), "Sunscreens");
            regex = new String[]{"SPF-50", "SPF-30"};
        }

        for (int i = 0; i < regex.length; i++) {
            productIndex = getIndexOfLeastPricedProduct(regex[i]);
            if (productIndex != -1) {
                String name = productNames.get(productIndex).getText();
                int price = Integer.parseInt(productPrices.get(productIndex).getText().substring(productPrices.get(productIndex).getText().lastIndexOf(" ") + 1));
                System.out.println(productIndex + " " + name + "  " + price);
                cartMap.put(name, price);
                addButton.get(productIndex).click();
            }
        }

        String cartValue = productPage.cartButton().getText();
        Assert.assertNotEquals("Empty", cartValue);
        productPage.cartButton().click();
    }

    @Test(priority=3, retryAnalyzer = core.RetryAnalyzer.class)
    public void test3_proceedWithCheckoutAndMakePayment() throws InterruptedException {

        int calcSum =0;
        List<WebElement> itemNames = checkoutPage.getItemNames();
        List<WebElement> itemPrices = checkoutPage.getItemPrices();

        for(int k=0; k<cartMap.size(); k++){
            if(cartMap.containsKey(itemNames.get(k).getText())){
                int mapVal = cartMap.get(itemNames.get(k).getText());
                int displayedVal = Integer.parseInt(itemPrices.get(k).getText().substring(itemPrices.get(k).getText().lastIndexOf(" ") + 1));
                if(mapVal == displayedVal)
                    calcSum = calcSum + mapVal;
            }
        }
        System.out.println("Calculated Price: "+calcSum);
        int displayedTotal = Integer.parseInt(checkoutPage.getTotalPrice().getText().substring(checkoutPage.getTotalPrice().getText().lastIndexOf(" ")+1));

       Assert.assertEquals(displayedTotal,calcSum);

       checkoutPage.getPayWithCardButton().click();

        try {
            checkoutPage.enterCardDetailsAndPay(configReader.readProperties("testEmailId"),configReader.readProperties("cardNumber"),
                     configReader.readProperties("expiryDate"),configReader.readProperties("cvv"));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        if(!confirmationPage.getConfirmationHeader().isDisplayed()){
            System.out.println("PAYMENT FAILED");
            driver.navigate().back();
            Assert.assertEquals("PAYMENT SCUCCESS","PAYMENT FAILED","Payment Failed.... rety");
        }
    }

    public int getIndexOfLeastPricedProduct(String productName){

        int leastPrice = Integer.MAX_VALUE;
        int selectProductIndex = -1;
        List<WebElement> productNames = productPage.getProductNames();
        List<WebElement> productPrices = productPage.getproductPrices();
        for (int i = 0; i < productNames.size(); i++) {
            if (productNames.get(i).getText().contains(productName)) {
                System.out.println(productNames.get(i).getText());
                int currPrice = Integer.parseInt(productPrices.get(i).getText().substring(productPrices.get(i).getText().lastIndexOf(" ") + 1));
                System.out.println(currPrice);
                if (currPrice < leastPrice) {
                    leastPrice = currPrice;
                    selectProductIndex = i;
                }
            }
        }
        if(selectProductIndex != -1)
            return selectProductIndex;
        else {
            System.out.println("No product found matching : "+productName);
            return -1;
        }
    }
}

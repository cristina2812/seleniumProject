import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;


public class MadisonTest {

    WebDriver driver;

    @Before
    public void connection(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/cristinaadam/IdeaProjects/selenium/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://qa2.dev.evozon.com/");
    }

    @After
    public void diconnect(){
        driver.close();
        driver.quit();

    }

    @Test
    public void checkHomePage(){


        //2.Get title and verify if is equals with expected title
        System.out.println(driver.getTitle());
        assert((driver.getTitle()).equals("Madison Island"));

        //3.Get current URL and verify if is equals with expected URL
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);
        assert(currentUrl.equals("http://qa2.dev.evozon.com/"));

        //4.click on the logo and verify if appears
        driver.findElement(By.className("logo")).click();
        Boolean logoPresent = driver.findElement(By.className("large")).isDisplayed();
        Assert.assertTrue(logoPresent);


        //5.Navigate to a different page
        driver.navigate().to("http://qa2.dev.evozon.com/women.html");

        //6.Navigate back
        driver.navigate().back();

        //7.Navigate froward
        driver.navigate().forward();

        //8.Refresh page
        driver.navigate().refresh();

        //Make sure you are on the homepage
        driver.navigate().to("http://qa2.dev.evozon.com/");
        Boolean homePage = driver.getCurrentUrl().equals("http://qa2.dev.evozon.com/");
        Assert.assertTrue(homePage);
    }



    @Test
    public void checkDropDownAccount(){
        // Make sure the dropdown appears account
        WebElement accountHeader = driver.findElement(By.cssSelector("a.skip-account"));
        accountHeader.click();
        Assert.assertTrue(driver.findElement(By.cssSelector("#header-account ul")).isDisplayed());

        //Make sure the account option has the correct text “account”
        Assert.assertEquals("ACCOUNT", accountHeader.getText());

    }

    @Test
    public void checkLanguages(){

        //2.List the number of language option
        List<WebElement> languageElement = driver.findElements(By.cssSelector("#select-language option"));

        //3.Change form the default to the next option
        driver.findElement(By.cssSelector("#select-language")).click();
        //Make sure there are only 3 language options for the users
        Assert.assertEquals(3,languageElement.size());
    }


    @Test
    public void checkSearch(){

        //Enter text "women" to field
        driver.findElement(By.cssSelector("#search")).sendKeys("Women");
        //click on serach button
        driver.findElement(By.className("search-button")).click();

        //Make sure search results title contains the keyword
        String pageTitle = driver.findElement(By.className("page-title")).getText().toUpperCase();
        String elementSearch = driver.findElement(By.cssSelector("#search")).getText().toUpperCase();
        Assert.assertTrue(pageTitle.contains(elementSearch));

    }

    @Test
    public void checkNewProducts(){
        //List the number of new products
        List<WebElement> newProductElements = driver.findElements(By.cssSelector(".item.last"));
        System.out.println(newProductElements.size());

        //List the name of new products
        List<WebElement> newProductElementNames =  driver.findElements(By.cssSelector(".product-name"));
        System.out.println(newProductElementNames);
        Iterator<WebElement> it = newProductElementNames.iterator();
        while(it.hasNext()){
            String itemText = it.next().getText();
            System.out.println(itemText);
        }

    }


    @Test
    public void navigation(){

        //list the navigation headlines
        List<WebElement> headerElementLists =  driver.findElements(By.cssSelector(".nav-primary"));
        Iterator<WebElement> it = headerElementLists.iterator();
        while(it.hasNext()){
            String itemText = it.next().getText().toUpperCase();
            System.out.println(itemText);
        }


        //Select "Sales" on the navigation headlines
        driver.findElement(By.xpath("//ol/li/a[text()='Sale']")).click();
        //Select a specific product from sale
        driver.findElement(By.id("product-collection-image-338")).click();
        Assert.assertEquals("http://qa2.dev.evozon.com/jackie-o-round-sunglasses.html", driver.getCurrentUrl());

    }


    @Test
    public void checkCheckout() {

        Actions actions = new Actions(driver);
        WebElement womenCategory = driver.findElement(By.xpath("//ol/li/a[text()='Women']"));
        actions.moveToElement(womenCategory).build().perform();
        WebElement topAndBlousesCategory = driver.findElement(By.xpath("//a[text()='Tops & Blouses']"));
        topAndBlousesCategory.click();

        List<WebElement> allProducts = driver.findElements(By.className("products-grid.products-grid--max-4-col"));
        //click on products from Tops&Blouses from women
        WebElement specificProduct = driver.findElement(By.id("product-collection-image-417"));
        specificProduct.click();

        //select colour
        WebElement specificProductColour = driver.findElement(By.id("swatch21"));
        specificProductColour.click();
        //select size
        WebElement specificProductSize = driver.findElement(By.id("swatch79"));
        specificProductSize.click();

        //click add to cart
        WebElement addToCartBtn = driver.findElement(By.className("add-to-cart-buttons"));
        addToCartBtn.click();

        //serach another element
        WebElement fieldSearchFromSpecificElement  = driver.findElement(By.cssSelector("#search"));
        fieldSearchFromSpecificElement.sendKeys("glasses");
        WebElement searchBtn = driver.findElement(By.className("search-button"));
        searchBtn.click();
        WebElement selectAProduct = driver.findElement(By.id("product-collection-image-338"));
        selectAProduct.click();
        WebElement addToCartElementBtn = driver.findElement(By.className("add-to-cart-buttons"));
        addToCartElementBtn.click();

        WebElement checkoutBtn = driver.findElement(By.className("method-checkout-cart-methods-onepage-bottom"));
        checkoutBtn.click();

        WebElement registeGuestCheckBox = driver.findElement(By.cssSelector("li.control:nth-child(1)"));
        registeGuestCheckBox.click();
        driver.findElement(By.cssSelector("button#onepage-guest-register-button")).click();

        WebElement firstNameBilling = driver.findElement(By.id("billing:firstname"));//select first name
        firstNameBilling.sendKeys("Maria");

        WebElement middleNameBilling = driver.findElement(By.id("billing:middlename"));
        middleNameBilling.sendKeys("Mya");

        WebElement lastNameBilling = driver.findElement(By.id("billing:lastname"));
        lastNameBilling.sendKeys("Pop");

        WebElement emailAddressBilling = driver.findElement(By.id("billing:email"));
        emailAddressBilling.sendKeys("mariapop@yahoo.com");

        WebElement addressBilling = driver.findElement(By.id("billing:street1"));
        addressBilling.sendKeys("Observatorului, 34");

        WebElement cityBilling = driver.findElement(By.id("billing:city"));
        cityBilling.sendKeys("Cluj");


        driver.findElement(By.id("billing:country_id")).click();
        Select countryBilling = new Select(driver.findElement(By.id("billing:country_id")));
        countryBilling.selectByValue("RO");

        driver.findElement(By.id("billing:region_id")).click();
        Select stateBilling = new Select(driver.findElement(By.id("billing:region_id")));
        stateBilling.selectByValue("291");


        WebElement telephoneBilling = driver.findElement(By.id("billing:telephone"));
        telephoneBilling.sendKeys("0754638420");

        WebElement checkboxAddressBilling = driver.findElement(By.id("billing:use_for_shipping_yes"));
        checkboxAddressBilling.click();
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();

        WebElement zipCodeBilling = driver.findElement(By.id("billing:postcode"));
        zipCodeBilling.sendKeys("123456");


        driver.findElement(By.cssSelector("#billing-buttons-container > button")).click();


        driver.findElement(By.cssSelector("#opc-shipping > div.step-title > h2")).click();


        WebElement firstNameShipping = driver.findElement(By.id("shipping:firstname"));//select first name
        while(true){
            try{
                WebDriverWait wait = new WebDriverWait(driver,10);
                wait.until(ExpectedConditions.visibilityOf(firstNameShipping)).sendKeys("Maria");
                break;
            }catch (Exception e){
                driver.findElement(By.cssSelector("#opc-shipping > div.step-title > h2")).click();

            }
        }


        WebElement middleNameShipping = driver.findElement(By.id("shipping:middlename"));
        middleNameShipping.sendKeys("Mya");

        WebElement lastNameShipping = driver.findElement(By.id("shipping:lastname"));
        lastNameShipping.sendKeys("Pop");

        // WebElement emailAddressSi = driver.findElement(By.cssSelector("#shipping\\:street1"));
        //emailAddressSi.sendKeys("mariapop@yahoo.com");

        WebElement addressShipping = driver.findElement(By.id("shipping:street1"));
        addressShipping.sendKeys("Observatorului, 34");

        WebElement cityShipping = driver.findElement(By.id("shipping:city"));
        cityShipping.sendKeys("Cluj");


        driver.findElement(By.id("shipping:country_id")).click();
        Select countryShipping = new Select(driver.findElement(By.id("shipping:country_id")));
        countryShipping.selectByValue("RO");

        driver.findElement(By.id("shipping:region_id")).click();
        Select stateShipping = new Select(driver.findElement(By.id("shipping:region_id")));
        stateShipping.selectByValue("291");


        WebElement telephoneShipping = driver.findElement(By.id("shipping:telephone"));
        telephoneShipping.sendKeys("0754638420");

        WebElement zipCodeShipping = driver.findElement(By.id("shipping:postcode"));
        zipCodeShipping.sendKeys("123456");

        driver.findElement(By.cssSelector("#shipping-buttons-container > button > span > span")).click();

        driver.findElement(By.id("shipping-buttons-container")).click();


        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("s_method_freeshipping_freeshipping"))).click();
        driver.findElement(By.cssSelector("#shipping-method-buttons-container .button")).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#payment-buttons-container .button"))).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#review-buttons-container .button"))).click();
        String orderPLacedMessage = "THANK YOU FOR YOUR PURCHASE!";
        new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".sub-title"))).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".sub-title")).getText().toUpperCase(), orderPLacedMessage.toUpperCase());


    }



}

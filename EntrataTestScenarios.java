package Tests;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

public class EntrataTestScenarios<WebDriverWait> {
	
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\SDE47\\Desktop\\Automation\\Ecclipse\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    //Test1:In this test we are checking if the invalid credentials gives an error message or not.
    public void validLoginTest() throws InterruptedException {
        driver.get("https://www.entrata.com");
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        WebElement SignIN=driver.findElement(By.linkText("Sign In"));
        SignIN.click();
        
       
        Thread.sleep(2000);
        driver.get("https://sso.entrata.com/entrata/login");
        Thread.sleep(5000);
        driver.findElement(By.id("entrata-username")).sendKeys("Usernam1");
        driver.findElement(By.id("entrata-password")).sendKeys("Password");
        driver.findElement(By.tagName("button")).click();
       
        String expectedStatus= "The username and password provided are not valid. Please try again.";
        String actualTitle = driver.findElement(By.cssSelector("#statusMessage")).getText();
        assert actualTitle.equals(expectedStatus);
    }

    @Test(priority = 2)
    //Test2:Here we are checking with the WatchDemo form
    public void watchDemo()  throws InterruptedException{
        driver.get("https://www.entrata.com");

        WebElement watchDemoBtn=driver.findElement(By.linkText("Watch Demo"));
        watchDemoBtn.click();
        driver.findElement(By.id("FirstName")).sendKeys("Sanket");
        driver.findElement(By.id("LastName")).sendKeys("Devghare");
        driver.findElement(By.cssSelector("#Email")).sendKeys("sanketdevghare@gmail.com");
        driver.findElement(By.cssSelector("#Company")).sendKeys("My Company ABC");
        driver.findElement(By.id("Phone")).sendKeys("324497990804");
        driver.findElement(By.id("Unit_Count__c")).click();
        
        Select objSelect = new Select(driver.findElement(By.id("Unit_Count__c")));
        objSelect.selectByValue("101 - 200");
        
        driver.findElement(By.id("Title")).sendKeys("Automation Tester");
        
        Select occupation =new Select (driver.findElement(By.cssSelector("#demoRequest")));
        occupation.selectByValue("a Resident");
        
        Thread.sleep(5000);
        //This will clear the entire form.
        driver.navigate().refresh();
        //navigate back.
        driver.navigate().back();
        
        //Check if we are back to homepage by getting the current url of the page.
        String homePageurl="https://www.entrata.com/";
        assert homePageurl.equals(driver.getCurrentUrl());     
        
    }
   
    @Test(priority = 3)
    //Test3:Checking if the scheduledemo form can be submitted using Schedule Demo button.
    public void SceduleDemoPageTest() throws InterruptedException {
        driver.get("https://go.entrata.com/schedule-demo.html");
        driver.findElement(By.id("FirstName")).sendKeys("Sanket");
        driver.findElement(By.id("LastName")).sendKeys("Devghare");
        driver.findElement(By.cssSelector("#Email")).sendKeys("sanketdevghare@gmail.com");
        driver.findElement(By.cssSelector("#Company")).sendKeys("My Company ABC");
        driver.findElement(By.id("Phone")).sendKeys("324497990804");
        driver.findElement(By.id("Unit_Count__c")).click();
        
        Select objSelect = new Select(driver.findElement(By.id("Unit_Count__c")));
        objSelect.selectByValue("101 - 200");
        
        driver.findElement(By.id("Title")).sendKeys("Automation Tester");
        
        Select occupation =new Select (driver.findElement(By.cssSelector("#demoRequest")));
        occupation.selectByValue("a Resident");
        //check if the button to "schedule Demo" exists
        WebElement Shedulebtn=driver.findElement(By.xpath("//button[contains(text(),'SCHEDULE DEMO')]"));
        
        Thread.sleep(3000);
        //This will clear the entire form.
        driver.navigate().refresh();
        //navigate back.
        driver.navigate().back();
        
        //Check if we are back to homepage by getting the current url of the page.
        String homePageurl="https://www.entrata.com/";
        assert homePageurl.equals(driver.getCurrentUrl()); 

        
    }

    @Test(priority = 4)
    //Test4: Checking the title of the homepage.
    public void HomeTitleTest() {
        driver.get("https://www.entrata.com");

        String expectedTitle = "Property Management Software | Entrata";
        String actualTitle = driver.getTitle();
        assert actualTitle.equals(expectedTitle);
    }



    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

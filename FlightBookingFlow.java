package seleniumTest.seleniumTest;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class FlightBookingFlow {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.goibibo.com/");
		
		
		//Flight Booking Flow
		driver.findElement(By.xpath("//span[@id='roundTrip']")).click();
		
		WebElement text1 = driver.findElement(By.xpath("//input[@id='gosuggest_inputDest']"));
		Actions act = new Actions(driver);
		WebElement text = driver.findElement(By.xpath("//input[@id='gosuggest_inputSrc']"));
     	act.click(text).perform();
		act.sendKeys(text, "Bengaluru").perform();
		act.pause(2000).perform();
		act.sendKeys(text, Keys.ARROW_DOWN).perform();
		act.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);
		
		act.click(text1).perform();
		act.sendKeys(text1, "Delhi").perform();
		act.pause(2000).perform();
		act.sendKeys(text1, Keys.ARROW_DOWN).perform();
		act.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);
		
		driver.findElement(By.id("departureCalendar")).click();
		int startDate = LocalDate.now().plusDays(10).getDayOfMonth();
		String required_date = "//div[text()='" + startDate + "']";
		driver.findElement(By.xpath(required_date)).click();
		Thread.sleep(2000);
		
		driver.findElement(By.id("returnCalendar")).click();
		int endDate = LocalDate.now().plusDays(14).getDayOfMonth();
		String end_date = "//div[text()='" + endDate + "']";
		driver.findElement(By.xpath(end_date)).click();
		Thread.sleep(2000);
		
		driver.findElement(By.id("pax_link_common")).click();
		driver.findElement(By.id("adultPaxPlus")).click();
		driver.findElement(By.id("gi_search_btn")).click();
		Thread.sleep(2000);
		
		//Flight Search Result Screen

		JavascriptExecutor je = (JavascriptExecutor) driver;
        Thread.sleep(2000);
        String jsCode2 = "window.scrollBy(0, 100)";
        je.executeScript(jsCode2);
        driver.findElement(By.xpath("//label[@for='DOTREZ#:I51324I5315#1']")).click();
        driver.findElement(By.xpath("//label[@for='DOTREZ#:I5768I51427#1']")).click();
        driver.findElement(By.xpath("//input[@value='BOOK']")).click();
        
		//Traveller Details Screen
		
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        WebElement drpDwn = driver.findElement(By.xpath("//div[@class='flex1 dF alignItemsStart']//div[@class='padR10']//select"));
        driver.findElement(By.xpath("//div[@class='flex1 dF alignItemsStart']//div[@class='padR10']//select")).click();
        Select sel = new Select (drpDwn);;
        sel.selectByVisibleText("Ms");
        driver.findElement(By.xpath("//input[@placeholder='First & Middle Name']")).sendKeys("Suman");
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("test@gmail.com");
        driver.findElement(By.xpath("//input[@placeholder='Mobile No']")).sendKeys("9540783014");
        driver.findElement(By.xpath("//button[normalize-space()='Proceed']")).click();

		driver.quit();

	}

}

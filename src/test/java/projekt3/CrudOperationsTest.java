package projekt3;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.SeleniumExtension;


@ExtendWith(SeleniumExtension.class)
public class CrudOperationsTest {
	
	private FirefoxDriver driver;
	
	public CrudOperationsTest(FirefoxDriver driver) {
		this.driver = driver;
	}
	
	@BeforeEach
	public void login() {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.phptravels.net/admin");
		driver.findElement(By.name("email")).sendKeys("admin@phptravels.com");
		driver.findElement(By.name("password")).sendKeys("demoadmin");
		driver.findElement(By.name("password")).submit();
	}

	@Test
	public void deleteTest() {
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//a[@title='DELETE']")).get(0)));
		String id =  driver.findElements(By.xpath("//a[@title='DELETE']")).get(0).getAttribute("id");

		driver.findElements(By.xpath("//a[@title='DELETE']")).get(0).click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("ui-pnotify")));

    	assertFalse(driver.findElements(By.xpath("//a[@title='DELETE']")).get(0).getAttribute("id").equals(id));
	}
	
	@Test
	public void updateTest() {

		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//a[@title='Edit']")).get(0)));

		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("//a[@title='Edit']"))).perform();
		
		driver.findElements(By.xpath("//a[@title='Edit']")).get(0).click();
		
		wait.until(ExpectedConditions.titleContains("Edit Booking"));
		
		Select dropdown = new Select(driver.findElement(By.name("status")));
		dropdown.selectByVisibleText("Cancelled");
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Edit']")));
		
		assertFalse(driver.findElements(By.xpath("//a[@title='Edit']")).isEmpty());
  
	}
	
	@Test
	public void createTest() {

		
		driver.manage().window().maximize();
		driver.get("https://www.phptravels.net/admin/accounts/admins/");
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("add_button")));
		
		driver.findElementByCssSelector(".add_button .btn-success").click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("content")));

		driver.findElement(By.name("fname")).sendKeys("Adam");
		driver.findElement(By.name("lname")).sendKeys("Brzoza");
		driver.findElement(By.name("email")).sendKeys("brzoza@wp.pl");
		driver.findElement(By.name("password")).sendKeys("password123");
		
		driver.findElementByCssSelector(".select2-arrow").click();
		driver.findElements(By.className("select2-result-label")).get(4).click();
		
		driver.findElementByCssSelector(".panel-footer .btn-primary").click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("ui-pnotify")));
		
		assertFalse(driver.findElements(By.className("ui-pnotify-title")).isEmpty());
		
	}
	
	
}

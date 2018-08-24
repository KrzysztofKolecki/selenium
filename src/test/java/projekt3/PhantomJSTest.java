package projekt3;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.SeleniumExtension;

@ExtendWith(SeleniumExtension.class)
public class PhantomJSTest {
	
	private PhantomJSDriver driver;
	
	public PhantomJSTest(PhantomJSDriver driver) {
		this.driver = driver;
	}

	@BeforeEach
	public void setDefaultPage() {
		driver.get("http://the-internet.herokuapp.com/");
	}

	@Test
	public void testTitlePage() {
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    	assertEquals("The Internet", driver.getTitle());
	}
	
	@Test
	public void testAuth(){
		driver.get("http://the-internet.herokuapp.com/login");
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login")));
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		driver.findElement(By.tagName("button")).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("flash"), "You logged into a secure area!"));
		assertEquals("The Internet", driver.getTitle());
	}
	
	@Test
	public void testAuthFail(){
		driver.get("http://the-internet.herokuapp.com/login");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.id("password")).sendKeys("SuperSecretPasswordWrong!");
		driver.findElement(By.tagName("button")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		boolean assertion = wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("flash"), "Your password is invalid!"));
		
		assertAll("auth",
				() -> assertEquals("The Internet", driver.getTitle()),
	            () -> assertTrue(assertion)
	    );
		
	}
	
}
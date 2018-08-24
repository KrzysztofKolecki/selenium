package projekt3;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.SeleniumExtension;

@ExtendWith(SeleniumExtension.class)
public class OtherTest {
	
	private ChromeDriver driver;
	
	public OtherTest(ChromeDriver driver) {
		this.driver = driver;
	}

	@Test
	public void dragAndDrop() {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			

		WebElement element = driver.findElement(By.id("column-a")); 

		WebElement target = driver.findElement(By.id("column-b"));

		(new Actions(driver)).dragAndDrop(element, target).perform();
		
    	assertTrue(driver.findElementByCssSelector("#column-b header").getText().equals("B"));
	}
	
	@Test
	public void keyPresses() {
		driver.get("http://the-internet.herokuapp.com/key_presses");
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("content")));
		
		(new Actions(driver)).keyDown(Keys.ALT).keyUp(Keys.ALT).build().perform();
		
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("result")));
		
    	assertTrue(driver.findElementById("result").getText().equals("You entered: ALT"));
	}
	
	
}
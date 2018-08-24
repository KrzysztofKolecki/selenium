package projekt3;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.SeleniumExtension;

@ExtendWith(SeleniumExtension.class)
public class ChromeFormTest {
	
	private ChromeDriver driver;
	
	public ChromeFormTest(ChromeDriver driver) {
		this.driver = driver;
	}

	@Test
	public void testForm() {
		driver.get("http://www.practiceselenium.com/practice-form.html");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.name("firstname")).sendKeys("Adam");
		driver.findElement(By.name("lastname")).sendKeys("Brzoza");
		driver.findElement(By.id("sex-1")).click();
		driver.findElement(By.id("exp-3")).click();
		driver.findElement(By.id("tea3")).click();
		driver.findElement(By.id("tool-1")).click();
		Select dropdown = new Select(driver.findElement(By.id("continents")));
		dropdown.selectByVisibleText("Australia");
			
		Select dropdown2 = new Select(driver.findElement(By.id("selenium_commands")));
		dropdown2.selectByVisibleText("Wait Commands");
		
		driver.findElement(By.id("submit")).click();
		
    	assertNotNull(driver.getTitle());
	}
	
	
}
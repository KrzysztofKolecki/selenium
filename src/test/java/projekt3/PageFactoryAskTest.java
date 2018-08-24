package projekt3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PageFactoryAskTest {

    private static WebDriver driver;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@Test
	public void test() throws Exception {
		PageFactoryAsk askPage = PageFactory.initElements(driver, PageFactoryAsk.class);
		askPage.search("Inf ug");
		assertTrue(askPage.assertTitle());
	}
	
	@Test
	public void testNoResults() throws Exception {
		PageFactoryAsk askPage = PageFactory.initElements(driver, PageFactoryAsk.class);
		askPage.search("safkjbfakjsbffkjb1masm;a;");
		assertTrue(askPage.assertNoResults());
	}
	

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

}
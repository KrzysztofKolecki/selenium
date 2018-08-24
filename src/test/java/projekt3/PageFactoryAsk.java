package projekt3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageFactoryAsk {
	
	@FindBy(id = "search-box")
	private WebElement searchBox;
	
	public WebDriver driver;
  
	public PageFactoryAsk(WebDriver driver){
		this.driver = driver;
		driver.get("https://www.ask.com/");
	}
  
	public void search(String text) throws Exception{
	  
		searchBox.sendKeys(text);
		searchBox.submit();
	  
	}
  
	public boolean assertTitle() throws Exception{
	  
	    WebDriverWait wait = new WebDriverWait(driver, 20);
	    
		Boolean result = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("PartialSearchResults-container"))).isDisplayed();

		return(result);
	}
	
	public boolean assertNoResults() throws Exception{
		  
	    WebDriverWait wait = new WebDriverWait(driver, 20);
	    
		Boolean result = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("PartialSearchResults-noresults-body"))).isDisplayed();

		return(result);
	}
  
  
}

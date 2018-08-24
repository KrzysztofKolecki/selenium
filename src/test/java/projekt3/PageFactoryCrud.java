package projekt3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageFactoryCrud {

		
	@FindBy(name = "email")
	private WebElement email;
	
	@FindBy(name = "password")
	private WebElement password;
	
	@FindBy(id = "113")
	private WebElement delete;
	
	public WebDriver driver;
  
	public PageFactoryCrud(WebDriver driver){
		this.driver = driver;

	}
  
	public void login() throws Exception{
	  
		driver.get("https://www.phptravels.net/admin");
		email.sendKeys("admin@phptravels.com");
		password.sendKeys("demoadmin");
		password.submit();
		

	  
	}
	
	public void delete() throws Exception {
		
		driver.get("https://www.phptravels.net/admin");
		email.sendKeys("admin@phptravels.com");
		password.sendKeys("demoadmin");
		password.submit();
		delete.click();
		driver.switchTo().alert().accept();
		
	}
  
	public boolean assertTitle() throws Exception{
	  
	    WebDriverWait wait = new WebDriverWait(driver, 20);
	    
		Boolean result = wait.until(ExpectedConditions.titleIs("Dashboard"));

		return(result);
	}
}

package br.com.ifsp.es4a4.projeto.cucumber.e2e.feature.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GoogleSteps {
	
	private static WebDriver driver;
	
	@Given("^I am on the Google search page$")
	public void I_visit_google() {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://www.google.com");
	}

	@When("^I search for \"(.*?)\"$")
	public void search_for(String query) {
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys(query);
		element.submit();
	}

	@Then("^the page title should start with \"(.*?)\"$")
	public void checkTitle(String titleStartsWith) {
		new WebDriverWait(driver,10L).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().startsWith(titleStartsWith);
			}
		});
	}

	@After()
	public void closeBrowser() {
		driver.quit();
	}

}

package br.com.ifsp.es4a4.projeto.cucumber.test.eng.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HelloCucumberSteps {

	private String value;
	
	@Given("^I have the vale \"(.*?)\" in the variable$")
	public void i_have_the_vale_in_the_variable(String value) throws Throwable {
		this.value = value;
	}
	
	@When("^I start the test script$")
	public void i_start_the_test_script() throws Throwable {
		assertNotEquals(value, null);
	}
	
	@Then("^show the variable's value$")
	public void show_the_variable_s_value() throws Throwable {
		assertEquals(value, "Hello Cucumber");
	}
	
}

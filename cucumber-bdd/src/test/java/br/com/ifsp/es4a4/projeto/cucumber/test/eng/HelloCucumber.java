package br.com.ifsp.es4a4.projeto.cucumber.test.eng;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:test_eng", tags = "@HelloCucumber",  glue = "br.com.ifsp.es4a4.projeto.cucumber.test.eng.steps", monochrome = true, dryRun = false)
public class HelloCucumber {

}

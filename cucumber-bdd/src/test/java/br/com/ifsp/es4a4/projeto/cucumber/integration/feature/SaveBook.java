package br.com.ifsp.es4a4.projeto.cucumber.integration.feature;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:integration", tags = "@SaveBook",  glue = "br.com.ifsp.es4a4.projeto.cucumber.integration.feature.steps", monochrome = true, dryRun = false)
public class SaveBook {

}

package br.com.ifsp.es4a4.projeto.cucumber.e2e.feature;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:e2e", tags = "@Google",  glue = "br.com.ifsp.es4a4.projeto.cucumber.e2e.feature.steps", monochrome = true, dryRun = false)
public class Google {

}

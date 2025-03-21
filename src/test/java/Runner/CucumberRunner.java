
package Runner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
    features = {"src/test/resources/Features"},  
    glue = "stepdefinition",                       
    plugin = {
        "pretty",                                  
        "html:target/cucumber-reports/cucumber.html",   
        "json:target/cucumber-reports/cucumber.json",   
        "junit:target/cucumber-reports/cucumber.xml",   
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" ,
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    },
    monochrome = true,  
    tags = "@Test",     
    dryRun = false      
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
    
}
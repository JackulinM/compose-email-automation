package composeEmail.GmailTesting;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)

@CucumberOptions(

		plugin = {"pretty", "html:target/Reports/report.html"},
		features = "src/test/java/composeEmail.feature"

      )

public class RunnerClass {

}
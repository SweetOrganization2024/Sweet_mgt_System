package project;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "feature",
        plugin = {"summary", "html:target/cucumber/report.html"},
        monochrome = true,
        snippets = SnippetType.CAMELCASE,
        glue = {"project"})
public class AcceptanceTest {
}
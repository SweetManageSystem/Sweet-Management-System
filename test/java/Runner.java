import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/signup.feature",// specify the path to your feature files
        glue = "com.test.stepdefinitions", // specify the package containing your step definitions
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class Runner {

}

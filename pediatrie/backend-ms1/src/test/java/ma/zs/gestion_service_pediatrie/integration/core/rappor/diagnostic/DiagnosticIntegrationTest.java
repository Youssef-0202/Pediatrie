package ma.zs.gestion_service_pediatrie.integration.core.rappor.diagnostic;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DiagnosticIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DiagnosticHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

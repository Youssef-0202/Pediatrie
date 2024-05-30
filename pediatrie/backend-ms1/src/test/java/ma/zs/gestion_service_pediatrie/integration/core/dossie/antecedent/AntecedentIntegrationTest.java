package ma.zs.gestion_service_pediatrie.integration.core.dossie.antecedent;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class AntecedentIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("AntecedentHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

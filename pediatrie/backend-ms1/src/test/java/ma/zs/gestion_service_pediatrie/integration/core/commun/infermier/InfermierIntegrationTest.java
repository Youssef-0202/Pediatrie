package ma.zs.gestion_service_pediatrie.integration.core.commun.infermier;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class InfermierIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("InfermierHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

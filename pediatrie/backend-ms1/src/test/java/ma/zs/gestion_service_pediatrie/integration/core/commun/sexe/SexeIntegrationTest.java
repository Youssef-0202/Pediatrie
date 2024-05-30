package ma.zs.gestion_service_pediatrie.integration.core.commun.sexe;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class SexeIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("SexeHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

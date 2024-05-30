package ma.zs.gestion_service_pediatrie.integration.core.commun.medecin;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class MedecinIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("MedecinHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

package ma.zs.gestion_service_pediatrie.integration.core.dossie.fiche-patient;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class FichePatientIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("FichePatientHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

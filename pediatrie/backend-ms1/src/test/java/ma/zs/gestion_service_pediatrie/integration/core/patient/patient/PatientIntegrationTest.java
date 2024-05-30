package ma.zs.gestion_service_pediatrie.integration.core.patient.patient;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class PatientIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("PatientHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

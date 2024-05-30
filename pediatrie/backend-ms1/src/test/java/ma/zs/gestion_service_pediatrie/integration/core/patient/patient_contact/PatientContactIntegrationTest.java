package ma.zs.gestion_service_pediatrie.integration.core.patient.patient-contact;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class PatientContactIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("PatientContactHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

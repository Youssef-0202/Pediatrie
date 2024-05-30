package ma.zs.gestion_service_pediatrie.integration.core.consultatio.consultation;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class ConsultationIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("ConsultationHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

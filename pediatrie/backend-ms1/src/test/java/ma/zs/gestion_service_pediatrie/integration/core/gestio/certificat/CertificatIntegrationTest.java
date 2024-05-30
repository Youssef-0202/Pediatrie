package ma.zs.gestion_service_pediatrie.integration.core.gestio.certificat;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class CertificatIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("CertificatHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

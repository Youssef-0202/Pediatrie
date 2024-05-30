package ma.zs.gestion_service_pediatrie.integration.core.rappor.synthese-medicale;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class SyntheseMedicaleIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("SyntheseMedicaleHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

package ma.zs.gestion_service_pediatrie.integration.core.gestio.traitement;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class TraitementIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("TraitementHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

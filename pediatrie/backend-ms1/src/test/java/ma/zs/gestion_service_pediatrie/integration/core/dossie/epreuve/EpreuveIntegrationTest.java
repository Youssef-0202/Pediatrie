package ma.zs.gestion_service_pediatrie.integration.core.dossie.epreuve;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class EpreuveIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("EpreuveHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

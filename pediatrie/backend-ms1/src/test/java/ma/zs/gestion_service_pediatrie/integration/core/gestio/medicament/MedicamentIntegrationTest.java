package ma.zs.gestion_service_pediatrie.integration.core.gestio.medicament;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class MedicamentIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("MedicamentHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

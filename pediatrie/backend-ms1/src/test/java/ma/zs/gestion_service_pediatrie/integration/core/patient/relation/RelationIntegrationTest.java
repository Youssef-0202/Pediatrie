package ma.zs.gestion_service_pediatrie.integration.core.patient.relation;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class RelationIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("RelationHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

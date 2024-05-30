package ma.zs.gestion_service_pediatrie.integration.core.dossie.groupe-sanguin;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class GroupeSanguinIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("GroupeSanguinHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

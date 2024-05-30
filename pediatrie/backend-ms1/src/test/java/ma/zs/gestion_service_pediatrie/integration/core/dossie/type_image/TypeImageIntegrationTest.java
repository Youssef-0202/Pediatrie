package ma.zs.gestion_service_pediatrie.integration.core.dossie.type-image;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class TypeImageIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("TypeImageHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

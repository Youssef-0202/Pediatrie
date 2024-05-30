package ma.zs.gestion_service_pediatrie.integration.core.dossie.radiologie;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class RadiologieIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("RadiologieHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

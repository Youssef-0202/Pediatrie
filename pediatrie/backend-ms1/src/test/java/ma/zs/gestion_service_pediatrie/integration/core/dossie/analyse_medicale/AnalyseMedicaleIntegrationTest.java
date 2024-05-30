package ma.zs.gestion_service_pediatrie.integration.core.dossie.analyse-medicale;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class AnalyseMedicaleIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("AnalyseMedicaleHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

package ma.zs.gestion_service_pediatrie.dao.facade.core.gestio;

import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Certificat;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Certificat;
import java.util.List;


@Repository
public interface CertificatDao extends AbstractRepository<Certificat,Long>  {
    Certificat findByRef(String ref);
    int deleteByRef(String ref);

    List<Certificat> findByConsultatuinId(Long id);
    int deleteByConsultatuinId(Long id);
    long countByConsultatuinRef(String ref);
   List<Certificat> findByConsultatuinPatientNumDossier(String num);
    @Query("SELECT NEW Certificat(item.id,item.ref) FROM Certificat item")
    List<Certificat> findAllOptimized();

}

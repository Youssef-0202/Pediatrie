package ma.zs.gestion_service_pediatrie.dao.facade.core.gestio;

import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Ordonnance;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Ordonnance;
import java.util.List;


@Repository
public interface OrdonnanceDao extends AbstractRepository<Ordonnance,Long>  {
    Ordonnance findByRef(String ref);
    int deleteByRef(String ref);

    List<Ordonnance> findByTraitementId(Long id);
    int deleteByTraitementId(Long id);
    long countByTraitementRef(String ref);
    List<Ordonnance> findByConsultatuinId(Long id);
    int deleteByConsultatuinId(Long id);
    long countByConsultatuinRef(String ref);

    List<Ordonnance> findByConsultatuinPatientNumDossier(String num );

    @Query("SELECT NEW Ordonnance(item.id,item.ref) FROM Ordonnance item")
    List<Ordonnance> findAllOptimized();

}

package ma.zs.gestion_service_pediatrie.dao.facade.core.gestio;

import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Traitement;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Traitement;
import java.util.List;


@Repository
public interface TraitementDao extends AbstractRepository<Traitement,Long>  {
    Traitement findByRef(String ref);
    int deleteByRef(String ref);

    List<Traitement> findByOrdonnanceId(Long id);
    int deleteByOrdonnanceId(Long id);
    long countByOrdonnanceRef(String ref);
    List<Traitement> findByConsultatuinId(Long id);
    int deleteByConsultatuinId(Long id);
    long countByConsultatuinRef(String ref);

    @Query("SELECT NEW Traitement(item.id,item.ref) FROM Traitement item")
    List<Traitement> findAllOptimized();

}

package ma.zs.gestion_service_pediatrie.dao.facade.core.gestio;

import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Medicament;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Medicament;
import java.util.List;


@Repository
public interface MedicamentDao extends AbstractRepository<Medicament,Long>  {
    Medicament findByRef(String ref);
    int deleteByRef(String ref);

    List<Medicament> findByTraitementId(Long id);
    int deleteByTraitementId(Long id);
    long countByTraitementRef(String ref);

    @Query("SELECT NEW Medicament(item.id,item.ref) FROM Medicament item")
    List<Medicament> findAllOptimized();

}

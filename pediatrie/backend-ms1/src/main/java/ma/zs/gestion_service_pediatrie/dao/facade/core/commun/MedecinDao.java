package ma.zs.gestion_service_pediatrie.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Medecin;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Medecin;
import java.util.List;


@Repository
public interface MedecinDao extends AbstractRepository<Medecin,Long>  {
    Medecin findByRef(String ref);
    int deleteByRef(String ref);

    List<Medecin> findBySexeId(Long id);
    int deleteBySexeId(Long id);
    long countBySexeRef(String ref);
    Medecin findByUsername(String username);

    @Query("SELECT NEW Medecin(item.id,item.email) FROM Medecin item")
    List<Medecin> findAllOptimized();

}

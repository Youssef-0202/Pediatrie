package ma.zs.gestion_service_pediatrie.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Infermier;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Infermier;
import java.util.List;


@Repository
public interface InfermierDao extends AbstractRepository<Infermier,Long>  {
    Infermier findByRef(String ref);
    int deleteByRef(String ref);

    List<Infermier> findBySexeId(Long id);
    int deleteBySexeId(Long id);
    long countBySexeRef(String ref);
    Infermier findByUsername(String username);

    @Query("SELECT NEW Infermier(item.id,item.email) FROM Infermier item")
    List<Infermier> findAllOptimized();

}

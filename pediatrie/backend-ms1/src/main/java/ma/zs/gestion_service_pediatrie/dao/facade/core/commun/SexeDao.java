package ma.zs.gestion_service_pediatrie.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Sexe;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Sexe;
import java.util.List;


@Repository
public interface SexeDao extends AbstractRepository<Sexe,Long>  {
    Sexe findByRef(String ref);
    int deleteByRef(String ref);


    @Query("SELECT NEW Sexe(item.id,item.ref) FROM Sexe item")
    List<Sexe> findAllOptimized();

}

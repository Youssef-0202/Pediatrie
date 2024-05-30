package ma.zs.gestion_service_pediatrie.dao.facade.core.dossie;

import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Epreuve;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Epreuve;
import java.util.List;


@Repository
public interface EpreuveDao extends AbstractRepository<Epreuve,Long>  {
    Epreuve findByRef(String ref);
    int deleteByRef(String ref);


    @Query("SELECT NEW Epreuve(item.id,item.ref) FROM Epreuve item")
    List<Epreuve> findAllOptimized();

}

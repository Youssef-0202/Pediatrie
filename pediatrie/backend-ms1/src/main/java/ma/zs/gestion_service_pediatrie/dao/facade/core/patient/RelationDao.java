package ma.zs.gestion_service_pediatrie.dao.facade.core.patient;

import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.patient.Relation;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.patient.Relation;
import java.util.List;


@Repository
public interface RelationDao extends AbstractRepository<Relation,Long>  {
    Relation findByRef(String ref);
    int deleteByRef(String ref);


    @Query("SELECT NEW Relation(item.id,item.ref) FROM Relation item")
    List<Relation> findAllOptimized();

}

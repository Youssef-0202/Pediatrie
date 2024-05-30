package ma.zs.gestion_service_pediatrie.dao.facade.core.dossie;

import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.GroupeSanguin;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.GroupeSanguin;
import java.util.List;


@Repository
public interface GroupeSanguinDao extends AbstractRepository<GroupeSanguin,Long>  {
    GroupeSanguin findByRef(String ref);
    int deleteByRef(String ref);


    @Query("SELECT NEW GroupeSanguin(item.id,item.ref) FROM GroupeSanguin item")
    List<GroupeSanguin> findAllOptimized();

}

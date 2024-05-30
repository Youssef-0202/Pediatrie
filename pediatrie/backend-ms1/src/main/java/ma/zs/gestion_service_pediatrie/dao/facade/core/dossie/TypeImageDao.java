package ma.zs.gestion_service_pediatrie.dao.facade.core.dossie;

import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.TypeImage;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.TypeImage;
import java.util.List;


@Repository
public interface TypeImageDao extends AbstractRepository<TypeImage,Long>  {
    TypeImage findByRef(String ref);
    int deleteByRef(String ref);


    @Query("SELECT NEW TypeImage(item.id,item.ref) FROM TypeImage item")
    List<TypeImage> findAllOptimized();

}

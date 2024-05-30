package ma.zs.gestion_service_pediatrie.dao.facade.core.dossie;

import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;
import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Radiologie;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Radiologie;
import java.util.List;


@Repository
public interface RadiologieDao extends AbstractRepository<Radiologie,Long>  {
    Radiologie findByRef(String ref);
    int deleteByRef(String ref);

    List<Radiologie> findByConsultationRef(String ref);

    List<Radiologie> findByConsultationId(Long id);
    int deleteByConsultationId(Long id);
    long countByConsultationRef(String ref);
    List<Radiologie> findByTypeImageId(Long id);
    int deleteByTypeImageId(Long id);
    long countByTypeImageRef(String ref);

    @Query("SELECT NEW Radiologie(item.id,item.ref) FROM Radiologie item")
    List<Radiologie> findAllOptimized();

}

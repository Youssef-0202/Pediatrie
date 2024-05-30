package ma.zs.gestion_service_pediatrie.dao.facade.core.dossie;

import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.AnalyseMedicale;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.AnalyseMedicale;
import java.util.List;


@Repository
public interface AnalyseMedicaleDao extends AbstractRepository<AnalyseMedicale,Long>  {
    AnalyseMedicale findByRef(String ref);
    int deleteByRef(String ref);

    List<AnalyseMedicale> findByEpreuveId(Long id);
    int deleteByEpreuveId(Long id);
    long countByEpreuveRef(String ref);
    List<AnalyseMedicale> findByConsultationId(Long id);
    List<AnalyseMedicale> findByConsultationRef(String ref);
    int deleteByConsultationId(Long id);
    long countByConsultationRef(String ref);

    @Query("SELECT NEW AnalyseMedicale(item.id,item.ref) FROM AnalyseMedicale item")
    List<AnalyseMedicale> findAllOptimized();

}

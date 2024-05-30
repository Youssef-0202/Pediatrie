package ma.zs.gestion_service_pediatrie.dao.facade.core.dossie;

import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.FichePatient;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.FichePatient;
import java.util.List;


@Repository
public interface FichePatientDao extends AbstractRepository<FichePatient,Long>  {
    FichePatient findByRef(String ref);
    int deleteByRef(String ref);

    List<FichePatient> findByAntecedentId(Long id);
    int deleteByAntecedentId(Long id);
    long countByAntecedentRef(String ref);
    List<FichePatient> findByConsultationId(Long id);

    List<FichePatient> findByConsultationRef(String ref);


    int deleteByConsultationId(Long id);
    long countByConsultationRef(String ref);

    @Query("SELECT NEW FichePatient(item.id,item.ref) FROM FichePatient item")
    List<FichePatient> findAllOptimized();

}

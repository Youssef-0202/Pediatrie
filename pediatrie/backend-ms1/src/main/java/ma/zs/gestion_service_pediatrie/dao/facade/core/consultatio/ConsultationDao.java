package ma.zs.gestion_service_pediatrie.dao.facade.core.consultatio;

import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;
import java.util.List;


@Repository
public interface ConsultationDao extends AbstractRepository<Consultation,Long>  {
    Consultation findByRef(String ref);
    int deleteByRef(String ref);

    List<Consultation> findByMedecinId(Long id);
    int deleteByMedecinId(Long id);
    long countByMedecinRef(String ref);
    List<Consultation> findByInfermierId(Long id);
    List<Consultation> findByPatientNumDossier(String num);
    int deleteByInfermierId(Long id);
    long countByInfermierRef(String ref);
    List<Consultation> findByPatientId(Long id);
    int deleteByPatientId(Long id);
    long countByPatientNumDossier(String numDossier);



    @Query("SELECT NEW Consultation(item.id,item.ref) FROM Consultation item")
    List<Consultation> findAllOptimized();

}

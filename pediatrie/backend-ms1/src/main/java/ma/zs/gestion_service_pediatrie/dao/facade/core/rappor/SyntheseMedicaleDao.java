package ma.zs.gestion_service_pediatrie.dao.facade.core.rappor;

import ma.zs.gestion_service_pediatrie.bean.core.rappor.Diagnostic;
import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.rappor.SyntheseMedicale;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.rappor.SyntheseMedicale;
import java.util.List;


@Repository
public interface SyntheseMedicaleDao extends AbstractRepository<SyntheseMedicale,Long>  {
    SyntheseMedicale findByRef(String ref);
    int deleteByRef(String ref);

    List<SyntheseMedicale> findByConsultationId(Long id);
    List<SyntheseMedicale> findByConsultationRef(String ref);
    int deleteByConsultationId(Long id);
    long countByConsultationRef(String ref);
    @Query("SELECT a.syntheseMedicale FROM Consultation a WHERE a.patient.numDossier = :nom")
    List<SyntheseMedicale> findByNumDossier(String nom);

    @Query("SELECT NEW SyntheseMedicale(item.id,item.ref) FROM SyntheseMedicale item")
    List<SyntheseMedicale> findAllOptimized();

}

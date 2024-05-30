package ma.zs.gestion_service_pediatrie.dao.facade.core.patient;

import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.patient.Patient;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.patient.Patient;
import java.util.List;


@Repository
public interface PatientDao extends AbstractRepository<Patient,Long>  {
    Patient findByNumDossier(String numDossier);
    int deleteByNumDossier(String numDossier);

    List<Patient> findBySexeId(Long id);
    int deleteBySexeId(Long id);
    long countBySexeRef(String ref);

    @Query("SELECT NEW Patient(item.id,item.numDossier) FROM Patient item")
    List<Patient> findAllOptimized();

}

package ma.zs.gestion_service_pediatrie.dao.facade.core.patient;

import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.patient.PatientContact;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.patient.PatientContact;
import java.util.List;


@Repository
public interface PatientContactDao extends AbstractRepository<PatientContact,Long>  {
    PatientContact findByCin(String cin);
    int deleteByCin(String cin);

    List<PatientContact> findByRelationId(Long id);
    int deleteByRelationId(Long id);
    long countByRelationRef(String ref);
    List<PatientContact> findByPatientId(Long id);
    int deleteByPatientId(Long id);
    long countByPatientNumDossier(String numDossier);

    List<PatientContact> findByPatientNumDossier(String num);

    @Query("SELECT NEW PatientContact(item.id,item.email) FROM PatientContact item")
    List<PatientContact> findAllOptimized();

}

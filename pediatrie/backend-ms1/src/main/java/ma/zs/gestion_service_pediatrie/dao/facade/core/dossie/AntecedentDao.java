package ma.zs.gestion_service_pediatrie.dao.facade.core.dossie;

import org.springframework.data.jpa.repository.Query;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Antecedent;
import org.springframework.stereotype.Repository;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Antecedent;
import java.util.List;


@Repository
public interface AntecedentDao extends AbstractRepository<Antecedent,Long>  {
    Antecedent findByRef(String ref);
    int deleteByRef(String ref);
    @Query("SELECT a.antecedent FROM FichePatient a WHERE a.consultation.patient.numDossier = :nom")
    List<Antecedent> findNumDossier(String nom);

    List<Antecedent> findByGroupeSanguinId(Long id);
    int deleteByGroupeSanguinId(Long id);
    long countByGroupeSanguinRef(String ref);

    @Query("SELECT NEW Antecedent(item.id,item.ref) FROM Antecedent item")
    List<Antecedent> findAllOptimized();

}

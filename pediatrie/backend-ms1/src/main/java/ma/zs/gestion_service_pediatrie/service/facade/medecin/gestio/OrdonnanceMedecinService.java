package ma.zs.gestion_service_pediatrie.service.facade.medecin.gestio;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Ordonnance;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio.OrdonnanceCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface OrdonnanceMedecinService {



    List<Ordonnance> findByTraitementId(Long id);
    int deleteByTraitementId(Long id);
    long countByTraitementRef(String ref);
    List<Ordonnance> findByConsultatuinId(Long id);
    int deleteByConsultatuinId(Long id);
    long countByConsultatuinRef(String ref);
    List<Ordonnance> findByConsultatuinPatientNumDossier(String num);




	Ordonnance create(Ordonnance t);

    Ordonnance update(Ordonnance t);

    List<Ordonnance> update(List<Ordonnance> ts,boolean createIfNotExist);

    Ordonnance findById(Long id);

    Ordonnance findOrSave(Ordonnance t);

    Ordonnance findByReferenceEntity(Ordonnance t);

    Ordonnance findWithAssociatedLists(Long id);

    List<Ordonnance> findAllOptimized();

    List<Ordonnance> findAll();

    List<Ordonnance> findByCriteria(OrdonnanceCriteria criteria);

    List<Ordonnance> findPaginatedByCriteria(OrdonnanceCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(OrdonnanceCriteria criteria);

    List<Ordonnance> delete(List<Ordonnance> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Ordonnance>> getToBeSavedAndToBeDeleted(List<Ordonnance> oldList, List<Ordonnance> newList);

    List<Ordonnance> importData(List<Ordonnance> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Ordonnance> importExcel(MultipartFile file);

}

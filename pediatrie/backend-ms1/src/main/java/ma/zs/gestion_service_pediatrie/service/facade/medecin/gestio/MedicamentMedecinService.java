package ma.zs.gestion_service_pediatrie.service.facade.medecin.gestio;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Medicament;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio.MedicamentCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface MedicamentMedecinService {



    List<Medicament> findByTraitementId(Long id);
    int deleteByTraitementId(Long id);
    long countByTraitementRef(String ref);




	Medicament create(Medicament t);

    Medicament update(Medicament t);

    List<Medicament> update(List<Medicament> ts,boolean createIfNotExist);

    Medicament findById(Long id);

    Medicament findOrSave(Medicament t);

    Medicament findByReferenceEntity(Medicament t);

    Medicament findWithAssociatedLists(Long id);

    List<Medicament> findAllOptimized();

    List<Medicament> findAll();

    List<Medicament> findByCriteria(MedicamentCriteria criteria);

    List<Medicament> findPaginatedByCriteria(MedicamentCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(MedicamentCriteria criteria);

    List<Medicament> delete(List<Medicament> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Medicament>> getToBeSavedAndToBeDeleted(List<Medicament> oldList, List<Medicament> newList);

    List<Medicament> importData(List<Medicament> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Medicament> importExcel(MultipartFile file);

}

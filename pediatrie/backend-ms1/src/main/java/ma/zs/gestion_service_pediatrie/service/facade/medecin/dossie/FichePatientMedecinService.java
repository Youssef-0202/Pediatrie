package ma.zs.gestion_service_pediatrie.service.facade.medecin.dossie;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.FichePatient;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.FichePatientCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface FichePatientMedecinService {



    List<FichePatient> findByAntecedentId(Long id);
    public List<FichePatient> findByConsultationRef(String ref);
    int deleteByAntecedentId(Long id);
    long countByAntecedentRef(String ref);
    List<FichePatient> findByConsultationId(Long id);
    int deleteByConsultationId(Long id);
    long countByConsultationRef(String ref);




	FichePatient create(FichePatient t);

    FichePatient update(FichePatient t);

    List<FichePatient> update(List<FichePatient> ts,boolean createIfNotExist);

    FichePatient findById(Long id);

    FichePatient findOrSave(FichePatient t);

    FichePatient findByReferenceEntity(FichePatient t);

    FichePatient findWithAssociatedLists(Long id);

    List<FichePatient> findAllOptimized();

    List<FichePatient> findAll();

    List<FichePatient> findByCriteria(FichePatientCriteria criteria);

    List<FichePatient> findPaginatedByCriteria(FichePatientCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(FichePatientCriteria criteria);

    List<FichePatient> delete(List<FichePatient> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<FichePatient>> getToBeSavedAndToBeDeleted(List<FichePatient> oldList, List<FichePatient> newList);

    List<FichePatient> importData(List<FichePatient> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<FichePatient> importExcel(MultipartFile file);

}

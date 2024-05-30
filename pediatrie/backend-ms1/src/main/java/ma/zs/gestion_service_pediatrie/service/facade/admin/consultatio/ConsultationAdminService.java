package ma.zs.gestion_service_pediatrie.service.facade.admin.consultatio;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.consultatio.ConsultationCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface ConsultationAdminService {



    List<Consultation> findByMedecinId(Long id);
    List<Consultation> findByPatientNumDossier(String num);
    int deleteByMedecinId(Long id);
    long countByMedecinRef(String ref);
    List<Consultation> findByInfermierId(Long id);
    int deleteByInfermierId(Long id);
    long countByInfermierRef(String ref);
    List<Consultation> findByPatientId(Long id);
    int deleteByPatientId(Long id);
    long countByPatientNumDossier(String numDossier);




	Consultation create(Consultation t);

    Consultation update(Consultation t);

    List<Consultation> update(List<Consultation> ts,boolean createIfNotExist);

    Consultation findById(Long id);

    Consultation findOrSave(Consultation t);

    Consultation findByReferenceEntity(Consultation t);

    Consultation findWithAssociatedLists(Long id);

    List<Consultation> findAllOptimized();

    List<Consultation> findAll();

    List<Consultation> findByCriteria(ConsultationCriteria criteria);

    List<Consultation> findPaginatedByCriteria(ConsultationCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ConsultationCriteria criteria);

    List<Consultation> delete(List<Consultation> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Consultation>> getToBeSavedAndToBeDeleted(List<Consultation> oldList, List<Consultation> newList);

    List<Consultation> importData(List<Consultation> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Consultation> importExcel(MultipartFile file);

}

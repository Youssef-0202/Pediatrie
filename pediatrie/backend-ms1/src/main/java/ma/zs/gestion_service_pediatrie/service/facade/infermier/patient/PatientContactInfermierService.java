package ma.zs.gestion_service_pediatrie.service.facade.infermier.patient;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.patient.PatientContact;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.patient.PatientContactCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface PatientContactInfermierService {



    List<PatientContact> findByRelationId(Long id);
    int deleteByRelationId(Long id);
    long countByRelationRef(String ref);
    List<PatientContact> findByPatientId(Long id);
    int deleteByPatientId(Long id);
    long countByPatientNumDossier(String numDossier);




	PatientContact create(PatientContact t);

    PatientContact update(PatientContact t);

    List<PatientContact> update(List<PatientContact> ts,boolean createIfNotExist);

    PatientContact findById(Long id);

    PatientContact findOrSave(PatientContact t);

    PatientContact findByReferenceEntity(PatientContact t);

    PatientContact findWithAssociatedLists(Long id);

    List<PatientContact> findAllOptimized();

    List<PatientContact> findAll();

    List<PatientContact> findByCriteria(PatientContactCriteria criteria);

    List<PatientContact> findPaginatedByCriteria(PatientContactCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PatientContactCriteria criteria);

    List<PatientContact> delete(List<PatientContact> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<PatientContact>> getToBeSavedAndToBeDeleted(List<PatientContact> oldList, List<PatientContact> newList);

    List<PatientContact> importData(List<PatientContact> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<PatientContact> importExcel(MultipartFile file);

}

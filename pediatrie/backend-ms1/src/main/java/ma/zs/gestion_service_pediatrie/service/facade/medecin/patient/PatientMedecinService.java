package ma.zs.gestion_service_pediatrie.service.facade.medecin.patient;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.patient.Patient;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.patient.PatientCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface PatientMedecinService {



    List<Patient> findBySexeId(Long id);
    int deleteBySexeId(Long id);
    long countBySexeRef(String ref);




	Patient create(Patient t);

    Patient update(Patient t);

    List<Patient> update(List<Patient> ts,boolean createIfNotExist);

    Patient findById(Long id);

    Patient findOrSave(Patient t);

    Patient findByReferenceEntity(Patient t);

    Patient findWithAssociatedLists(Long id);

    List<Patient> findAllOptimized();

    List<Patient> findAll();

    List<Patient> findByCriteria(PatientCriteria criteria);

    List<Patient> findPaginatedByCriteria(PatientCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PatientCriteria criteria);

    List<Patient> delete(List<Patient> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Patient>> getToBeSavedAndToBeDeleted(List<Patient> oldList, List<Patient> newList);

    List<Patient> importData(List<Patient> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Patient> importExcel(MultipartFile file);

}

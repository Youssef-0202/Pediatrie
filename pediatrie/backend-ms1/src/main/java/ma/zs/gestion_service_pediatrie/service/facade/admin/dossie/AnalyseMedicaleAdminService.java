package ma.zs.gestion_service_pediatrie.service.facade.admin.dossie;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.AnalyseMedicale;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.AnalyseMedicaleCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface AnalyseMedicaleAdminService {



    List<AnalyseMedicale> findByEpreuveId(Long id);
    int deleteByEpreuveId(Long id);
    long countByEpreuveRef(String ref);
    List<AnalyseMedicale> findByConsultationId(Long id);
    int deleteByConsultationId(Long id);
    long countByConsultationRef(String ref);




	AnalyseMedicale create(AnalyseMedicale t);

    AnalyseMedicale update(AnalyseMedicale t);

    List<AnalyseMedicale> update(List<AnalyseMedicale> ts,boolean createIfNotExist);

    AnalyseMedicale findById(Long id);

    AnalyseMedicale findOrSave(AnalyseMedicale t);

    AnalyseMedicale findByReferenceEntity(AnalyseMedicale t);

    AnalyseMedicale findWithAssociatedLists(Long id);

    List<AnalyseMedicale> findAllOptimized();

    List<AnalyseMedicale> findAll();

    List<AnalyseMedicale> findByCriteria(AnalyseMedicaleCriteria criteria);

    List<AnalyseMedicale> findPaginatedByCriteria(AnalyseMedicaleCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AnalyseMedicaleCriteria criteria);

    List<AnalyseMedicale> delete(List<AnalyseMedicale> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<AnalyseMedicale>> getToBeSavedAndToBeDeleted(List<AnalyseMedicale> oldList, List<AnalyseMedicale> newList);

    List<AnalyseMedicale> importData(List<AnalyseMedicale> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<AnalyseMedicale> importExcel(MultipartFile file);

}

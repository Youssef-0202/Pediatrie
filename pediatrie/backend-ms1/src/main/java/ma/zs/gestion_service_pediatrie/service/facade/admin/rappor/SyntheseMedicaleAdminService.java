package ma.zs.gestion_service_pediatrie.service.facade.admin.rappor;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.rappor.SyntheseMedicale;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.rappor.SyntheseMedicaleCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface SyntheseMedicaleAdminService {



    List<SyntheseMedicale> findByConsultationId(Long id);
    int deleteByConsultationId(Long id);
    long countByConsultationRef(String ref);




	SyntheseMedicale create(SyntheseMedicale t);

    SyntheseMedicale update(SyntheseMedicale t);

    List<SyntheseMedicale> update(List<SyntheseMedicale> ts,boolean createIfNotExist);

    SyntheseMedicale findById(Long id);

    SyntheseMedicale findOrSave(SyntheseMedicale t);

    SyntheseMedicale findByReferenceEntity(SyntheseMedicale t);

    SyntheseMedicale findWithAssociatedLists(Long id);

    List<SyntheseMedicale> findAllOptimized();

    List<SyntheseMedicale> findAll();

    List<SyntheseMedicale> findByCriteria(SyntheseMedicaleCriteria criteria);

    List<SyntheseMedicale> findPaginatedByCriteria(SyntheseMedicaleCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(SyntheseMedicaleCriteria criteria);

    List<SyntheseMedicale> delete(List<SyntheseMedicale> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<SyntheseMedicale>> getToBeSavedAndToBeDeleted(List<SyntheseMedicale> oldList, List<SyntheseMedicale> newList);

    List<SyntheseMedicale> importData(List<SyntheseMedicale> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<SyntheseMedicale> importExcel(MultipartFile file);

}

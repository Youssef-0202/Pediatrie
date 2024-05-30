package ma.zs.gestion_service_pediatrie.service.facade.infermier.patient;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.patient.Relation;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.patient.RelationCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface RelationInfermierService {







	Relation create(Relation t);

    Relation update(Relation t);

    List<Relation> update(List<Relation> ts,boolean createIfNotExist);

    Relation findById(Long id);

    Relation findOrSave(Relation t);

    Relation findByReferenceEntity(Relation t);

    Relation findWithAssociatedLists(Long id);

    List<Relation> findAllOptimized();

    List<Relation> findAll();

    List<Relation> findByCriteria(RelationCriteria criteria);

    List<Relation> findPaginatedByCriteria(RelationCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(RelationCriteria criteria);

    List<Relation> delete(List<Relation> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Relation>> getToBeSavedAndToBeDeleted(List<Relation> oldList, List<Relation> newList);

    List<Relation> importData(List<Relation> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Relation> importExcel(MultipartFile file);

}

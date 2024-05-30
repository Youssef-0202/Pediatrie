package ma.zs.gestion_service_pediatrie.service.facade.admin.dossie;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Radiologie;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.RadiologieCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface RadiologieAdminService {



    List<Radiologie> findByConsultatuinId(Long id);
    int deleteByConsultatuinId(Long id);
    long countByConsultatuinRef(String ref);
    List<Radiologie> findByTypeImageId(Long id);
    int deleteByTypeImageId(Long id);
    long countByTypeImageRef(String ref);




	Radiologie create(Radiologie t);

    Radiologie update(Radiologie t);

    List<Radiologie> update(List<Radiologie> ts,boolean createIfNotExist);

    Radiologie findById(Long id);

    Radiologie findOrSave(Radiologie t);

    Radiologie findByReferenceEntity(Radiologie t);

    Radiologie findWithAssociatedLists(Long id);

    List<Radiologie> findAllOptimized();

    List<Radiologie> findAll();

    List<Radiologie> findByCriteria(RadiologieCriteria criteria);

    List<Radiologie> findPaginatedByCriteria(RadiologieCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(RadiologieCriteria criteria);

    List<Radiologie> delete(List<Radiologie> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Radiologie>> getToBeSavedAndToBeDeleted(List<Radiologie> oldList, List<Radiologie> newList);

    List<Radiologie> importData(List<Radiologie> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Radiologie> importExcel(MultipartFile file);

}

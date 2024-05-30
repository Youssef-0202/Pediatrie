package ma.zs.gestion_service_pediatrie.service.facade.infermier.dossie;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.TypeImage;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.TypeImageCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface TypeImageInfermierService {







	TypeImage create(TypeImage t);

    TypeImage update(TypeImage t);

    List<TypeImage> update(List<TypeImage> ts,boolean createIfNotExist);

    TypeImage findById(Long id);

    TypeImage findOrSave(TypeImage t);

    TypeImage findByReferenceEntity(TypeImage t);

    TypeImage findWithAssociatedLists(Long id);

    List<TypeImage> findAllOptimized();

    List<TypeImage> findAll();

    List<TypeImage> findByCriteria(TypeImageCriteria criteria);

    List<TypeImage> findPaginatedByCriteria(TypeImageCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TypeImageCriteria criteria);

    List<TypeImage> delete(List<TypeImage> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<TypeImage>> getToBeSavedAndToBeDeleted(List<TypeImage> oldList, List<TypeImage> newList);

    List<TypeImage> importData(List<TypeImage> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<TypeImage> importExcel(MultipartFile file);

}

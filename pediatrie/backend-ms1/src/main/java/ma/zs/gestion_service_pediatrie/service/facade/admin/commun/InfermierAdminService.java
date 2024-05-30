package ma.zs.gestion_service_pediatrie.service.facade.admin.commun;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Infermier;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.commun.InfermierCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface InfermierAdminService {


    Infermier findByUsername(String username);
    boolean changePassword(String username, String newPassword);

    List<Infermier> findBySexeId(Long id);
    int deleteBySexeId(Long id);
    long countBySexeRef(String ref);




	Infermier create(Infermier t);

    Infermier update(Infermier t);

    List<Infermier> update(List<Infermier> ts,boolean createIfNotExist);

    Infermier findById(Long id);

    Infermier findOrSave(Infermier t);

    Infermier findByReferenceEntity(Infermier t);

    Infermier findWithAssociatedLists(Long id);

    List<Infermier> findAllOptimized();

    List<Infermier> findAll();

    List<Infermier> findByCriteria(InfermierCriteria criteria);

    List<Infermier> findPaginatedByCriteria(InfermierCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(InfermierCriteria criteria);

    List<Infermier> delete(List<Infermier> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Infermier>> getToBeSavedAndToBeDeleted(List<Infermier> oldList, List<Infermier> newList);

    List<Infermier> importData(List<Infermier> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Infermier> importExcel(MultipartFile file);

}

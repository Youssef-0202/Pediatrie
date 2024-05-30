package ma.zs.gestion_service_pediatrie.service.facade.admin.commun;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Medecin;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.commun.MedecinCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface MedecinAdminService {


    Medecin findByUsername(String username);
    boolean changePassword(String username, String newPassword);

    List<Medecin> findBySexeId(Long id);
    int deleteBySexeId(Long id);
    long countBySexeRef(String ref);




	Medecin create(Medecin t);

    Medecin update(Medecin t);

    List<Medecin> update(List<Medecin> ts,boolean createIfNotExist);

    Medecin findById(Long id);

    Medecin findOrSave(Medecin t);

    Medecin findByReferenceEntity(Medecin t);

    Medecin findWithAssociatedLists(Long id);

    List<Medecin> findAllOptimized();

    List<Medecin> findAll();

    List<Medecin> findByCriteria(MedecinCriteria criteria);

    List<Medecin> findPaginatedByCriteria(MedecinCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(MedecinCriteria criteria);

    List<Medecin> delete(List<Medecin> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Medecin>> getToBeSavedAndToBeDeleted(List<Medecin> oldList, List<Medecin> newList);

    List<Medecin> importData(List<Medecin> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Medecin> importExcel(MultipartFile file);

}

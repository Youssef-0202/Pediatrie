package ma.zs.gestion_service_pediatrie.service.facade.admin.dossie;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Antecedent;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.AntecedentCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface AntecedentAdminService {



    List<Antecedent> findByGroupeSanguinId(Long id);
    int deleteByGroupeSanguinId(Long id);
    long countByGroupeSanguinRef(String ref);




	Antecedent create(Antecedent t);

    Antecedent update(Antecedent t);

    List<Antecedent> update(List<Antecedent> ts,boolean createIfNotExist);

    Antecedent findById(Long id);

    Antecedent findOrSave(Antecedent t);

    Antecedent findByReferenceEntity(Antecedent t);

    Antecedent findWithAssociatedLists(Long id);

    List<Antecedent> findAllOptimized();

    List<Antecedent> findAll();

    List<Antecedent> findByCriteria(AntecedentCriteria criteria);

    List<Antecedent> findPaginatedByCriteria(AntecedentCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AntecedentCriteria criteria);

    List<Antecedent> delete(List<Antecedent> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Antecedent>> getToBeSavedAndToBeDeleted(List<Antecedent> oldList, List<Antecedent> newList);

    List<Antecedent> importData(List<Antecedent> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Antecedent> importExcel(MultipartFile file);

}

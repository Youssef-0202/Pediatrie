package ma.zs.gestion_service_pediatrie.service.facade.medecin.commun;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Sexe;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.commun.SexeCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface SexeMedecinService {







	Sexe create(Sexe t);

    Sexe update(Sexe t);

    List<Sexe> update(List<Sexe> ts,boolean createIfNotExist);

    Sexe findById(Long id);

    Sexe findOrSave(Sexe t);

    Sexe findByReferenceEntity(Sexe t);

    Sexe findWithAssociatedLists(Long id);

    List<Sexe> findAllOptimized();

    List<Sexe> findAll();

    List<Sexe> findByCriteria(SexeCriteria criteria);

    List<Sexe> findPaginatedByCriteria(SexeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(SexeCriteria criteria);

    List<Sexe> delete(List<Sexe> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Sexe>> getToBeSavedAndToBeDeleted(List<Sexe> oldList, List<Sexe> newList);

    List<Sexe> importData(List<Sexe> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Sexe> importExcel(MultipartFile file);

}

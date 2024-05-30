package ma.zs.gestion_service_pediatrie.service.facade.infermier.dossie;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Epreuve;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.EpreuveCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface EpreuveInfermierService {







	Epreuve create(Epreuve t);

    Epreuve update(Epreuve t);

    List<Epreuve> update(List<Epreuve> ts,boolean createIfNotExist);

    Epreuve findById(Long id);

    Epreuve findOrSave(Epreuve t);

    Epreuve findByReferenceEntity(Epreuve t);

    Epreuve findWithAssociatedLists(Long id);

    List<Epreuve> findAllOptimized();

    List<Epreuve> findAll();

    List<Epreuve> findByCriteria(EpreuveCriteria criteria);

    List<Epreuve> findPaginatedByCriteria(EpreuveCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EpreuveCriteria criteria);

    List<Epreuve> delete(List<Epreuve> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Epreuve>> getToBeSavedAndToBeDeleted(List<Epreuve> oldList, List<Epreuve> newList);

    List<Epreuve> importData(List<Epreuve> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Epreuve> importExcel(MultipartFile file);

}

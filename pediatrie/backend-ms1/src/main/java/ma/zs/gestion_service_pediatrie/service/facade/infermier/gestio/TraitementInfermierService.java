package ma.zs.gestion_service_pediatrie.service.facade.infermier.gestio;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Traitement;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio.TraitementCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface TraitementInfermierService {



    List<Traitement> findByOrdonnanceId(Long id);
    int deleteByOrdonnanceId(Long id);
    long countByOrdonnanceRef(String ref);
    List<Traitement> findByConsultatuinId(Long id);
    int deleteByConsultatuinId(Long id);
    long countByConsultatuinRef(String ref);




	Traitement create(Traitement t);

    Traitement update(Traitement t);

    List<Traitement> update(List<Traitement> ts,boolean createIfNotExist);

    Traitement findById(Long id);

    Traitement findOrSave(Traitement t);

    Traitement findByReferenceEntity(Traitement t);

    Traitement findWithAssociatedLists(Long id);

    List<Traitement> findAllOptimized();

    List<Traitement> findAll();

    List<Traitement> findByCriteria(TraitementCriteria criteria);

    List<Traitement> findPaginatedByCriteria(TraitementCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TraitementCriteria criteria);

    List<Traitement> delete(List<Traitement> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Traitement>> getToBeSavedAndToBeDeleted(List<Traitement> oldList, List<Traitement> newList);

    List<Traitement> importData(List<Traitement> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Traitement> importExcel(MultipartFile file);

}

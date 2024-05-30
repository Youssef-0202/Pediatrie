package ma.zs.gestion_service_pediatrie.service.facade.medecin.gestio;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Certificat;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio.CertificatCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface CertificatMedecinService {



    List<Certificat> findByConsultatuinId(Long id);
    int deleteByConsultatuinId(Long id);
    long countByConsultatuinRef(String ref);

    public List<Certificat> findByConsultatuinPatientNumDossier(String num);


    Certificat create(Certificat t);

    Certificat update(Certificat t);

    List<Certificat> update(List<Certificat> ts,boolean createIfNotExist);

    Certificat findById(Long id);

    Certificat findOrSave(Certificat t);

    Certificat findByReferenceEntity(Certificat t);

    Certificat findWithAssociatedLists(Long id);

    List<Certificat> findAllOptimized();

    List<Certificat> findAll();

    List<Certificat> findByCriteria(CertificatCriteria criteria);

    List<Certificat> findPaginatedByCriteria(CertificatCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CertificatCriteria criteria);

    List<Certificat> delete(List<Certificat> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Certificat>> getToBeSavedAndToBeDeleted(List<Certificat> oldList, List<Certificat> newList);

    List<Certificat> importData(List<Certificat> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Certificat> importExcel(MultipartFile file);

}
